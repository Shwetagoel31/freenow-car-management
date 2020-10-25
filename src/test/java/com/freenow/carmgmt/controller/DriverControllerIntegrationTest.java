package com.freenow.carmgmt.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import com.freenow.carmgmt.FreeNowCarManagementApplication;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.MOCK, classes = FreeNowCarManagementApplication.class)
@AutoConfigureMockMvc
public class DriverControllerIntegrationTest
{

    @Autowired
    private MockMvc mvc;

    private static final String DRIVERDTO_JSON =
        "{\"username\":\"Tapan\",\"password\":\"ha\", \"onlineStatus\":\"ONLINE\"}";

    @Test
    void givenDriver_whenCreateDriver_thenSuccess() throws Exception
    {

        mvc
            .perform(
                post("/api/v1/drivers")
                    .accept(MediaType.APPLICATION_JSON)
                    .content(DRIVERDTO_JSON)
                    .contentType(MediaType.APPLICATION_JSON))
            .andExpect(status().isCreated())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON))
            .andExpect(content().json(DRIVERDTO_JSON));

    }

}
