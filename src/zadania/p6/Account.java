package zadania.p6;

class Account {

    private final int number;
    private int balance;

    Account(int number, int balance) {
        this.number = number;
        this.balance = balance;
    }

    int getNumber() {
        return number;
    }

    int getBalance() {
        return balance;
    }

    void changeBalance(int amount) {
        balance += amount;
    }
}
