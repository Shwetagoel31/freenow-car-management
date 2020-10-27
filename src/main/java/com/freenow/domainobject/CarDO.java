package com.freenow.domainobject;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotNull;

import com.freenow.domainvalue.CarSize;
import com.freenow.domainvalue.EngineType;

@Entity
@Table(name = "car", uniqueConstraints = @UniqueConstraint(name = "uc_licensePlate", columnNames = {"licensePlate"}))
public class CarDO extends BaseDO
{

    @Column(nullable = false)
    @NotNull(message = "LicensePlate cannot be null!")
    private String licensePlate;

    @Column(nullable = false)
    @NotNull(message = "SeatCount cannot be null!")
    private int seatCount;

    @Column(nullable = false)
    private boolean convertible;

    private double rating;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private EngineType engineType;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private CarSize carSize;

    private String manufacturerName;

    @OneToOne
    @JoinColumn(name = "driver_id")
    private DriverDO driverDO;

    public CarDO()
    {
        super();
        // TODO Auto-generated constructor stub
    }


    public CarDO(
        @NotNull(message = "LicensePlate cannot be null!") String licensePlate, @NotNull(message = "SeatCount cannot be null!") int seatCount, boolean convertible, double rating,
        EngineType engineType, CarSize carSize, String manufacturerName)
    {
        super();
        this.licensePlate = licensePlate;
        this.seatCount = seatCount;
        this.convertible = convertible;
        this.rating = rating;
        this.engineType = engineType;
        this.carSize = carSize;
        this.manufacturerName = manufacturerName;
        this.setDeleted(false);
    }


    public DriverDO getDriver()
    {
        return driverDO;
    }


    public void setDriver(DriverDO driverDO)
    {
        this.driverDO = driverDO;
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


    public String getManufacturerName()
    {
        return manufacturerName;
    }


    public void setManufacturerName(String manufacturerName)
    {
        this.manufacturerName = manufacturerName;
    }
}
