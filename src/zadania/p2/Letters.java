package zadania.p2;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Letters implements Iterable<Thread> {

    private final List<Thread> threads = new ArrayList<>();
    private volatile boolean running = true;

    public Letters(String text) {
        for (char letter : text.toCharArray()) {
            Thread thread = new Thread(() -> {
                while (running) {
                    System.out.print(letter);
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        break;
                    }
                }
            });

            thread.setName("Thread " + letter);
            threads.add(thread);
        }
    }

    public void start() {
        for (Thread thread : threads) {
            thread.start();
        }
    }

    public void stop() {
        running = false;

        for (Thread thread : threads) {
            thread.interrupt();
        }
    }

    @Override
    public Iterator<Thread> iterator() {
        return threads.iterator();
    }
}