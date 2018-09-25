package com.example.demo.service;

import java.io.UnsupportedEncodingException;

import com.example.demo.model.Personalizacao;
import com.example.demo.model.Pizza;

public interface PizzaService {

	public abstract Iterable<Pizza> getAllPizza();
	public abstract Pizza addPizza(Pizza pizza) throws UnsupportedEncodingException;
	public abstract Pizza checkSize(Pizza pizza) throws UnsupportedEncodingException;
	public abstract void pizzaSmall(Pizza pizza);
	public abstract void pizzaMedium(Pizza pizza);
	public abstract void pizzaBig(Pizza pizza);
	public abstract Pizza checkFlavor(Pizza pizza);
	public abstract void addTime(Pizza pizza, int time);
	public abstract Pizza addAddicional(int pid, Personalizacao personalizacao) throws UnsupportedEncodingException;
	public abstract Integer checkPersonalizationName(Personalizacao p) throws UnsupportedEncodingException ;
	public abstract Pizza putAdditional(Pizza pizza,Personalizacao personalizacao);
	public abstract Pizza getPedido(int id);
}
