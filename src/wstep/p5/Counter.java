package wstep.p5;

public class Counter implements Licznik {

    private int value = 0;

    @Override
    public void increment() {
        value++;
    }

    @Override
    public int getValue() {
        return value;
    }
}
