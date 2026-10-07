package zadania.p3;

public class StringTask implements Runnable{
    private final String text;
    private final int repetitions;

    private volatile TaskState state = TaskState.CREATED;
    private volatile boolean abortRequested = false;

    private String result = " ";
    private Thread thread;

    public StringTask(String text, int repetitions) {
        this.text = text;
        this.repetitions = repetitions;
    }

    @Override
    public void run() {
        state = TaskState.RUNNING;
        for (int i = 0; i < repetitions; i++) {
            if (abortRequested || Thread.currentThread().isInterrupted()) {
                state = TaskState.ABORTED;
                return;
            }
            result = result + text;
        }
        state = TaskState.READY;
    }

    public String getResult() {
        return result;
    }

    public TaskState getState() {
        return state;
    }

    public void start() {
        if (state == TaskState.CREATED) {
            thread = new Thread(this);
            thread.start();
        }
    }

    public void abort() {
        abortRequested = true;

        if (thread != null) {
            thread.interrupt();
        }
    }

    public boolean isDone() {
        return state == TaskState.READY || state == TaskState.ABORTED;
    }
}
