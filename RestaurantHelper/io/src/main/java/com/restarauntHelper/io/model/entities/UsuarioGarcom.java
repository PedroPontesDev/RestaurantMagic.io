package com.restarauntHelper.io.model.entities;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "tb_vendedor")
@DiscriminatorValue("VENDEDOR")
public class UsuarioGarcom extends User {
	private static final long serialVersionUID = 1L;

	@Column(name = "salario")
	private Double salario;

	@Column(name = "comissao")
	private static final Double COMISSAO = 0.10; // COMISSÃO FIXA DE TODOS GARCONS EM DEZ PORCENTO?

	@Column(name = "quantidade_de_vendas")
	private Integer qtdVendas;

	@ManyToMany
	private List<Mesa> mesaDeGarconsRelacionados = new ArrayList<>();

	@ManyToOne
	private List<Pedido> pedidosDoGarcon = new ArrayList<>();

	@Column(name = "top_vendedor")
	private boolean isTopSeller;

	public UsuarioGarcom(Double salario, List<Mesa> mesaDeGarconsRelacionados,
			List<Pedido> pedidosDoGarcon, boolean isTopSeller) {
		this.salario = salario;
	
		this.mesaDeGarconsRelacionados = mesaDeGarconsRelacionados;
		this.pedidosDoGarcon = pedidosDoGarcon;
		this.isTopSeller = isTopSeller;
	}

	public boolean isTopSeller(LocalDate mesAnterior) {
		return true;
			
	}

	public Integer getQtdVendas() {
		return qtdVendas;
	}

	public void setQtdVendas(Integer qtdVendas) {
		this.qtdVendas = qtdVendas;
	}

	public void setTopSeller(boolean isTopSeller) {
		this.isTopSeller = isTopSeller;
	}

	public UsuarioGarcom() {
	}

	public Double getSalario() {
		return salario;
	}

	public void setSalario(Double salario) {
		this.salario = salario;
	}

	public Double getCOMISSAO() {
		return COMISSAO;
	}



	public List<Mesa> getMesaDeGarconsRelacionados() {
		return mesaDeGarconsRelacionados;
	}

	public void setMesaDeGarconsRelacionados(List<Mesa> mesaDeGarconsRelacionados) {
		this.mesaDeGarconsRelacionados = mesaDeGarconsRelacionados;
	}

	public List<Pedido> getPedidosDoGarcon() {
		return pedidosDoGarcon;
	}

	@Override
	public String toString() {
		return "UsuarioGarcom [salario=" + salario + ", qtdVendas=" + qtdVendas + ", mesaDeGarconsRelacionados="
				+ mesaDeGarconsRelacionados + ", pedidosDoGarcon=" + pedidosDoGarcon + ", isTopSeller=" + isTopSeller
				+ "]";
	}

	
	
	
}
