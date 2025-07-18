package com.restarauntHelper.io.services.impl;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.Comparator;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.restarauntHelper.io.model.entities.ItemPedido;
import com.restarauntHelper.io.model.entities.Mesa;
import com.restarauntHelper.io.model.entities.Pedido;
import com.restarauntHelper.io.model.entities.RegistroDePonto;
import com.restarauntHelper.io.model.entities.UsuarioCliente;
import com.restarauntHelper.io.model.entities.UsuarioGarcom;
import com.restarauntHelper.io.model.entities.dtos.GarcomDTO;
import com.restarauntHelper.io.model.entities.enums.StatusPedido;
import com.restarauntHelper.io.repositories.MesaRepositories;
import com.restarauntHelper.io.repositories.OrderItemRepositories;
import com.restarauntHelper.io.repositories.PedidoRepositories;
import com.restarauntHelper.io.repositories.UsuarioClienteRepositories;
import com.restarauntHelper.io.repositories.UsuarioGarcomRepositories;
import com.restarauntHelper.io.services.GarcomUsuarioServices;

@Service
public class GarcomUsuarioServicesImpl implements GarcomUsuarioServices {

	@Autowired
	private PedidoRepositories pedidoRepository;

	@Autowired
	private UsuarioGarcomRepositories garcomRepository;

	@Autowired
	private MesaRepositories mesaRepository;

	@Autowired
	private UsuarioClienteRepositories clienteRepository;

	@Autowired
	private OrderItemRepositories itemPedidoRepository;

	private static final long EXPIRACAO_PEDIDO_MINUTOS = 60; // 1 hora
	
	// FAZER RELAÇÕES E FAZER BOAS REGRAS D E NEGOCIOS BEM PENSADAS

	@Override
	public Set<UsuarioGarcom> listarTodosGarcons() {
		var todosGarcons = garcomRepository.listarTodosGarcons();
		return todosGarcons;

	}

	@Override
	public GarcomDTO procurarGarcomPorId(Long garcomid) throws Exception {
		return new GarcomDTO(null, null, null, false);

	}

	@Override
	public boolean verificarDisponibilidadeDeGarcom(String nomeGarcom) throws Exception {
		UsuarioGarcom garcomExistente = garcomRepository.findByNome(nomeGarcom);

		if (garcomExistente == null) {
			return false;
		}

		if (garcomExistente.getMesaDeGarconsRelacionados().size() >= 5) {
			return false;

		}
		return true;

	}

	@Override
	public boolean promoverGarcom(Long garcomId) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Mesa alocarMesa(Long garcomId, Long mesaId, Long clienteId) throws Exception {

		Mesa mesaProcurada = mesaRepository.findById(mesaId)
				.orElseThrow(() -> new Exception("Mesa não encontrada com ID" + mesaId));

		UsuarioCliente clienteParaMesa = clienteRepository.findById(clienteId)
				.orElseThrow(() -> new Exception("Cliente não encontrada com ID" + clienteId));

		UsuarioGarcom garcomDaMesa = garcomRepository.findById(garcomId)
				.orElseThrow(() -> new Exception("Cliente não encontrada com ID" + garcomId));

		if (clienteParaMesa.getQuantidadeDeVisitas() > mesaProcurada.getCapacidaeMaxima())
			throw new Exception("Não é possivel trazer mais de 10 pessoas para está mesa!");
		if (!mesaProcurada.isDisponivel())
			throw new Exception("Mesa já contem cliente!");

		mesaProcurada.getGarconsDaMesa().add(garcomDaMesa);
		mesaProcurada.setClienteMesa(clienteParaMesa);
		mesaProcurada.setDisponivel(false);
		mesaRepository.save(mesaProcurada);

		return mesaProcurada;
	}

	@Override
	public List<Mesa> listarMesasPorGarcom(Long garcomId) throws Exception {
		UsuarioGarcom garcomDeMesa = garcomRepository.findById(garcomId)
				.orElseThrow(() -> new Exception("Usuario não encontrado com ID" + garcomId));
		return garcomDeMesa.getMesaDeGarconsRelacionados().stream()
				.sorted(Comparator.comparing(Mesa::getNumeroMesa).reversed()).toList();

	}

	@Override
	public boolean liberarMesa(Long mesaId, Long garcomId) {
		// TODO Auto-generated method stub
		return false; // remover garcom do arraylist atrelado a mesa ou o queal q seja a collection comp por tbm umm cliente
	}

