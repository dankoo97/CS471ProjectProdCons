# Problem 1: Producer-Consumer Problem

The producer-consumer problem relates to having a set of _p_ producers, _c_ consumers, with a buffer size of _b_.
A producer produces items and stores them in the buffer while a consumer grabs items from the buffer and consumes them.
The programmer must be careful that:
* Only one mutation operation happens at a single time
* The buffer does not overflow
* The buffer does not underflow
* The producers and consumers all have equal opportunity
* The producers and consumers can all be signaled when the computation is complete

## How to Build and Run

A jar file is provided, and can be run via `java -jar CS471ProjectProdCons-1.0-SNAPSHOT.jar p c b`
This project can be recompiled into a jar file via `./gradlew jar` or run via `./gradlew run --args "p c b"`.

## Sample commands

`java -jar CS471ProjectProdCons-1.0-SNAPSHOT.jar p c b` where p is the total number of producers, c is the total number 
of consumers, and b represents the buffer size

`java -jar CS471ProjectProdCons-1.0-SNAPSHOT.jar 2 2 5` 2 producers, 2 consumers, and buffer of size 5  
`java -jar CS471ProjectProdCons-1.0-SNAPSHOT.jar 2 5 5` 2 producers, 5 consumers, and buffer of size 5  
`java -jar CS471ProjectProdCons-1.0-SNAPSHOT.jar 2 10 5` 2 producers, 10 consumers, and buffer of size 5
<html>&#8942</html>

`java -jar CS471ProjectProdCons-1.0-SNAPSHOT.jar 10 10 100` 10 producers, 10 consumers, and buffer of size 100  