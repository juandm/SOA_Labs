package br.ufsc.das.discovery;

import org.apache.camel.builder.RouteBuilder;

import br.ufsc.das.discovery.ServiceDiscoveryProcessor;

public class SumServiceDiscovery extends RouteBuilder {

	/**
	 * The Camel route is configured via this method.  The from endpoint is required to be a SwitchYard service.
	 */
	public void configure() {
		// TODO Auto-generated method stub
		from("switchyard://SumService")
		.log("Received message for 'SumService' : ${body}")
		.process(new ServiceDiscoveryProcessor("sumService"))
		.to("switchyard://DiscoveredSumService");
	}

}
