package pageObjects;

public class Notes
{
	/*Automation Framework:
	We organize automation project files/folders in structured manner.
	Objectives:
	(1) Re-usability
	(2) Maintainability
	(3) Readability
	Types of frameworks:
	(1) Built-in:
	Example-TestNG,JUnit,Cucumber,Robot,etc..
	(2) Customized(User defined):
	Example-Modular framework,Data driven,Keyword driven,Hybrid driven,etc..
	Phases/Stages:
	(1) Analyzing AUT(Application Under Test)
	(a) Number of pages
	(b) What are all elements/how to automate/type
	(c) What to automate/What we cannot automate
	(2) Choose test cases for automation
	Example: 100 Test cases---->90 automatable,10 cannot automate
	100% automation is achieved if all automatable test cases are automated(According to our perspective)
	(a) Sanity test cases - P1
	(b) Data driven test cases/re-tests - P2
	(c) Regression test cases - P3
	(d) Any other test cases - P4
	(3) Design and Development of framework
	(4) Execution - local,remotely
	(5) Maintenance
	
	E-Commerce Domain:
	(1) Frontend operations(Customers/Users)
	(a) Register an account
	(b) Login
	(c) Search for the product
	(d) Add/edit/delete products from cart
	(e) Order products
	(f) Reviews
	etc..
	(2) Backend operations(Admins/backend teams)
	(a) Products
	(b) Customers
	(c) Orders
	(d) Store
	etc..*/
	
	/*Development of Hybrid Driven Framework:
	(1) Test case: Account Registration
	(1.1) Create BasePage under "pageObjects" which includes only constructor. This will be invoked by every Page Object Class constructor(Re-usability).
	(1.2) Create Page Object Classes for HomePage,RegistrationPage under pageObjects package.
	(These classes extend from BasePage).
	(1.3) Create AccountRegistrationTest under "testCases"
	(1.4) Create BaseClass under testBase package and copy re-usable methods.
	(1.5) Create re-usable methods to generate random numbers and strings in BaseClass.
	
	(2) Adding logs to test case (log4j2)
	(2.1) Add log4j2.xml file under src/test/resources.
	(2.2) Update BaseClass.
	(2.3) Add log statements to AccountRegistrationTest.
	
	(3) Run Tests on Desired Browser/Cross Browser/Parallel
	(3.1) Create testng.xml file to Run Test Cases and parameterize browser name and OS to BaseClass ->setup() method.
	(3.2) Update BaseClass ->setup() method, launch browser based on conditions.
	(3.3) Maintain separate xml to run tests multiple browsers parallelly.
	
	(4) Read Common values from config.properties file.
	(4.1) Add config.properties file under src/test/resources.
	(4.2) Update BaseClass ->setup() method, add script to load config.properties file.
	(4.3) Replace hard coded values in Test Cases like url, username, password etc...
	
	(5) Login Test Case
	(5.1) Create and update page object classes. LoginPage, MyAccountPage - new classes HomePage - update by adding login link element.
	(5.2) Create LoginTest
	(5.3) Add entry testng.xml
	
	(6) Data Driven Login Test
	(6.1) Prepare test data in Excel, place the excel file inside the testData folder.
	(6.2) Create ExcelUtility class under utilities package.
	(6.3) Update Page Object class MyAccountPage, add logout link element.
	(6.4) Create DataProviders class in utilities package to maintain data providers for data driven tests.
	(6.5) Create LoginDataDrivenTest under testCases package.
	(6.6) Add an Entry in testng.xml file
	
	(7) Grouping Tests.
	(7.1) Add all test cases into specific group (sanity, regression, master etc.).
	(7.2) Also add BaseClass methods setup() and teardown() to all groups.
	(7.3) Create separate TestNG xml file(grouping.xml) to run groups and include groups which we want to execute.
	
	(8) Add Extent Reports to Project
	(8.1) Create ExtentReportUtility utility class under utilities package.
	(8.2) Add captureScreen() method in BaseClass
	(8.3) Add ExtentReportUtility (Listener class) entry in testng.xml file.
	(8.4) Make sure WebDriver is static in BaseClass, we refer same driver instance in ExtentReportUtility.
	
	(9) Run Failed Tests.
	test-output->testng-failed.xml
	
	(10) Run Tests on Selenium Grid
	Grid Setup:
	(a) Standalone Setup (Single machine):
	- Download selenium-server-4.15.0.jar and place it somewhere.
	- Run below command in command prompt to start Selenium Grid
	  java -jar selenium-server-4.15.0.jar standalone
	- URL to see sessions: http://localhost:4444 (http://192.168.122.149:4444) //Grid URL
	(b) Hub and Node Setup (Multiple machines):
	- Download selenium-server-4.15.0.jar and place it somewhere in both (hub and node) the machines.
	- Run below command in command prompt to make machine as hub
	  java -jar selenium-server-4.15.0.jar hub
	- Run below command in command prompt to make machine as node
	  java -jar selenium-server-4.15.0.jar node --hub http://<hub-ip>:4444
	- URL to see sessions: http://localhost:4444
	(10.1) Add execution_env=local/remote in config.properties file under resources folder.
	(10.2) Update setup() method in the BaseClass (capture execution environment from config.properties file then add required capabilities of OS and Browser in conditions).
	(10.3) Run the tests from testng.xml
	
	(11) Run Tests on Docker with Selenium Grid Environment.
	Refer the DockerSetup Document.
	
	(12) Run Tests using Maven pom.xml, Command Prompt and run.bat file.
	
	(13) Push the Code to Git and GitHub Repository
	
	(14) Run Tests using Jenkins.*/
	
