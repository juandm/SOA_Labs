package com.brrobotics.service;

import javax.jws.WebParam;
import javax.jws.WebService;

import com.brrobotics.dao.DAOPedido;
import com.brrobotics.model.Pedido;

@WebService
public class BrRoboticsService {

	private final DAOPedido dao = DAOPedido.getInstance();

	public Pedido inserirPedido(@WebParam(name = "pedido") Pedido pedido) {

		double totalRoboSeguranca = 1400 * pedido.getQtdRoboSeguranca();
		double totalRoboDomestico = 900 * pedido.getQtdRoboDomestico();
		double totalRoboMedico = 2300 * pedido.getQtdRoboMedico();

		double total = totalRoboSeguranca + totalRoboDomestico + totalRoboMedico;

		pedido.setTotalRoboSeguranca(totalRoboSeguranca);
		pedido.setTotalRoboDomestico(totalRoboDomestico);
		pedido.setTotalRoboMedico(totalRoboMedico);

		pedido.setTotal(total);

		pedido = dao.insert(pedido);

		System.out.println("[BrRoboticsService] - " + pedido);

		return pedido;

	}

}
