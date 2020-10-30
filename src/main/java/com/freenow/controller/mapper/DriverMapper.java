package com.freenow.controller.mapper;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.freenow.datatransferobject.CarDTO;
import com.freenow.datatransferobject.DriverDTO;
import com.freenow.domainobject.CarDO;
import com.freenow.domainobject.DriverDO;
import com.freenow.domainvalue.GeoCoordinate;

@Component
public class DriverMapper
{

    public static DriverDO makeDriverDO(DriverDTO driverDTO)
    {
        //return new DriverDO(driverDTO.getUsername(), driverDTO.getPassword());
        return new DriverDO();
    }


    public static DriverDTO makeDriverDTO(DriverDO driverDO)
    {
        /*DriverDTO.DriverDTOBuilder driverDTOBuilder =
            DriverDTO
                .newBuilder()
                .setId(driverDO.getId())
                .setPassword(driverDO.getPassword())
                .setUsername(driverDO.getUsername());*/
        
        DriverDTO.DriverDTOBuilder driverDTOBuilder =
            DriverDTO
                .newBuilder()
                .setId(driverDO.getId());

        GeoCoordinate coordinate = driverDO.getCoordinate();
        if (coordinate != null)
        {
            driverDTOBuilder.setCoordinate(coordinate);
        }

        return driverDTOBuilder.createDriverDTO();
    }


    public static List<DriverDTO> makeDriverDTOList(Collection<DriverDO> drivers)
    {
        return drivers
            .stream()
            .map(DriverMapper::makeDriverDTO)
            .collect(Collectors.toList());
    }
}
