# Session 2 - SOA Lab

This document contains the information related to the exercises of session #2 of SOA Labs:

1. Configuring service registry (UDDI Server - [jUDDI])
2. Registering CEP service in UDDI.
3. Discovering SOAP Web Service registered in UDDI and invoking it in SwitchYard.
4. Invoking two services (Operation **_Choice_** === _***IF-ELSE*_ in BPEL)

___

### 1. Setup service registry jUDDI
**(The tutorial below is for information purposes, in the lab please USE the [provided UDDI] folder).**
Follow the tutorial in ([jUDDI-Setup]) for setup and run the service registry.

___

### 2. Registering a service in the UDDI server

#### Note:
>jUDDI offers an API that is divided into several "sets" (Web Services) representing a specific area of functionality. **For more information read [this](https://juddi.apache.org/docs/3.x/devguide/html/ch01.html#_uddi_and_juddi_api).**
>The web service used to query the registry is the **_"Inquiry service"_**, available in:
     http://localhost:8082/juddiv3/services/inquiry?wsdl

##### For registering a service in the UDDI node follow the steps below:

 **URL GUI:** http://localhost:8082/juddi-gui
 **login/password:** admin/admin

The steps 1 and 2 creates a UDDI area to represent the enterprise that will provide/publish the services (for this example DAS)

1) **Register Key Generator (do it only once)**
	- **Key:** 	uddi:das.ufsc.br:keygenerator
	- **Name:**	DAS Key Generator 

2) **Register Business (do it only once)**
	- **Key:** 	uddi:das.ufsc.br:repository
	- **Name:** 	DAS Repository

3) **Create T-Model to categorize registered services (do it only once)**
	- **Key:**	uddi:das.ufsc.br:servicecategory
	- **Name:**	Service Category

4) **Register service (Create --> Register Services for WSDL)**
	- Put the location of the service's WSDL
	Choose the Business registered in step 1	
	**WSDL:** https://ws.homologacao.ufsc.br/services/CEPService?wsdl
	**User/Password:** admin/admin
	
5) **Set service category to the category created in step 3.** 
(_Discover --> Services --> Choose the service from the list --> Categories --> Add Key Reference --> Picker --> Choose T-Model **"uddi:das.ufsc.br:servicecategory"** --> fill input data --> save_).

	- **T-Model Key:**	uddi:das.ufsc.br:servicecategory
	- **Name:**		    das.ufsc.br:servicecategory
	- **Value:**			Is the category name _(cepService)_


All the configurations made in last steps are saved to the UDDI database that is located in the folder **"YOUR\UDDI\DIRECTORY\juddi-tomcat-3.3.2\bin\target"**:
- Delete this folder means "clean" the UDDI.
- This foldercan be replaced with a ready to use database (Folder "target" available in the lesson files).

#### Find services in UDDI usign jUDDI web service API

