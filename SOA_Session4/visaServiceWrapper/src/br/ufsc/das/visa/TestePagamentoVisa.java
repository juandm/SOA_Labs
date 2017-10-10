package br.ufsc.das.visa;

public class TestePagamentoVisa {

	public static void main(String[] args) throws ProcessamentoPagamentoException {

		PagamentoVisa broker = new PagamentoVisa();

		RequisicaoCartaoCredito requisicao = new RequisicaoCartaoCredito();

		requisicao.setNome("ROQUE O BEZERRA");
		requisicao.setNumero(4123456789012345L);
		requisicao.setDataVencimento("12/2020");
		requisicao.setValor("30.50");

		ResultadoPagamentoCartaoCredito resultado = broker.efetuarPagamento(requisicao);

		System.out.println(resultado);

	}

}
