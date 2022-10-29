package com.michaelbutrico.RealestateBackEnd.control;

import com.michaelbutrico.RealestateBackEnd.model.Vehicle;
import com.michaelbutrico.RealestateBackEnd.services.RepositoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;



@RestController
public class ApiController {

    public final static String getStock = "/stock";
    public final static String getPurchases = "/purchases";
    public final static String postAddStock = "/addToStock";
    public final static String postRegisterPurchase = "/registerPurchase";
    @Autowired
    private final RepositoryService repository;

    public ApiController(RepositoryService repository) {
        this.repository = repository;
    }

    /**
     * get route that returns the inventory of cars
     */
    @GetMapping(ApiController.getStock)
    public Iterable<Vehicle> getStock(){
       return repository.getAllVehicles();
    }
    @GetMapping(ApiController.getPurchases)
    public Iterable<Purchase> getPurchases(){
        return repository.getAllPurchases();
    }

    @PostMapping(value = ApiController.postAddStock, consumes = {"application/json"})
    public void addToStock(@RequestBody UtilityVehicle vehicle ){
        repository.addVehicle(vehicle);
    }
    @PostMapping(ApiController.postRegisterPurchase)
    public void registerPurchase(@RequestBody PurchaseRequestBody body){
        repository.sellVehicle(body.getVehicle(),body.getPerson(), body.getPrice());
    }

}
