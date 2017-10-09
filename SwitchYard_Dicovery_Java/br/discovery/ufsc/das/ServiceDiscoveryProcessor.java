package br.discovery.ufsc.das;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.switchyard.component.soap.util.SOAPUtil;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

public class ServiceDiscoveryProcessor implements Processor {

	private static final DocumentBuilderFactory xmlFactory = DocumentBuilderFactory.newInstance();

	private String serviceCategory;

	public ServiceDiscoveryProcessor(String serviceCategory) {
		this.serviceCategory = serviceCategory;
	}

	@Override
	public void process(Exchange exchange) throws Exception {
		String endpointUri = discoverServiceEndpoint();
		defineEndpoint(exchange, endpointUri);
	}

	protected String discoverServiceEndpoint() throws Exception {
		return ServiceDiscoveryHelper.discoverServiceEndpoint(serviceCategory);
	}

	private void defineEndpoint(Exchange exchange, String endpointUri) {
		Element wsaToElement = getWsaToXmlElement(endpointUri);
		exchange.getIn().setHeader(SOAPUtil.WSA_TO_STR, wsaToElement);
	}

	private static Element getWsaToXmlElement(String endpointUri) {
		try {

			DocumentBuilder dBuilder = xmlFactory.newDocumentBuilder();

			Document doc = dBuilder.newDocument();

			// https://docs.oracle.com/cd/E24329_01/web.1211/e24965/wsaddressing.htm
			// https://www.w3.org/TR/ws-addr-wsdl/
			// Specific header to change the SOAP endpoint dynamically
			Element e = doc.createElementNS(SOAPUtil.WSA_URI, "To");
			e.appendChild(doc.createTextNode(endpointUri));

			return e;

		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

}
