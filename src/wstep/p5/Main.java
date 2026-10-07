package wstep.p5;

import java.util.List;

public class Main {

    static void main() throws InterruptedException {

        List<Licznik> liczniki = List.of(
                new Counter(),
                new CounterSynchronizedMetoda(),
                new CounterSynchronizedBlok(),
                new CounterLock(),
                new CounterAtomic());



        for (Licznik licznik : liczniki) {
            uruchomTrzyWatki(licznik);
            System.out.println(licznik.opis() + ": " + licznik.getValue());
        }

    }


    private static void uruchomTrzyWatki(Licznik licznik) throws InterruptedException {
        Runnable zadanie = () -> {
            for (int i = 0; i < 100_000; i++) {
                licznik.increment();
            }
        };

        Thread t1 = new Thread(zadanie);
        Thread t2 = new Thread(zadanie);
        Thread t3 = new Thread(zadanie);
        t1.start();
        t2.start();
        t3.start();

        t1.join();
        t2.join();
        t3.join();
    }
}
