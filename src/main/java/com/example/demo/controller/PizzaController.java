package com.example.demo.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.Pizza;
import com.example.demo.service.PizzaService;

@RestController
@RequestMapping("/pizza")
public class PizzaController {
	
	@Autowired
	private PizzaService pizzaServ;
	
	@GetMapping
	public Iterable<Pizza> listPizza(){
		return pizzaServ.getAllPizza();
	}
	
	@PostMapping
	public Pizza addPizza(@Valid @RequestBody Pizza pizza) {
		return pizzaServ.addPizza(pizza);
	}
	
	
}
