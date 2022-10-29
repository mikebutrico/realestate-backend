package com.michaelbutrico.RealestateBackEnd.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;

@Entity
@IdClass(VehicleId.class)
@JsonIgnoreProperties(ignoreUnknown = true)
public class Vehicle {
    @Id
    private String manufacturer;
    @Id
    private String modelNumber; //model identifier eg:123EF5

    private String modelName; //legible name Eg: Fiesta

    private int stockAmount;
    @ManyToOne
    @JoinColumn(name = "engine_model")
    private Engine engine;
    private int retailPrice;

    public Vehicle() {
    }

    public Vehicle(String manufacturer, String modelNumber, String modelName, int stockAmount, Engine engine, int retailPrice) {
        this.manufacturer = manufacturer;
        this.modelNumber = modelNumber;
        this.modelName = modelName;
        if(stockAmount<=0){
            throw new RuntimeException("Wrong Vehicle stock amount");
        }
        this.stockAmount = stockAmount;
        this.engine = engine;
        this.retailPrice = retailPrice;
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

    public String getModelName() {
        return modelName;
    }

    public void setModelName(String modelName) {
        this.modelName = modelName;
    }

    public int getStockAmount() {
        return stockAmount;
    }

    public void setStockAmount(int stockAmount) {
        this.stockAmount = stockAmount;
    }

    public Engine getEngine() {
        return engine;
    }

    public void setEngine(Engine engine) {
        this.engine = engine;
    }

    public int getRetailPrice() {
        return retailPrice;
    }

    public void setRetailPrice(int retailPrice) {
        this.retailPrice = retailPrice;
    }

    @Override
    public String toString() {
        return "Vehicle{" +
                "manufacturer='" + manufacturer + '\'' +
                ", modelNumber='" + modelNumber + '\'' +
                ", modelName='" + modelName + '\'' +
                ", stockAmount=" + stockAmount +
                ", engine=" + engine +
                ", retailPrice=" + retailPrice +
                '}';
    }
}
