package GUI;

import Event.TickEvent;

import javax.swing.*;
import java.awt.*;

public class GameUI extends JPanel {
    private TickEvent.TickEventListener tickEventListener = this::tickEvent;
    private JTable table;
    private CustomTable data;
    private Board board;

    public GameUI(Board board) {
        data = new CustomTable();
        this.board = board;

        setLayout(new BorderLayout());
        setOpaque(false);

        table = new JTable();
        table.setOpaque(false);
        table.setModel(data);
        table.setDefaultRenderer(Integer.class, new UserView());
        table.setRowMargin(0);
        table.setRowHeight(80);
        table.setPreferredSize(new Dimension(800, 600));
        add(table);
        table.setGridColor(new Color(255, 0, 0, 0));
        setPreferredSize(new Dimension(800, 600));

        TickEvent.addTickEventListener(tickEventListener);
    }

    public void tickEvent(){
        data.setData(board.getBoardArr());
        table.setModel(data);
        repaint();
    }
}