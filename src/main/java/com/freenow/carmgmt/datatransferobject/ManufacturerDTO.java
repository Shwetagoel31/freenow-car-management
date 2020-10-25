package com.freenow.carmgmt.datatransferobject;

import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class ManufacturerDTO
{
    @JsonIgnore
    private Long id;
    
    @NotNull(message = "Name cannot be null!")
    private String name;

    public ManufacturerDTO()
    {
        super();
        // TODO Auto-generated constructor stub
    }


    public ManufacturerDTO(Long id, String name)
    {
        super();
        this.id = id;
        this.name = name;
    }


    public Long getId()
    {
        return id;
    }


    public void setId(Long id)
    {
        this.id = id;
    }


    public String getName()
    {
        return name;
    }


    public void setName(String name)
    {
        this.name = name;
    }

}
