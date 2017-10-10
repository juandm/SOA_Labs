package br.ufsc.das.visa;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class PagamentoVisa {

	private static final String ERROR_PREFIX = "ERROR";

	public ResultadoPagamentoCartaoCredito efetuarPagamento(RequisicaoCartaoCredito requisicao) throws ProcessamentoPagamentoException {

		System.out.println("Recebendo req de pagamento --> " + requisicao);

		String serviceAddr = "127.0.0.1";
		int servicePort = 8900;

		try (Socket socket = new Socket(serviceAddr, servicePort)) {

			BufferedReader input = new BufferedReader(new InputStreamReader(socket.getInputStream()));

			PrintWriter output = new PrintWriter(socket.getOutputStream(), true);

			String resp = receive(input);

			send(output, "PAY");
			resp = receive(input);
			throwExceptionIfError(resp);

			send(output, requisicao.getNumero().toString());
			resp = receive(input);
			throwExceptionIfError(resp);

			send(output, requisicao.getNome());
			resp = receive(input);
			throwExceptionIfError(resp);

			send(output, requisicao.getDataVencimento());
			resp = receive(input);
			throwExceptionIfError(resp);

			send(output, String.valueOf(requisicao.getValor()));
			resp = receive(input);
			throwExceptionIfError(resp);

			send(output, "COMMIT");
			resp = receive(input);
			throwExceptionIfError(resp);

			resp = receive(input);
			throwExceptionIfError(resp);

			// a3ba892b-0569-4ae4-9a36-3494406c2525:ROQUE BEZERRA:3456:12/2020:252.43
			// Identificador da transa��o;
			// Nome da pessoa;
			// �ltimos seis d�gitos de cart�o;
			// Data de validade do cart�o
			// Valor da transa��o
			// Valor da transa��o com desconto

			String[] parts = resp.split(":");

			String codigoTransacao = parts[0];
			float valor = Float.parseFloat(parts[5]);

			return new ResultadoPagamentoCartaoCredito(codigoTransacao, valor);

		} catch (IOException e) {
			System.out.println("Erro se comunicando com o servi�o --> " + e.getMessage());
			throw new ProcessamentoPagamentoException(3006, "Sistema indispon�vel: " + e.getMessage());
		}

	}

	private static void send(PrintWriter output, String msg) {
		System.out.println("send--> " + msg);
		output.println(msg);
	}

	private static String receive(BufferedReader input) throws IOException {
		String msg = input.readLine();
		System.out.println("receive--> " + msg);
		return msg;
	}

	private void throwExceptionIfError(String resp) throws ProcessamentoPagamentoException {

		if (resp.startsWith(ERROR_PREFIX)) {

			int codigoErro = Integer.parseInt(resp.substring(ERROR_PREFIX.length() + 1));

			String mensagem = "";

			switch (codigoErro) {

			case 3000:
				mensagem = "Comando inv�lido";
				break;

			case 3001:
				mensagem = "N�mero de cart�o de cr�dito inv�lido";
				break;

			case 3002:
				mensagem = "Cart�o de cr�dito n�o � da bandeira Visa";
				break;

			case 3003:
				mensagem = "Nome da pessoa inv�lido";
				break;

			case 3004:
				mensagem = "Data de expira��o do cart�o de cr�dito inv�lida";
				break;

			case 3005:
				mensagem = "Valor da transa��o inv�lido";
				break;

			case 3006:
				mensagem = "Sistema indispon�vel";
				break;

			}
			throw new ProcessamentoPagamentoException(codigoErro, mensagem);
		}

	}
}
