package com.freenow.controller.mapper;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.freenow.datatransferobject.CarDTO;
import com.freenow.domainobject.CarDO;

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
