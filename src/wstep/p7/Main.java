package wstep.p7;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Random;

public class Main {

    // Producent wytwarza elementy i wkłada je do kolejki.
    // Konsument wyjmuje je z kolejki i przetwarza.

    private static final Random generator = new Random();
    private static final int ITEM_COUNT = 5;

    private static long obroty = 0;
    private static int uspienia = 0;


    public static void main(String[] args) throws InterruptedException {
        wariant1AktywneCzekanie();
        wariant2WaitNotify();

    }


    private static void wariant1AktywneCzekanie() throws InterruptedException {
        System.out.println("=== Wariant 1: aktywne czekanie ===");

        // Zwykła LinkedList nie jest przygotowana na wiele wątków,
        // dlatego każde użycie kolejki jest w synchronized (queue).
        Queue<String> queue = new LinkedList<>();

        Thread producer = new Thread(() -> {
            try {
                for (int i = 0; i < ITEM_COUNT; i++) {
                    Thread.sleep(200 + generator.nextInt(400));   // produkcja trwa chwilę
                    synchronized (queue) {
                        queue.add("Item no. " + i);
                    }
                }
            } catch (InterruptedException e) {
                System.out.println("Producent przerwany");
            }
        }, "producent");

        Thread consumer = new Thread(() -> {
            int itemsLeft = ITEM_COUNT;
            while (itemsLeft > 0) {
                obroty++;
                String item;
                synchronized (queue) {
                    if (queue.isEmpty()) {
                        continue;                         // pusto - sprawdzamy jeszcze raz, i jeszcze raz...
                    }
                    item = queue.poll();                  // wyjmuje pierwszy element
                }
                itemsLeft--;
                System.out.println("  Consumer got item: " + item);
            }
        }, "konsument");

        consumer.start();
        producer.start();
        producer.join();
        consumer.join();

        System.out.println("Obrotów pętli konsumenta: " + obroty + " (elementów było " + ITEM_COUNT + ")");
    }


    private static void wariant2WaitNotify() throws InterruptedException {


        // Każdy obiekt w Javie ma te metody (z klasy Object):
        //   obj.wait()      - uśpij bieżący wątek, aż ktoś go obudzi
        //   obj.notify()    - obudź jeden śpiący wątek
        //   obj.notifyAll() - obudź wszystkie śpiące wątki
        // Wolno ich używać tylko wewnątrz synchronized (obj). Inaczej poleci wyjątek.

        Queue<String> queue = new LinkedList<>();

        Thread producer = new Thread(() -> {
            try {
                for (int i = 0; i < ITEM_COUNT; i++) {
                    Thread.sleep(200 + generator.nextInt(400));
                    synchronized (queue) {
                        queue.add("Item no. " + i);
                        queue.notify();                   // "hej, coś się pojawiło"
                    }
                }
            } catch (InterruptedException e) {
                System.out.println("Producent przerwany");
            }
        }, "producent");

        Thread consumer = new Thread(() -> {
            int itemsLeft = ITEM_COUNT;
            while (itemsLeft > 0) {
                String item;
                synchronized (queue) {
                    // Sprawdzamy w pętli while, a nie w if.
                    // Wątek czasem budzi się "sam z siebie", a wtedy kolejka nadal może być pusta.
                    while (queue.isEmpty()) {
                        uspienia++;
                        try {
                            // wait() otwiera zamek queue i usypia wątek.
                            // Dzięki temu producent może wejść i coś dodać.
                            // Po obudzeniu wątek znowu zamyka zamek i idzie dalej.
                            queue.wait();
                        } catch (InterruptedException e) {
                            Thread.currentThread().interrupt();
                            return;
                        }
                    }
                    item = queue.poll();
                }
                itemsLeft--;
                System.out.println("  Consumer got item: " + item);
            }
        }, "konsument");

        consumer.start();
        producer.start();
        producer.join();
        consumer.join();

        System.out.println("Konsument zasnął " + uspienia + " razy - zamiast milionów pustych obrotów");
    }
}
