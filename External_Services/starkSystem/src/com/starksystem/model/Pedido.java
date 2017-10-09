package com.starksystem.model;

public class Pedido {

	private Integer codigo;
	private String cliente;
	private String endereco;
	private String estado;

	private int qtdReatorSolar;

	private int qtdReatorArk;

	private Double totalReatorSolar;

	private Double totalReatorArk;

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

	public int getQtdReatorSolar() {
		return qtdReatorSolar;
	}

	public void setQtdReatorSolar(int qtdReatorSolar) {
		this.qtdReatorSolar = qtdReatorSolar;
	}

	public int getQtdReatorArk() {
		return qtdReatorArk;
	}

	public void setQtdReatorArk(int qtdReatorArk) {
		this.qtdReatorArk = qtdReatorArk;
	}

	public Double getTotalReatorSolar() {
		return totalReatorSolar;
	}

	public void setTotalReatorSolar(Double totalReatorSolar) {
		this.totalReatorSolar = totalReatorSolar;
	}

	public Double getTotalReatorArk() {
		return totalReatorArk;
	}

	public void setTotalReatorArk(Double totalReatorArk) {
		this.totalReatorArk = totalReatorArk;
	}

	@Override
	public String toString() {
		return "Pedido [codigo=" + codigo + ", cliente=" + cliente + ", endereco=" + endereco + ", estado=" + estado
				+ ", qtdReatorSolar=" + qtdReatorSolar + ", qtdReatorArk=" + qtdReatorArk + ", totalReatorSolar="
				+ totalReatorSolar + ", totalReatorArk=" + totalReatorArk + ", total=" + total + "]";
	}

}
