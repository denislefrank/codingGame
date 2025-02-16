# Queo’s coding game #

## Prerequisites ##

- Java 17+
- Maven 3.6+

## Following Features are done ##

- stdin/stdout, sum, csv input, csv output
- minMax, LT4
- file input
- json output

## How to Run? ##

To run the Application use ``mvn clean install`` to build the whole project. <br/>
After you can use ``mvn package`` to build the jar ``target/CodingGame-1.0-SNAPSHOT.jar`` <br/>
Finally you can use the Application like this basic
functionality ``java -jar target/CodingGame-1.0.jar -i - -o - -a sum``

## Options ##

### Sum ###

Run csv input and output ``java -jar target/CodingGame-1.0.jar -i - -o - -a sum``

### Min Max ###

Run csv input and output ``java -jar target/CodingGame-1.0.jar -i - -o - -a minMax``

### LT4 ###

Run csv input and output ``java -jar target/CodingGame-1.0.jar -i - -o - -a lt4``

### File Input ###

Run file input and csv output ``java -jar target/CodingGame-1.0.jar -i FILE -o - -a sum``

### JSON Output ###

Run csv input and csv output ``java -jar target/CodingGame-1.0.jar -i FILE -o - -a sum -F JSON``

## Possible Exit Codes ##

- 0 ok
- 1 input is empty
- 2 read Error
- 3 write Error (not implemented yet)
- 4 format Error