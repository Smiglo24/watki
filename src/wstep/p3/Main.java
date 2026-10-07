package wstep.p3;

public class Main {

    public static void main(String[] args) {
        Thread thread = new Thread(() -> licz("Wątek"));
        thread.start();

        licz("main");
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
}

