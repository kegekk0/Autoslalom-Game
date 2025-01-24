package GUI;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.table.TableCellRenderer;
import java.awt.*;
import java.io.File;
import java.io.IOException;

public class UserView extends JPanel implements TableCellRenderer {
    private Image obstacle;
    private Image car;
    private Image rightBorder;
    private Image leftBorder;
    static double[][] itemCoords = {
            {22,23,24},
            {19,20.5,22.2},
            {15.5, 18, 21},
            {12, 16, 19.7},
            {9.5, 14, 18},
            {6.5, 12, 16.7},
            {5, 11, 17}
    };
    int row = 0;
    int value = 1;

    public UserView() {
        setOpaque(false);
        try {
            obstacle = ImageIO.read(new File(".\\assets\\Obstacle.png"));
            car = ImageIO.read(new File(".\\assets\\autko.png"));
            rightBorder = ImageIO.read(new File(".\\assets\\rightBorder.png"));
            leftBorder = ImageIO.read(new File(".\\assets\\leftBorder.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
        this.value = (int)value;
        this.row = row;
        return this;
    }
    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        if (shouldDrawBorder()) {
            drawLeftBorder(g);
            drawRightBorder(g);
        }

        if (row!= 6) {
            drawObstacles(g);
        } else {
            drawCar(g);
        }
    }

    private boolean shouldDrawBorder() {
        return CustomTable.getCount() % 2 == row % 2 && row!= 6 && CustomTable.getCount()!= 0;
    }

    private void drawLeftBorder(Graphics g) {
        int x = (int) (itemCoords[row][0] * 32) - 40 * (row + 1) / 2;
        int y = row == 0? 10 : 0;
        int width = 38 * (row + 1) / 2;
        int height = 29 * (row + 1) / 2;
        g.drawImage(leftBorder, x, y, width, height, null);
    }

    private void drawRightBorder(Graphics g) {
        int x = (int) (itemCoords[row][2] * 32) + 40 * (row + 1) / 2;
        int y = row == 0? 10 : 0;
        int width = 15 * (row + 1) / 2;
        int height = 20 * (row + 1) / 2;
        g.drawImage(rightBorder, x, y, width, height, null);
    }

    private void drawObstacles(Graphics g) {
        if (value > 0 && value < 4) {
            drawObstacle(g, value - 1);
        } else if (value > 3) {
            for (int j = 0; j < 3; j++) {
                if (j!= value - 4) {
                    drawObstacle(g, j);
                }
            }
        }
    }

    private void drawObstacle(Graphics g, int index) {
        int x = (int) (itemCoords[row][index] * 32);
        int y = row == 0? 10 : 0;
        int width = 45 * (row + 1) / 2;
        int height = 15 * (row + 1) / 2;
        g.drawImage(obstacle, x, y, width, height, null);
    }

    private void drawCar(Graphics g) {
        int x = (int) (itemCoords[row][value] * 32);
        int y = 0;
        int width = 100;
        int height = 75;
        g.drawImage(car, x, y, width, height, this);
    }
}