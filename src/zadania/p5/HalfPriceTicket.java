package zadania.p5;

public class HalfPriceTicket extends Ticket {
    public HalfPriceTicket(int id, double price, Seance seance,int seat) {
        super(id, price, seance,seat);
    }

    @Override
    public double getPrice() {
        return getBasePrice()*0.5;
    }

    @Override
    public String getType() {
        return "Half-Price";
    }
}
