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
public class PizzaServiceImpl implements PizzaService{

@Autowired
private PizzaDao pizzaDao;

@Autowired
private PersonalizacaoDao personalizacaoDao;
	
	private enum SizePizza{
		pequena,media,grande,média;
	}
	
	private enum FlavorPizza{
		calabresa,marguerita,portuguesa;
	}

	@Override
	public Iterable<Pizza> getAllPizza() {
		try {
			return pizzaDao.findAll();
		}catch (Exception e) {
			return null;
		}
		
	}

	@Override
	public Pizza addPizza(Pizza pizza) throws UnsupportedEncodingException{
		try {
			if(checkSize(pizza) == null) {
				return null;
			}else if (checkFlavor(pizza) == null){
				return null;
			}else{
				return pizzaDao.save(pizza);
			}
		}catch (Exception e) {
			return null;
		}
	}

	@Override
	public Pizza checkSize(Pizza pizza) throws UnsupportedEncodingException{
		String decodedSize = URLDecoder.decode(pizza.getTamanho(), "UTF-8");
		SizePizza size = SizePizza.valueOf(decodedSize.toLowerCase());
		
		switch (size) {
		case pequena:
			pizzaSmall(pizza);
			break;
			
		case media:
			pizzaMedium(pizza);
			break;
			
		case média:
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

	@Override
	public void pizzaSmall(Pizza pizza){
		pizza.setValorTotal(20.00);
		pizza.setTempoTotal(15);
	}

	@Override
	public void pizzaMedium(Pizza pizza) {
		pizza.setValorTotal(30.00);
		pizza.setTempoTotal(20);
	}

	@Override
	public void pizzaBig(Pizza pizza) {
		pizza.setValorTotal(40.00);
		pizza.setTempoTotal(25);
	}

	@Override
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

	@Override
	public void addTime(Pizza pizza, int time){
		pizza.setTempoTotal(pizza.getTempoTotal()+time);
	}

	@Override
	public Pizza addAddicional(int pid, Personalizacao personalizacao) throws UnsupportedEncodingException {
		try {
			String personalizationName = URLDecoder.decode(personalizacao.getNome(), "UTF-8");
			Integer indexOfPersonalizacao = checkPersonalizationName(personalizationName);
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
		}catch (Exception e) {
			return null;
		}
	}

	@Override
	public Integer checkPersonalizationName(String personalizationName) throws UnsupportedEncodingException {
		for (Personalizacao element : personalizacaoDao.findAll()) {
			if(element.getNome().equals(personalizationName.toLowerCase())) {
				return element.getId();
			}
		}
		return null;
	}

	@Override
	public Pizza putAdditional(Pizza pizza,Personalizacao personalizacao) {
		pizza.setTempoTotal(pizza.getTempoTotal()+personalizacao.getTempoAdicional());
		pizza.setValorTotal(pizza.getValorTotal()+personalizacao.getValorAdicional());
		
		return pizza;
	}

	@Override
	public Pizza getPedido(int id) {
		try {
			return pizzaDao.findById(id).get();
		}catch (Exception e) {
			return null;
		}
		
	}
}

