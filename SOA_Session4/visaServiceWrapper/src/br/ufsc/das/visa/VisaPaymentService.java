package br.ufsc.das.visa;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;

@WebService()
public class VisaPaymentService {

	private PagamentoVisa broker = new PagamentoVisa();

	@WebMethod()
	public ResultadoPagamentoCartaoCredito efetuarPagamento(
			@WebParam(name = "requisicao") RequisicaoCartaoCredito requisicao) throws ProcessamentoPagamentoException {
		return broker.efetuarPagamento(requisicao);
	}
}
