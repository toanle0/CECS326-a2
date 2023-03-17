CECS326-proj2
Group6
=====================

Project 2 - The Dining Philosophers

Toan Le  
CECS 326: Computer Architecture & Organization
Dr. Xu
March 16, 2023


## Introduction
The Dining Philosophers problem is a classic example of a concurrency problem, used to demonstrate the challenges of synchronizing multiple threads while avoiding deadlocks and ensuring fairness. This report discusses the design of a Java-based solution for the Dining Philosophers problem.
  

## Objective
The objective of this lab is to design an Arithmetic and Logic Unit (ALU) using VHDL. 
We first developed an ALU defined to the behavioral VHDL model.
We then converted the ALU into a block design using the IP integrator tool. 
The ALU component is illustrated in Figure 1. 

### Design Process
The program is designed using an object-oriented approach, with separate classes for the main program, philosophers, and the dining server. The design ensures modularity and extensibility, making it easy to maintain and modify.


## Lab Design
 DiningPhilosophers.java serves as the main entry point for the program. It is responsible for initializing the dining server and creating philosopher threads. The class also outputs the initial fork distribution before starting the philosopher threads.

Philosopher.java
The Philosopher class represents an individual philosopher. It implements the Runnable interface, enabling the philosopher to run as a separate thread. Each philosopher has an ID, a reference to the dining server, and a random number generator for determining thinking and eating times.

The philosopher's behavior is encapsulated in the run() method, which consists of an infinite loop where the philosopher thinks, attempts to pick up forks, eats, and returns the forks. The think() and eat() methods handle the respective actions, including random delays to simulate the time spent on those activities.

DiningServer.java
The DiningServer interface defines the methods required for a dining server implementation. It serves as a contract for any concrete implementations, ensuring that they provide the necessary functionality for managing forks and synchronizing philosopher actions.

 #methods
takeForks(int philosopherId): Called by a philosopher when they want to pick up the adjacent forks.
returnForks(int philosopherId): Called by a philosopher when they have finished eating and want to return the forks.
DiningServerImpl
The DiningServerImpl class is a concrete implementation of the DiningServer interface. It manages the forks and ensures that deadlocks are avoided and fairness is maintained among the philosophers.

The class uses an array of Semaphore objects to represent the forks, with each semaphore initialized to a single permit. This ensures that only one philosopher can hold a fork at a time.

The takeForks() and returnForks() methods are synchronized to prevent race conditions, ensuring that only one philosopher can attempt to pick up or return forks at a time.

To avoid deadlocks, the takeForks() method employs the "try and retry" strategy: a philosopher tries to acquire both forks, and if they cannot, they release any acquired fork and try again later. This prevents circular wait and allows the philosophers to continue thinking and eating without getting stuck in a deadlock.

##Conclusion
This Java implementation of the Dining Philosophers problem provides a solution that addresses the challenges of concurrency, synchronization, deadlock avoidance, and fairness. The modular design allows for easy maintenance and modification, making it a useful starting point for further exploration and experimentation with different synchronization strategies and concurrency patterns.
