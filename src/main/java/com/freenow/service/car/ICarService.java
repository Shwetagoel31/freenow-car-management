package com.freenow.service.car;

import java.util.List;

import com.freenow.domainobject.CarDO;
import com.freenow.domainobject.DriverDO;
import com.freenow.exception.ConstraintsViolationException;
import com.freenow.exception.EntityNotFoundException;

public interface ICarService
{

    CarDO createCar(CarDO carDo) throws ConstraintsViolationException, EntityNotFoundException;


    CarDO update(CarDO carDo);


    CarDO findByLicensePlate(String licensePlate) throws EntityNotFoundException;


    List<CarDO> findByRating(Double rating);


    CarDO findByDriver(DriverDO driverDO) throws EntityNotFoundException;

}
