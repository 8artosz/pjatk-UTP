package zad2;

import java.util.concurrent.atomic.AtomicInteger;

public class StringTask implements Runnable {


    public enum TaskState {
        CREATED, RUNNING, ABORTED, READY
    }

    private volatile TaskState state;
    private volatile String text;
    private volatile String resultText;
    private final AtomicInteger ai = new AtomicInteger(0);
    public Thread thread;

    public StringTask() {
        state = TaskState.CREATED;
    }

    public StringTask(String a, int i) {
        this();
        text = a;
        ai.set(i);
        resultText = "";
    }

    public boolean isDone() {
        if (state == TaskState.READY || state == TaskState.ABORTED) return true;
        return false;
    }

    public void abort() {
        state = TaskState.ABORTED;
    }

    @Override
    public void run() {
        state = TaskState.RUNNING;
        while (ai.get() != 0 && state != TaskState.ABORTED) {
            resultText += text;
            ai.decrementAndGet();
        }
        if (state != TaskState.ABORTED) {
            state = TaskState.READY;
        }
    }

    public TaskState getState() {
        return state;

    }

    public String getResult() {
        return resultText;
    }

    public void start() {
        thread = new Thread(this);
        state = TaskState.RUNNING;
        thread.start();
    }
}