package wstep;

public class Teoria {
    static void main() throws InterruptedException {

        // Teoria do opowiedzenia przy tablicy (10-15 minut).
        //
        // 1. Proces i wątek
        //    Proces to uruchomiony program. Ma swoją własną pamięć.
        //    Wątek to "pracownik" wewnątrz programu. Każdy program ma co najmniej jeden wątek - "main".
        //    Program może mieć wiele wątków, które pracują w tym samym czasie.
        //
        //    Co wątki mają wspólne, a co osobne?
        //
        //        +---------------------------- program ----------------------------+
        //        |   obiekty i ich pola (sterta)  ->  WSPÓLNE dla wszystkich wątków |
        //        |                                                                  |
        //        |   wątek "main"         wątek "T-1"          wątek "T-2"          |
        //        |   zmienne lokalne      zmienne lokalne      zmienne lokalne      |
        //        |   ->  każdy wątek ma SWOJE zmienne lokalne (stos)                |
        //        +------------------------------------------------------------------+
        //
        //    - zmienna lokalna jest bezpieczna, bo widzi ją tylko jeden wątek,
        //    - pole obiektu, które zmienia kilka wątków naraz, może sprawiać problemy.
        //
        // 2. Współbieżność i równoległość
        //    Przykład: zadanie1 trwa 5 godzin, zadanie2 - 3 sekundy, zadanie3 - 5 sekund.
        //
        //    - Jeden rdzeń procesora: zadania nie idą naprawdę naraz. Procesor bardzo szybko
        //      przełącza się między nimi (co kilka milisekund). Dzięki temu zadanie2 i zadanie3
        //      skończą się po kilku sekundach, a nie dopiero po 5 godzinach.
        //      To jest współbieżność.
        //
        //    - Wiele rdzeni: zadania naprawdę wykonują się w tym samym momencie.
        //      To jest równoległość.
        //
        //    Który wątek i kiedy dostaje procesor, decyduje system operacyjny.
        //    Nie mamy na to wpływu, więc nie możemy zakładać żadnej kolejności.
        //
        // 3. Po co wątki?
        //    - czekanie na plik, sieć, bazę danych - w tym czasie procesor może robić coś innego,
        //    - aplikacja okienkowa nie "zamarza" podczas długiej operacji,
        //    - duże obliczenia można podzielić między kilka rdzeni,
        //    - serwer obsługuje wielu użytkowników naraz.
        //
        // 4. Ile to kosztuje?
        //    - każdy wątek zajmuje pamięć (ok. 1 MB) i jego utworzenie trwa,
        //    - więcej wątków niż rdzeni nie przyspieszy obliczeń,
        //    - błędy w programach z wątkami pojawiają się losowo: raz tak, raz nie.
        //      Program może działać 99 razy dobrze, a za setnym razem zepsuć dane.
        //
        // 5. Stany wątku
        //    Wątek przez całe życie przechodzi przez różne stany. Sprawdzimy je metodą getState().
        //    Rysunek: https://www.samouczekprogramisty.pl/assets/images/2019/02/05_thread_states.svg
        //
        //    NEW           - wątek utworzony (new Thread), ale jeszcze nie uruchomiony
        //    RUNNABLE      - wątek pracuje albo czeka na swoją kolej do procesora
        //    BLOCKED       - chce wejść do kodu synchronized, ale inny wątek jest już w środku (krok 5)
        //    WAITING       - śpi i czeka bez limitu czasu, aż coś się stanie, np. join() albo wait()
        //    TIMED_WAITING - śpi przez określony czas, np. Thread.sleep(500)
        //    TERMINATED    - wątek skończył pracę. Nie da się go uruchomić drugi raz.
        //
        //    Kolejność: NEW -> RUNNABLE -> (czasem BLOCKED / WAITING / TIMED_WAITING) -> TERMINATED
        //    Te same stany widać w debuggerze IntelliJ (zakładka Threads).

        System.out.println("Liczba rdzeni (logicznych): " + Runtime.getRuntime().availableProcessors());

        System.out.println("Ten kod wykonuje wątek: " + Thread.currentThread().getName());


        demoSekwencyjnieVsWatki();

    }

    private static void demoSekwencyjnieVsWatki() throws InterruptedException {
        int liczbaZadan = 4;

        long start = System.currentTimeMillis();
        for (int i = 0; i < liczbaZadan; i++) {
            pobierzZSieci();
        }
        System.out.println("Sekwencyjnie: " + (System.currentTimeMillis() - start) + " ms");

        start = System.currentTimeMillis();
        Thread[] watki = new Thread[liczbaZadan];
        for (int i = 0; i < liczbaZadan; i++) {
            watki[i] = new Thread(() -> pobierzZSieci());
            watki[i].start();
        }

        // t.join() - "poczekaj, aż wątek t skończy pracę".
        // Wątek, który wywołuje join() (tutaj main), zatrzymuje się w tym miejscu.
        // Rusza dalej dopiero wtedy, gdy wątek t skończy swoje zadanie (metodę run()).
        // Jeśli t już skończył, join() nie czeka wcale.
        for (Thread t : watki) {
            t.join();
        }
        System.out.println("W wątkach:    " + (System.currentTimeMillis() - start) + " ms");
    }

    private static void pobierzZSieci() {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

}