1. **find_service:**

    Request example using inquiry UDDI service:
    ```xml
    <soapenv:Envelope xmlns:soapenv="http://schemas.xmlsoap.org/soap/envelope/" xmlns:urn="urn:uddi-org:api_v3">
       <soapenv:Header/>
       <soapenv:Body>
          <urn:find_service>
             <urn:categoryBag>
                <urn:keyedReference tModelKey="uddi:das.ufsc.br:servicecategory" keyName="das.ufsc.br:servicecategory" keyValue="cepService"/>
             </urn:categoryBag>
          </urn:find_service>
       </soapenv:Body>
    </soapenv:Envelope>
    ```

    Response example
    ```xml
    <soap:Envelope xmlns:soap="http://schemas.xmlsoap.org/soap/envelope/">
       <soap:Body>
          <ns2:serviceList truncated="false" xmlns:ns11="urn:uddi-org:vs_v3" xmlns:ns10="urn:uddi-org:policy_v3_instanceParms" xmlns:ns9="urn:uddi-org:repl_v3" xmlns:ns8="urn:uddi-org:custody_v3" xmlns:ns7="urn:uddi-org:policy_v3" xmlns:ns6="urn:uddi-org:vscache_v3" xmlns:ns5="urn:uddi-org:subr_v3" xmlns:ns4="urn:uddi-org:sub_v3" xmlns:ns3="http://www.w3.org/2000/09/xmldsig#" xmlns:ns2="urn:uddi-org:api_v3">
             <ns2:listDescription>
                <ns2:includeCount>1</ns2:includeCount>
                <ns2:actualCount>1</ns2:actualCount>
                <ns2:listHead>1</ns2:listHead>
             </ns2:listDescription>
             <ns2:serviceInfos>
                <ns2:serviceInfo serviceKey="uddi:das.ufsc.br:service_cepservicesoapservice" businessKey="uddi:das.ufsc.br:repository">
                   <ns2:name xml:lang="en">CEPServiceSoapService</ns2:name>
                </ns2:serviceInfo>
             </ns2:serviceInfos>
          </ns2:serviceList>
       </soap:Body>
    </soap:Envelope>
    ```

2) UDDI service `get_serviceDetail` (use `serviceKey(s)` of response `find_service`)
    Response example
    ```xml
    <soapenv:Envelope xmlns:soapenv="http://schemas.xmlsoap.org/soap/envelope/" xmlns:urn="urn:uddi-org:api_v3">
       <soapenv:Header/>
       <soapenv:Body>
          <urn:get_serviceDetail>
             <urn:serviceKey>uddi:das.ufsc.br:service_cepservicesoapservice</urn:serviceKey> <!-- pode haver varias linhas dessa dependendo da quantidade de serviceKeys retornados -->		 
          </urn:get_serviceDetail>
       </soapenv:Body>
    </soapenv:Envelope>
    ```
    Response (example).
    ```xml
    <soap:Envelope xmlns:soap="http://schemas.xmlsoap.org/soap/envelope/">
       <soap:Body>
          <ns2:serviceDetail xmlns:ns11="urn:uddi-org:vs_v3" xmlns:ns10="urn:uddi-org:policy_v3_instanceParms" xmlns:ns9="urn:uddi-org:repl_v3" xmlns:ns8="urn:uddi-org:custody_v3" xmlns:ns7="urn:uddi-org:policy_v3" xmlns:ns6="urn:uddi-org:vscache_v3" xmlns:ns5="urn:uddi-org:subr_v3" xmlns:ns4="urn:uddi-org:sub_v3" xmlns:ns3="http://www.w3.org/2000/09/xmldsig#" xmlns:ns2="urn:uddi-org:api_v3">
             <ns2:businessService serviceKey="uddi:das.ufsc.br:service_cepservicesoapservice" businessKey="uddi:das.ufsc.br:repository">
                <ns2:name xml:lang="en">CEPServiceSoapService</ns2:name>
                <ns2:description xml:lang="en">Default service description when no &lt;wsdl:document> element is defined inside the &lt;wsdl:service> element.</ns2:description>
                <ns2:bindingTemplates>
                   <ns2:bindingTemplate bindingKey="uddi:das.ufsc.br:binding_ws.homologacao.ufsc.br_cepservicesoapservice_cepserviceport_443" serviceKey="uddi:das.ufsc.br:service_cepservicesoapservice">
                      <ns2:description xml:lang="en">Default binding description when no &lt;wsdl:document> element is defined inside the &lt;wsdl:binding> element.</ns2:description>
                      <ns2:accessPoint useType="endPoint">https://ws.homologacao.ufsc.br/services/CEPService</ns2:accessPoint>
                      <ns2:tModelInstanceDetails>
                         <ns2:tModelInstanceInfo tModelKey="uddi:das.ufsc.br:cepservicesoapservicesoapbinding">
                            <ns2:description xml:lang="en">The wsdl:binding that this wsdl:port implements. Default binding description when no &lt;wsdl:document> element is defined inside the &lt;wsdl:binding> element. The instanceParms specifies the port local name.</ns2:description>
                            <ns2:instanceDetails>
                               <ns2:instanceParms>CEPServicePort</ns2:instanceParms>
                            </ns2:instanceDetails>
                         </ns2:tModelInstanceInfo>
                         <ns2:tModelInstanceInfo tModelKey="uddi:das.ufsc.br:cepservice">
                            <ns2:description xml:lang="en">The wsdl:portType that this wsdl:port implements.</ns2:description>
                         </ns2:tModelInstanceInfo>
                         <ns2:tModelInstanceInfo tModelKey="uddi:uddi.org:protocol:soap"/>
                         <ns2:tModelInstanceInfo tModelKey="uddi:uddi.org:protocol:serverauthenticatedssl3"/>
                      </ns2:tModelInstanceDetails>
                      <ns2:categoryBag>
                         <ns2:keyedReference tModelKey="uddi:uddi.org:categorization:types" keyName="uddi-org:types:wsdl" keyValue="wsdlDeployment"/>
                      </ns2:categoryBag>
                   </ns2:bindingTemplate>
                </ns2:bindingTemplates>
                <ns2:categoryBag>
                   <ns2:keyedReference tModelKey="uddi:uddi.org:wsdl:types" keyName="uddi-org:wsdl:types" keyValue="service"/>
                   <ns2:keyedReference tModelKey="uddi:uddi.org:xml:namespace" keyName="uddi-org:xml:namespace" keyValue="http://interfaces.cep.services.ufsc.br/"/>
                   <ns2:keyedReference tModelKey="uddi:das.ufsc.br:servicecategory" keyName="das.ufsc.br:servicecategory" keyValue="cepService"/>
                   <ns2:keyedReference tModelKey="uddi:uddi.org:xml:localname" keyName="uddi-org:xml:localName" keyValue="CEPServiceSoapService"/>
                </ns2:categoryBag>
             </ns2:businessService>
          </ns2:serviceDetail>
       </soap:Body>
    </soap:Envelope>
    ```
