package com.restarauntHelper.io.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.restarauntHelper.io.model.entities.dtos.GarcomDTO;
import com.restarauntHelper.io.model.entities.dtos.ItemPedidoDTO;
import com.restarauntHelper.io.model.entities.dtos.PedidoDTO;

@Service
public interface PedidoServices {
	

	Double calcularComissaoDeVendedor(Long pedidoId, Long mesaId); //Trazer os garçons ou de um pedido ou de uma mesa a critério
	
	Double calcularTotalDoPedido(Long pedidoId);
	
	PedidoDTO abrirNovoPedido(Long mesaId, Long garcomId, PedidoDTO novoPedido);

	PedidoDTO fecharPedido(Long pedidoId);
	
	PedidoDTO adicionarItemAPedido(ItemPedidoDTO item, Long pedidoId);
	
	List<PedidoDTO> acharPedidosPorNumeroDeMesa(String mesaNumero);
	
	void adicionarItensAUmPedido(List<Long> itensDePedidoId, Long pedidoId);
	
	void deletarPedido(Long pedidoId);
	
	GarcomDTO findGarcomPorPedidoId(Long pedidoId);
	
	List<PedidoDTO> acharPedidosPorNomeGarcom(String garcomName);
	
}
