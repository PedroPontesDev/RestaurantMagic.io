package com.restarauntHelper.io.model.entities;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity	
@Table(name = "tb_mesas")
public class Mesa {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private String numeroMesa;
	
	@OneToMany(mappedBy = "mesaDoPedido")
	private List<Pedido> pedidosDaMesa = new ArrayList<>();

	@OneToOne
	private UsuarioCliente clienteMesa;

	@ManyToMany
	private List<UsuarioGarcom> garconsDaMesa;

	public Mesa(Long id, String numeroMesa, List<Pedido> pedidosDaMesa, UsuarioCliente clienteMesa,
			List<UsuarioGarcom> garconsDaMesa) {
		this.id = id;
		this.numeroMesa = numeroMesa;
		this.pedidosDaMesa = pedidosDaMesa;
		this.clienteMesa = clienteMesa;
		this.garconsDaMesa = garconsDaMesa;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNumeroMesa() {
		return numeroMesa;
	}

	public void setNumeroMesa(String numeroMesa) {
		this.numeroMesa = numeroMesa;
	}

	public List<Pedido> getPedidosDaMesa() {
		return pedidosDaMesa;
	}

	public void setPedidosDaMesa(List<Pedido> pedidosDaMesa) {
		this.pedidosDaMesa = pedidosDaMesa;
	}

	public UsuarioCliente getClienteMesa() {
		return clienteMesa;
	}

	public void setClienteMesa(UsuarioCliente clienteMesa) {
		this.clienteMesa = clienteMesa;
	}

	public List<UsuarioGarcom> getGarconsDaMesa() {
		return garconsDaMesa;
	}

	public void setGarconsDaMesa(List<UsuarioGarcom> garconsDaMesa) {
		this.garconsDaMesa = garconsDaMesa;
	}

	@Override
	public int hashCode() {
		return Objects.hash(id);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Mesa other = (Mesa) obj;
		return Objects.equals(id, other.id);
	}

	@Override
	public String toString() {
		return "Mesa [id=" + id + ", numeroMesa=" + numeroMesa + ", pedidosDaMesa=" + pedidosDaMesa + ", clienteMesa="
				+ clienteMesa + ", garconsDaMesa=" + garconsDaMesa + "]";
	}
	
	
	
}
