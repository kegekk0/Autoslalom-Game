package Event;

import Logic.Tick;

import java.util.ArrayList;
import java.util.List;

public class StartEvent{
    private static final List<StartEventListener> listeners = new ArrayList<>();

    public StartEvent() {
        Tick.getInstance().startGame();
    }

    public interface StartEventListener {
        void startEventOccurred();
    }

    public static void addStartEventListener(StartEventListener listener) {
        listeners.add(listener);
    }

    public void notifyStartEventListeners() {
        for (StartEventListener listener : listeners) {
            listener.startEventOccurred();
        }
    }
}