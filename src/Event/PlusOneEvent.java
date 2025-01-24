package Event;

import java.util.ArrayList;
import java.util.List;

public class PlusOneEvent {
    private static final List<PlusOneEventListener> listeners = new ArrayList<>();

    public interface PlusOneEventListener {
        void plusOneEventOccurred();
    }

    public static void notifyPlusOneListeners() {
        for(PlusOneEventListener listeners : listeners){
            listeners.plusOneEventOccurred();
        }
    }

    public static void addPlusOneEventListener(PlusOneEventListener listener) {
        listeners.add(listener);
    }
}