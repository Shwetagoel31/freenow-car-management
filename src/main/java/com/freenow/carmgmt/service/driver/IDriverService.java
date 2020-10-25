package com.freenow.carmgmt.service.driver;

import java.util.List;

import com.freenow.carmgmt.domainobject.CarDO;
import com.freenow.carmgmt.domainobject.DriverDO;
import com.freenow.carmgmt.domainvalue.OnlineStatus;
import com.freenow.carmgmt.exception.ConstraintsViolationException;
import com.freenow.carmgmt.exception.EntityNotFoundException;

public interface IDriverService
{

    DriverDO find(Long driverId) throws EntityNotFoundException;

    DriverDO create(DriverDO driverDO) throws ConstraintsViolationException;

    void delete(Long driverId) throws EntityNotFoundException;

    void updateLocation(long driverId, double longitude, double latitude) throws EntityNotFoundException;

    List<DriverDO> find(OnlineStatus onlineStatus);
    
    DriverDO save(DriverDO driverDO);
    
    List<DriverDO> findByUsernameContains(String username);
    
    DriverDO findByCarDO(CarDO carDO) throws EntityNotFoundException;
    
    List<DriverDO> findByCarDOIn(List<CarDO> carDOs);

}
