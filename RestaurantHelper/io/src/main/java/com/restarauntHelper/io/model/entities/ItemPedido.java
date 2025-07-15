package com.restarauntHelper.io.model.entities;

import java.util.Objects;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "itens_pedido")
public class ItemPedido {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long itensId;
	
	private String nomeItem;
	private double precoUnitario;
	private Double quantidade;
	private Double subTotal;
	
	@ManyToOne
	private Pedido pedido;

	public ItemPedido(Long itensId, String nomeItem, double precoUnitario, Double quantidade, Double subTotal,
			Pedido pedido) {
		this.itensId = itensId;
		this.nomeItem = nomeItem;
		this.precoUnitario = precoUnitario;
		this.quantidade = quantidade;
		this.subTotal = subTotal;
		this.pedido = pedido;
	}
	
	public ItemPedido() {
		
	}

	public Long getItensId() {
		return itensId;
	}

	public void setItensId(Long itensId) {
		this.itensId = itensId;
	}

	public String getNomeItem() {
		return nomeItem;
	}

	public void setNomeItem(String nomeItem) {
		this.nomeItem = nomeItem;
	}

	public double getPrecoUnitario() {
		return precoUnitario;
	}

	public void setPrecoUnitario(double precoUnitario) {
		this.precoUnitario = precoUnitario;
	}

	public Double getQuantidade() {
		return quantidade;
	}

	public void setQuantidade(Double quantidade) {
		this.quantidade = quantidade;
	}

	public Double getSubTotal() {
		return subTotal;
	}

	public void setSubTotal(Double subTotal) {
		this.subTotal = subTotal;
	}

	public Pedido getPedido() {
		return pedido;
	}

	public void setPedido(Pedido pedido) {
		this.pedido = pedido;
	}

	@Override
	public int hashCode() {
		return Objects.hash(itensId);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ItemPedido other = (ItemPedido) obj;
		return Objects.equals(itensId, other.itensId);
	}

	@Override
	public String toString() {
		return "OrderItem [itensId=" + itensId + ", nomeItem=" + nomeItem + ", precoUnitario=" + precoUnitario
				+ ", quantidade=" + quantidade + ", subTotal=" + subTotal + ", pedido=" + pedido + "]";
	}
	
	
	
}
