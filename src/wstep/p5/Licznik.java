package wstep.p5;

public interface Licznik {

    void increment();

    int getValue();

    default String opis() {
        return getClass().getSimpleName();
    }
}
