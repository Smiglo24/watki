package wstep;

public class Main2 {
    public static void main(String[] args) throws InterruptedException {


        przyklad1Join();
        przyklad2Przeplot();
    }


    // Liczy od 1 do 5, z przerwą między liczbami.
    private static void licz(String kto) {
        for (int i = 1; i <= 5; i++) {
            System.out.println(kto + " " + i);
            try {
                // sleep(100) - wątek śpi 100 milisekund. W tym czasie nie zajmuje procesora.
                Thread.sleep(100);
            } catch (InterruptedException e) {
                return;
            }
        }
    }

    // join() - czekanie na koniec wątku.
    //
    // thread.join() działa tak:
    // - Wątek, który wywołał join() (tutaj main), zatrzymuje się w tej linijce.
    // - Czeka, aż wątek "thread" skończy całą swoją pracę (cały kod z run() albo z lambdy).
    // - Potem main rusza dalej, od następnej linijki.
    // - Jeśli "thread" już wcześniej skończył, join() nie czeka wcale.
    //
    // join() NIE uruchamia wątku (to robi start()) i NIE zatrzymuje go. Tylko czeka.
    // Po join() mamy pewność, że wątek skończył, więc możemy użyć jego wyników.
    private static void przyklad1Join() throws InterruptedException {
        System.out.println("=== 1. join() ===");

        Thread thread = new Thread(() -> licz("Wątek"));
        thread.start();

        System.out.println("main czeka na wątek...");
        //thread.join();   // main stoi tutaj, dopóki wątek nie skończy
        System.out.println("Wątek skończył, main idzie dalej");

    }

    // main i nowy wątek liczą w tym samym czasie.
    private static void przyklad2Przeplot() throws InterruptedException {
        System.out.println("\n=== 2. main i wątek naraz ===");

        Thread thread = new Thread(() -> licz("Wątek"));
        thread.start();

        licz("main");    // main nie czeka - od razu liczy razem z wątkiem

        thread.join();

    }
}
