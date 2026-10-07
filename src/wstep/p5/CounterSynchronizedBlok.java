package wstep.p5;

public class CounterSynchronizedBlok implements Licznik {

    private int value = 0;

    @Override
    public void increment() {
        // tu może być kod, który wiele wątków wykonuje jednocześnie
        synchronized (this) {
            value++;           // tu wchodzi tylko jeden wątek naraz
        }
    }

    @Override
    public int getValue() {
        synchronized (this) {
            return value;
        }
    }
}