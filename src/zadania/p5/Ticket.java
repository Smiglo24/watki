package zadania.p5;

public abstract class  Ticket {
    private int id;
    private double price;
    private Seance seance;
    private Client client;
    private int seatNumber;
    public Ticket(int id, double price, Seance seance,int seatNumber) {
        this.id = id;
        this.price = price;
        this.seance = seance;
        this.seatNumber=seatNumber;

    }

    @Override
    public String toString() {
        return "Ticket{" +
                "id=" + id +
                ", price=" + price +
                ", seance=" + seance;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getSeatNumber() {
        return seatNumber;
    }

    public void setSeatNumber(int seatNumber) {
        this.seatNumber = seatNumber;
    }

    public String reserve(Client client) {
        return seance.reserve(this, client);
    }
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public abstract double getPrice();
    public abstract String getType();
    public void assignClient(Client client){
        this.client=client;
    }

    public double getBasePrice(){
        return price;
    }
    public void setBasePrice(double price) {
        this.price = price;
    }

    public Seance getSeance() {
        return seance;
    }

    public void setSeance(Seance seance) {
        this.seance = seance;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }
}
