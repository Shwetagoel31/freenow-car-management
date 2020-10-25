package com.freenow.carmgmt.controller.mapper;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.freenow.carmgmt.datatransferobject.CarDTO;
import com.freenow.carmgmt.domainobject.CarDO;

@Component
public class CarMapper
{
    @Autowired
    private ModelMapper mapper;

    public CarDTO convertToCarDTO(CarDO carDo)
    {
        return mapper.map(carDo, CarDTO.class);
    }


    public CarDO convertToCarDO(CarDTO carDto)
    {
        return mapper.map(carDto, CarDO.class);
    }

}
