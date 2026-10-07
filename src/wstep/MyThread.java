package wstep;

public class MyThread extends Thread {

    @Override
    public void run() {
        System.out.println("1. extends Thread       -> wątek: " + getName());
    }


}
