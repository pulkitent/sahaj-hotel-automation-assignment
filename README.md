# Getting started (Run the application)

Go to Startup.java and run the main method (no need to give inputs from console) 
and check output on console 

(PS: No specific reason to make this project on Gradle)


# Things I tried to follow :

1. Tried to create all the required domain entities/models

2. Tried not to break encapsulation by avoiding getters & setters (as much as possible)

3. Tried to have immutable state (with Value objects) as much as possible so as to avoid 
concurrency issues (Thread safety)

4. Tried to have readable methods & variables naming so as to clear the intention 
(4 rules of simple design). Although in doing that some names got big :P
 
5. Tried to have small & logical commits

6. Tried to avoid code duplication by refactoring/reusing duplicate code (DRY) 
but still code duplication can be improved if given more time

7. Tried to avoid making interfaces as per YAGNI principles because for now 
I didn't feel the need for the same (Yes, I am aware of principle - 
"Program to interfaces rather than concrete implementation")

8. Tried to put some comments so as to make business logic more understandable

9. Tried to write job on every class so as to clear it's use case


# Things I could have done/improved if given more time :

1. TDD with 100% code coverage

2. Code duplication in Controller.java & Corridor.java can be further reduced
at some extent

3. Encapsulation of behaviour in Controller.java can be further improved

3. Startup.java can be improved by refactoring IO into some other class

4. Level of indentation can be further reduced by extracting out the switch in 
optimizePowerConsumptionForAllFloors of Controller.java into Motion.java