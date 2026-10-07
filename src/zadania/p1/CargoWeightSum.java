package zadania.p1;

public class CargoWeightSum implements Runnable {

    private final Repository repository;

    public CargoWeightSum(Repository repository) {
        this.repository = repository;
    }

    @Override
    public void run() {
        double sum = 0;
        int counter = 0;
        while (true) {

            Cargo cargo = repository.poll();
            if (cargo == null) {
                break;
            }

            sum += cargo.getWeight();
            counter++;
            if (counter % 100 == 0) {
                System.out.println("policzono wage " + counter + " towarów");
            }
        }

        System.out.println("Sumaryczna waga wszystkich towarów: " + sum);
    }
}