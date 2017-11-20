package br.ufsc.das.visa.service;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;

import br.ufsc.das.visa.PagamentoVisa;
import br.ufsc.das.visa.ProcessamentoPagamentoException;
import br.ufsc.das.visa.RequisicaoCartaoCredito;
import br.ufsc.das.visa.ResultadoPagamentoCartaoCredito;

@WebService()
public class VisaPaymentService {

	private PagamentoVisa broker = new PagamentoVisa();

	@WebMethod()
	public ResultadoPagamentoCartaoCredito efetuarPagamento(
			@WebParam(name = "requisicao") RequisicaoCartaoCredito requisicao) throws ProcessamentoPagamentoException {
		return broker.efetuarPagamento(requisicao);
	}
}
