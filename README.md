# Due Date Calculator

## Description

This project implements a due date calculator for an issue tracking system. The calculator determines the due date based on the submit date/time and the turnaround time in working hours. 

## Requirements

- Java 11 or later
- Maven 3.6 or later

## Working Hours and Days

- Working hours are from 9 AM to 5 PM on every working day, Monday to Friday.
- June 18, 2024, is assumed to be a holiday and is not counted as a working day.

## How to Run

### 1. Clone the Repository

```bash
git clone <repository_url>
cd <repository_directory>
```

### 2. Build the Project

Use Maven to build the project:

```bash
mvn clean install
```

### 3. Run the Application

To run the application, execute the `Main` class:

```bash
java -cp target/DueDateCalculator-1.0-SNAPSHOT.jar org.emarsys.Main
```

### 4. Run Unit Tests

To run the unit tests, use the following Maven command:

```bash
mvn test
```

## Files Included

1. `DueDateCalculator.java`: Contains the implementation of the due date calculation logic.
2. `Main.java`: Entry point of the application, demonstrating how to use the `DueDateCalculator`.
3. `DueDateCalculatorTest.java`: Unit tests for `DueDateCalculator` to ensure correctness of the logic.

## Please Note:
- Working hours are from 9 AM to 5 PM on every working day, Monday to Friday.
- Exception: June 18, 2024, is assumed to be a holiday.



