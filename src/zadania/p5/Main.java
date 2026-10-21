package zadania.p5;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;

public class Main {

    private static final Scanner scanner = new Scanner(System.in);
    private static final DateTimeFormatter FORMAT =
            DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

    private static final Map<Integer, Client> clients = new HashMap<>();
    private static final AtomicInteger nextClientId = new AtomicInteger(1);
    private static final AtomicInteger nextTicketId = new AtomicInteger(1);
    private static final AtomicInteger nextSeanceId = new AtomicInteger(1);
    private static final double BASE_PRICE = 30.0;

    private static Cinema cinema;

    public static void main(String[] args) {
        createInitialData();

        while (true) {
            printMenu();
            int choice = readInt("Wybierz opcje ");

            try {
                switch (choice) {
                    case 1 -> showSchedule();
                    case 2 -> showClients();
                    case 3 -> addClient();
                    case 4 -> reserveTicket();
                    case 5 -> showSeats();
                    case 6 -> addSeance();
                    case 7 -> findFilm();
                    case 0 -> {
                        return;
                    }
                }
            } catch (RuntimeException e) {
                System.out.println("Blad");
            }
        }
    }
    private static void createInitialData() {
        Hall hall1 = new Hall(1, 20);
        Hall hall2 = new Hall(2, 15);
        cinema = new Cinema(List.of(hall1, hall2));
        Film film1 = new Film("Adam", "lot");
        Film film2 = new Film("Andrew", "gwiezdne wojny");
        Film film3 = new Film("John", "minionki");
        cinema.addSeance(new Seance(LocalDateTime.of(2026,10,22,3,20), hall1, film1));
        cinema.addSeance(new Seance (LocalDateTime.of(2026,10,22,3,20), hall2, film2));
        cinema.addSeance(new Seance(LocalDateTime.of(2026,10,22,11,20),hall1, film3));
        Client anna = new Client(nextClientId.getAndIncrement(), "Anna");
        Client jan = new Client(nextClientId.getAndIncrement(), "Jan");
        clients.put(anna.getId(), anna);
        clients.put(jan.getId(), jan);
    }


    private static void printMenu() {
        System.out.println("1. wyswietl seanse");
        System.out.println("2. wyswietl klientow");
        System.out.println("3. dodaj klienta");
        System.out.println("4. zarezerwuj bilet");
        System.out.println("5. sprawdz wolne miejsca");
        System.out.println("6. dodaj seans");
        System.out.println("7. znajdz seanse filmu");
        System.out.println("0. wyjscie");
    }

    private static String readText(String prompt) {
        while (true) {
            System.out.print(prompt);
            String text = scanner.nextLine().trim();
            if (!text.isEmpty()) {
                return text;
            }

        }
    }
    private static int readInt(String prompt) {
            return Integer.parseInt(readText(prompt));

    }
    private static LocalDateTime readDateTime() {
            return LocalDateTime.parse(readText("data i godzina (yyyy-MM-dd HH:mm): "),FORMAT);
    }

    private static Client getClient() {
        int id = readInt("client id ");
        return clients.get(id);
    }

    private static void showSchedule() {
       int count=0;
        for(Seance s:cinema.getSeanceList()){
           count++;
            System.out.println(count+" "+" "+s);
       }
    }

    private static void showClients() {
        clients.values().stream()
                .sorted(Comparator.comparingInt(Client::getId))
                .forEach(c -> System.out.println(
                        c + " -> " + c.getTickets()));
    }
    private static void addClient() {
        String name = readText("Name ");

        Client client = new Client(
                nextClientId.getAndIncrement(), name);

        clients.put(client.getId(), client);
    }
    private static Ticket createTicket(Seance seance, int seat, String type) {

        String normalized = type.toUpperCase(Locale.ROOT);
        int id = nextTicketId.getAndIncrement();

        return switch (normalized) {
            case "N" -> new NormalTicket(id, BASE_PRICE, seance, seat);

            case "U" -> new HalfPriceTicket(id, BASE_PRICE, seance, seat);

            case "V" -> new VipTicket(id, BASE_PRICE, seance, seat);
            default -> throw new IllegalStateException("Unexpected value" );
        };
    }
    private static void reserveTicket() {
        Client client = getClient();

        Seance seance = cinema.getSeance(readInt("seance id: "));

        int seat = readInt("seat number ");

        String type = readText("Bilet (N-normalny, U-ulgowy, V-VIP): ");
        Ticket ticket = createTicket(seance, seat, type);

        System.out.println(ticket.reserve(client));

        System.out.print(ticket.getId()+ ticket.getType()+ticket.getPrice());
        System.out.println();
    }

    private static void showSeats() {
        Seance seance = cinema.getSeance(readInt("seance id "));

        System.out.println("zajete miejsca: " + new TreeSet<>(seance.getOccupiedSeats()));

        System.out.println("Wolnych miejsc: " + seance.getFreeSeats());
    }

    private static Seance readSeance(int id) {
        LocalDateTime dateTime = readDateTime();

        Hall hall = cinema.getHall(readInt("hall number"));

        String title = readText("title ");
        String author = readText("director ");

        return new Seance(dateTime, hall, new Film(author, title));
    }

    private static void addSeance() {
        int id = nextSeanceId.getAndIncrement();
        cinema.addSeance(readSeance(id));
    }

    private static void findFilm() {
        String title = readText("title ");
        List<Seance> found = cinema.findSeancesForFilm(title);
        if (found.isEmpty()) {
            System.out.println("brak");
        } else {
            found.forEach(System.out::println);
        }
    }


}
