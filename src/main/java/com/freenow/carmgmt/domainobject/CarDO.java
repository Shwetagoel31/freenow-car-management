package com.freenow.carmgmt.domainobject;

import java.time.ZonedDateTime;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.freenow.carmgmt.domainvalue.CarSize;
import com.freenow.carmgmt.domainvalue.EngineType;

@Entity
@Table(name = "car", uniqueConstraints = @UniqueConstraint(name = "uc_licensePlate", columnNames = {"licensePlate"}))
public class CarDO
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;

    @Column(nullable = false)
    @DateTimeFormat(iso = ISO.DATE_TIME)
    private ZonedDateTime dateCreated = ZonedDateTime.now();

    @Column(nullable = false)
    @NotNull(message = "LicensePlate cannot be null!")
    private String licensePlate;

    @Column(nullable = false)
    @NotNull(message = "SeatCount cannot be null!")
    private int seatCount;

    private boolean convertible;

    private double rating;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private EngineType engineType;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private CarSize carSize;
    
    @Column(nullable = false)
    private Boolean deleted = false;

    private String manufacturerName;
    
    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "carDO")    
    private DriverDO driver;

    public DriverDO getDriver()
    {
        return driver;
    }


    public Boolean getDeleted() {
		return deleted;
	}


	public void setDeleted(Boolean deleted) {
		this.deleted = deleted;
	}


	@Override
    public String toString()
    {
        return "CarDO [Id="
            + Id + ", dateCreated=" + dateCreated + ", licensePlate=" + licensePlate + ", seatCount=" + seatCount + ", convertible=" + convertible + ", rating=" + rating
            + ", engineType=" + engineType + ", carSize=" + carSize + ", manufacturerName=" + manufacturerName + ", driver=" + driver + "]";
    }


    public void setDriver(DriverDO driver)
    {
        this.driver = driver;
    }

    public CarDO()
    {
        super();
        // TODO Auto-generated constructor stub
    }


    public CarDO(String licensePlate, int seatCount)
    {
        super();
        this.licensePlate = licensePlate;
        this.seatCount = seatCount;
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


    public String getManufacturerName()
    {
        return manufacturerName;
    }


    public void setManufacturerName(String manufacturerName)
    {
        this.manufacturerName = manufacturerName;
    }
}
