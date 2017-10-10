package br.ufsc.das.visa;

import java.io.Serializable;

public class ResultadoPagamentoCartaoCredito implements Serializable {

	private static final long serialVersionUID = 1L;

	private String autorizacao;

	private float valor;

	public ResultadoPagamentoCartaoCredito() {

	}

	public ResultadoPagamentoCartaoCredito(String autorizacao, float valor) {
		this.autorizacao = autorizacao;
		this.valor = valor;
	}

	public String getAutorizacao() {
		return autorizacao;
	}

	public void setAutorizacao(String autorizacao) {
		this.autorizacao = autorizacao;
	}

	public float getValor() {
		return valor;
	}

	public void setValor(float valor) {
		this.valor = valor;
	}

	@Override
	public String toString() {
		return "autorizacao=" + autorizacao + ", valor=" + valor;
	}

}
