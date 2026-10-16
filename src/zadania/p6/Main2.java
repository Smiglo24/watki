package zadania.p6;

public class Main2 {


    static class TicketCounter {
        private int soldTickets = 0;

        public synchronized void sellTicket() {
            soldTickets++;
        }

        public int getSoldTickets() {
            return soldTickets;
        }
    }

    static void main() throws InterruptedException {
        TicketCounter counter = new TicketCounter();

        Runnable cashier = () -> {
            for (int i = 0; i < 10_000; i++) {
                counter.sellTicket();
            }
        };

        Thread cashier1 = new Thread(cashier);
        Thread cashier2 = new Thread(cashier);
        cashier1.start();
        cashier2.start();
        cashier1.join();
        cashier2.join();

        System.out.println("Sprzedane bilety: " + counter.getSoldTickets() + " (powinno być 20000)");
    }
}
