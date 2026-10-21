package zadania.p5;

public class Hall {
    private int id;
    private int limit;

    public Hall(int id, int limit) {
        this.id = id;
        this.limit = limit;
    }

    @Override
    public String toString() {
        return "Hall{" +
                "id=" + id +
                ", limit=" + limit +
                '}';
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getLimit() {
        return limit;
    }

    public void setLimit(int limit) {
        this.limit = limit;
    }
}
