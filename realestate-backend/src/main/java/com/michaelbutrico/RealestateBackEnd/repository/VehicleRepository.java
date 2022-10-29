package com.michaelbutrico.RealestateBackEnd.repository;

import com.michaelbutrico.RealestateBackEnd.model.Vehicle;
import com.michaelbutrico.RealestateBackEnd.model.VehicleId;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.NoRepositoryBean;

@NoRepositoryBean
public interface VehicleRepository extends CrudRepository<Vehicle, VehicleId> {
}
