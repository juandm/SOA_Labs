# Session 1 - SOA Lab

Information related to exercises of session #1 of SOA Labs.

1. HelloWordl BPEL - Switchyard
2. Calling external SOAP Web Service

## Prerequisites
You should have installed all software listed in [previous step][step0].

#### 1. HelloWorld Project

- **Creating a new SwitchYard project**

    In JBoss Developer Studio (JBDS) click on: 
    >**File->New Project-> Other...-> SwitchYard-> SwitchYard Project**

- **Project Structure**
    - _Project_Name_
        -   _src/main/java_
            -  _**JAVA Packages/Code**_ 
        -   _src/main/resources_
            -   _META-INF_
                -  _**SwitchYard.xml** (Project definition)_
            - _**BPEL_Process_Name.bpel** (BPEL process definition)_
            - _**deploy.xml** (Deployment desciptor File)_
            - ...
            - _Other project files_

    General Steps to create and test a project:
    1. Create switchYard project.
    2. Create BPEL component in `switchyard.xml`.
    3. Edit BPEL process.
    4. Create deployment descriptor file `deploy.xml`.
    5. Start the server and test using a tool like SoapUI.
    
##### Code Snippets "Hello World Project"
- SwitchYard
    - **Group Id:** `com.switchyard.helloworld`
    - **Namespace:** `http://helloworld.switchyard.com`
    - **target Id:** `com.switchyard.helloworld`
    
- BPEL Xpath expression code snippet for the assign block BPEL file:
    ```javascript
    concat('Hello ', $HelloWorldRequest.parameters/in)
    ```
- deploy.xml
    ```xml
    <?xml version="1.0" encoding="UTF-8"?>
    <deploy xmlns="http://www.apache.org/ode/schemas/dd/2007/03" xmlns:helloworld.switchyard.com="http://helloworld.switchyard.com">
      <process name="helloworld.switchyard.com:HelloWorld">
        <process-events generate="all"/>
        <provide partnerLink="HelloWorld">
          <service name="helloworld.switchyard.com:HelloWorld" port="ignored"/>
        </provide>
      </process>
    </deploy>
    ```

#### 2. Invoking External SOAP Service Project
This example shows how to invoke a SOAP Web Service to find Brazilian CEP information, the service provider is **UFSC**.

The service endpoint is:
https://ws.homologacao.ufsc.br/services/CEPService

The service description is:
https://ws.homologacao.ufsc.br/services/CEPService?wsdl

##### Code Snippets "CEP Service Project"
- **SwitchYard**
    - **Group Id:** `br.ufsc.das`
    - **Namespace:** `http://das.ufsc.br`
    - **target Id:** `br.ufsc.das`
    
- **BPEL**
    Variable initialization code snippet for _service partnerLink_ in the assign block BPEL file:
    ```xml
    <int:getCepInfo xmlns:int="http://interfaces.cep.services.ufsc.br/">
    	<cep/>
    </int:getCepInfo>
    ```
- **deploy.xml**
    ```xml
    <?xml version="1.0" encoding="UTF-8"?>
    <deploy xmlns="http://www.apache.org/ode/schemas/dd/2007/03" xmlns:das.ufsc.br="http://das.ufsc.br">
      <process name="das.ufsc.br:CEP_Process">
        <process-events generate="all"/>
        <provide partnerLink="CEP_Process">
    		<service name="das.ufsc.br:CEP_Process" port="ignored" />    
        </provide>
        <invoke partnerLink="CEP_Service">
        <service name="das.ufsc.br:CEPService" port="ignored" />
        </invoke>
      </process>
    </deploy>
    ```

