package com.freenow.dataaccessobject;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.freenow.domainobject.CarDO;
import com.freenow.domainobject.DriverDO;

@Repository
public interface ICarRepository extends JpaRepository<CarDO, Long>
{
    Optional<CarDO> findByLicensePlate(String licensePlate);


    List<CarDO> findByRating(Double rating);


    Optional<CarDO> findByDriverDO(DriverDO driverDO);

}
