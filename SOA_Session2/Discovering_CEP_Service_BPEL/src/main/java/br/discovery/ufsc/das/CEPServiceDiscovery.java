package br.discovery.ufsc.das;

import org.apache.camel.builder.RouteBuilder;

public class CEPServiceDiscovery extends RouteBuilder {

	public void configure() {
		from("switchyard://CEPService") //
				.log("Received message for 'CEPService' : ${body}") //
				.process(new ServiceDiscoveryProcessor("cepService1")) //
				.to("switchyard://DiscoveredCEPService");
	}

}