This XML fragment shows the endpoint (wsdl port) for access the service.
```xml
<ns2:accessPoint useType="endPoint">https://ws.homologacao.ufsc.br/services/CEPService</ns2:accessPoint> 
```

___

### 3. Discovering and Invoking External SOAP Service Project
This example shows necessary code snippets and the steps that should be made in order to discover a SOAP web service registered in jUDDI.

The service to be discovered is the same that was used in session 1, is used to find Brazilian CEP information and is provided by **UFSC**.

The service endpoint is:
> https://ws.homologacao.ufsc.br/services/CEPService

The service description is:
> https://ws.homologacao.ufsc.br/services/CEPService?wsdl

Remember to initialize the input variable in the **_BPEL assign bolck_** using the following code snippet for **_CEP service partnerLink_** in BPEL file:
```xml
<int:getCepInfo xmlns:int="http://interfaces.cep.services.ufsc.br/">
	<cep/>
</int:getCepInfo>
```
In order to support UDDI discovering in the Switchyard project the following modifications should to be made:

- Modify **"pom.xml"** file to support UDDI dependency.

    Validate that compiler level is in 1.8:

    ```xml
    <maven.compiler.source>1.8</maven.compiler.source>
    <maven.compiler.target>1.8</maven.compiler.target>
    .....
    <dependencies>
    .....
	    <dependency>
		<groupId>org.apache.juddi</groupId>
		<artifactId>juddi-client</artifactId>
	    </dependency>
    .....
    </dependencies>
    <build>
        <plugins>
            .......
	        <plugin>
		        <groupId>org.apache.maven.plugins</groupId>
		        <artifactId>maven-jar-plugin</artifactId>
			<configuration>
			    <archive>
			       <manifestEntries>
				    <Dependencies>deployment.juddi-uddi-client-1.0.0.jar</Dependencies>
			       </manifestEntries>
			    </archive>
			</configuration>
	        </plugin>
            .......
	<plugins>
    </build>
    ```
