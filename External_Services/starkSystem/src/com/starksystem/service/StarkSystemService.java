package com.starksystem.service;

import javax.jws.WebParam;
import javax.jws.WebService;

import com.starksystem.dao.DAOPedido;
import com.starksystem.model.Pedido;

@WebService
public class StarkSystemService {

	private final DAOPedido dao = DAOPedido.getInstance();

	public Pedido inserirPedido(@WebParam(name = "pedido") Pedido pedido) {

		double totalReatorSolar = 2000 * pedido.getQtdReatorSolar();
		double totalReatorArk = 2500 * pedido.getQtdReatorArk();

		double total = totalReatorSolar + totalReatorArk;

		pedido.setTotalReatorSolar(totalReatorSolar);
		pedido.setTotalReatorArk(totalReatorArk);

		pedido.setTotal(total);

		pedido = dao.insert(pedido);

		System.out.println("[StarkSystemService] - " + pedido);

		return pedido;
	}
}