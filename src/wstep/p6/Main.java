package wstep.p6;

public class Main {


    public static void main(String[] args) throws InterruptedException {
        przyklad1Interrupt();
           przyklad2Volatile();
    }


    private static void przyklad1Interrupt() throws InterruptedException {
        System.out.println("=== 1. interrupt() ===");

        Thread t = new Thread(() -> {
            try {
                while (true) {
                    System.out.println("Pracuję...");
                    Thread.sleep(2500);
                }
            } catch (InterruptedException e) {
                // Nie zostawiaj pustego catch - to sygnał, że trzeba skończyć.
                System.out.println("Przerwano mnie, kończę");
            }
        });

        t.start();
        Thread.sleep(1200);
        t.interrupt();
        t.join();
    }

    private static volatile boolean stop = false;

    private static void przyklad2Volatile() throws InterruptedException {
        System.out.println("\n=== 2. Flaga volatile ===");

        Thread t = new Thread(() -> {
            while (!stop) {
                // "ciężka praca"
            }
            System.out.println("Zauważyłem stop, kończę");
        });

        t.start();
        Thread.sleep(2500);
        stop = true;
        t.join();

    }
}
