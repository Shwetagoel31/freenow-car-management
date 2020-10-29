package com.freenow.service.driver;

import java.util.List;

import com.freenow.domainobject.CarDO;
import com.freenow.domainobject.DriverDO;
import com.freenow.domainvalue.OnlineStatus;
import com.freenow.exception.CarAlreadyInUseException;
import com.freenow.exception.CarSelectDeselectException;
import com.freenow.exception.DriverNotOnlineException;
import com.freenow.exception.EntityNotFoundException;

public interface IDriverService
{

    void updateLocation(long driverId, double longitude, double latitude) throws EntityNotFoundException;


    List<DriverDO> find(OnlineStatus onlineStatus);


    CarDO selectCar(Long driverId, Long carId) throws EntityNotFoundException, DriverNotOnlineException, CarAlreadyInUseException, CarSelectDeselectException;


    void deselectCar(Long driverId) throws EntityNotFoundException, CarSelectDeselectException;


    List<DriverDO> findByUsernameContains(String username);


    DriverDO findByCarDO(CarDO carDO) throws EntityNotFoundException;


    List<DriverDO> findByCarDOIn(List<CarDO> carDOs);

}
