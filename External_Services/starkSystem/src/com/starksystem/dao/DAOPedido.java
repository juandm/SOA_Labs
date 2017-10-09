package com.starksystem.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.starksystem.model.Pedido;

public class DAOPedido {

	private static final DAOPedido INSTANCE = new DAOPedido();

	public static DAOPedido getInstance() {
		return INSTANCE;
	}

	private Connection openConnection() {

		try {
			Class.forName("org.sqlite.JDBC");

			String url = "jdbc:sqlite:C:/Users/SOA_PC/Desktop/SOA_Labs/External_Services/DBs/StarkSystem.db";
			return DriverManager.getConnection(url);

		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}

	}

	public Pedido insert(Pedido pedido) {

		Connection conn = null;

		PreparedStatement stmt = null;

		try {

			conn = openConnection();

			// Passando os campos para UPPERCASE
			pedido.setCliente(pedido.getCliente() != null ? pedido.getCliente().toUpperCase() : null);
			pedido.setEndereco(pedido.getEndereco() != null ? pedido.getEndereco().toUpperCase() : null);

			stmt = conn.prepareStatement(
					"INSERT INTO pedidos (CLIENTE, ENDERECO, ESTADO, QTD_REATOR_SOLAR, QTD_REATOR_ARK, TOTAL_REATOR_SOLAR, TOTAL_REATOR_ARK, TOTAL) VALUES (?, ?, ?, ?, ?, ?, ?, ?)", //
					Statement.RETURN_GENERATED_KEYS);

			stmt.setString(1, pedido.getCliente());
			stmt.setString(2, pedido.getEndereco());
			stmt.setString(3, pedido.getEstado());
			stmt.setInt(4, pedido.getQtdReatorSolar());
			stmt.setInt(5, pedido.getQtdReatorArk());
			stmt.setDouble(6, pedido.getTotalReatorSolar());
			stmt.setDouble(7, pedido.getTotalReatorArk());
			stmt.setDouble(8, pedido.getTotal());

			if (stmt.executeUpdate() == 1) {

				// Obtendo o id incremental...
				try (ResultSet rs = stmt.getGeneratedKeys()) {
					if (rs.next()) {
						pedido.setCodigo(rs.getInt(1));
					}
				}

				return pedido;
			} else {
				return null;
			}

		} catch (SQLException e) {
			System.out.println("Error: " + e.getMessage());
			throw new RuntimeException(e);
		} finally {
			if (stmt != null) {
				try {
					stmt.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}

			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}

	}
}
