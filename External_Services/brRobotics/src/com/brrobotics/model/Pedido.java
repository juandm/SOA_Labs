package com.brrobotics.model;

public class Pedido {

	private Integer codigo;
	private String cliente;
	private String endereco;
	private String estado;

	private int qtdRoboSeguranca;
	private int qtdRoboDomestico;
	private int qtdRoboMedico;

	private Double totalRoboSeguranca;
	private Double totalRoboDomestico;
	private Double totalRoboMedico;

	private Double total;

	public Integer getCodigo() {
		return codigo;
	}

	public void setCodigo(Integer codigo) {
		this.codigo = codigo;
	}

	public String getCliente() {
		return cliente;
	}

	public void setCliente(String cliente) {
		this.cliente = cliente;
	}

	public String getEndereco() {
		return endereco;
	}

	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	public Double getTotal() {
		return total;
	}

	public void setTotal(Double total) {
		this.total = total;
	}

	public int getQtdRoboSeguranca() {
		return qtdRoboSeguranca;
	}

	public void setQtdRoboSeguranca(int qtdRoboSeguranca) {
		this.qtdRoboSeguranca = qtdRoboSeguranca;
	}

	public int getQtdRoboDomestico() {
		return qtdRoboDomestico;
	}

	public void setQtdRoboDomestico(int qtdRoboDomestico) {
		this.qtdRoboDomestico = qtdRoboDomestico;
	}

	public int getQtdRoboMedico() {
		return qtdRoboMedico;
	}

	public void setQtdRoboMedico(int qtdRoboMedico) {
		this.qtdRoboMedico = qtdRoboMedico;
	}

	public Double getTotalRoboSeguranca() {
		return totalRoboSeguranca;
	}

	public void setTotalRoboSeguranca(Double totalRoboSeguranca) {
		this.totalRoboSeguranca = totalRoboSeguranca;
	}

	public Double getTotalRoboDomestico() {
		return totalRoboDomestico;
	}

	public void setTotalRoboDomestico(Double totalRoboDomestico) {
		this.totalRoboDomestico = totalRoboDomestico;
	}

	public Double getTotalRoboMedico() {
		return totalRoboMedico;
	}

	public void setTotalRoboMedico(Double totalRoboMedico) {
		this.totalRoboMedico = totalRoboMedico;
	}

	@Override
	public String toString() {
		return "Pedido [codigo=" + codigo + ", cliente=" + cliente + ", endereco=" + endereco + ", estado=" + estado
				+ ", qtdRoboSeguranca=" + qtdRoboSeguranca + ", qtdRoboDomestico=" + qtdRoboDomestico
				+ ", qtdRoboMedico=" + qtdRoboMedico + ", totalRoboSeguranca=" + totalRoboSeguranca
				+ ", totalRoboDomestico=" + totalRoboDomestico + ", totalRoboMedico=" + totalRoboMedico + ", total="
				+ total + "]";
	}

}
