package br.ufsc.das.productservice.impl;

import javax.jws.WebService;

import br.ufsc.das.productservice.ProductService;

@WebService(serviceName = "ProductService", endpointInterface = "br.ufsc.das.productservice.ProductService", targetNamespace = "http://das.ufsc.br/ProductService/")
public class ProductServiceImpl implements ProductService {
	public float product(float a, float b) {
		float v = a * b;
		System.out.println("Received product request: " + a + " * " + b + " = " + v);
		return v;
	}
}