	@Override
	public Pedido abrirComandaComItens(Long garcomId, Long mesaId, String nomeCliente, List<Long> itemPedidoIds)
			throws Exception {
		
		if(itemPedidoIds.isEmpty()) throw new Exception("Itens não encontrados com ids" + itemPedidoIds);

		UsuarioGarcom garcomDoPedido = garcomRepository.findById(garcomId)
				.orElseThrow(() -> new Exception("Garcom não encontrando com ID" + garcomId));

		Mesa mesaDeComanda = mesaRepository.findById(mesaId)
				.orElseThrow(() -> new Exception("Mesa não enoncontrada com ID" + mesaId));

		UsuarioCliente usuarioCliente = clienteRepository.findByNomeCliente(nomeCliente)
				.orElseThrow((() -> new Exception("Cliente não encontrado com nome" + nomeCliente)));

		Pedido abrirPedidoComanda = new Pedido();
		abrirPedidoComanda.setGarcomPedido(garcomDoPedido);
		abrirPedidoComanda.setMesaDoPedido(mesaDeComanda);
		abrirPedidoComanda.setClientePedido(usuarioCliente);
		abrirPedidoComanda.setTotal(0.0);
		abrirPedidoComanda.setSubTotal(0.0);
		abrirPedidoComanda.setStatusPedido(StatusPedido.ABERTO); 

		//Acha os itens de pedido por ID e insere no array ordenado pela quantidade
		List<ItemPedido> itemPedido = itemPedidoRepository.findAllById(itemPedidoIds).stream()
				.sorted(Comparator.comparing(ItemPedido::getQuantidade).reversed()).toList();

		abrirPedidoComanda.getItensPedido().addAll(itemPedido);
		pedidoRepository.save(abrirPedidoComanda);

		return abrirPedidoComanda;

	}

	@Override
	public List<Pedido> listarPedidosDoGarcom(Long garcomId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public double calcularComissaoMensal(Long garcomId) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public RegistroDePonto registrarFaltaGarcom(Long garcomId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<RegistroDePonto> listarFaltasGarcom(Long garcomId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public GarcomDTO listarGarcomComMaiorSalario(long garcomId, double limiteMax) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public GarcomDTO procurarGarcomPorNome(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public double calcularSalarioFinal(List<Long> garcomsId) {
		// TODO Auto-generated method stub
		return 0;
	}


	@Override
	public Pedido fecharComandaECalcularComissaoDeGarcom(double bonus, Long pedidoId) throws Exception { 
		//Metodo deve atualizar o ststus de pedido como fechado calcular o total baseao nos itens e a comissao do garcom * total da conta / 100
		Pedido pedidoAberto = pedidoRepository.findById(pedidoId)
														.orElseThrow(() -> new Exception("Pedido não encontrado com ID" + pedidoId));

		UsuarioCliente cliente = pedidoAberto.getClientePedido();
		UsuarioGarcom garcom = pedidoAberto.getGarcomPedido();
		
		
		double totalPedido = pedidoAberto.getItensPedido()
							.stream().mapToDouble(i -> i.getPrecoUnitario() * i.getQuantidade()).sum();
		
		double salarioAtualGarcomPedido = garcom.getSalario();
		
		double comissao = (totalPedido * garcom.getCOMISSAO()) / 100;

		if(pedidoAberto.getStatusPedido().equals(StatusPedido.ABERTO)) {
			pedidoAberto.setClientePedido(cliente);
			pedidoAberto.setGarcomPedido(garcom);
			pedidoAberto.setStatusPedido(StatusPedido.FECHADO);
			pedidoAberto.setSubTotal(totalPedido);
			pedidoAberto.setTotal(totalPedido + comissao);
			pedidoRepository.save(pedidoAberto);
			
			
			garcom.setSalario(salarioAtualGarcomPedido + comissao + bonus);
			garcomRepository.save(garcom);
			
			return pedidoAberto;
			
		} throw new Exception("Erro ao fechar comanda");
	
	
	}
	
	
	
	//CRIAR METODO INSERIR item EM PEDIDO COMANDA QUE JA ESTA ABERTO

	@Override
	public Pedido abrirComandaSemItens(Long garcomId, Long mesaId, String nomeCliente) throws Exception {

		UsuarioGarcom garcomDoPedido = garcomRepository.findById(garcomId)
				.orElseThrow(() -> new Exception("Garcom não encontrando com ID" + garcomId));

		Mesa mesaDeComanda = mesaRepository.findById(mesaId)
				.orElseThrow(() -> new Exception("Mesa não enoncontrada com ID" + mesaId));

		UsuarioCliente usuarioCliente = clienteRepository.findByNomeCliente(nomeCliente)
				.orElseThrow((() -> new Exception("Cliente não encontrado com nome" + nomeCliente)));

		
		Pedido novoPedido = new Pedido();
		novoPedido.setClientePedido(usuarioCliente);
		novoPedido.setGarcomPedido(garcomDoPedido);
		novoPedido.setMesaDoPedido(mesaDeComanda);
		novoPedido.setSubTotal(0.0);
		novoPedido.setTotal(null);
		novoPedido.setStatusPedido(StatusPedido.PENDENTE);
		pedidoRepository.save(novoPedido);
		return novoPedido;
		
		
	}

}
