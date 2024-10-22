# Rule Engine with AST (Abstract Syntax Tree)

This project implements a 3-tier rule engine application that determines user eligibility based on attributes like age, department, income, and more. It allows the dynamic creation, combination, and modification of rules represented as an Abstract Syntax Tree (AST).

Features

	•	Dynamic Rule Creation: Create rules that can be modified dynamically.
	•	AST Representation: Use AST to represent conditional rules.
	•	Combine Rules: Combine multiple rules into a single AST.
	•	Evaluate Rules: Evaluate the rules against user-provided data.
	•	Persistence with MySQL: Store and manage rules in a MySQL database.
	•	Error Handling: Handles invalid rule formats and data inputs.

Table of Contents

	•	Tech Stack
	•	Database Setup
	•	Running the Application
	•	Endpoints
	•	Create Rule
	•	Evaluate Rule
	•	Get Rule by ID
	•	Postman URLs
	•	License

Tech Stack

	•	Java 17 (or any compatible version)
	•	Spring Boot 3 (Spring Data JPA, Spring Web)
	•	MySQL for rule persistence
	•	Jackson for JSON parsing
	•	Maven for dependency management

Database Setup

	1.	Install MySQL and create a database for the project:
 		CREATE DATABASE rule_engine_db;
	2.	Update the MySQL credentials in src/main/resources/application.properties:
		# MySQL Configuration
		spring.datasource.url=jdbc:mysql://localhost:3306/rule_engine_db
		spring.datasource.username=root
		spring.datasource.password=your_password
		
		# Hibernate settings
		spring.jpa.hibernate.ddl-auto=update
		spring.jpa.show-sql=true
Running the Application

	1.	Clone the repository:
		git clone https://github.com/your-username/rule-engine-ast.git
	2.	Navigate to the project directory:
		cd rule-engine-ast
  	3.	Install the dependencies:
		mvn clean install
	4.	Run the application:
 		mvn spring-boot:run
	The application will start on http://localhost:8080/.
# Endpoints

1. Create Rule

	•	URL: POST /rules/create
	•	Parameters:
	•	ruleName (Query param): Name of the rule.
	•	Body (raw, JSON format):
		"age > 30 AND department = 'Sales'"
	Example Request
		curl -X POST "http://localhost:8080/rules/create?ruleName=rule1" \
		     -H "Content-Type: application/json" \
		     -d '"age > 30 AND department = \'Sales\'"'
2. Evaluate Rule

	•	URL: POST /rules/evaluate/{id}
	•	Replace {id} with the rule’s ID in the database.
	•	Body (raw, JSON format):
		{
		    "age": 35,
		    "department": "Sales",
		    "salary": 60000,
		    "experience": 4
		}
	Example Request:
		curl -X POST "http://localhost:8080/rules/evaluate/1" \
		     -H "Content-Type: application/json" \
		     -d '{"age": 35, "department": "Sales", "salary": 60000, "experience": 4}'
3. Get Rule by ID

	•	URL: GET /rules/get/{id}
	•	Replace {id} with the rule’s ID.

	Example Request
		curl -X GET "http://localhost:8080/rules/get/1"

This returns the AST in JSON format for the given rule ID.
