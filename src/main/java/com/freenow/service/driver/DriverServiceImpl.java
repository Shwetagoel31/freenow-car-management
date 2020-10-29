package com.freenow.service.driver;

import java.util.List;
import java.util.Objects;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.freenow.dataaccessobject.IDriverRepository;
import com.freenow.domainobject.CarDO;
import com.freenow.domainobject.DriverDO;
import com.freenow.domainvalue.GeoCoordinate;
import com.freenow.domainvalue.OnlineStatus;
import com.freenow.exception.CarAlreadyInUseException;
import com.freenow.exception.CarSelectDeselectException;
import com.freenow.exception.ConstraintsViolationException;
import com.freenow.exception.DriverNotOnlineException;
import com.freenow.exception.EntityNotFoundException;
import com.freenow.service.BaseServiceImpl;
import com.freenow.service.car.CarServiceImpl;
import com.freenow.service.car.ICarService;

/**
 * Service to encapsulate the link between DAO and controller and to have business logic for some driver specific things.
 * <p/>
 */
@Service
public class DriverServiceImpl extends BaseServiceImpl<DriverDO, Long> implements IDriverService
{

    private static final Logger LOG = LoggerFactory.getLogger(DriverServiceImpl.class);

    private final IDriverRepository driverRepository;

    private final ICarService carService;

    public DriverServiceImpl(final IDriverRepository driverRepository, final ICarService carService)
    {
        this.driverRepository = driverRepository;
        this.carService = carService;
    }


    @Override
    protected JpaRepository<DriverDO, Long> getRepository()
    {
        return driverRepository;
    }


    /**
     * Update the location for a driver.
     *
     * @param driverId
     * @param longitude
     * @param latitude
     * @throws EntityNotFoundException
     */
    @Override
    @Transactional
    public void updateLocation(long driverId, double longitude, double latitude) throws EntityNotFoundException
    {
        DriverDO driverDO = find(driverId);
        driverDO.setCoordinate(new GeoCoordinate(latitude, longitude));
    }


    /**
     * Find all drivers by online state.
     *
     * @param onlineStatus
     */
    @Override
    public List<DriverDO> find(OnlineStatus onlineStatus)
    {
        return driverRepository.findByOnlineStatus(onlineStatus);
    }


    @Override
    public CarDO selectCar(Long driverId, Long carId) throws EntityNotFoundException, DriverNotOnlineException, CarAlreadyInUseException, CarSelectDeselectException
    {
        LOG.info("Driver with ID {} is trying to select car with ID {}", driverId, carId);

        DriverDO driverDO = find(driverId);
        validateNoCarSelectedAndOnlineStatus(driverDO);

        CarDO carDO = carService.findById(carId);
        validateNoDriverAssociated(carDO, driverDO);

        carDO.setDriverDO(driverDO);

        return null;
    }


    @Override
    public void deselectCar(Long driverId) throws EntityNotFoundException, CarSelectDeselectException
    {
        LOG.info("Driver with id {} is trying to deselect the alloted car", driverId);

        DriverDO driverDO = find(driverId);
        CarDO carDO = carService.findByDriver(driverDO);
        validateCarSelected(carDO);
        carDO.setDriverDO(null);

    }


    private boolean validateNoCarSelectedAndOnlineStatus(DriverDO driverDO) throws DriverNotOnlineException, CarSelectDeselectException
    {
        if (Objects.nonNull(driverDO.getCarDO()))
            throw new CarSelectDeselectException("Cannot select car for driver " + driverDO.getId() + " as he already has a car associated with him.");
        else if (!OnlineStatus.ONLINE.equals(driverDO.getOnlineStatus()))
            throw new DriverNotOnlineException("Cannot select car for driver " + driverDO.getId() + " as he is NOT online");
        return true;
    }


    private boolean validateNoDriverAssociated(CarDO carDO, DriverDO driverDO) throws CarAlreadyInUseException
    {
        DriverDO associatedDriver = carDO.getDriverDO();

        if (Objects.nonNull(associatedDriver) && !associatedDriver.getId().equals(driverDO.getId()))
        {
            throw new CarAlreadyInUseException("Requested Car with id: " + carDO.getId() + " is already in use by driver: " + associatedDriver.getId());
        }

        return true;
    }


    private boolean validateCarSelected(CarDO carDO) throws CarSelectDeselectException
    {
        if (Objects.isNull(carDO))
        {
            throw new CarSelectDeselectException("No car has been allocated to the driver");
        }

        return true;
    }


    /**
     * Selects a driver by id.
     *
     * @param driverId
     * @return found driver
     * @throws EntityNotFoundException if no driver with the given id was found.
     */
    @Override
    public DriverDO find(Long driverId) throws EntityNotFoundException
    {
        return driverRepository
            .findById(driverId)
            .orElseThrow(() -> new EntityNotFoundException("Could not find driver with Id " + driverId));
    }


    /**
     * Creates a new driver.
     *
     * @param driverDO
     * @return
     * @throws ConstraintsViolationException if a driver already exists with the given username, ... .
     */
    @Override
    public DriverDO create(DriverDO driverDO) throws ConstraintsViolationException
    {
        DriverDO driver;
        try
        {
            driver = driverRepository.save(driverDO);
        }
        catch (DataIntegrityViolationException e)
        {
            LOG.warn("ConstraintsViolationException while creating a driver: {}", driverDO, e);
            throw new ConstraintsViolationException(e.getMessage());
        }
        return driver;
    }


    /**
     * Deletes an existing driver by id.
     *
     * @param driverId
     * @throws EntityNotFoundException if no driver with the given id was found.
     */
    @Override
    @Transactional
    public void delete(Long driverId) throws EntityNotFoundException
    {
        DriverDO driverDO = driverRepository
                                        .findById(driverId)
                                        .orElseThrow(() -> new EntityNotFoundException("Could not delete driver as no driver found with Id " + driverId));
        driverDO.setDeleted(true);
    }


    @Override
    public List<DriverDO> findByUsernameContains(String username)
    {
        return driverRepository.findByUsernameContaining(username);
    }


    @Override
    public DriverDO findByCarDO(CarDO carDO) throws EntityNotFoundException
    {
        return driverRepository.findByCarDO(carDO).orElseThrow(() -> new EntityNotFoundException("Could not find Driver with carId " + carDO.getId()));
    }


    @Override
    public List<DriverDO> findByCarDOIn(List<CarDO> carDOs)
    {
        return driverRepository.findByCarDOIn(carDOs);

    }

}
