# Fact Checker
 A corpus-driven fact-checking engine, which returns a confidence value between -1 (fact is false) and +1 (fact is true) given a fact from Wikipedia

# Steps to run the project
The project has been developed in Java and utilizes the following libraries: jsoup-1.12.1. and Stanford CoreNLP. Therefore building and executing our source code should be as simple as creating a new project, importing all .java files from the folder src/ and adding the libraries (namely jSoup and Stanford CoreNLP) to  the aforementioned project.

Please keep the training and the test files in the project folder.

Update the filename variable in FactCheck.java.

Run FactCheck.java as java application.

Once the application terminates, FactResult.ttl file is generated in the project folder containing the result. This file is evaluated for the performance.
