package com.freenow.datatransferobject;

import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.freenow.domainvalue.CarSize;
import com.freenow.domainvalue.EngineType;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class CarDTO
{
    @JsonIgnore
    private Long id;

    @NotNull(message = "LicensePlate cannot be null!")
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

    private CarDTO()
    {
        super();
        // TODO Auto-generated constructor stub
    }


    private CarDTO(
        Long id, @NotNull(message = "LicensePlate cannot be null!") String licensePlate, @NotNull(message = "SeatCount cannot be null!") int seatCount, boolean convertible,
        double rating, @NotNull(message = "EngineType cannot be null!") EngineType engineType, @NotNull(message = "CarSize cannot be null!") CarSize carSize,
        @NotNull(message = "ManufacturerName cannot be null!") String manufacturerName)
    {
        super();
        this.id = id;
        this.licensePlate = licensePlate;
        this.seatCount = seatCount;
        this.convertible = convertible;
        this.rating = rating;
        this.engineType = engineType;
        this.carSize = carSize;
        this.manufacturerName = manufacturerName;
    }

    @JsonProperty
    public Long getId()
    {
        return id;
    }


    public String getLicensePlate()
    {
        return licensePlate;
    }


    public int getSeatCount()
    {
        return seatCount;
    }


    public boolean isConvertible()
    {
        return convertible;
    }


    public double getRating()
    {
        return rating;
    }


    public EngineType getEngineType()
    {
        return engineType;
    }


    public CarSize getCarSize()
    {
        return carSize;
    }


    public String getManufacturerName()
    {
        return manufacturerName;
    }


    public static CarDTOBuilder newBuilder()
    {
        return new CarDTOBuilder();
    }

    private static class CarDTOBuilder
    {

        private Long id;

        private String licensePlate;

        private int seatCount;

        private boolean convertible;

        private double rating;

        private EngineType engineType;

        private CarSize carSize;

        private String manufacturerName;

        public CarDTOBuilder setId(Long id)
        {
            this.id = id;
            return this;
        }


        public CarDTOBuilder setLicensePlate(String licensePlate)
        {
            this.licensePlate = licensePlate;
            return this;
        }


        public CarDTOBuilder setSeatCount(int seatCount)
        {
            this.seatCount = seatCount;
            return this;
        }


        public CarDTOBuilder setConvertible(boolean convertible)
        {
            this.convertible = convertible;
            return this;
        }


        public CarDTOBuilder setRating(double rating)
        {
            this.rating = rating;
            return this;
        }


        public CarDTOBuilder setEngineType(EngineType engineType)
        {
            this.engineType = engineType;
            return this;
        }


        public CarDTOBuilder setCarSize(CarSize carSize)
        {
            this.carSize = carSize;
            return this;
        }


        public CarDTOBuilder setmanufacturerName(String manufacturerName)
        {
            this.manufacturerName = manufacturerName;
            return this;
        }
        
        public CarDTO createCarDTO() {
            return new CarDTO(id, licensePlate, seatCount, convertible, rating, engineType, carSize, manufacturerName);
        }

    }

}
