package br.ufsc.das.discovery;

import org.apache.juddi.v3.client.config.ClientConfig;
import org.apache.juddi.v3.client.config.UDDIClerkManager;
import org.apache.juddi.v3.client.config.UDDINode;
import org.apache.juddi.v3.client.transport.Transport;
import org.uddi.v3_service.UDDIInquiryPortType;

public class UDDIAccessor {

	private UDDINode node;

	private UDDIInquiryPortType uddiInquiryService;	

	public UDDIAccessor() {

		try {

			UDDIClerkManager clerkManager = new UDDIClerkManager("META-INF/uddi.xml");

			clerkManager.start();

			ClientConfig clientConfig = clerkManager.getClientConfig();

			UDDINode node = clientConfig.getUDDINode("default");

			Transport transport = node.getTransport();

			uddiInquiryService = transport.getUDDIInquiryService();

		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	public UDDINode getNode() {
		return node;
	}

	public UDDIInquiryPortType getInquiryService() {
		return uddiInquiryService;
	}

}
