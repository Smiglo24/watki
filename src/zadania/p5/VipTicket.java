package zadania.p5;

public class VipTicket extends Ticket {
    public VipTicket(int id, double price, Seance seance,int seat) {
        super(id, price, seance,seat);
    }

    @Override
    public double getPrice() {
        return getBasePrice()*1.5;
    }

    @Override
    public String getType() {
        return "VIP";
    }
}
