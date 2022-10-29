package com.michaelbutrico.RealestateBackEnd;

import com.michaelbutrico.RealestateBackEnd.model.*;
import com.michaelbutrico.RealestateBackEnd.repository.CarRepository;
import com.michaelbutrico.RealestateBackEnd.repository.EngineRepository;
import com.michaelbutrico.RealestateBackEnd.repository.UtilityVehicleRepository;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

import java.net.URISyntaxException;

@SpringBootApplication
public class Main {

	public static void main(String[] args) throws URISyntaxException {
		ConfigurableApplicationContext context =
		SpringApplication.run(Main.class, args);

		CarRepository carRepository = context.getBean(CarRepository.class);
		UtilityVehicleRepository utilityVehicleRepository = context.getBean(UtilityVehicleRepository.class);
		EngineRepository engineRepository = context.getBean(EngineRepository.class);


		Engine sportEngine = new Engine("sport engine",300);
		Engine truckEngine = new Engine("truck engine",1000);

		engineRepository.save(sportEngine);
		engineRepository.save(truckEngine);
		Car a5 = new Car("Audi","A5SPRT674","A5", 10,sportEngine,30000);
		Car a4 = new Car("Audi","A4STX123","A4",20,sportEngine,40000);
		Car a7 = new Car("Audi","A7SPRT674","A7", 1,sportEngine,30000);
		UtilityVehicle iveco = new UtilityVehicle("IVECO","IVC15234","Daily",5,truckEngine,60000,3000);
		carRepository.save(a4);
		carRepository.save(a5);
		utilityVehicleRepository.save(iveco);

		CliClient cli = new CliClient("http://localhost:8080");

		cli.start();






	}

}
