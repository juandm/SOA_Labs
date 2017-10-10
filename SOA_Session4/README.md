# Session 4 - SOA Lab

Information related to exercises of session #4 of SOA Labs.

1. Setup of web services to be used in the assigment, projects (**brRobotics** and **starkSystem**).
2. Wrapper for _"legacy payment application"_.

### 1.  Setup of WS
The services are locate in the folder **`External_Services/`**. 

Use **`SQLite Studio`** to run the scripts below and create the databases used by the services, or just copy/paste the databases provided in the folder **"`Executable_Files/Partners Services DBs/`"**

- **Databases**
Create table script for **`BrRobotics`** DB. 

     ```sql
     CREATE TABLE PEDIDOS (
        CODIGO               INTEGER       PRIMARY KEY AUTOINCREMENT,
        CLIENTE              VARCHAR (40)  NOT NULL,
        ENDERECO             VARCHAR (100) NOT NULL,
        ESTADO               VARCHAR (20)  NOT NULL,
        QTD_ROBO_SEGURANCA   INTEGER       NOT NULL,
        QTD_ROBO_DOMESTICO   INTEGER       NOT NULL,
        QTD_ROBO_MEDICO      INTEGER       NOT NULL,
        TOTAL_ROBO_SEGURANCA REAL          NOT NULL,
        TOTAL_ROBO_DOMESTICO REAL          NOT NULL,
        TOTAL_ROBO_MEDICO    REAL          NOT NULL,
        TOTAL                REAL          NOT NULL
    );
     ```
 
    Create table script for **`StarkSystem`** DB. 
     ```sql
    CREATE TABLE PEDIDOS (
        CODIGO               INTEGER       PRIMARY KEY AUTOINCREMENT,
        CLIENTE              VARCHAR (40)  NOT NULL,
        ENDERECO             VARCHAR (100) NOT NULL,
        ESTADO               VARCHAR (20)  NOT NULL,
        QTD_REATOR_SOLAR     INTEGER       NOT NULL,
        QTD_REATOR_ARK       INTEGER       NOT NULL,
        TOTAL_REATOR_SOLAR   REAL          NOT NULL,
        TOTAL_REATOR_ARK     REAL          NOT NULL,
        TOTAL                REAL          NOT NULL
    );
     ```
 - After create the data bases, in both projects modify the **url connection** (_line 25_) in the file  `DAOPedido.java`.

    ```java
    String url = "jdbc:sqlite:C:/YOUR/PATH/TO/DBs/BrRobotics.db";
    ```
    
 - Requests for test the WS
    
    **BrRobotics**
    ```xml
    <soapenv:Envelope xmlns:soapenv="http://schemas.xmlsoap.org/soap/envelope/" xmlns:ser="http://service.brrobotics.com/">
       <soapenv:Header/>
       <soapenv:Body>
          <ser:inserirPedido>
             <pedido>
                <cliente>Fulano BrRobotics</cliente>
                <codigo>98765</codigo>
                <endereco>Rua 567</endereco>
                <estado>RJ</estado>
                <qtdRoboDomestico>1</qtdRoboDomestico>
                <qtdRoboMedico>33</qtdRoboMedico>
                <qtdRoboSeguranca>3</qtdRoboSeguranca>
             </pedido>
          </ser:inserirPedido>
       </soapenv:Body>
    </soapenv:Envelope>
    ```

    **StarkSystem**
    
    ```xml
    <soapenv:Envelope xmlns:soapenv="http://schemas.xmlsoap.org/soap/envelope/" xmlns:ser="http://service.starksystem.com/">
       <soapenv:Header/>
       <soapenv:Body>
          <ser:inserirPedido>
             <pedido>
                <cliente>Fulano</cliente>
                <codigo>12312312</codigo>
                <endereco>Rua Stark 123</endereco>
                <estado>SC</estado>
                <qtdReatorArk>4</qtdReatorArk>
                <qtdReatorSolar>5</qtdReatorSolar>
             </pedido>
          </ser:inserirPedido>
       </soapenv:Body>
    </soapenv:Envelope>
    ```
 
### 2.  Wrapper for legacy app

This example shows how to expose a legacy app (_using propietary protocols and data format_) as a SOAP web service, the approach used to build the service is **bottom/up**.

 - Run the **_run-visapayment-service-1.0.0.bat_** file located in **"_Executable_Files/Payments/visapayment-service.zip_"**, the app will be listening in localhost port **8089**.

- The service wrapper code used to exchange information with this app is provided in the project "_**visaServiceWrapper**_"
- To test the wrapper just made a request _(could be with SoapUI)_ to the service endpoint with valid data.

     #### **Request Example**

     ```xml
     <soapenv:Envelope xmlns:soapenv="http://schemas.xmlsoap.org/soap/envelope/" xmlns:visa="http://visa.das.ufsc.br/">
        <soapenv:Header/>
        <soapenv:Body>
           <visa:efetuarPagamento>
              <requisicao>
                 <dataVencimento>12/2020</dataVencimento>
                 <nome>FULANO</nome>
                 <numero>4234567890123456</numero>
                 <valor>252.43</valor>
              </requisicao>
           </visa:efetuarPagamento>
        </soapenv:Body>
     </soapenv:Envelope>
     ```
     
     #### **Response Example**

     ```xml
     <soap:Envelope xmlns:soap="http://schemas.xmlsoap.org/soap/envelope/">
        <soap:Body>
           <ns2:efetuarPagamentoResponse xmlns:ns2="http://visa.das.ufsc.br/">
              <return>
                 <autorizacao>cdd85455-e100-404f-95b0-a9da11749949</autorizacao>
                 <valor>239.81</valor>
              </return>
           </ns2:efetuarPagamentoResponse>
        </soap:Body>
     </soap:Envelope>
     ```