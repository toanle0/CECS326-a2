/**
 * DiningServer.java
 *
 * This class contains the methods called by the  philosophers.
 * You are flexible to change it, here only display a sample
 */

public interface DiningServer {
    void takeForks(int philosopherId);

    void returnForks(int philosopherId);
}
