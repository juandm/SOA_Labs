# Session 5 - SOA Lab

Information related to session #5 of SOA Labs.

This session shows how to create a web portal to invoke the bpel process created in Session 3 (**Order_Simulation_BPEL**).

### 1. Creating a portal to invoke the bpel process (Project name: _Parallel_Invocation_BPEL_)

- **Create the BPEL Process**
    For this exercise use the project created in [Session 3, project 2]

- **Create the Web site**
    - Create a new **Dynamic Web Project** and name it _**OrderPortal**_.
    - Copy the WSDL of the Order process into `src` folder.
    - Create HTML page in the folder **WebContent** name it **_index.html_** and copy the following snippet in the file.
        
        ```html
        <!DOCTYPE html>
        <html>
        <head>
        <meta charset="ISO-8859-1">
        <title>Order Process</title>
        </head>
        <body>
        	<h1>Order Process</h1>
        	<hr>
        	<p>
        		Please fill the fields and click buy<br>
        	</p>
        	<form action="OrderProcess" method="post">
        		Name:
        		<input type="text" name="clientName" size="30"><br>
        		CEP:
        		<input type="text" name="cep" size="30"><br>
        		Quantity:
        		<input type="text" name="quantity" size="30"><br>
        		<hr>
        		<input type="submit" value="Buy"> <input type="reset"
        			value="Clean">
        	</form>
        
        </body>
        </html>
        ```
    - Create the web service client
    
        To create the web service client open a **cmd console** in the path of **EAP server installation**. (**Tip:** To find the folder. In the tab **_"Servers"_** choose the server, rigth click on it, and choose _**"Show in >> File Browser"**_ option).
        
        Navigate to the `bin` folder and execute the following command _(adjust the command to the local paths)_.
	
        ```
        YOUR\PATH\EAP-6.4.0\bin>wsconsume.bat -k -p br.ufsc.das.wsclient YOUR\PATH\TO\WORKSPACE\OrderPortal\src\OrderProcess.wsdl
        ```
       The client code is generated in the `output` folder inside `EAP server` path, copy the generated files into `scr` folder in `OrderPortal` project. 

    - Create a package in `src` folder and name it **_br.ufsc.das.controller_**.
    - Create a `Servlet` inside the package and name it **_OrderProcessServ_**.
    
        Modify the servlet code with the following **snnipets**:
        ```java
        import br.ufsc.das.wsclient.*;
        ```
        Verify that `WebServlet` annotation is set to _**"OrderProcess"**_
        ```java
        @WebServlet("/OrderProcess")
        ```
        Declare the `orderProcesService` variable.
        ```java
        private OrderProcess orderProcesService;
        ```
        
        Create the `getOrderProcessService()` method:
        ```java
        public OrderProcess getOrderProcessService() {
    	    if(this.orderProcesService == null) {
    		    this.orderProcesService = new OrderProcess_Service().getOrderProcessSOAP();
    	    }
    	    return this.orderProcesService ;
        }
        ```
        Modify the constructor adding:
        ```java
        public OrderProcessServ() {
            super();
            this.orderProcesService = getOrderProcessService(); //Add this line
        }
        ```
        In the `DoPost` method paste:
        ```java
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
        ```
		
### IMPORTANT NOTE
If you are importing this project you should change the **PATH of OrderProcess WSDL** in the file `OrderProcess_Service` to match your path in **lines 18, 29, 33**

[Session 3, project 2]: <https://github.com/juandm/SOA_Labs/tree/master/SOA_Session3/Order_Simulation_BPEL>