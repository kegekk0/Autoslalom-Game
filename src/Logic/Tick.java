package Logic;

import Event.TickEvent;

public class Tick extends Thread {
    private static Tick instance;
    private Thread thread;
    private boolean running;
    private int interval;
    private static final int gameInterval = 700;

    private Tick() {
        this.running = false;
        this.interval = gameInterval;
    }

    public static synchronized Tick getInstance() {
        if (instance == null) {
            instance = new Tick();
        }
        return instance;
    }

    @Override
    public void run() {
        running = true;
        while (running) {
            try {
                Thread.sleep(interval);
                TickEvent.notifyTickEventListeners();
                fasterTick();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    private void fasterTick() {
        if (interval > 400) {
            interval -= 10;
        }
    }

    public void startGame() {
        if (!running) {
            interval = gameInterval;
            thread = new Thread(this);
            thread.start();
        }
    }

    public void stopGame() {
        running = false;
        thread = null;
    }

    public void resetGame() {
        stopGame();
        interval = gameInterval;
    }
}