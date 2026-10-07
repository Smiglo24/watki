package wstep.p8;

import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;


public class Main {

    public static void main(String[] args) throws InterruptedException {
        przyklad1ZwyklaKolejka();
        przyklad2ProducentKonsument();
    }

    // Czym jest kolejka?
    // To kolekcja, która działa jak kolejka w sklepie: nowe elementy dodajemy na koniec,
    // a wyjmujemy zawsze z początku. Kto pierwszy wszedł, ten pierwszy wychodzi.
    //
    //   wyjmujemy stąd -> [Piotr][Ania][Tomek] <- dodajemy tutaj
    //
    // Queue to interfejs (jak List), a LinkedList to jedna z klas, które go implementują.
    //
    // Po co kolejka przy wątkach?
    // Jeden wątek wkłada do niej zadania, a drugi je wyjmuje i wykonuje (przykład 2).
    private static void przyklad1ZwyklaKolejka() {
        System.out.println("=== 1. Queue ===");

        Queue<String> queue = new LinkedList<>();
        queue.offer("Piotr");    // offer() - dodaj na koniec
        queue.offer("Ania");
        queue.offer("Tomek");
        System.out.println("kolejka: " + queue);

        System.out.println("peek():  " + queue.peek() + "   <- tylko podgląda pierwszego");
        System.out.println("poll():  " + queue.poll() + "   <- wyjmuje pierwszego");
        System.out.println("kolejka: " + queue);
    }

    // BlockingQueue to kolejka bezpieczna dla wielu wątków, która potrafi poczekać:
    //   put()  - gdy kolejka pełna, czeka, aż zwolni się miejsce
    //   take() - gdy kolejka pusta, czeka, aż coś się pojawi
    private static void przyklad2ProducentKonsument() throws InterruptedException {
        System.out.println("\n=== 2. Producent-konsument na BlockingQueue ===");

        BlockingQueue<String> queue = new ArrayBlockingQueue<>(3);   // mieści najwyżej 3 elementy

        Thread producent = new Thread(() -> {
            try {
                for (int i = 1; i <= 5; i++) {
                    queue.put("paczka " + i);
                    System.out.println("Producent dodał paczkę " + i);
                }
            } catch (InterruptedException e) {
                System.out.println("Producent przerwany");
            }
        });

        Thread konsument = new Thread(() -> {
            try {
                for (int i = 1; i <= 5; i++) {
                    String paczka = queue.take();
                    System.out.println("  Konsument wziął " + paczka);
                    Thread.sleep(200);   // konsument jest wolniejszy, więc producent czasem czeka
                }
            } catch (InterruptedException e) {
                System.out.println("Konsument przerwany");
            }
        });

        producent.start();
        konsument.start();
        producent.join();
        konsument.join();
    }
}
