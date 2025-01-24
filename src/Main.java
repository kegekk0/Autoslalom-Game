import GUI.Background;
import GUI.Board;
import GUI.GameUI;
import Logic.Score;

import javax.swing.*;
import java.awt.*;

public class Main {
    public static void main(String[] args) {
        JFrame frame = new JFrame("Autoslalom Game");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());

        Image backgroundImage = new ImageIcon("./assets/background.png").getImage();
        Background background = new Background(backgroundImage);

        JLayeredPane layeredPane = new JLayeredPane();
        layeredPane.setPreferredSize(new Dimension(800, 600));

        background.setBounds(0, 0, 800, 600);
        layeredPane.add(background, JLayeredPane.DEFAULT_LAYER);

        Score scoreCounter = new Score();
        scoreCounter.setBounds(100, 50, 100, 75);
        layeredPane.add(scoreCounter, JLayeredPane.MODAL_LAYER);

        Board board = new Board(scoreCounter);
        board.setBounds(500, -50, 1, 1);
        layeredPane.add(board, JLayeredPane.MODAL_LAYER);

        GameUI gamePanel = new GameUI(board);
        gamePanel.setBounds(0, 0, 800, 600);
        layeredPane.add(gamePanel, JLayeredPane.MODAL_LAYER);

        frame.add(layeredPane, BorderLayout.CENTER);
        frame.setSize(800, 600);
        frame.setVisible(true);

        frame.addKeyListener(board);
    }
}