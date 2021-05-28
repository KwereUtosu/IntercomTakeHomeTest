# Intercom Take Home Test

This project was created with Java.
The application filters customers within 100km of Intercom's Dublin office, displaying names and user_ids of matching customers.

The GPS coordinates for Intercom Dublin office are 53.339428, -6.257664.

This application can function with or without an internet connection. When internet is connected, the Customer's details are gotten from here => https://s3.amazonaws.com/intercom-take-home-test/customers.txt but
when internet is not connected, customer's details are gotten from a resource file(customer.txt) within the application.

The Output is written to output.txt in the root folder.

## How To Run This Project

## Requirements
* JDK (1.8.0) or newer version
* Maven (3.0) or newer version

#### Java SE Development Kit 8 (1.8.0) or newer 

To check if you have this, Run this command in your terminal
```
javac -version
```
If you do not see something like `javac 1.8.0` then you will need to install it and return here afterwards.

Click here [Java installation](https://java.com/en/download/help/download_options.html) for help with installation.
#### Maven 3.0 or newer 

To check if you have this, Run this command in your terminal. 
```
mvn -version
```
If you don't have it installed, then you will need to install it.

## To Download the project

#### Here you have 2 options 
* Download the .zip file and extract it
* Clone the project using git 

## To Build the Project 

* Once you have unzipped / cloned the project, using your terminal navigate to its root folder
* Now run this command:  `mvn clean install` 
* This will use Maven to download the required dependencies, and build an executable jar file in the newly created "target" folder

## To Run the Project 
You can now execute the project by navigating inside the target folder and running this command

`java -jar target\intercomtest-0.0.1.jar`
## To Run the Tests 
You can now execute the tests project by running this command

`mvn clean compile test`
## Author
Joy Utosu
