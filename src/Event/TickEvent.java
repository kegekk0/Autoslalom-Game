package Event;

import java.util.ArrayList;
import java.util.List;

public class TickEvent {
    private static final List<TickEventListener> listeners = new ArrayList<>();

    public interface TickEventListener {
        void tickEventOccurred();
    }

    public static void addTickEventListener(TickEventListener listener) {
        listeners.add(listener);
    }

    public static void notifyTickEventListeners() {
        for (TickEventListener listener : listeners)
            listener.tickEventOccurred();
    }
}