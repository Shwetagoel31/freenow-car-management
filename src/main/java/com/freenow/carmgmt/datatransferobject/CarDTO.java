package com.freenow.carmgmt.datatransferobject;

import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.freenow.carmgmt.domainvalue.CarSize;
import com.freenow.carmgmt.domainvalue.EngineType;

public class CarDTO
{
    @JsonIgnore
    private Long Id;

    //@NotNull(message = "LicensePlate cannot be null!")

    private String licensePlate;

    @NotNull(message = "SeatCount cannot be null!")
    private int seatCount;

    private boolean convertible;

    private double rating;

    @NotNull(message = "EngineType cannot be null!")
    private EngineType engineType;

    @NotNull(message = "CarSize cannot be null!")
    private CarSize carSize;

    @NotNull(message = "ManufacturerName cannot be null!")
    private String manufacturerName;

    private DriverDTO driverDto;

    public DriverDTO getDriverDto()
    {
        return driverDto;
    }


    public void setDriverDto(DriverDTO driverDto)
    {
        this.driverDto = driverDto;
    }

    public CarDTO()
    {
        super();
        // TODO Auto-generated constructor stub
    }


    public CarDTO(Long id, String licensePlate, int seatCount, boolean convertible, double rating, EngineType engineType, CarSize carSize)
    {
        super();
        Id = id;
        this.licensePlate = licensePlate;
        this.seatCount = seatCount;
        this.convertible = convertible;
        this.rating = rating;
        this.engineType = engineType;
        this.carSize = carSize;
    }


    public Long getId()
    {
        return Id;
    }


    public void setId(Long id)
    {
        Id = id;
    }


    public String getLicensePlate()
    {
        return licensePlate;
    }


    public void setLicensePlate(String licensePlate)
    {
        this.licensePlate = licensePlate;
    }


    public int getSeatCount()
    {
        return seatCount;
    }


    public void setSeatCount(int seatCount)
    {
        this.seatCount = seatCount;
    }


    public boolean isConvertible()
    {
        return convertible;
    }


    public void setConvertible(boolean convertible)
    {
        this.convertible = convertible;
    }


    public double getRating()
    {
        return rating;
    }


    public void setRating(double rating)
    {
        this.rating = rating;
    }


    public EngineType getEngineType()
    {
        return engineType;
    }


    public void setEngineType(EngineType engineType)
    {
        this.engineType = engineType;
    }


    public CarSize getCarSize()
    {
        return carSize;
    }


    public void setCarSize(CarSize carSize)
    {
        this.carSize = carSize;
    }


    public void setManufacturerName(String ManufacturerName)
    {
        this.manufacturerName = ManufacturerName;
    }


    public String getManufacturerName()
    {
        return this.manufacturerName;
    }

}
