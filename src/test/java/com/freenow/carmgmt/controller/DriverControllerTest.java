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

import com.freenow.carmgmt.controller.mapper.CarMapper;
import com.freenow.carmgmt.controller.mapper.DriverMapper;
import com.freenow.carmgmt.datatransferobject.DriverDTO;
import com.freenow.carmgmt.domainobject.DriverDO;
import com.freenow.carmgmt.domainvalue.OnlineStatus;
import com.freenow.carmgmt.service.car.ICarService;
import com.freenow.carmgmt.service.driver.IDriverService;

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
