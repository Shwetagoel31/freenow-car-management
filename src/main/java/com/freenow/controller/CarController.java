package com.freenow.controller;

import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
//import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.freenow.controller.mapper.CarMapper;
import com.freenow.datatransferobject.CarDTO;
import com.freenow.domainobject.CarDO;
import com.freenow.exception.ConstraintsViolationException;
import com.freenow.exception.EntityNotFoundException;
import com.freenow.service.car.ICarService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/api/v1/cars")
//@Secured("ADMIN")
@Api(description = "Operations available for Admins to control Cars")
public class CarController
{

    private final ICarService carService;
    private final CarMapper mapper;

    @Autowired
    public CarController(final ICarService carService, final CarMapper mapper)
    {
        this.carService = carService;
        this.mapper = mapper;
    }

    @ApiOperation(value = "View all Cars", response = List.class)
    @GetMapping
    public List<CarDTO> findAll()
    {

        return (carService
            .findAll()
            .stream()
            .map(c -> mapper.convertToCarDTO(c))
            .collect(Collectors.toList()));

    }


    @GetMapping("/{id}")
    public CarDTO findOne(@Valid @PathVariable Long id) throws EntityNotFoundException
    {
        return mapper.convertToCarDTO(carService.find(id));
    }


    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public CarDTO save(@Valid @RequestBody CarDTO carDTO) throws ConstraintsViolationException, EntityNotFoundException
    {
        CarDO carDO = mapper.convertToCarDO(carDTO);
        return mapper.convertToCarDTO(carService.createCar(carDO));
    }


    @PutMapping("/{id}")
    public CarDTO update(@Valid @PathVariable Long id, @Valid @RequestBody CarDTO carDto)
    {
        CarDO carDo = carService.update(mapper.convertToCarDO(carDto));
        return mapper.convertToCarDTO(carDo);

    }


    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@Valid @PathVariable Long id) throws EntityNotFoundException
    {
        carService.delete(id);

    }

}
