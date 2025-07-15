package com.restarauntHelper.io.model.entities;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.TreeSet;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;

@Entity
public class Pedido {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long pedidoId;

	@OneToMany
	private List<ItemPedido> itensPedido = new ArrayList<>();

	@OneToMany
	private UsuarioGarcom garcomPedido;

	@ManyToOne
	private UsuarioCliente clientePedido;

	@ManyToOne
	Mesa mesaDoPedido;

	private Double Total;

	
	public Pedido(Long pedidoId, List<ItemPedido> itensPedido, UsuarioGarcom garcomPedido, UsuarioCliente clientePedido,
			Mesa mesaDoPedido, Double total) {
		this.pedidoId = pedidoId;
		this.itensPedido = itensPedido;
		this.garcomPedido = garcomPedido;
		this.clientePedido = clientePedido;
		this.mesaDoPedido = mesaDoPedido;
		Total = total;
	}

	public Pedido() {

	}

	public Long getPedidoId() {
		return pedidoId;
	}

	public void setPedidoId(Long pedidoId) {
		this.pedidoId = pedidoId;
	}

	public Mesa getMesaDoPedido() {
		return mesaDoPedido;
	}

	public void setMesaDoPedido(Mesa mesaDoPedido) {
		this.mesaDoPedido = mesaDoPedido;
	}

	public void setGarçomPedido(UsuarioGarcom arçomPedido) {
		this.garcomPedido = arçomPedido;
	}

	public void setTotal(Double total) {
		Total = total;
	}

	public List<ItemPedido> getItensPedido() {
		return itensPedido;
	}


	public UsuarioGarcom getGarcomPedido() {
		return garcomPedido;
	}

	public void setGarcomPedido(UsuarioGarcom garcomPedido) {
		this.garcomPedido = garcomPedido;
	}

	public UsuarioCliente getClientePedido() {
		return clientePedido;
	}

	public void setClientePedido(UsuarioCliente clientePedido) {
		this.clientePedido = clientePedido;
	}

	public Double getTotal() {
		return Total;
	}

	public void setSubTotal(Double Total) {
		this.Total = Total;
	}

	@Override
	public int hashCode() {
		return Objects.hash(pedidoId);
	}

	public double calcularComisaoBaseadoTotalPedido(ItemPedido item) throws Exception {

		// Dados garçom
		double salarioAtual;
		double salarioAtualizado;
		double salarioGarcom;
		UsuarioGarcom garcomDaMesa;

		// Calcular total do pedido
		double valorItem;
		List<ItemPedido> itensDoPedido = new ArrayList<>();
		
		for (ItemPedido itens : this.itensPedido) {
			if (!this.itensPedido.contains(itens))
				throw new Exception("Itens não estão no pedido");
		
			ItemPedido itemPedido =new ItemPedido();
			itemPedido.setNomeItem(itens.getNomeItem());
			itemPedido.setPrecoUnitario(itens.getPrecoUnitario());
			itemPedido.setQuantidade(itens.getQuantidade());
			itensDoPedido.add(itemPedido);
			valorItem = itemPedido.getPrecoUnitario();
		}

		double subtotal = itensDoPedido.
				stream()
				.mapToDouble(i -> i.getPrecoUnitario() * i.getQuantidade())
				.sum();

		double valorTotalDePedido = 0.0;

		// Calcular comissão e total do pedido

		for (UsuarioGarcom garcom : garcomPedido) {
			garcomDaMesa = garcom;
			salarioGarcom = garcomDaMesa.getSalario();
			salarioAtual = salarioGarcom;

			double comissao = (garcomDaMesa.getSalario() * garcomDaMesa.getCOMISSAO() / valorTotalDePedido);
			valorTotalDePedido = subtotal + comissao;
			;
			garcomDaMesa.setSalario(garcomDaMesa.getSalario() + comissao);

		}
		return valorTotalDePedido;

	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Pedido other = (Pedido) obj;
		return Objects.equals(pedidoId, other.pedidoId);
	}

	@Override
	public String toString() {
		return "Pedido [pedidoId=" + pedidoId + ", itensPedido=" + itensPedido + ", garcomPedido=" + garcomPedido
				+ ", clientePedido=" + clientePedido + ", mesaDoPedido=" + mesaDoPedido + ", Total=" + Total + "]";
	}

	
	
}
