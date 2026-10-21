package zadania.p5;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.*;

public class Seance {
    private LocalDateTime date;
    private Hall hall;
    private Film film;
    private List<Ticket> tickets=new ArrayList<>();
    private Map<Integer, Ticket> occupiedSeats = new HashMap<>();

    public Seance(LocalDateTime date, Hall hall, Film film) {
        this.date = date;
        this.hall = hall;
        this.film = film;
    }

    @Override
    public String toString() {
        return "Seance{" +
                "date=" + date +
                ", hall=" + hall +
                ", film=" + film ;

    }
    public synchronized String reserve(Ticket ticket, Client client) {


        if (ticket.getClient() != null ||
                tickets.stream()
                        .anyMatch(t -> t.getId() == ticket.getId())) {
            throw new IllegalStateException(
                    "someone bought that ticket before");
        }

        if (occupiedSeats.containsKey(ticket.getSeatNumber())) {
            throw new IllegalStateException(
                    "seat occpied");
        }

        occupiedSeats.put(ticket.getSeatNumber(), ticket);
        tickets.add(ticket);
        ticket.assignClient(client);
        client.addTicket(ticket);
        return client.getName() + " reserved ticket " + ticket.getId() ;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
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
    public Set<Integer> getOccupiedSeats() {
        Set<Integer> occupiedSeats = new HashSet<>();
        for (Ticket ticket : tickets) {
            occupiedSeats.add(ticket.getSeatNumber());
        }
        return occupiedSeats;
    }
    public int getFreeSeats() {
        return hall.getLimit() - getOccupiedSeats().size();
    }
}
