package com.michaelbutrico.RealestateBackEnd.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

@Component
@Repository
public interface EngineRepository extends CrudRepository<Engine,String> {
}
