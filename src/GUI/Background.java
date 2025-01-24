package GUI;

import javax.swing.*;
import java.awt.*;

public class Background extends JPanel {
    private final Image backgroundImage;

    public Background(Image backgroundImage) {
        this.backgroundImage = backgroundImage;
        setBackground(new Color(201,201,201));
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (backgroundImage != null) {
            g.drawImage(backgroundImage, 0, 0, this.getWidth(), this.getHeight(), this);
        }
    }
}