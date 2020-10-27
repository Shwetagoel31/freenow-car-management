package com.freenow.dataaccessobject;

import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.freenow.domainobject.CarDO;
import com.freenow.domainobject.DriverDO;

@Repository
public interface ICarRepository extends PagingAndSortingRepository<CarDO, Long>
{
    Optional<CarDO> findByLicensePlate(String licensePlate);


    List<CarDO> findByRating(Double rating);


    Optional<CarDO> findByDriverDO(DriverDO driverDO);

}
