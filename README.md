# Things I tried to follow :

1. Tried to create all the required domain entities/models

2. Tried not to break encapsulation by avoiding getters & setters (as much as possible)

3. Tried to have immutable state (with Value objects) as much as possible so as to avoid 
concurrency issues (Thread safety)

4. Tried to have readable methods & variables naming so as to clear the intention 
(4 rules of simple design). Although in doing that some names got big :P
 
5. Tried to have logical and good number of commits

6. Tried to avoid code duplication by refactoring/reusing duplicate code (DRY) 
but still code duplication can be improved if given more time

7. Tried to avoid making interfaces as per YAGNI principles because for now 
I didn't feel the need for the same (Yes, I am aware of principle - 
"Program to interfaces rather than concrete implementation")

8. Tried to put some comments so as to make business logic more understandable

9. Tried to write job on every class so as to clear it's use case

# Getting started

Go to Startup.java and run the main method (no need to give inputs from console) 
and check output on console (No specific reason to make this project on Gradle)

# Things I could have done/improved if given more time :

1. TDD and wrote unit test case with 100% code coverage

2. More DRY principle, zero getters & zero setters in any class

3. 100% immutable state in each class

