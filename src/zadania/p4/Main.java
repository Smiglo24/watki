package zadania.p4;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;

public class Main {
    static void main() throws InterruptedException {
        AtomicLong result = new AtomicLong();
        /**
         *
         * Policz sumę liczb od 1 do 1 000 000, dzieląc pracę na 4 wątki:
         *   wątek 1 sumuje liczby od 1 do 250 000,
         *   wątek 2 - od 250 001 do 500 000,
         *   wątek 3 - od 500 001 do 750 000,
         *   wątek 4 - od 750 001 do 1 000 000.
         * main czeka na wszystkie wątki, dodaje ich wyniki i wypisuje sumę (powinno być 500000500000).
         */
        Thread thread1 = new Thread(() -> {
            Long sum1 = 0L;
            for (int i = 0; i < 250000; i++) {

                sum1 += i;

            }
            result.addAndGet(sum1);

        });
        Thread thread2 = new Thread(() -> {
            Long sum2 = 0L;
            for (int i = 250000; i < 500000; i++) {

                sum2 += i;
            }
            result.addAndGet(sum2);
        }
        );


        Thread thread3 = new Thread(() -> {
            Long sum3 = 0L;
            for (int i = 500000; i < 750000; i++) {

                sum3 += i;
            }
            result.addAndGet(sum3);
        });
        Thread thread4 = new Thread(() -> {
            Long sum4 = 0L;
            for (int i = 750000; i < 1000001; i++) {
                sum4 += i;
            }
            result.addAndGet(sum4);
        });
        thread1.start();
        thread2.start();
        thread3.start();
        thread4.start();

        thread1.join();
        thread2.join();
        thread3.join();
        thread4.join();


        System.out.println(result);
        /* *
         * Napisz wątek, który wypisuje liczby od 10 do 1, jedną co pół sekundy.
         * main uruchamia wątek, czeka 2 sekundy i przerywa go przez interrupt().
         * Po przerwaniu wątek wypisuje "Przerwano" i kończy pracę, nie dochodząc do 1.
         *
         * Przykładowy wynik:
         *   10
         *   9
         *   8
         *   7
         *   Przerwano
         */
        //todo
        Thread thread=new Thread(()->{
            for (int i = 10; i >=1 ; i--) {
                try {
                    Thread.sleep(500);
                    System.out.println(i);
                } catch (InterruptedException e) {
                    System.out.println("przerwano");
                    break;
                }

            }
        });
        thread.start();
        Thread.sleep(2000);
        thread.interrupt();
    }


}
