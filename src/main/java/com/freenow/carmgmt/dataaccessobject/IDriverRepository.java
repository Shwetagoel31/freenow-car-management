package com.freenow.carmgmt.dataaccessobject;

import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import com.freenow.carmgmt.domainobject.CarDO;
import com.freenow.carmgmt.domainobject.DriverDO;
import com.freenow.carmgmt.domainvalue.OnlineStatus;

/**
 * Database Access Object for driver table.
 * <p/>
 */
public interface IDriverRepository extends CrudRepository<DriverDO, Long>
{

    List<DriverDO> findByOnlineStatus(OnlineStatus onlineStatus);
    List<DriverDO> findByUsernameContaining(String username);
    Optional<DriverDO> findByCarDO(CarDO carDO);
    List<DriverDO> findByCarDOIn(List<CarDO> carDOs);
}
