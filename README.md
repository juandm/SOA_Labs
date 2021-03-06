# SOA_Labs

## Tools for SOA lessons 

#### 1. Download all necessary tools
- Install the tools in the order stated below. link for download [here].
- **SQLite Studio:** https://sqlitestudio.pl/index.rvt?act=download
- **SOAP UI:** https://www.soapui.org/downloads/latest-release.html 

#### 2. Download and install the **Java development kit (jdk)**.
* If the OS is a 64 bits Windows install the JDK using the `.jar` available in the downloaded folder _**"1. JDK"**_ in the first step.

    If you are using other OS, download the JDK here:
    http://www.oracle.com/technetwork/java/javase/downloads/jdk8-downloads-2133151.html

#### 3. JBoss EAP 6.4

To install the _**JBoss EAP runtime**_ package, use the `.jar` located in the folder _**"2.EAP 6.04"**_ downloaded in the first step.

Run the JBoss EAP installer double clicking on the file or executing the following command in the console:
```sh
java -jar jboss-eap-6.4.0-installer.jar
```
During installation:

- Accept the terms and conditions.
- Choose your preferred installation path **(EAP_Install_Path)**, for the JBoss EAP runtime.
- Create an administrative user and make a careful note of these administrative user credentials for later.
- You can accept the default settings on the remaining screens.
 
#### 4. Fuse on EAP runtime
Use the `.jar` located in the folder _**"3. Fuse Runtime"**_ downloaded in the first step to install the _**Fuse on EAP runtime**_ package.

- Open a command prompt and change directory to **(EAP_Install_Path)** used in the last step.
- From the **(EAP_Install_Path)** directory, run the **_Fuse on EAP installer_**, as follows:
    ```sh
    java -jar <TEMP_LOCATION>/fuse-eap-installer-6.3.0.redhat-187.jar
    ```
	When asked in the highlighted line press ENTER and wait to finish the process.

	![fuse install cmd](/imgs/3_Install_FuseEAP_Runtime.PNG)
    
#### 5. JBoss Developer Studio
- Run the installer located in the folder _**"(4.JBossDevStudio)"**_
    - For Mac / Windows Development Hosts:
        - Double-click on the jar to start the installer (on Windows).
    - For Linux Development Hosts
        - Go to the folder where you have downloaded the installer and execute it. with
            ```sh
            java -jar devstudio-11.0.0.GA-installer-standalone.jar
            ```
        - Select Additional Features to Install page, be sure to select JBoss Fuse. Tooling

- During installation:
    - Accept the terms and conditions.
    - Choose your preferred installation path.
    - Select the Java 8 JVM installed in 2dn step.
		![jdk path](/imgs/1_JDK_Path_.PNG)
    - At the **Select Platforms and Servers** step, **configure the runtime server by clicking Add and browsing to the location of the **(EAP_Install_Path)** directory** _(last step)_.
- Select JBoss Fuse Development as additional features.
- The developer environment will start up. When the Searching for runtimes dialog appears, click OK to create the JBoss EAP runtime.
- Accept any additional dependencies and license agreements.

#### 6. JBoss Integration Stack on Jboss Dev Studio

- In JBoss Dev Studio go to _**"Help >> Install New Software"**_.
- In _**"Work With"**_ choose the Core Repository **(Red Hat Developer Studio - Core)**
- In the displayed list, select **"JBoss Integration and SOA Development"** and click _Finish_.
- Accept terms in warnings and finish the installtion process.
	![soa tools](/imgs/2_Install_SOA_Suite.PNG)
	
#### 7. Testing installation and downloading SwitchYard Maven dependencies
Create a new SwitchYard project call it _**test_project**_ and click on finish button, let the environment download all the maven dependencies, after that save all and close the dev Studio.

[here]: <https://drive.google.com/open?id=0B4PAsBMomKsjYUJIWjVaQVF5cEk>