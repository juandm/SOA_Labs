package br.ufsc.das.visa;

public class ProcessamentoPagamentoException extends Exception {

	private static final long serialVersionUID = 1L;

	private int codigoErro;

	public ProcessamentoPagamentoException(int codigoErro, String mensagem) {
		super(mensagem);
		this.codigoErro = codigoErro;
	}

	public int getCodigoErro() {
		return codigoErro;
	}

	@Override
	public String toString() {
		return "ERRO " + codigoErro + ": " + getMessage();
	}

}
