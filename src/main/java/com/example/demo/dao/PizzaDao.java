package com.example.demo.dao;

import org.springframework.data.repository.CrudRepository;

import com.example.demo.model.Pizza;

public interface PizzaDao extends CrudRepository<Pizza, Integer>{

}
