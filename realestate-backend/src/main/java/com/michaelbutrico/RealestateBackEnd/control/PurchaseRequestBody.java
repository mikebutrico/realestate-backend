package com.michaelbutrico.RealestateBackEnd.control;

import com.michaelbutrico.RealestateBackEnd.model.Vehicle;

public class PurchaseRequestBody {
    private Person person;
    private Vehicle vehicle;
    private float price;

    public PurchaseRequestBody() {
    }

    public PurchaseRequestBody(Person person, Vehicle vehicle, float price) {
        this.person = person;
        this.vehicle = vehicle;
        this.price = price;
    }

    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
    }

    public Vehicle getVehicle() {
        return vehicle;
    }

    public void setVehicle(Vehicle vehicle) {
        this.vehicle =  vehicle;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }
}
