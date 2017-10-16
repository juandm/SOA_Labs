package br.ufsc.das.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.ufsc.das.wsclient.AcceptOrderFault_Exception;
import br.ufsc.das.wsclient.Order;
import br.ufsc.das.wsclient.OrderConfirmation;
import br.ufsc.das.wsclient.OrderProcess;
import br.ufsc.das.wsclient.OrderProcess_Service;

/**
 * Servlet implementation class OrderProcessServ
 */
@WebServlet("/OrderProcess")
public class OrderProcessServ extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private OrderProcess orderProcesService;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public OrderProcessServ() {
        super();
        this.orderProcesService = getOrderProcessService();
    }
    
    public OrderProcess getOrderProcessService() {
    	if(this.orderProcesService == null) {
    		this.orderProcesService = new OrderProcess_Service().getOrderProcessSOAP();
    	}
    	return this.orderProcesService ;
    }
    
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		Order order = new Order();
		order.setCep(request.getParameter("cep"));
		order.setClientName(request.getParameter("clientName"));
		order.setQuantity(Integer.parseInt(request.getParameter("quantity")));
		
		OrderConfirmation confirmation;
		PrintWriter writer = response.getWriter();
		
		try {
			confirmation = getOrderProcessService().acceptOrder(order);
			response.setContentType("text/plain");
			
			writer.write("Name: " + confirmation.getClientName() + "\n");
			writer.write("----------- Shipping Info ------------\n");
			writer.write("Address: " + confirmation.getClientAddress() + "\n");
			writer.write("City: " + confirmation.getClientCity() + "\n");
			writer.write("State: " + confirmation.getClientState() + "\n");
			writer.write("CEP: " + order.getCep() + "\n");
			writer.write("----------- Order Info ------------\n");
			writer.write("Quantity: " + confirmation.getQuantity() + "\n");
			writer.write("Total: " + confirmation.getTotal() + "\n");
			
		} catch (AcceptOrderFault_Exception e) {
			writer.write("-------- Error processing the order --------\n");
		}
		
	}

}
