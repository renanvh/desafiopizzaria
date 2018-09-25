package com.example.demo.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "tb_personalizacao")
public class Personalizacao {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	
	@NotNull
	private String nome;
	
	
	private Double valorAdicional;
	
	private int tempoAdicional;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public Double getValorAdicional() {
		return valorAdicional;
	}
	public void setValorAdicional(Double valorAdicional) {
		this.valorAdicional = valorAdicional;
	}
	public int getTempoAdicional() {
		return tempoAdicional;
	}
	public void setTempoAdicional(int tempoAdicional) {
		this.tempoAdicional = tempoAdicional;
	}
	
	
	
}
