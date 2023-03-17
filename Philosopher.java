/**
 * Philosopher.java
 *
 * This class represents each philosopher thread.
 * Philosophers alternate between eating and thinking.
 *
 */
import java.util.Random;

public class Philosopher implements Runnable {
    private final int id;
    private final DiningServer diningServer;
    private final Random random;

    public Philosopher(int id, DiningServer diningServer) {
        this.id = id;
        this.diningServer = diningServer;
        this.random = new Random();
    }

    @Override
    public void run() {
        while (true) {
            think();
            diningServer.takeForks(id);
            eat();
            diningServer.returnForks(id);
        }
    }

    private void think() {
        try {
            int thinkingTime = random.nextInt(1000) + 1;
            System.out.println("Philosopher #" + id + " took " + thinkingTime + "ms thinking");
            Thread.sleep(thinkingTime);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private void eat() {
        try {
            int eatingTime = random.nextInt(1000) + 1;
            System.out.println("Philosopher #" + id + " took " + eatingTime + "ms eating");
            Thread.sleep(eatingTime);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
