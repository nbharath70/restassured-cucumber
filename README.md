Demonstration of Rest-Assured and Cucumber for web services automation. 
=====================


##Objective##
Intent of this project is to illustrate REST services testing using REST-assured and BDD development process  (https://code.google.com/p/rest-assured/)

###This project illustrate different  types of service tests which corresponds to different feature files ###
* get.feature - Corresponds to test which performs a get request and verifies the response 
* Post.feature - Corresponds to test which performs a post request and verifies the response
* putAndDelete.feature - Corresponds to test which performs a put and delete requests and verifies the response



##Stack##
This java project has been created using Eclipse Neon. REST-assured and cucumber have been included in project via maven. Tests have been written in Junit.

##Different test styles##
Using REST-assured and cucumber there are many ways to write tests. 
The glue codes are in stepdefs package and cucumberRunner is starting point for Junit to start executing the tests.
Base Steps class contains the methods to perform the actual http method requests and verifies the response.
apiClasses package contains the classes which defines the attributes present in response given by api.
pom.xml is the maven build file which manages the dependencies required to execute the project.  
For downloading  and setting up a rest api use URL:https://github.com/typicode/json-server and follow the steps to configure it.
Change the test data value for every api call in feature files.

## Run your pom.xml with command clean test
