package Customs;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import javax.imageio.ImageIO;

public class ImagePanel extends JPanel {

    private Image img;

    public ImagePanel(String imgPath, LayoutManager layout) {
        try {
            System.out.println(imgPath);
            img = ImageIO.read(getClass().getResource(imgPath));
            this.setLayout(layout);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    public ImagePanel(Image img) {
        this.img = img;
        Dimension size = new Dimension(img.getWidth(null), img.getHeight(null));
        this.setPreferredSize(size);
        this.setMinimumSize(size);
        this.setMaximumSize(size);
        this.setSize(size);
        this.setLayout(null);
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(img, 0, 0, null);
    }
    
}
