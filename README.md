#Fraunhofer IOSB-AST Robot Simulation#

This project simulates a robot's movement on a table of size N x M. 
The application takes the table dimensions, the robot's initial position and orientation, and a sequence of movement commands as input to perform the simulation. 
The output of this application will be the robot's final position and orientation.


##Getting Started##
###Prerequisites###
- Java 21
- Maven

### Installing ###

1. Build the project using Maven. Open your terminal or command line and run the following commands.
    Navigate to the target directory:
    - cd <project_directory>/robot_simulator/target


### Running Application ###
Start the application by running the following command in the terminal:

- java -jar "robot-simulator.jar" “<simulation-string>“.
-  Eg. java -jar "robot-simulator.jar" "5 5:1 2 S:MRMLM"

Explanation:
* 5 5: Represents the table size (5 rows and 5 columns).
* 1 2 S: Represents the robot's initial position on the table (row 1, column 2, facing South).
* MRMLM: Represents the movement commands for the robot on the table.
  Assumption: If Robot move outside the given table then it will be Invalid Move and Execution will Stop.

### Testing ###
The application includes unit and integration tests for the service layer. To run the tests, use the following command:
- mvn test

### Built With ###
- Java - The programming language used.
- Maven - Dependency Management
- JUnit - Testing framework
- Mockito - Mocking framework for unit tests


