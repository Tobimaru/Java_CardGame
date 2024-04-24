# Java_CardGame

A card game implemented in Java as a learning case. It's not meant to be state-of-the-art, just something to practice.

## Unit tests

Dependencies: JUnit5 (can be installed with sudo apt-get install junit5)

To compile the unit tests, cd into project directory and run:

javac -cp "/usr/share/java/junit-jupiter-api.jar:." -d \<path to the output dir\> ./test/*.java

To run JUnit from terminal you can use the standalone launcher:

java -jar /usr/share/java/junit-platform-console-standalone.jar --class-path \<path to the output dir\> --scan-class-path

## Applications

To compile applications, cd into project directory and run:

javac -cp . -d \<path to the binary dir\> ./applications/*.java

To run, cd into binary dir and run:

java \<name of application\>


