package br.ufsc.das.sumservice.impl;

import javax.jws.WebService;

import br.ufsc.das.sumservice.SumService;

@WebService(serviceName = "SumService", endpointInterface = "br.ufsc.das.sumservice.SumService", targetNamespace = "http://das.ufsc.br/SumService/")
public class SumServiceImpl implements SumService {
	public float sum(float a, float b) {
		float v = a + b;
		System.out.println("Received sum request: " + a + " + " + b + " = " + v);
		return v;
	}
}
