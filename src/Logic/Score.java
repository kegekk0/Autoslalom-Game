package Logic;

import Event.*;

import javax.swing.*;
import java.awt.*;

public class Score extends JPanel {
    private SevenSegmentDigit hundreds;
    private SevenSegmentDigit tens;
    private SevenSegmentDigit ones;

    public Score() {
        setLayout(new FlowLayout(FlowLayout.LEFT));
        Dimension digitSize = new Dimension(25, 50);
        hundreds = new SevenSegmentDigit(digitSize, null);
        tens = new SevenSegmentDigit(digitSize, hundreds.getListener());
        ones = new SevenSegmentDigit(digitSize, tens.getListener());

        add(hundreds);
        add(tens);
        add(ones);
        setOpaque(false);

        ResetEvent.ResetEventListener resetEventListener = this::resetGame;
        ResetEvent.addResetEventListener(resetEventListener);
        StartEvent.StartEventListener startEventListener = this::startGame;
        StartEvent.addStartEventListener(startEventListener);
        PlusOneEvent.PlusOneEventListener plusOneEventListener = () -> {
            ones.getListener().plusOneEventOccurred();
        };
        PlusOneEvent.addPlusOneEventListener(plusOneEventListener);
    }

    public void startGame(){
        this.setVisible(true);
        ones.setValue(0);
        tens.setValue(0);
        hundreds.setValue(0);
    }

    public void resetGame() {
        this.setVisible(false);
    }

    public int countObstacles() {
        int count = 0;
        if (hundreds.getValue() == 0) {
            count++;
            if (tens.getValue() == 0) {
                count++;
            }
        }
        return count;
    }

    public boolean isMaxScore() {
        return hundreds.getValue() == 9 && tens.getValue() == 9 && ones.getValue() == 9;
    }
}