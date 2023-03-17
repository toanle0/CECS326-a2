/**
 * DiningPhilosophers.java
 *
 * This program starts the dining philosophers problem.
 *
 */
public class DiningPhilosophers {
    public static void main(String[] args) {
        final int numberOfPhilosophers = 5;
        DiningServer diningServer = new DiningServerImpl(numberOfPhilosophers);
        Thread[] philosopherThreads = new Thread[numberOfPhilosophers];

        System.out.println("Initial fork distribution:");
        for (int i = 0; i < numberOfPhilosophers; i++) {
            int leftFork = i;
            int rightFork = (i + 1) % numberOfPhilosophers;
            System.out.println("Philosopher #" + i + " is between fork #" + leftFork + " and fork #" + rightFork);
        }
        System.out.println();

        for (int i = 0; i < numberOfPhilosophers; i++) {
            Philosopher philosopher = new Philosopher(i, diningServer);
            philosopherThreads[i] = new Thread(philosopher, "Philosopher " + (i + 1));
            philosopherThreads[i].start();
        }

        for (Thread thread : philosopherThreads) {
            try {
                thread.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
