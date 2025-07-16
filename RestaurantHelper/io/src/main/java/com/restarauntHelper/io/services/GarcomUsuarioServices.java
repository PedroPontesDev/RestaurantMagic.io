package com.restarauntHelper.io.services;

import java.util.List;
import java.util.Set;

import com.restarauntHelper.io.model.entities.Mesa;
import com.restarauntHelper.io.model.entities.Pedido;
import com.restarauntHelper.io.model.entities.RegistroDePonto;
import com.restarauntHelper.io.model.entities.UsuarioGarcom;
import com.restarauntHelper.io.model.entities.dtos.ComandaDTO;
import com.restarauntHelper.io.model.entities.dtos.GarcomDTO;

public interface GarcomUsuarioServices {

    // --- GESTÃO DE GARÇONS ---
    Set<UsuarioGarcom> listarTodosGarcons();

    GarcomDTO listarGarcomComMaiorSalario(long garcomId, double limiteMax);

    GarcomDTO procurarGarcomPorId(Long id) throws Exception;
    
    GarcomDTO procurarGarcomPorNome(Long id);

    boolean verificarDisponibilidadeDeGarcom(String nomeGarcom) throws Exception;

    boolean promoverGarcom(Long garcomId); //METODO NA CLASSE PAI IS TOOP SELLER

    
    // --- GESTÃO DE MESA E COMANDA ---
    
    Mesa alocarMesa(Long garcomId, Long mesaId, Long clienteId) throws Exception;

    List<Mesa> listarMesasPorGarcom(Long garcomId) throws Exception;

    boolean liberarMesa(Long mesaId, Long garcomId) throws Exception;


    Pedido abrirComandaSemItens(Long garcomId, Long mesaId, String nomeCliente) throws Exception;
    
    Pedido abrirComandaComItens(Long garcomId, Long mesaId, String nomeCliente, List<Long> itemPedidoIds)
			throws Exception;

    Pedido fecharComandaECalcularComissaoDeGarcom(double bonus, Long pedidoId)throws Exception;

    List<Pedido> listarPedidosDoGarcom(Long garcomId);

    
    // --- GESTÃO FINANCEIRA ---
    
    double calcularSalarioFinal(List<Long> garcomsId);

    double calcularComissaoMensal(Long garcomId);

   
    // --- REGISTRO DE FALTAS / PONTO ---
    RegistroDePonto registrarFaltaGarcom(Long garcomId);

    List<RegistroDePonto> listarFaltasGarcom(Long garcomId);

	

}


	