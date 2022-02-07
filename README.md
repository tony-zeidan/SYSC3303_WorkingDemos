# Cryptech: SYSC3303 Elevator Project

Welcome to Cryptech's implementation of the Elevator Control System project for SYSC3303.

## Team Contributions

The contributions of each CRYPTECH team member for iteration 1 are listed below.

| Team Member      | Contributions                                                                                                                              |
| ---------------- | ------------------------------------------------------------------------------------------------------------------------------------------ |
| Shaopeng Liu     | Floor subsystem development/testing, Elevator subsystem development, Scheduler development, Iteration0 calculations, UML class             |
| Tony Abou Zeidan | Floor subsystem development, Elevator subsystem development, Scheduler development/testing, UML class, UML sequence, README.md, README.txt |
| Evan Lloyd       | Floor subsystem development, Elevator subsystem development/testing, Scheduler development, UML sequence                                   |
| Dominic Motora   | Floor/Elevator/Scheduler development ideas, UML class diagram, UML sequence diagram                                                        |
| YouHeng Zhou     | ElevatorSubsystem development/refactor, Floor/Elevator/Scheduler development ideas, UML class diagram, UML sequence diagram, README.md     |
| Leenesh Kumar    | Floor/Elevator/Scheduler development ideas, README.md, README.txt, UML class diagram ideas                                                 |

## Project Structure

ElevatorHelper.java: This class has operations that pertain to the Elevators that are in the Elevator Subsystem. In here we have fields for speed, distance, time, acceleration and etc. An operation that helps calculate travel time to a specific floor is included.

ElevatorHelperTest.java: This class serves as a test to check if the ElevatorHelper.java class is functional. Here, we have an operation to test the method that calculates the travel time to a specific floor. If the output is correctly matching. That means the test passed.

ElevatorSimulator.java: A class that gives us a main method that helps to run the Elevator simulation system. In this class, the main method will take a scheduler object, create threads with ElevatorSubsystem and FloorSubsystem objects and then run them.

ElevatorSubsystem.java: This class is an implementation of the functionality of the Elevator Subsystem. In this class, events from the scheduler are handled by run().

ElevatorSubsystemTest.java: This class serves as a test to check if the ElevatorSubsystem.java class is functional. Here, we have an operation to test the method that get specific floor. If the output is correctly matching. That means the test passed.

EventObj.java: This class is used to store that object that keeps track of Elevator system related-events. It possesses getters and setter methods in order to initialize all fields.

FloorSubsystem.java: This class is an implementation of the Floor subsystem. Here, we will have operations that handle events from files and then push them into the scheduler.

FloorSubsystemTest.java: This class serves as a test to check if the FloorSubsystem.java class is functional. Here, we have an operation to test methods that read data, and then read lines.

Scheduler.java: This class is an implementation of the scheduler. It is able to have communications with the floor and elevator subsystems through buffers. It has operations that handle various types of events.

SchedulerTest.java: This class serves as a test to check if the Scheduler.java class if functional and working as its is supposed to. Here we have an operation to test the Push and Getting of an event from the scheduler.

## Set-up Instructions

Follow these instructions for setup in Eclipse:

1. Extract source code zip file (SYSC3303_elevator_project.zip)
2. Open Eclipse
3. Import the project into Eclipse:\
`File->Import->Existing projects into workspace->Select archive file->(Path to SYSC3303_elevator_project.zip, source code)`

## Testing

There are three test files:

```
FloorSubsystemTest.java -> tests the Floor subsystem's ability to read data
ElevatorSubsystemTest.java -> tests the Elevator subsystem's ability to switch floors
ElevatorHelperTest.java -> tests the calculations for Elevator timings
SchedulerTest.java -> tests the Scheduler's ability to act as a communication link
```
