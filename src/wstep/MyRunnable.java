package wstep;

public class MyRunnable implements Runnable {
    @Override
    public void run() {
        System.out.println("2. implements Runnable  -> wątek: " + Thread.currentThread().getName());

    }
}
