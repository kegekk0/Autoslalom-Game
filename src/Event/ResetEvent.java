package Event;

import Logic.Tick;

import java.util.ArrayList;
import java.util.List;

public class ResetEvent {
    private static final List<ResetEventListener> listeners = new ArrayList<>();

    public ResetEvent() {
        Tick.getInstance().resetGame();
    }

    public interface ResetEventListener {
        void resetEventOccurred();
    }

    public static void addResetEventListener(ResetEventListener listener) {
        listeners.add(listener);
    }

    public static void notifyResetEvent() {
        for (ResetEventListener listeners : listeners)
            listeners.resetEventOccurred();
    }
}