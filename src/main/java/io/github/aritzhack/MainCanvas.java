package io.github.aritzhack;

import javax.swing.JPanel;
import javax.swing.border.BevelBorder;
import java.awt.Color;
import java.awt.Graphics;

/**
 * @author Aritz Lopez
 */
public class MainCanvas extends JPanel {

    public MainCanvas() {
        this.setBorder(new BevelBorder(BevelBorder.LOWERED));

    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        if (!this.isDoubleBuffered()) this.setDoubleBuffered(true);

        g.setColor(Color.BLACK);
        g.fillRect(0, 0, this.getWidth(), this.getHeight());

        // this.repaint();
    }
}
