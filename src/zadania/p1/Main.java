package zadania.p1;

public class Main {
    /* Zadanie: Towary (8 punktów)
     Kod, działający w wątku A czyta z pliku ../Towary.txt informacje o towarach w postaci:
     id_towaru waga
     tworzy obiekty klasy Towar, zawierające przeczytane informacje oraz wyprowadza na konsolę informacje
     o liczbie utworzonych obiektów. Informacja ma być wyprowadzana co 200 obiektów w postaci:
     utworzono 200 obiektów
     utworzono 400 obiektów
     utworzono 600 obiektów
     itd.
     Kod działający równolegle w innym wątku (B) sięga po te obiekty, sumuje wagę towarów i wyprowadza
     na konsolę informację o przeprowadzonym sumowaniu co 100 obiektów np.:
     policzono wage 100 towarów
     policzono wage 200 towarów
     policzono wage 300 towarów
     itd.
     Na końcu podaje sumaryczną wagę wszystkich towarów.
             Uwagi:
             1. Plik powinien zawierać co najmniej 10 tys. opisów towarów (należy sobie go wygenerować
             programistycznie, ale na boku, nie w tym programie), nazwa pliku (wraz ze ścieżką) jest
     obowiązkowa , proszę nie dołączać tego pliku do projektu..
             2. Zapewnić synchronizację i koordynację pracy obu wątków.
 3. Forma wydruku na konsoli jest obowiązakowa
 4. Wszystkie klasy w programie winny być publiczne (w różnych plikach)
 5. Wykonanie programu winno zaczynać się w metodzide main() obowiązakowej klasy Main.
             6. Plik winien znajdować się w nadkatalogu projektu (czyli w katalogu workspace'u)
             */
    static void main() {
        Repository repository = new Repository();

        Thread threadA = new Thread(new CargoCreator(repository));
        Thread threadB = new Thread(new CargoWeightSum(repository));

        threadA.start();
        threadB.start();

        try {
            threadA.join();
            threadB.join();
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

}
