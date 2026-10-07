package zadania;

public class Zad2 {
    static void main() throws InterruptedException {

        /**
         * Zadanie 2
         *
         * 1. Wątek "Tik" wypisuje "Tik 1", "Tik 2", ... "Tik 5". Między napisami czeka 300 ms.
         * 2. Wątek "Tak" wypisuje "Tak 1", ... "Tak 5". Między napisami czeka 500 ms.
         * 3. Uruchom oba wątki naraz.
         * 4. main czeka na oba wątki, a potem wypisuje "Koniec" i czas działania w ms.
         *
         * Pytania:
         * - Co się stanie, gdy usuniesz join()? Kiedy wtedy wypisze się "Koniec"?

         */


        long start = System.currentTimeMillis();

        Thread tik = new Thread(() -> {
            for (int i = 1; i <= 5; i++) {
                System.out.println("Tik " + i);
                try {
                    Thread.sleep(300);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }

            }
        });

        Thread tak = new Thread(() -> {
            for (int i = 1; i <= 5; i++) {
                System.out.println("Tak " + i);

                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }

            }
        });
        tik.start();
        tak.start();
        tik.join();
        tak.join();
        long end = System.currentTimeMillis();
        System.out.println("finished");
        System.out.println(end-start+"ms");
    }
}
