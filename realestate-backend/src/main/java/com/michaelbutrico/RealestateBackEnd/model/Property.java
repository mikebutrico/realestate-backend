package com.michaelbutrico.RealestateBackEnd.model;

import org.apache.tomcat.jni.Address;

import javax.persistence.Entity;
import javax.persistence.OneToMany;

@Entity
public class Property {
    private String name;
    private Address address;
    //!TODO: add FK
    @OneToMany(mappedBy = "")
    private Set<Tenant> tenants;
    private String description;
    private Set<Attachment> attachments;

}
