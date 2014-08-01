package io.github.aritzhack;

import io.github.aritzhack.aritzh.logging.ILogger;
import io.github.aritzhack.aritzh.logging.SLF4JLogger;
import net.miginfocom.swing.MigLayout;

import javax.swing.JFrame;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.WindowConstants;
import java.awt.Dimension;

/**
 * @author Aritz Lopez
 */
public class Main {

    public static final JFrame FRAME = new JFrame("JTiles");
    public static final ILogger LOG = new SLF4JLogger("Main");

    public static void main(String[] args) {

        /*
        MAYBE Add a command line option to just open a "project" file
        MAYBE and simply update the output based on the changes made in
        MAYBE the input files
        */

        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException ignored) {
            try {
                UIManager.setLookAndFeel(UIManager.getCrossPlatformLookAndFeelClassName());
            } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException e) {
                e.printStackTrace();
            }
        }

        FRAME.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        FRAME.setLayout(new MigLayout("fill"));
        FRAME.add(new Panel(), "push, grow");
        FRAME.setMinimumSize(new Dimension(830, 600));
        FRAME.pack();
        FRAME.setVisible(true);


    }
}
