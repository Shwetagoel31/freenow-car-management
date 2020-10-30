package com.freenow.carmgmt.dataaccessobject;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

import com.freenow.dataaccessobject.IDriverRepository;
import com.freenow.domainobject.DriverDO;
import com.freenow.domainvalue.OnlineStatus;

@RunWith(SpringRunner.class)
@DataJpaTest
class DriverRepositoryIntegrationTest
{
    private static final Logger LOG = LoggerFactory.getLogger(DriverRepositoryIntegrationTest.class);

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private IDriverRepository driverRepository;

    @BeforeEach
    void testData()
    {
        DriverDO driverDO = new DriverDO("Anil", "Aa");
        driverDO.setOnlineStatus(OnlineStatus.ONLINE);

        entityManager.persist(driverDO);
        entityManager.flush();
    }


    @Test
    void givenDriverId_whenFindById_thenReturnDriver()
    {

        DriverDO driverDO = new DriverDO("Vikas", "ha");

        entityManager.persist(driverDO);
        entityManager.flush();

        //when
        Optional<DriverDO> retrievedDriver = driverRepository.findById(driverDO.getId());

        //then
        assertEquals(retrievedDriver.get().getUsername(), driverDO.getUsername());

    }


    @Test
    void whenFindAll_thenReturnAllDrivers()
    {

        Iterable<DriverDO> drivers = driverRepository.findAll();
        List<DriverDO> driversList = new ArrayList<>();

        drivers.forEach(d -> driversList.add(d));

        assertThat(driversList.size(), is(2));

    }

}
