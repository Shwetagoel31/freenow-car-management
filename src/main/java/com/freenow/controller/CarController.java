package com.freenow.controller;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.freenow.controller.mapper.CarMapper;
import com.freenow.datatransferobject.CarDTO;
import com.freenow.domainobject.CarDO;
import com.freenow.exception.EntityNotFoundException;
import com.freenow.service.car.ICarService;

@RestController
@RequestMapping("/api/v1/cars")
public class CarController
{

    private final ICarService carService;
    private final CarMapper mapper;
    
    @Autowired
    public CarController(final ICarService carService, final CarMapper mapper) {
        this.carService = carService;
        this.mapper = mapper;
    }
    
    @GetMapping
    public Collection<CarDTO> findAll()
    {
        List<CarDTO> carDTOs = new ArrayList<>();
        Iterable<CarDO> carDOs = carService.findAll();
        
        carDOs.forEach(c -> carDTOs.add(mapper.convertToCarDTO(c)));
        return carDTOs;
    }


    @GetMapping("/{id}")
    public CarDO findOne(@PathVariable Long id) throws EntityNotFoundException
    {
        return carService.findById(id);
    }


    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public CarDTO save(@Valid @RequestBody CarDTO carDto)
    {
        CarDO carDo = carService.save(mapper.convertToCarDO(carDto));
        return mapper.convertToCarDTO(carDo);
    }


    @PutMapping("/{id}")
    public CarDTO update(@PathVariable Long id, @Valid @RequestBody CarDTO carDto)
    {
        CarDO carDo = carService.update(mapper.convertToCarDO(carDto));
        return mapper.convertToCarDTO(carDo);
            
    }


    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) throws EntityNotFoundException
    {
        carService.deleteById(id);
        
    }

}
