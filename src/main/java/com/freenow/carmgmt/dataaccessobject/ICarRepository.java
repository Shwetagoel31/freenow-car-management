package com.freenow.carmgmt.dataaccessobject;

import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.freenow.carmgmt.domainobject.CarDO;

@Repository
public interface ICarRepository extends PagingAndSortingRepository<CarDO, Long>
{
    Optional<CarDO> findByLicensePlate(String licensePlate);
    List<CarDO> findByRating(Double rating);

}
