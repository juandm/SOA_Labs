package br.ufsc.das.discovery;

import org.apache.camel.builder.RouteBuilder;

public class ProdServiceDiscovery extends RouteBuilder {

	/**
	 * The Camel route is configured via this method.  The from endpoint is required to be a SwitchYard service.
	 */
	public void configure() {
		// TODO Auto-generated method stub
		from("switchyard://ProductService")
			.log("Received message for 'ProductService' : ${body}")
			.process(new ServiceDiscoveryProcessor("productService"))
			.to("switchyard://DiscoveredProductService");
	}

}
