package com.restarauntHelper.io.services.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.restarauntHelper.io.model.entities.dtos.GarcomDTO;
import com.restarauntHelper.io.model.entities.dtos.ItemPedidoDTO;
import com.restarauntHelper.io.model.entities.dtos.PedidoDTO;
import com.restarauntHelper.io.services.PedidoServices;


public class PedidoServicesImpl  implements PedidoServices{

	//Relação com garcomRepositoriees
	
	//Relação com mesaRepositories
	
	//JPA EM PedidoRepositorie
	
	//FAZER RELAÇÕES E FAZER BOAS REGRAS DE NEGOCIOS BEM PENSADAS
	
	private Logger logger = LoggerFactory.getLogger(PedidoServicesImpl.class.getName());
	
	@Override
	public Double calcularComissaoDeVendedor(Long pedidoId, Long mesaId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Double calcularTotalDoPedido(Long pedidoId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public PedidoDTO abrirNovoPedido(Long mesaId, Long garcomId, PedidoDTO novoPedido) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public PedidoDTO fecharPedido(Long pedidoId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public PedidoDTO adicionarItemAPedido(ItemPedidoDTO item, Long pedidoId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void adicionarItensAUmPedido(List<Long> itensDePedidoId, Long pedidoId) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deletarPedido(Long pedidoId) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public GarcomDTO findGarcomPorPedidoId(Long pedidoId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<PedidoDTO> acharPedidosPorNomeGarcom(String garcomName) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<PedidoDTO> acharPedidosPorNumeroDeMesa(String mesaNumero) {
		// TODO Auto-generated method stub
		return null;
	}

}
