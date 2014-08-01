package io.github.aritzhack;

import net.miginfocom.layout.AC;
import net.miginfocom.layout.LC;
import net.miginfocom.swing.MigLayout;

import javax.swing.JButton;
import javax.swing.JPanel;
import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * @author Aritz Lopez
 */
public class Panel extends JPanel {

    private final JPanel panelLeft, panelCenter, panelRight;
    private final JButton testButtonL, testButtonC, testButtonR;
    private final MainCanvas canvas;

    public Panel() {
        super(new MigLayout(
            new LC().gridGap("0px", "0px").insets("n 0")/*.debug(1000)*/,
            new AC().size("150px:220px:20%").gap("5").size("30%:60%:60%").gap("5").size("150px:220px:20%"),
            new AC().size("100%")
        ));

        // new MigLayout("gap 0px 0px, ins n 0" + ", debug", "[100px:110px:15%]5[60%:100%:100%]5[100px:110px:15%]", "[100%]")

        this.panelLeft = new JPanel(new MigLayout("ins 0"));
        this.panelCenter = new JPanel(new MigLayout("ins 0"));
        this.panelRight = new JPanel(new MigLayout("ins 0"));

        this.testButtonL = new JButton("Reload GUI!");
        this.testButtonC = new JButton("Center!");
        this.testButtonR = new JButton("Right!");

        this.canvas = new MainCanvas();

        testButtonL.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Container parent = Panel.this.getParent();
                parent.removeAll();
                parent.add(new Panel(), "push, grow");
                parent.revalidate();
            }
        });

        testButtonR.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Panel.this.repaintCanvas();
            }
        });

        this.initComponents();
    }

    private void repaintCanvas() {
        this.canvas.repaint();
    }

    private void initComponents() {
        this.add(this.panelLeft, "grow");
        this.add(this.panelCenter, "grow");
        this.add(this.panelRight, "grow");

        initLeftPane();
        initCenterPane();
        initRightPane();
    }

    private void initLeftPane() {
        this.panelLeft.setName("LeftPanel");

        this.panelLeft.add(this.testButtonL, "ay top, pushx, growx, wrap");
        this.panelLeft.add(new ImageButton(), "split 2, w 49%, wmax 49%, growy");
        this.panelLeft.add(new ImageButton(), "w 49%, wmax 49%, gapleft 2%, growy, wrap");

        this.panelLeft.add(new ImageButton(), "split 2, w 49%, wmax 49%, growy");
        this.panelLeft.add(new ImageButton(), "w 49%, wmax 49%, gapleft 2%, growy, wrap");

        this.panelLeft.add(new ImageButton(), "split 2, w 49%, wmax 49%, growy");
        this.panelLeft.add(new ImageButton(), "w 49%, wmax 49%, gapleft 2%, growy, wrap");

        this.panelLeft.setSize(this.panelLeft.getSize());

        // C:\Users\USUARIO\Desktop\Test.png
    }

    private void initCenterPane() {
        this.panelCenter.setName("CenterPanel");

        this.panelCenter.add(this.canvas, "ay top, pushx, growx, growy, pushy 60, wrap");
        this.panelCenter.add(this.testButtonC, "ay top, pushy 40");
    }

    private void initRightPane() {
        this.panelRight.setName("RightPanel");

        this.panelRight.add(this.testButtonR, "ay top, pushx, growx");
    }


}
