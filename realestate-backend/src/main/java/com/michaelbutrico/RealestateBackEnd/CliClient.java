package com.michaelbutrico.RealestateBackEnd;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.michaelbutrico.RealestateBackEnd.control.ApiController;
import com.michaelbutrico.RealestateBackEnd.control.PurchaseRequestBody;
import com.michaelbutrico.RealestateBackEnd.model.*;
import org.springframework.http.*;
import org.springframework.web.client.RestTemplate;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URISyntaxException;
import java.util.Arrays;

public class CliClient {
    private String url;

    //URL including port
    public CliClient(String url) {
        this.url = url;
    }

    /**
     * starts the CLI tool to test the API
     */
    public void start() {
        System.out.println("\n-------------REST API CLI CLIENT-------------");
        System.out.println("every request is ran trough the REST api\n");
        int choice = 0;
        do {
            choice = integerReadLine();
            try{
                BufferedReader reader = new BufferedReader(
                        new InputStreamReader(System.in));
                switch (choice){
                    case 1:
                       Iterable<Vehicle> vehicles = getStock();
                       System.out.println("\n"+vehicles+"\n");
                       break;
                    case 2:
                        Iterable<Purchase> purchases = getPurchases();
                        System.out.println("\n"+purchases+"\n");
                        break;
                    case 3:
                        String input;
                        UtilityVehicle vehicle = new UtilityVehicle();
                        Engine engine = new Engine();
                        int intInput;
                        System.out.println("enter vehicle information");
                        System.out.println("manufacturer:");
                        input = reader.readLine();
                        vehicle.setManufacturer(input);

                        System.out.println("model number:");
                        input = reader.readLine();
                        vehicle.setModelNumber(input);

                        System.out.println("model name:");
                        input = reader.readLine();
                        vehicle.setModelName(input);

                        System.out.println("stock amount:");
                        input = reader.readLine();
                        intInput = Integer.parseInt(input);
                        vehicle.setStockAmount(intInput);

                        System.out.println("retail price:");
                        input = reader.readLine();
                        vehicle.setRetailPrice(Integer.parseInt((input)));

                        System.out.println("engine model:");
                        input = reader.readLine();
                        engine.setModel(input);

                        System.out.println("engine hp:");
                        input = reader.readLine();
                        intInput = Integer.parseInt(input);
                        engine.setHorsePower(intInput);


                        System.out.println("max load:");
                        input = reader.readLine();
                        vehicle.setMaxLoad(Integer.parseInt(input));
                        vehicle.setEngine(engine);
                        addToStock(vehicle);

                        break;
                    case 4:
                        System.out.println("enter sale information");
                        PurchaseRequestBody request = new PurchaseRequestBody();
                        Person buyer = new Person();
                        UtilityVehicle vehicle1 = new UtilityVehicle();
                        Engine engine1 = new Engine();
                        int price =0 ;

                        System.out.println("enter vehicle information");
                        System.out.println("manufacturer:");
                        input = reader.readLine();
                        vehicle1.setManufacturer(input);

                        System.out.println("model number:");
                        input = reader.readLine();
                        vehicle1.setModelNumber(input);

                        System.out.println("purchase amount:");
                        input = reader.readLine();
                        vehicle1.setStockAmount(Integer.parseInt(input));
                        vehicle1.setModelName("default");
                        vehicle1.setEngine(new Engine());
                        vehicle1.setRetailPrice(0);


                        request.setVehicle(vehicle1);
                        System.out.println("enter the price:");
                        input = reader.readLine();
                        price =Integer.parseInt(input);
                        request.setPrice(price);

                        System.out.println("enter person information");
                        System.out.println("first name:");
                        input = reader.readLine();
                        buyer.setFirstName(input);
                        System.out.println("last name:");
                        input = reader.readLine();
                        buyer.setLastName(input);
                        request.setPerson(buyer);
                        registerPurchase(request);
                        break;
                    case 5:
                        System.out.println("\nclient closed..");
                        return;
                    default :

                        break;
                }
            }
            catch (Exception e){
                e.printStackTrace();
            }

        }while (choice!=5);




    }
    private void printCliOptions(){
        System.out.println("Enter an integer to choose the next operation\n1.check stock\n2.check sales\n3.add to stock\n4.register sale\n5.exit");
    }
    private int integerReadLine(){
        while(true) {
            BufferedReader reader = new BufferedReader(
                    new InputStreamReader(System.in));
            printCliOptions();
            try {
                String input = reader.readLine();
                int choice = Integer.parseInt(input);
                return choice;
            }
            catch (Exception e){
                System.out.println("\nInput is not a number\n");
            }

        }
    }
    private Iterable<Vehicle> getStock() throws URISyntaxException {
        RestTemplate restTemplate = new RestTemplate();
        UtilityVehicle[] forNow = restTemplate.getForObject(this.url+ApiController.getStock, UtilityVehicle[].class);
        return Arrays.asList(forNow);
    }
    private Iterable<Purchase> getPurchases() throws URISyntaxException {
        RestTemplate restTemplate = new RestTemplate();
        Purchase[] forNow = restTemplate.getForObject(this.url+ApiController.getPurchases, Purchase[].class);
        return Arrays.asList(forNow);
    }
    private void addToStock(Vehicle vehicle){
        RestTemplate restTemplate = new RestTemplate();
        ObjectMapper Obj = new ObjectMapper();

        try {
            String jsonStr = Obj.writeValueAsString(vehicle);
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);

            HttpEntity<String> request =
                    new HttpEntity<String>(jsonStr, headers);

            String personResultAsJsonStr =
                    restTemplate.postForObject(this.url+ApiController.postAddStock, request, String.class);
        }

        catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void registerPurchase(PurchaseRequestBody purchaseRequestBody){
        RestTemplate restTemplate = new RestTemplate();
        ObjectMapper Obj = new ObjectMapper();

        try {
            String jsonStr = Obj.writeValueAsString(purchaseRequestBody);
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);

            HttpEntity<String> request =
                    new HttpEntity<String>(jsonStr, headers);
            System.out.println(jsonStr);
            String personResultAsJsonStr = restTemplate.postForObject(this.url+ApiController.postRegisterPurchase, request, String.class);
        }

        catch (IOException e) {
            e.printStackTrace();
        }
    }
}
