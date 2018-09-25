package com.example.demo.controller;

import java.io.UnsupportedEncodingException;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.Personalizacao;
import com.example.demo.model.Pizza;
import com.example.demo.service.PizzaService;

@RestController
@RequestMapping("/pizza")
public class PizzaController {
	
	@Autowired
	private PizzaService pizzaServ;
	
	@GetMapping
	public ResponseEntity<Iterable<Pizza>> listPizza(){
		return ResponseEntity.ok(pizzaServ.getAllPizza());
	}
	
	@PostMapping
	public ResponseEntity<Pizza> addPizza(@Valid @RequestBody Pizza pizza) throws UnsupportedEncodingException {
		if(pizzaServ.addPizza(pizza) != null) {
			return ResponseEntity.ok(pizzaServ.addPizza(pizza));
		}
		return ResponseEntity.badRequest().build();		
	}
	
	@PostMapping("/{pid}/personalizar")
	public ResponseEntity<Pizza> addAdditional(@Valid @PathVariable int pid, @RequestBody Personalizacao personalizacao) throws UnsupportedEncodingException{
		if(pizzaServ.addAddicional(pid, personalizacao) != null) {
			return ResponseEntity.ok(pizzaServ.addAddicional(pid, personalizacao));
		}
		return ResponseEntity.badRequest().build();		
	}
	
	@GetMapping("/{pid}")
	public ResponseEntity<Pizza> getPedido(@Valid @PathVariable int pid) {
		if(pizzaServ.getPedido(pid) != null) {
			return ResponseEntity.ok(pizzaServ.getPedido(pid));
		}
		return ResponseEntity.notFound().build();
	}
	
}
