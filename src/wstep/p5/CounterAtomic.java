package wstep.p5;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * Wersja 4: AtomicInteger - liczba, którą można bezpiecznie zmieniać z wielu wątków.
 *
 * Nie używa zamka. Działa mniej więcej tak:
 *     "jeśli nadal jest 100, zapisz 101. Jeśli ktoś zdążył zmienić - spróbuj jeszcze raz."
 * Sprawdzenie i zapis dzieją się w jednym kroku, którego nic nie przerwie.
 * Ten mechanizm nazywa się CAS (Compare-And-Swap). Zwykle jest najszybszy.
 */
public class CounterAtomic implements Licznik {

    private final AtomicInteger value = new AtomicInteger(0);

    @Override
    public void increment() {
        value.incrementAndGet();
    }

    @Override
    public int getValue() {
        return value.get();
    }

}
