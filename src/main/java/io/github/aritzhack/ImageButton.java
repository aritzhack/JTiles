package io.github.aritzhack;

import io.github.aritzhack.aritzh.util.Nullable;

import javax.imageio.ImageIO;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import java.awt.Color;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

/**
 * @author Aritz Lopez
 */
public class ImageButton extends JButton implements ActionListener {

    public static final ImageIcon defaultIcon;

    private final JFileChooser FC = new JFileChooser();
    @Nullable
    private Image image = null;

    static {
        BufferedImage trImage = new BufferedImage(500, 500, BufferedImage.TYPE_4BYTE_ABGR);

        trImage.getGraphics().setColor(new Color(0, 0, 0, 0));
        trImage.getGraphics().fillRect(0, 0, 500, 500);

        ImageIcon tIcon = null;
        try {
            tIcon = new ImageIcon(ImageIO.read(ImageButton.class.getResourceAsStream("/empty.png")));
        } catch (IOException e) {
            tIcon = new ImageIcon(trImage);
        }
        defaultIcon = tIcon;
    }

    public ImageButton() {
        super();
        this.addActionListener(this);
    }

    public ImageButton(String image) {
        super();
        this.setImage(ImageButton.getImage(image));
        this.fitIcon();
        this.addActionListener(this);
    }

    public void setImage(@Nullable Image image) {
        this.image = image;
        if (this.image != null) this.setIcon(new ImageIcon(this.image));
        else this.setIcon(defaultIcon);
        this.revalidate();
    }

    protected static Image getImage(String image) {
        return Toolkit.getDefaultToolkit().getImage(image);
    }

    public void fitIcon() {
        if (this.getIcon() instanceof ImageIcon) {
            ImageIcon icon = (ImageIcon) this.getIcon();
            icon.setImage(icon.getImage().getScaledInstance(this.getWidth(), this.getWidth(), Image.SCALE_DEFAULT));
        }
    }

    @Override
    public void setIcon(Icon icon) {
        super.setIcon(icon);
        this.fitIcon();
    }

    @Override
    public void setBounds(int x, int y, int width, int height) {
        // noinspection SuspiciousNameCombination
        super.setBounds(x, y, width, width); // It is width too so that the button is square
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
                this.setImage(ImageButton.getImage(f.getAbsolutePath()));
                break;
            case JFileChooser.CANCEL_OPTION:
                break;
            case JFileChooser.ERROR_OPTION:
                break;
        }
    }

    @Nullable
    public Image getImage() {
        return image;
    }
}
