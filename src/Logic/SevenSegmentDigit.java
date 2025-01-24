package Logic;

import Event.PlusOneEvent;

import javax.swing.*;
import java.awt.*;

public class SevenSegmentDigit extends JPanel {
    private int value;
    private PlusOneEvent.PlusOneEventListener plusOneEventListener = this::increment;
    private PlusOneEvent.PlusOneEventListener nextDigitUp;

    public SevenSegmentDigit(Dimension size, PlusOneEvent.PlusOneEventListener next) {
        this.value = 0;
        setOpaque(false);
        this.setPreferredSize(size);
        this.nextDigitUp = next;
    }

    public PlusOneEvent.PlusOneEventListener getListener(){
        return this.plusOneEventListener;
    }

    private void drawDigit(Graphics g, int value) {
        boolean[][] segments = {
                {true, true, true, true, true, true, false},   // from 0 down
                {false, true, true, false, false, false, false},
                {true, true, false, true, true, false, true},
                {true, true, true, true, false, false, true},
                {false, true, true, false, false, true, true},
                {true, false, true, true, false, true, true},
                {true, false, true, true, true, true, true},
                {true, true, true, false, false, false, false},
                {true, true, true, true, true, true, true},
                {true, true, true, true, false, true, true}
        };

        int[][] positions = {
                {5, 5, 15, 5}, // top
                {20, 10, 5, 15}, // top right
                {20, 30, 5, 15}, // bottom right
                {5, 45, 15, 5}, // bottom
                {0, 30, 5, 15},  // bottom left
                {0, 10, 5, 15},  // top left
                {5, 25, 15, 5}  // middle
        };

        g.setColor(Color.BLACK);
        for (int i = 0; i < segments[value].length; i++) {
            if (segments[value][i]) {
                int[] pos = positions[i];
                g.fillRect(pos[0], pos[1], pos[2], pos[3]);
            }
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        drawDigit(g, value);
    }

    public void increment() {
        if (value == 9) {
            value = 0;
            nextDigitUp.plusOneEventOccurred();
        } else {
            value++;
        }
        repaint();
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
        repaint();
    }
}
