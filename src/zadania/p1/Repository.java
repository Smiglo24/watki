package zadania.p1;
import java.util.concurrent.LinkedBlockingQueue;

public class Repository {

    private final LinkedBlockingQueue<Cargo> cargos = new LinkedBlockingQueue<>();
    private boolean prodEnd = false;

    public synchronized void add(Cargo cargo) {
        cargos.add(cargo);
        notifyAll();
    }

    public synchronized Cargo poll() {

        while (cargos.isEmpty() && !prodEnd) {
            try {
                wait();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                return null;
            }
        }
        return cargos.poll();
    }

    public synchronized void prodEnd() {
        prodEnd = true;
        notifyAll();
    }
}