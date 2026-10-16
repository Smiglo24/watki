package zadania.p6;

public class Main {
    static void main() throws InterruptedException {
        Account account1 = new Account(1, 1000);
        Account account2 = new Account(2, 1000);


        Thread t1 = new Thread(() -> {
            for (int i = 1; i <= 1_000_000; i++) {
                transfer2(account1, account2, 10);
                if (i % 10 == 0) {
                    System.out.println("Wątek 1: przelew nr " + i + " (1 -> 2)");
                }
            }
        });
        Thread t2 = new Thread(() -> {
            for (int i = 1; i <= 1_000_000; i++) {
                transfer2(account2, account1, 10);
                if (i % 10 == 0) {
                    System.out.println("Wątek 2: przelew nr " + i + " (2 -> 1)");
                }
            }
        });

        t1.start();
        t2.start();
        t1.join();
        t2.join();


        System.out.println("Suma sald: " + (account1.getBalance() + account2.getBalance()) + " (powinno być 2000)");

    }

    // 1 2
    // 2 1
    private static void transfer1(Account from, Account to, int amount) {
        synchronized (from) {
            synchronized (to) {
                transfer(from, to, amount);
            }
        }
    }

    private static void transfer2(Account from, Account to, int amount) {
        Account first = from.getNumber() < to.getNumber() ? from : to;
        Account second = first == from ? to : from;

        synchronized (first) { //1
            synchronized (second) { // 2
                transfer(from, to, amount);
            }
        }
    }


    private static void transfer(Account from, Account to, int amount) {
        if (from.getBalance() >= amount) {
            from.changeBalance(-amount);
            to.changeBalance(amount);
        }
    }
}
