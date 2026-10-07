package wstep.p5;

public class CounterSynchronizedMetoda implements Licznik {

    private int value = 0;

    @Override
    public synchronized void increment() {
        value++;
    }

    @Override
    public synchronized int getValue() {
        return value;
    }
}
