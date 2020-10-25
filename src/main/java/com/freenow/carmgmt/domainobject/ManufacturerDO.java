package com.freenow.carmgmt.domainobject;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

@Entity
@Table(name = "manufacturer", uniqueConstraints = @UniqueConstraint(name = "uc_name", columnNames = {"name"}))
public class ManufacturerDO
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "manufacturer_id")
    private Long id;
    
    @Column(nullable = false)
    private String name;

}
