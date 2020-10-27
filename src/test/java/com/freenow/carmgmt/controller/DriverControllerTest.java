package com.freenow.carmgmt.controller;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import com.freenow.controller.DriverController;
import com.freenow.controller.mapper.CarMapper;
import com.freenow.controller.mapper.DriverMapper;
import com.freenow.datatransferobject.DriverDTO;
import com.freenow.domainobject.DriverDO;
import com.freenow.domainvalue.OnlineStatus;
import com.freenow.service.car.ICarService;
import com.freenow.service.driver.IDriverService;

@RunWith(SpringRunner.class)
@WebMvcTest(DriverController.class)
class DriverControllerTest
{
    @Autowired
    private MockMvc mvc;
    
    @MockBean
    private IDriverService driverService;
    
    @MockBean
    private ICarService carService;
    
    @MockBean
    private CarMapper carMapper;

    @Test
    void givenDriver_whenGetDriverById_thenReturnDriver() throws Exception
    {
        DriverDTO driverDTO = DriverDTO.newBuilder()
                                    .setUsername("Pramod")
                                    .setPassword("ha")
                                    .setId(100l)
                                    .setOnlineStatus(OnlineStatus.ONLINE)
                                    .createDriverDTO();
        
        DriverDO driverDO = DriverMapper.makeDriverDO(driverDTO);
        
        when(driverService.find(100l)).thenReturn(driverDO);
     
        mvc.perform(get("/api/v1/drivers/100")
          .contentType(MediaType.APPLICATION_JSON))
          .andExpect(status().isOk());
          
    }

}
