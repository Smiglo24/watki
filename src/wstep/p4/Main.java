package wstep.p4;

public class Main {
    static void main() throws InterruptedException {

        Thread thread = new Thread(() -> licz("Wątek"));
        thread.start();

        System.out.println("main czeka na wątek...");
       // thread.join();   // main stoi tutaj, dopóki wątek nie skończy
        System.out.println("Wątek skończył, main idzie dalej");
    }


    private static void licz(String kto) {
        for (int i = 1; i <= 5; i++) {
            System.out.println(kto + " " + i);
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                return;
            }
        }
    }
}
