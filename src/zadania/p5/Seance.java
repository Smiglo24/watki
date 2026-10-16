package zadania.p5;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Seance {
    private LocalDate date;
    private Hall hall;
    private Film film;
    private List<Ticket> tickets=new ArrayList<>();

    public Seance(LocalDate date, Hall hall, Film film) {
        this.date = date;
        this.hall = hall;
        this.film = film;
    }

    @Override
    public String toString() {
        return "Seance{" +
                "date=" + date +
                ", hall=" + hall +
                ", film=" + film +
                ", tickets=" + tickets +
                '}';
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public Hall getHall() {
        return hall;
    }

    public void setHall(Hall hall) {
        this.hall = hall;
    }

    public Film getFilm() {
        return film;
    }

    public void setFilm(Film film) {
        this.film = film;
    }

    public List<Ticket> getTickets() {
        return tickets;
    }

    public void setTickets(List<Ticket> tickets) {
        this.tickets = tickets;
    }
}
