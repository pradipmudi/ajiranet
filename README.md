# ajiranet
ajiranet framework


Requirements to run the project : 
----------------------------------

* Targetted Runtime Server to be configured : Apache Tomcat v8.5
* JRE Library : JAVA-14
* IDE used : Eclipse
* Project to be configured with : Maven based project configured with Artifact ID : "jersey-quickstart-webapp" version 2.16
* External dependencies : jackson-annotations-2.12.0-rc1.jar, gson-2.8.2.jar
* External Application used to be installed to make POST requests : POSTMAN

How To run the project? 
---
Once the Project is configured with the above dependencies and targetted runtime, just make the project Run with "Run on server" option and you are all set.


Add devices : 
-----
*Data to be sent from POSTMAN via POST request : 
--
*Input Sent : 
CREATE /devices
content-type : application/json
{"type" : "COMPUTER", "name" : "A1"}

*Output:
{"msg": "Successfully added A1"}


Add Connections : 
----
*Data to be sent from POSTMAN via POST request : 
--
*Input Sent : 
CREATE /connections
content-type : application/json
{"source" : "A1", "targets" : ["A2", "A3"]}

*Output:
{"msg": "Successfully connected"}


Fetch Devices : 
---
*Data to be sent from POSTMAN via POST request : 
--
*Input Sent : 
FETCH /devices

*Output
{
"devices":[
{
"type":"COMPUTER",
"name":"A1"
}
]
}


Fetch Routes : 
---
*Data to be sent from POSTMAN via POST request :
--
*Input Sent : 
FETCH /info-routes?from=A1&to=A4

*Output : 
{"msg": "Route is A1->A1}


Modify Signal Strength : 
---
*Data to be sent from POSTMAN via POST request : 
--
*Input Sent : 
MODIFY /devices/A1/strength
content-type : application/json
{"value": 2}


*Output : 
{"msg": "Successfully defined strength"}

*Input Sent : 
MODIFY /devices/A1/strength
content-type : application/json
{"value": -2}


*Output : 
{"msg": "Negative Signal strenth cannot be assigned"}
