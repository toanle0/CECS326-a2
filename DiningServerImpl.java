/**
 * DiningServer.java
 *
 * This class contains the methods called by the Philosophers.
 *
 */
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class DiningServerImpl implements DiningServer {
    private enum State {THINKING, HUNGRY, EATING}

    private final int numberOfPhilosophers;
    private final State[] philosopherStates;
    private final Lock lock;
    private final Condition[] conditions;
    private final int[] forkOwners;

    public DiningServerImpl(int numberOfPhilosophers) {
        this.numberOfPhilosophers = numberOfPhilosophers;
        philosopherStates = new State[numberOfPhilosophers];
        lock = new ReentrantLock();
        conditions = new Condition[numberOfPhilosophers];
        forkOwners = new int[numberOfPhilosophers];

        for (int i = 0; i < numberOfPhilosophers; i++) {
            philosopherStates[i] = State.THINKING;
            conditions[i] = lock.newCondition();
            forkOwners[i] = -1; // -1 indicates that no philosopher owns the fork initially
        }
    }

    @Override
    public void takeForks(int philosopherId) {
        lock.lock();
        try {
            philosopherStates[philosopherId] = State.HUNGRY;
            test(philosopherId);
            if (philosopherStates[philosopherId] != State.EATING) {
                conditions[philosopherId].await();
            } else {
                forkOwners[leftOf(philosopherId)] = philosopherId;
                forkOwners[rightOf(philosopherId)] = philosopherId;
                System.out.println("Fork #" + leftOf(philosopherId) + " is with Philosopher #" + philosopherId);
                System.out.println("Fork #" + rightOf(philosopherId) + " is with Philosopher #" + philosopherId);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    @Override
    public void returnForks(int philosopherId) {
        lock.lock();
        try {
            philosopherStates[philosopherId] = State.THINKING;
            forkOwners[leftOf(philosopherId)] = -1;
            forkOwners[rightOf(philosopherId)] = -1;
            test(leftOf(philosopherId));
            test(rightOf(philosopherId));
        } finally {
            lock.unlock();
        }
    }

    private void test(int philosopherId) {
        if (philosopherStates[leftOf(philosopherId)] != State.EATING &&
                philosopherStates[rightOf(philosopherId)] != State.EATING &&
                philosopherStates[philosopherId] == State.HUNGRY) {
            philosopherStates[philosopherId] = State.EATING;
            conditions[philosopherId].signal();
        }
    }

    private int leftOf(int philosopherId) {
        return (philosopherId + numberOfPhilosophers - 1) % numberOfPhilosophers;
    }

    private int rightOf(int philosopherId) {
        return (philosopherId + 1) % numberOfPhilosophers;
    }
}
