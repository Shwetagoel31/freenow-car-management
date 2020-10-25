package com.freenow.carmgmt.controller;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.InputMismatchException;
import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.freenow.carmgmt.controller.mapper.CarMapper;
import com.freenow.carmgmt.controller.mapper.DriverMapper;
import com.freenow.carmgmt.datatransferobject.CarDTO;
import com.freenow.carmgmt.datatransferobject.DriverDTO;
import com.freenow.carmgmt.domainobject.CarDO;
import com.freenow.carmgmt.domainobject.DriverDO;
import com.freenow.carmgmt.domainvalue.OnlineStatus;
import com.freenow.carmgmt.exception.CarAlreadyInUseException;
import com.freenow.carmgmt.exception.ConstraintsViolationException;
import com.freenow.carmgmt.exception.DriverNotOnlineException;
import com.freenow.carmgmt.exception.EntityNotFoundException;
import com.freenow.carmgmt.service.car.ICarService;
import com.freenow.carmgmt.service.driver.IDriverService;

/**
 * All operations with a driver will be routed by this controller.
 * <p/>
 */
@RestController
@RequestMapping("/api/v1/drivers")
public class DriverController
{

    private static final Logger LOG =
        LoggerFactory
            .getLogger(DriverController.class);

    private final IDriverService driverService;
    private final ICarService carService;
    private final CarMapper carMapper;

    @Autowired
    public DriverController(
        final IDriverService driverService,
        final ICarService carService,
        final CarMapper carMapper)
    {
        this.driverService = driverService;
        this.carService = carService;
        this.carMapper = carMapper;
    }


    @GetMapping("/{driverId}")
    public DriverDTO getDriver(@PathVariable long driverId)
        throws EntityNotFoundException
    {
        DriverDO driverDO = driverService.find(driverId);
        DriverDTO driverDTO = DriverMapper.makeDriverDTO(driverDO);
        CarDO carDO = driverDO.getCarDO();
        if(carDO != null) {
            CarDTO carDTO = carMapper.convertToCarDTO(carDO);
            driverDTO.setCarDTO(carDTO);
        }
        
        return driverDTO;
    }


    @GetMapping("/search")
    public List<DriverDTO> findByAttribute(@RequestParam String key, @RequestParam String value) throws EntityNotFoundException
    {
        List<DriverDTO> driverDTOs = new ArrayList<>();
        List<DriverDO> driverDOs = new ArrayList<>();

        switch (key.toUpperCase())
        {
            case "USERNAME":
                driverDOs = driverService.findByUsernameContains(value);

                break;

            case "ONLINESTATUS":
                driverDOs = driverService.find(OnlineStatus.valueOf(value.toUpperCase()));

                break;

            case "LICENSEPLATE":
                CarDO carDO = carService.findByLicensePlate(value);
                DriverDO driverDO = driverService.findByCarDO(carDO);
                driverDOs.add(driverDO);

                break;

            case "RATING":
                List<CarDO> carDOs = carService.findByRating(Double.parseDouble(value));
                driverDOs = driverService.findByCarDOIn(carDOs);
                
                break;
            default:
                break;
        }

        driverDOs.stream().forEach(d -> driverDTOs.add(DriverMapper.makeDriverDTO(d)));
        return driverDTOs;
    }


    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public DriverDTO createDriver(@Valid @RequestBody DriverDTO driverDTO)
        throws ConstraintsViolationException
    {
        DriverDO driverDO = DriverMapper.makeDriverDO(driverDTO);
        return DriverMapper.makeDriverDTO(driverService.create(driverDO));
    }


    @DeleteMapping("/{driverId}")
    public void deleteDriver(@PathVariable long driverId)
        throws EntityNotFoundException
    {
        driverService.delete(driverId);
    }


    @PutMapping("/{driverId}")
    public void updateLocation(
        @PathVariable long driverId,
        @RequestParam double longitude, @RequestParam double latitude)
        throws EntityNotFoundException
    {
        driverService.updateLocation(driverId, longitude, latitude);
    }


    @GetMapping
    public List<DriverDTO> findDrivers(
        @RequestParam OnlineStatus onlineStatus)
    {
        return DriverMapper.makeDriverDTOList(driverService.find(onlineStatus));
    }


    @PutMapping("/{driverId}/selectCar/{carId}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public void selectCar(
        @PathVariable Long driverId,
        @PathVariable Long carId)
        throws EntityNotFoundException, DriverNotOnlineException, CarAlreadyInUseException
    {

        DriverDO driverDO = driverService.find(driverId);
        if (driverDO.getOnlineStatus() == OnlineStatus.ONLINE)
        {
            CarDO carDO = carService.findById(carId);
            if (carDO.getDriver() == null)
            {

                driverDO.setCarDO(carDO);

                driverService.save(driverDO);
            }
            else
            {
                throw new CarAlreadyInUseException("CarID " + carId + " cannot be selected as it's already in use");
            }
        }
        else
        {
            throw new DriverNotOnlineException("Driver's status should be ONLINE to select a car");
        }

    }


    @PutMapping("/{driverId}/deSelectCar/{carId}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public void deSelectCar(
        @PathVariable Long driverId,
        @PathVariable Long carId)
        throws EntityNotFoundException, InputMismatchException
    {

        DriverDO driverDO = driverService.find(driverId);
        CarDO carDO = carService.findById(carId);

        Long selectedCarID = driverDO.getCarDO().getId();
        if (selectedCarID != carId)
            throw new InputMismatchException("Cannot deselect provided carID " + carId + " as driver had previously selected " + selectedCarID + " carID");

        driverDO.setCarDO(null);

        driverService.save(driverDO);

    }
}
