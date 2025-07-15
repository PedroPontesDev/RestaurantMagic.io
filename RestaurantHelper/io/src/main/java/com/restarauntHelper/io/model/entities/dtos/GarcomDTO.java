package com.restarauntHelper.io.model.entities.dtos;

import java.util.ArrayList;
import java.util.List;

import com.restarauntHelper.io.model.entities.Mesa;
import com.restarauntHelper.io.model.entities.Pedido;

import jakarta.persistence.Column;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;

public class GarcomDTO {

	private Long id;

	private static final Double COMISSAO = 0.10; // COMISSÃO FIXA DE TODOS GARCONS EM DEZ PORCENTO?
	
	private Integer qtdVendas;
	
	private List<Mesa> mesaDeGarconsRelacionados = new ArrayList<>();
	
	private List<Pedido> pedidosDoGarcon = new ArrayList<>();
	
	private boolean isTopSeller;

	public GarcomDTO(Integer qtdVendas, List<Mesa> mesaDeGarconsRelacionados, List<Pedido> pedidosDoGarcon,
			boolean isTopSeller) {
		this.qtdVendas = qtdVendas;
		this.mesaDeGarconsRelacionados = mesaDeGarconsRelacionados;
		this.pedidosDoGarcon = pedidosDoGarcon;
		this.isTopSeller = isTopSeller;
	}

	public Integer getQtdVendas() {
		return qtdVendas;
	}

	public void setQtdVendas(Integer qtdVendas) {
		this.qtdVendas = qtdVendas;
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

	public void setPedidosDoGarcon(List<Pedido> pedidosDoGarcon) {
		this.pedidosDoGarcon = pedidosDoGarcon;
	}

	public boolean isTopSeller() {
		return isTopSeller;
	}

	public void setTopSeller(boolean isTopSeller) {
		this.isTopSeller = isTopSeller;
	}

	public static Double getComissao() {
		return COMISSAO;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@Override
	public String toString() {
		return "GarcomDTO [id=" + id + ", qtdVendas=" + qtdVendas + ", mesaDeGarconsRelacionados="
				+ mesaDeGarconsRelacionados + ", pedidosDoGarcon=" + pedidosDoGarcon + ", isTopSeller=" + isTopSeller
				+ "]";
	}
	
	
	
	
	
	
}
