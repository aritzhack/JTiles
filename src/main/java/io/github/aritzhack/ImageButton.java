package io.github.aritzhack;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

/**
 * @author Aritz Lopez
 */
public class ImageButton extends JButton implements ActionListener {

    private final JFileChooser FC = new JFileChooser();

    public ImageButton() {
        super();
        this.addActionListener(this);
    }

    public ImageButton(String image) {
        super(ImageButton.getIcon(image));
        this.fitIcon();
        this.addActionListener(this);
    }

    public static ImageIcon getIcon(String image) {
        return new ImageIcon(Toolkit.getDefaultToolkit().getImage(image));
    }

    public void fitIcon() {
        if (this.getIcon() instanceof ImageIcon) {
            ImageIcon icon = (ImageIcon) this.getIcon();
            icon.setImage(icon.getImage().getScaledInstance(this.getWidth(), this.getWidth(), Image.SCALE_DEFAULT));
        }
    }

    @Override
    public void setBounds(int x, int y, int width, int height) {
        super.setBounds(x, y, width, width);
        this.fitIcon();
        this.revalidate();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        FC.setFileSelectionMode(JFileChooser.FILES_ONLY);
        FC.setMultiSelectionEnabled(false);
        int result = FC.showOpenDialog(this);
        switch (result) {
            case JFileChooser.APPROVE_OPTION:
                File f = FC.getSelectedFile();
                this.setIcon(ImageButton.getIcon(f.getAbsolutePath()));
                this.fitIcon();
                break;
            case JFileChooser.CANCEL_OPTION:
                break;
            case JFileChooser.ERROR_OPTION:
                break;
        }

    }
}