- Copy the **"uddi.xml"** file to **"src/main/resources/META-INF"**.
- Unzip files of **"switchyard-discovery-java.zip"** to **"src/main/java"**.
- Modify service WSDL's (external services to be invoked) to state that support **WS-Addressing**:
     ```xml
    <wsdl:binding ...>
    <!-- START: fragmento ws-addresing -->
    <wsaw:UsingAddressing wsdl:required="false" xmlns:wsaw="http://www.w3.org/2006/05/addressing/wsdl" />
    <!-- END: fragmento ws-addresing -->
    ........
    <wsdl:binding/>
     ```
- For this example of **_CEP Service_** the class `CEPServiceDiscovery.java` is responsible to discover services of this category. The service category that will be registered as `T-Model` is "`cepService`".

- Edit Switchyard project file (switchyard.xml) creating a Java camel route that will do the discovery invoking the code provided in **"switchyard-discovery-java.zip"**.

- Be sure that the library **"juddi-uddi-client-1.0.0.jar"** is in:
	>**{YOUR/SERVER/INSTALATION/PATH}**/EAP/instalation/folder/standalone/deployments

**Suggestion:** to find this folder go to the tab _**Servers**_ in JBoss Developer Studio, rigth click on the server, go to _**Show In**_ and click in _**File Browser**_ option.


___

### 4. Invoking two services (Project name: _Add_Multiply_ChoiceBPEL_)
To run this example is necessary to deploy the two web service projects that are going to be invoked, these can be found in the `External_Services` folder, the projects are:
- _**sumService**_
- _**productService**_


#### Code Snnipets
- **SwitchYard**
    - **Group Id:** `br.ufsc.das`
    - **Namespace:** `http://das.ufsc.br`
    - **target Id:** `br.ufsc.das`
    
- **BPEL**
 
    Xpath expressions code snippets for the **`choice`** block in the BPEL file:
    - **Sum flow**
        ```javascript
        $processRequest.parameters/operation = "add" or $processRequest.parameters/operation = "sum"
        ```
    - **Product flow**
        ```javascript
        $processRequest.parameters/operation = "product" or $processRequest.parameters/operation = "multiply"
        ```
    - **Unsuported operation flow**
        Assign error variable message when unsuported operation is sent in the request
        ```javascript
        concat($processRequest.parameters/operation," is not a supported arithmetic operation")
        ```
- **deploy.xml**
    ```xml
    <?xml version="1.0" encoding="UTF-8"?>
    <deploy xmlns="http://www.apache.org/ode/schemas/dd/2007/03" xmlns:das.ufsc.br="http://das.ufsc.br">
      <process name="das.ufsc.br:CalculatorProcess">
        <process-events generate="all"/>
        <!-- BPEL process service -->
        <provide partnerLink="CalculatorProcess">
          <service name="das.ufsc.br:CalculatorProcess" port="ignored"/>
        </provide>
        <!-- External services -->
        <invoke partnerLink="Sum_Service">
          <service name="das.ufsc.br:SumService" port="ignored"/>
        </invoke>
        <invoke partnerLink="Prod_Service">
        	<service name="das.ufsc.br:ProductService" port="ignored"/>
        </invoke>
      </process>
    </deploy>
    ```


   [jUDDI]: <https://juddi.apache.org/>
   [jUDDI-Setup]:<https://gist.github.com/juandm/d9fae604368a3a9fcb549fd8db4294ba>
   [jUDDI-RegisterService]:<https://gist.github.com/juandm/d9fae604368a3a9fcb549fd8db4294ba>
   [provided UDDI]: <https://drive.google.com/open?id=0B4PAsBMomKsjYUJIWjVaQVF5cEk>