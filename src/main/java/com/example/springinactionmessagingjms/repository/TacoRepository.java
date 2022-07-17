package com.example.springinactionmessagingjms.repository;


import com.example.springinactionmessagingjms.model.Taco;
import org.springframework.data.repository.CrudRepository;

public interface TacoRepository extends CrudRepository<Taco, Long> {
}