	/*Docker - Containerization
	Docker Commands:
	(a) Basic Commands
	(1) docker version - Displays the Docker version installed on the system.
	(2) docker-compose version
	(3) docker -v - Short form of docker version. It shows the Docker version.
	(4) docker info - Provides detailed information about the Docker installation.
	(5) docker --help - Displays general help information.
	Example: To get information about specific commands:
	(*) docker images --help - Details about managing images.
	(*) docker run --help - Details about running containers.
	(6) docker login - Logs into a Docker registry, such as Docker Hub. Used for push or pull docker images from Docker Hub.
	(b) Images Commands
	(7) docker images - Lists all the Docker images present on the machine.
	(8) docker pull - Pulls an image from a Docker registry.
	Example: docker pull ubuntu
	(9) docker rmi - Removes Docker images.
	(*) docker images -q - Lists image IDs.
	(*) docker rmi <image ID> - Deletes the specified image.
	(*) After deletion, confirm with docker images.
	(c) Container Commands
	(10) docker ps and docker run
	(*) docker ps - Lists running containers.
	(*) docker run <image> - Creates a container from a specified image. If local image is not available then it will pull from Docker Hub automatically.
	(-) Example: docker run ubuntu
	(-) Example: docker run -it ubuntu (For interaction)
	(11) docker start - Starts a stopped container.
	Example: docker start <container id>
	(12) docker stop - Stops a running container.
	Example: docker stop <container id or image>
	(13) docker rm - Removes a container.
	Example: docker rm <container id or container name>
	(d) System Commands
	(14) docker stats - Provides resource usage statistics for running containers such as CPU, memory, etc.
	(15) docker system df - Displays disk usage related to Docker.
	(16) docker system prune - Cleans up unused data, such as stopped containers.
	(*) docker system prune -f - Forcefully removes all stopped containers.
	These commands form the basic toolkit for managing Docker containers and images, as well as maintaining the Docker environment.
	
	Selenium Grid Setup with Docker Containers:
	(1) Pull Docker Images
	(a) Pull Selenium-hub image using command
	docker pull selenium/hub
	(b) Pull FireFox image using command
	docker pull selenium/node-firefox
	(c) Pull Chrome image using below command
	docker pull selenium/node-chrome
	(2) Verify Images
	docker images
	(3) Running Docker Containers by using below commands.
	(a) docker network create <network name>
	Example: docker network create grid
	(b) docker run -d -p 4442-4444:4442-4444 --net grid --name selenium-hub selenium/hub
	(c) docker run -d --net grid -e SE_EVENT_BUS_HOST=selenium-hub -e SE_EVENT_BUS_PUBLISH_PORT=4442 -e SE_EVENT_BUS_SUBSCRIBE_PORT=4443 selenium/node-chrome
	(d) docker run -d --net grid -e SE_EVENT_BUS_HOST=selenium-hub -e SE_EVENT_BUS_PUBLISH_PORT=4442 -e SE_EVENT_BUS_SUBSCRIBE_PORT=4443 selenium/node-firefox
	(URL to check hub and nodes added on Grid environment: http://localhost:4444/ui)
	(4) When you are done using the Grid, and the containers have exited, the network can be removed with the following command:
	docker network rm <network name>
	Example: docker network rm grid (Removes the grid network)
	
	Selenium Grid Setup with docker-compose.yaml file
	(1) Create a file docker-compose.yaml with Required config
	(2) Open the docker-compose file location in Command Prompt and run docker-compose.yaml file using below command:
	docker-compose up
	(3) To check hub and nodes running state:
	http://localhost:4444/grid/console
	(4) To increase number of nodes:
	docker-compose scale chrome=3
	(5) To stop the grid and cleanup the created containers, run
	docker-compose down*/
}