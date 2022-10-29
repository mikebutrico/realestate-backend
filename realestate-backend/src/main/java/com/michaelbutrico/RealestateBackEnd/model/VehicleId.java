package com.michaelbutrico.RealestateBackEnd.model;

import javax.persistence.Id;
import javax.persistence.IdClass;
import java.io.Serializable;
@IdClass(VehicleId.class)
public class VehicleId implements Serializable {
    @Id
    private String manufacturer;
    @Id
    private String modelNumber;

    public VehicleId(String manufacturer, String modelNumber) {
        this.manufacturer = manufacturer;
        this.modelNumber = modelNumber;
    }

    public VehicleId() {
    }

    public String getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
    }

    public String getModelNumber() {
        return modelNumber;
    }

    public void setModelNumber(String modelNumber) {
        this.modelNumber = modelNumber;
    }
}
