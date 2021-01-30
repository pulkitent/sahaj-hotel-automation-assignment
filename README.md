Problem Statement :

A very prestigious chain of hotels is facing a problem of huge consumption of electricity bills for
its electronic equipments. The common equipments, like lights, ACs, etc are currently controlled
manually, by the hotel staff, using manual switches. Hotel Management wants to optimise the
usage of electricity consumption and also ensure that there is no inconvenience caused to the
guests and staff. So, it has installed Motion Sensors at appropriate places and have approached
you to program a Controller which takes inputs from these sensors and controls various
equipments.
The way the hotel equipments are organised and the requirements for the Controller are listed
below:

● A Hotel can have multiple floors
● Each floor can have multiple main corridors and sub corridors
● Both main corridor and sub corridor have one light each
● Both main and sub corridor lights consume 5 units of power when ON
● Both main and sub corridor have independently controllable ACs
● Both main and sub corridor ACs consume 10 units of power when ON
● All the lights in all the main corridors need to be switched ON between 6PM to 6AM, which is the Night Time slot
● By default, all ACs are switched ON, all the time
● When a motion is detected in one of the sub corridors the corresponding lights need to be switched ON between 6PM to 6AM (Night Time slot)
● The total power consumption of all the ACs and lights combined should not exceed (Number of Main corridors * 15) + (Number of sub corridors * 10) units of per floor. Sub corridor AC could be switched OFF to ensure that the power consumption is not more than the specified maximum value
● When there is no motion for more than a minute the sub corridor lights should be switched OFF and AC needs to be switched ON Motion in sub-corridors is input to the controller, which needs to keep track and optimise the power consumption.

Write a program that takes input values for Floors, Main corridors, Sub corridors and takes
different external inputs for motion in sub corridors. For each input, the program prints out the
state of all the lights and ACs in the hotel. For simplicity, assume that the controller is operating
at the Night Time.

![alt text](https://github.com/pulkitent/hotel-automation/edit/master/blob/master/sample-input-and-output.png?raw=true)

Since the hotel management is trying this for the first time, it would be changing the
requirements as to which electronic equipments are controlled and the criteria based on which
they are controlled. Therefore, the solution design should be flexible enough to absorb these
changes without a need to make significant changes in the program.


What the company is looking for

They are looking for people who can write code that has flexibility built in, by
adhering to the principles of Object Oriented Development, and have the ability to
deal with the real-life constraints / trade-offs while designing a system.

It is important to note that theyt are not looking for a GUI and they are not assessing
you on the capabilities around code required to do the I/O. The focus is on the
overall design​. So, while building a solution, it would be nicer if input to the code
is provided either via unit tests or a file. Using command line (for input) can be
tedious and difficult to test, so it is best avoided.
Following is a list of things to keep in mind, before you submit your code :

● Is behaviour of an object distinguished from its state and is the state
encapsulated?
● Have you applied SOLID principles to your code?
● Have you applied principles of YAGNI and KISS (additional info here)?
● Have you looked at basic refactoring to improve design of your code?
● Finally, and foremost, are the principles applied in a pragmatic way.

Simplicity is the strongest of the trait of a piece of code. However, easily
written code may not necessarily be simple code.

# Getting started (Run the application)

Go to Startup.java and run the main method (no need to give inputs from console) 
and check output on console 

(PS: No specific reason to make this project on Gradle)

# Things I tried to follow :

1. Tried to create all the required domain entities/models as per problem statement

2. Tried not to break encapsulation by avoiding getters & setters (as much as possible)

3. Tried to have immutable state with value objects (as much as possible) so as to avoid 
concurrency issues (Thread safety)

4. Tried to have readable methods & variables naming so as to clear the intention 
(4 rules of simple design).
 
5. Tried to have small & logical commits

6. Tried to avoid code duplication by refactoring/reusing duplicate code (DRY) 
but still code duplication can be improved if given more time

7. Didn't make interfaces as per YAGNI principles because for now I don't feel 
the need for the same (Yes, I am aware of this principle also - "Program to interfaces rather 
than concrete implementation")

8. Tried to put some comments so as to make business logic more understandable

9. Wrote the job on every class so as to clear it's use case


# Things I could have done/improved if given more time :

1. TDD with 100% code coverage

2. Code duplication in Controller.java & Corridor.java can be further reduced
at some extent

3. Level of indentation can be further reduced in some methods by breaking them into smaller methods

4. Encapsulation of behaviour in some classes can be further improved

5. Startup.java can be improved by refactoring IO into some other class

6. More mocking and stubbing of test data in unit tests
