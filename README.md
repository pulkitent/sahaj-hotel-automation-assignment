# Problem Statement :

A very prestigious chain of hotels is facing a problem of huge consumption of electricity bills for
its electronic equipments. The common equipments, like lights, ACs, etc are currently controlled
manually, by the hotel staff, using manual switches. Hotel Management wants to optimise the
usage of electricity consumption and also ensure that there is no inconvenience caused to the
guests and staff. So, it has installed Motion Sensors at appropriate places and have approached
you to program a Controller which takes inputs from these sensors and controls various
equipments.
The way the hotel equipments are organised and the requirements for the Controller are listed
below:

1. A Hotel can have multiple floors

2. Each floor can have multiple main corridors and sub corridors
   
3. Both main corridor and sub corridor have one light each
   
4. Both main and sub corridor lights consume 5 units of power when ON
   
5. Both main and sub corridor have independently controllable ACs
   
6. Both main and sub corridor ACs consume 10 units of power when ON
   
7. All the lights in all the main corridors need to be switched ON between 6PM to 6AM, which is the Night Time slot
   
8. By default, all ACs are switched ON, all the time
   
9. When a motion is detected in one of the sub corridors the corresponding lights need to be switched ON between 6PM 
   to 6AM (Night Time slot)
   
10. The total power consumption of all the ACs and lights combined should not exceed (Number of Main corridors * 15) + (Number of sub corridors * 10) 
    units of per floor. Sub corridor AC could be switched OFF to ensure that the power 
    consumption is not more than the specified maximum value
  
11. When there is no motion for more than a minute the sub corridor lights should be switched OFF and AC needs to be 
    switched ON Motion in sub-corridors is input to the controller, which needs to keep track and optimise the power 
    consumption.

Write a program that takes input values for Floors, Main corridors, Sub corridors and takes
different external inputs for motion in sub corridors. For each input, the program prints out the
state of all the lights and ACs in the hotel. For simplicity, assume that the controller is operating
at the Night Time.

![alt text](https://raw.githubusercontent.com/pulkitent/hotel-automation/master/Sample%20input%20and%20output.png)

Since the hotel management is trying this for the first time, it would be changing the
requirements as to which electronic equipments are controlled and the criteria based on which
they are controlled. Therefore, the solution design should be flexible enough to absorb these
changes without a need to make significant changes in the program.


# What the company is looking for or what are the company's expectations?

They are looking for people who can write code that has flexibility built-in, by adhering to the principles of Object-Oriented Development, and have the ability to deal with the real-life constraints/trade-offs while designing a system.

It is important to note that they are not looking for a GUI and they are not assessing you on the capabilities around code required to do the I/O. The focus is on the overall design. So, while building a solution, it would be nicer (not mandatory) if the input to the code is provided either via unit tests or a file. Using a command-line (for input) can be tedious and difficult to test, so it is best avoided (again not mandatory). Following is a list of things to keep in mind, before you submit your code for any LLD/OOD/OOPs round:

1. Clear identification of domain entities or classes and their relations with appropriate object modeling using composition.

2. Functionality should not be dumped in a single class, method, or file (don't create God class).

3. Write a clean Code with clear intention so as to have good readability (Proper Naming Conventions, Self-documenting code, Avoid redundant commenting, etc).

3. Clear and logical separation of responsibilities with proper boundaries (emphasis on single responsibility (SRP) for high cohesion).

4. Have you applied the principles of YAGNI and KISS?

5. Have you applied SOLID principles to your code?

6. Is the behavior of an object distinguished from its state and is the state encapsulated? 

7. Have you looked at basic refactoring to improve the design of your code?

8. Are the principles applied in a pragmatic way.

9. Code should be easily extensible & maintainable

11. Atomicity and Coverage of Unit Tests.

**Simplicity is the strongest trait of a piece of code. However, easily written code may not necessarily be simple code.**


# Why LLD/OOD/OOPs in software engineering interviews?

So main reason behind asking this kind of problems in an interview is to see whether a candidate can do the following:

1. Can a candidate write a working code in a given short span of time? So as to measure his/her delivery capability?

2. Can a candidate write highly readable, maintainable & extensible code? The intention must be clear by reading the code (Check 4 rules of simple design)

3. Can a candidate follow the principle of DRY (Don't Repeat Yourself) and avoid breaking encapsulation by following or Fat model pattern or Domain-Driven Design(DDD)? (Read tell don't ask principle and Law Demeter)

4. Can a candidate achieve the solution with a minimum number of elements using the YAGNI principle (that is without creating unnecessary interfaces etc)?


# Rules they want you to follow:

You should not use any external libraries to solve this problem, but you can use external libraries or tools for building or testing purposes. Specifically, you can use unit-testing libraries or build tools available for your chosen language (e.g., JUnit, Ant, NUnit, Rspec, Rake, etc.).

They assess a number of things including the design aspect of your solution and your object-oriented programming skills. While these are small problems, They expect you to submit what you believe is production-quality code; code that you’d be able to run, maintain and evolve. You don’t need to gold plate your solution, however, we are looking for something more than a bare-bones algorithm.


# Things I tried to follow in this project/repo:

1. Tried to create all the required domain entities/models as per the problem statement.

2. Tried not to break encapsulation by avoiding getters & setters (as much as possible).

3. Tried to have an immutable state with value objects (as much as possible) so as to avoid concurrency issues (Thread safety).

4. Tried to have readable methods & variables naming so as to clear the intention (4 rules of simple design).

5. Tried to have small & logical commits.

6. Tried to avoid code duplication by refactoring/reusing duplicate code (DRY).

7. Didn't make interfaces as per YAGNI principles because for now, I don't feel the need for the same (Yes, I am aware of this principle also - "Program to interfaces rather than concrete implementation").

8. Tried to put some comments so as to make business logic more understandable.

9. Wrote the job on every class so as to clear its use case.


# Things I could have done/improved in this project/repo if given more time :

1. TDD with 100% code coverage.

2. Code duplication can be further reduced to some extent.

3. Level of indentation can be further reduced in some methods by breaking them into smaller methods.

4. Encapsulation of behavior in some classes can be further improved.

5. More mocking and stubbing of test data in unit tests.
