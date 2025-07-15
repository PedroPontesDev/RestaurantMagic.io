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

    GarcomDTO listarGarcomComMaiorSalario(double limiteMax);

    GarcomDTO procurarGarcomPorId(Long id);

    boolean verificarDisponibilidadeDeGarcom(String nomeGarcom);

    boolean promoverGarcom(Long garcomId); //METODO NA CLASSE PAI IS TOOP SELLER

    
    // --- GESTÃO DE MESA E COMANDA ---
    Mesa alocarMesa(Long garcomId, Long mesaId, Long clienteId);

    List<Mesa> listarMesasPorGarcom(Long garcomId);

    boolean liberarMesa(Long mesaId, Long garcomId);


    Pedido abrirComanda(Long garcomId, Long mesaId, String nomeCliente, List<Long> itemPedidoIds) throws Exception;


    Pedido fecharComandaECalcularComissaoDeSeller(double bonus, Long pedidoId);

    List<Pedido> listarPedidosDoGarcom(Long garcomId);

    
    // --- GESTÃO FINANCEIRA ---
    double calcularSalarioFinal(Long pedidoId);

    double calcularComissaoMensal(Long garcomId);

   
    // --- REGISTRO DE FALTAS / PONTO ---
    RegistroDePonto registrarFaltaGarcom(Long garcomId);

    List<RegistroDePonto> listarFaltasGarcom(Long garcomId);

    long calcularDiasDesdeUltimaFalta(Long garcomId);

	