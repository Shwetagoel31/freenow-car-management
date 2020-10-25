package com.freenow.carmgmt.service.driver;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import com.freenow.carmgmt.dataaccessobject.IDriverRepository;
import com.freenow.carmgmt.domainobject.DriverDO;
import com.freenow.carmgmt.domainvalue.OnlineStatus;
import com.freenow.carmgmt.exception.EntityNotFoundException;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes = {DriverServiceImpl.class})
class DriverServiceImplTest
{
    @Autowired
    private DriverServiceImpl driverService;

    @MockBean
    private IDriverRepository driverRepository;

    @Test
    void givenDriver_whenSave_thenCreatedSuccessfully()
    {
        DriverDO driverDO = new DriverDO("Bahadur", "ha", OnlineStatus.ONLINE);
        when(driverRepository.save(driverDO)).thenReturn(driverDO);

        assertEquals(driverService.save(driverDO), driverDO);
        verify(driverRepository, times(1)).save(driverDO);
    }
    
    @Test
    void givenNonExistentDriverId_whenDelete_thenEntityNotFoundException() {
        
        doThrow(new EntityNotFoundException("No driver exists with given ID"))
        .when(driverRepository)
        .deleteById(100L);
        
        assertThrows(EntityNotFoundException.class, () -> driverRepository.deleteById(100L));
    }
}
