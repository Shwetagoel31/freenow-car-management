package com.freenow.carmgmt.service.car;

import java.util.List;

import com.freenow.carmgmt.domainobject.CarDO;
import com.freenow.carmgmt.exception.EntityNotFoundException;

public interface ICarService
{
    CarDO findById(Long id) throws EntityNotFoundException;


    Iterable<CarDO> findAll();


    CarDO save(CarDO carDo);


    CarDO update(CarDO carDo);


    void deleteById(Long id) throws EntityNotFoundException;


    CarDO findByLicensePlate(String licensePlate) throws EntityNotFoundException;


    List<CarDO> findByRating(Double rating);

}
