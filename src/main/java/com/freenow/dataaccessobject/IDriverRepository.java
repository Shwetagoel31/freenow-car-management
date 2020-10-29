package com.freenow.dataaccessobject;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.freenow.domainobject.CarDO;
import com.freenow.domainobject.DriverDO;
import com.freenow.domainvalue.OnlineStatus;

/**
 * Database Access Object for driver table.
 * <p/>
 */
@Repository
public interface IDriverRepository extends JpaRepository<DriverDO, Long>
{

    List<DriverDO> findByOnlineStatus(OnlineStatus onlineStatus);
    List<DriverDO> findByUsernameContaining(String username);
    Optional<DriverDO> findByCarDO(CarDO carDO);
    List<DriverDO> findByCarDOIn(List<CarDO> carDOs);
}
