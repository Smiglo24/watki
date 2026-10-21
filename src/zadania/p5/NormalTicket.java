package zadania.p5;

public class NormalTicket extends Ticket {
    public NormalTicket(int id, double price, Seance seance,int seat) {
        super(id, price, seance,seat);
    }

    @Override
    public double getPrice() {
        return getBasePrice();
    }

    @Override
    public String getType() {
        return "Normal";
    }
}
