package zadania.p1;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class CargoCreator implements Runnable {

    private final Repository repository;

    public CargoCreator(Repository repository) {
        this.repository = repository;
    }

    @Override
    public void run() {
        int counter = 0;
        try (BufferedReader reader = new BufferedReader(new FileReader("towary.txt"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                line = line.trim();
                if (line.isEmpty()) {
                    continue;
                }
                String[] data = line.split("\\s+");
                Cargo cargo = new Cargo(Integer.parseInt(data[0]), Double.parseDouble(data[1]));
                repository.add(cargo);
                counter++;

                if (counter % 200 == 0) {
                    System.out.println("utworzono " + counter + " obiektow");
                }
            }

            repository.prodEnd();

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}