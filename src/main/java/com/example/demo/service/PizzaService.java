package com.example.demo.service;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.dao.PersonalizacaoDao;
import com.example.demo.dao.PizzaDao;
import com.example.demo.model.Personalizacao;
import com.example.demo.model.Pizza;

@Service
public class PizzaService{

@Autowired
private PizzaDao pizzaDao;

@Autowired
private PersonalizacaoDao personalizacaoDao;
	
	private enum SizePizza{
		pequena,media,grande;
	}
	
	private enum FlavorPizza{
		calabresa,marguerita,portuguesa;
	}

	public Iterable<Pizza> getAllPizza() {
		return pizzaDao.findAll();
	}
	
	public Pizza addPizza(Pizza pizza){
		if(checkSize(pizza) == null) {
			return null;
		}else if (checkFlavor(pizza) == null){
			return null;
		}else{
			return pizzaDao.save(pizza);
		}
	}
	
	public Pizza checkSize(Pizza pizza){
		SizePizza size = SizePizza.valueOf(pizza.getTamanho().toLowerCase());
		
		switch (size) {
		case pequena:
			pizzaSmall(pizza);
			break;
			
		case media:
			pizzaMedium(pizza);
			break;
		
		case grande:
			pizzaBig(pizza);
			break;
			
		default:
			return null;
		}
		
		return pizza;
	}
	
	public void pizzaSmall(Pizza pizza){
		pizza.setValorTotal(20.00);
		pizza.setTempoTotal(15);
	}
	
	public void pizzaMedium(Pizza pizza) {
		pizza.setValorTotal(30.00);
		pizza.setTempoTotal(20);
	}
	
	public void pizzaBig(Pizza pizza) {
		pizza.setValorTotal(40.00);
		pizza.setTempoTotal(25);
	}
	
	public Pizza checkFlavor(Pizza pizza){
		FlavorPizza flavor = FlavorPizza.valueOf(pizza.getSabor().toLowerCase());
		
		switch (flavor) {
		case calabresa:
			break;

		case marguerita:
			break;
			
		case portuguesa:
			addTime(pizza,5);
			break;
		
		default:
			return null;
		}
		
		return pizza;
	}
	
	public void addTime(Pizza pizza, int time){
		pizza.setTempoTotal(pizza.getTempoTotal()+time);
	}

	public Pizza addAddicional(int pid, Personalizacao personalizacao) throws UnsupportedEncodingException {
		Integer indexOfPersonalizacao = checkPersonalizationName(personalizacao);
		Pizza pizzaOnDb = pizzaDao.findById(pid).get();
		if(indexOfPersonalizacao != null) {
			Personalizacao personalizacaoOnDb = 
					personalizacaoDao.findById(indexOfPersonalizacao).get();			
			
			if(!pizzaOnDb.getPersonalizacoes().contains(personalizacaoOnDb)) {
				pizzaOnDb.getPersonalizacoes().add(personalizacaoOnDb);
				
				Pizza newPizza = putAdditional(pizzaOnDb, personalizacaoOnDb);	
				BeanUtils.copyProperties(newPizza, pizzaOnDb, "id");
				
				return pizzaDao.save(pizzaOnDb);
			}
		}
		return null;
	}
	
	public Integer checkPersonalizationName(Personalizacao p) throws UnsupportedEncodingException {
		String personalizationName = URLDecoder.decode(p.getNome(), "UTF-8");
		
		for (Personalizacao element : personalizacaoDao.findAll()) {
			if(element.getNome().equals(personalizationName.toLowerCase())) {
				return element.getId();
			}
		}
		return null;
	}
	
	public Pizza putAdditional(Pizza pizza,Personalizacao personalizacao) {
		pizza.setTempoTotal(pizza.getTempoTotal()+personalizacao.getTempo());
		pizza.setValorTotal(pizza.getValorTotal()+personalizacao.getValor());
		
		return pizza;
	}
}

