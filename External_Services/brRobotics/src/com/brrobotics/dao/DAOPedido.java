package com.brrobotics.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.brrobotics.model.Pedido;

public class DAOPedido {

	private static final DAOPedido INSTANCE = new DAOPedido();

	public static DAOPedido getInstance() {
		return INSTANCE;
	}

	private Connection openConnection() {
		Connection conn = null;
		try {
			Class.forName("org.sqlite.JDBC");

			String url = "jdbc:sqlite:C:/Users/SOA_PC/Desktop/SOA_Labs/External_Services/DBs/BrRobotics.db";
			conn = DriverManager.getConnection(url);
			
			if(conn == null){
            	throw new NullPointerException();
            }
			
			return conn;
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

			stmt = conn.prepareStatement(
					"INSERT INTO pedidos (CLIENTE, ENDERECO, ESTADO, QTD_ROBO_SEGURANCA, QTD_ROBO_DOMESTICO, QTD_ROBO_MEDICO, TOTAL_ROBO_SEGURANCA, TOTAL_ROBO_DOMESTICO, TOTAL_ROBO_MEDICO, TOTAL) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)", //
					Statement.RETURN_GENERATED_KEYS);

			stmt.setString(1, pedido.getCliente());
			stmt.setString(2, pedido.getEndereco());
			stmt.setString(3, pedido.getEstado());

			stmt.setInt(4, pedido.getQtdRoboSeguranca());
			stmt.setInt(5, pedido.getQtdRoboDomestico());
			stmt.setInt(6, pedido.getQtdRoboMedico());

			stmt.setDouble(7, pedido.getTotalRoboSeguranca());
			stmt.setDouble(8, pedido.getTotalRoboDomestico());
			stmt.setDouble(9, pedido.getTotalRoboMedico());
			stmt.setDouble(10, pedido.getTotal());

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
