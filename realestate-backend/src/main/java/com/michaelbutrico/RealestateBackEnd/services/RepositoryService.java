package com.michaelbutrico.RealestateBackEnd.services;

import com.michaelbutrico.RealestateBackEnd.model.*;
import com.michaelbutrico.RealestateBackEnd.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@Component
public class RepositoryService {
    @Autowired
    private final UtilityVehicleRepository utilityVehicleRepository;
    @Autowired
    private final CarRepository carRepository;
    @Autowired
    private final EngineRepository engineRepository;
    @Autowired
    private final PersonRepository personRepository;
    @Autowired
    private final PurchaseRepository purchaseRepository;

    public RepositoryService(UtilityVehicleRepository utilityVehicleRepository, CarRepository carRepository, EngineRepository engineRepository, PersonRepository personRepository, PurchaseRepository purchaseRepository) {
        this.utilityVehicleRepository = utilityVehicleRepository;
        this.carRepository = carRepository;
        this.engineRepository = engineRepository;
        this.personRepository = personRepository;
        this.purchaseRepository = purchaseRepository;
    }

    public Iterable<Vehicle> getAllVehicles(){
        return utilityVehicleRepository.findAll();
    }
    public Iterable<Purchase> getAllPurchases(){return purchaseRepository.findAll();}

    public Iterable<Engine> getAllEngines(){
        return engineRepository.findAll();
    }

    //does not overwrite the current stock value eg(if stock is 1, and vehicle received has stock = 2, saved stock will be 3)
    public void addVehicle(Vehicle vehicle){
        Optional<Vehicle> result =  utilityVehicleRepository.findById(new VehicleId(vehicle.getManufacturer(), vehicle.getModelNumber()));
        if(result.isPresent()){
            vehicle.setStockAmount(vehicle.getStockAmount() + result.get().getStockAmount());
        }

        Optional<Engine>engineResult =  engineRepository.findById(vehicle.getEngine().getModel());
        if(!engineResult.isPresent()){
            engineRepository.save(vehicle.getEngine());
        }
        utilityVehicleRepository.save(vehicle);
    }
    public boolean sellVehicle(Vehicle vehicle, Person person, float price){
        Optional<Vehicle> result =  utilityVehicleRepository.findById(new VehicleId(vehicle.getManufacturer(), vehicle.getModelNumber()));
        //ignore the stock amount and use the one already present
        if(result.isPresent()){
            Vehicle previous = result.get();

            if (previous.getStockAmount()-vehicle.getStockAmount() < 0) return  false;

            Purchase purchase = new Purchase(person,previous,price);
            previous.setStockAmount(previous.getStockAmount()-vehicle.getStockAmount());
            int maxLoad = 0;
            if (vehicle instanceof UtilityVehicle) {
            maxLoad = ((UtilityVehicle) vehicle).getMaxLoad();
            }

            personRepository.save(person);
            purchaseRepository.save(purchase);
            utilityVehicleRepository.save(previous);

            return true;
        }
        else {
            return false;
        }

    }
    public void deleteVehicle(Vehicle vehicle){
        utilityVehicleRepository.deleteById(new VehicleId(vehicle.getManufacturer(), vehicle.getModelNumber()));
    }

    public UtilityVehicleRepository getUtilityVehicleRepository() {
        return utilityVehicleRepository;
    }

    public CarRepository getCarRepository() {
        return carRepository;
    }

    public EngineRepository getEngineRepository() {
        return engineRepository;
    }

    public PersonRepository getPersonRepository() {
        return personRepository;
    }

    public PurchaseRepository getPurchaseRepository() {
        return purchaseRepository;
    }
}
