# Session 3 - SOA Lab

Information related to exercises of session #3 of SOA Labs.

1. How to do parallel invocations and join responses in BPEL.
2. Simulating Order reception Process.

### 1. Parallel invocations and joining responses in BPEL (Project name: _Parallel_Invocation_BPEL_)

To run this example is necessary to deploy the two web service projects that are going to be invoked, these can be found in the `External_Services` folder, the projects are:
- _**sumService**_
- _**productService**_

Below are the code snippets used in this project.

##### Code Snippets
- **SwitchYard**
    - **Group Id:** `br.ufsc.das`
    - **Namespace:** `http://das.ufsc.br`
    - **target Id:** `br.ufsc.das`
    - **Target Namespace:** `http://das.ufsc.br/Parallel_Process/`
    
- **deploy.xml**
    ```xml
    <?xml version="1.0" encoding="UTF-8"?>
    <deploy xmlns="http://www.apache.org/ode/schemas/dd/2007/03" xmlns:das.ufsc.br="http://das.ufsc.br">
      <process name="das.ufsc.br:Parallel_Process">
        <process-events generate="all"/>

        <provide partnerLink="Parallel_Process">
          <service name="das.ufsc.br:Parallel_Process" port="ignored"/>
        </provide>

        <invoke partnerLink="Sum_Service">
          <service name="das.ufsc.br:SumService" port="ignored"/>
        </invoke>
        <invoke partnerLink="Prod_Service">
            <service name="das.ufsc.br:ProductService" port="ignored"/>
        </invoke>

      </process>
    </deploy>
    ```
___

### 2. Simulating Order Process (Project name: _Order_Simulation_BPEL_)

##### Code Snippets
- **SwitchYard**
    - **Group Id:** `br.ufsc.das`
    - **Namespace:** `http://das.ufsc.br`
    - **target Id:** `br.ufsc.das`
    - **namespace:** `http://das.ufsc.br/OrderProcess/`
    
- **BPEL**
 
    Xpath expressions code snippets for the **`assign`** block that simulates the total value of the order in the BPEL file:
    
    ```javascript
    $processRequest.parameters/order/quantity * 1200
    ```

- **deploy.xml**
    ```xml
    <?xml version="1.0" encoding="UTF-8"?>
    <deploy xmlns="http://www.apache.org/ode/schemas/dd/2007/03" xmlns:das.ufsc.br="http://das.ufsc.br">
      <process name="das.ufsc.br:OrderProcess">
        <process-events generate="all"/>
        <provide partnerLink="OrderProcess">
          <service name="das.ufsc.br:OrderProcess" port="ignored"/>
        </provide>
        
        <invoke partnerLink="CEP_Service">
          <service name="das.ufsc.br:CEPService" port="ignored"/>
        </invoke>
      </process>
    </deploy>
    ```
