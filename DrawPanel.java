import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import javax.imageio.ImageIO;
import javax.swing.*;

// This panel represents the animated part of the view with the car images.

public class DrawPanel extends JPanel{
    ArrayList<Sprite> sprites = new ArrayList<>(3);

    BufferedImage volvoWorkshopImage;
    Point volvoWorkshopPoint = new Point(300,300);

    void moveit(int x, int y, Sprite sprite) {
        sprite.position.setPosition(new Position(x, y));
    }

    // Initializes the panel and reads the images
    public DrawPanel(int x, int y) {
        this.setDoubleBuffered(true);
        this.setPreferredSize(new Dimension(x, y));
        this.setBackground(Color.green);

        sprites.add(new Sprite(new Position(0, 0), "pics/Volvo240.jpg"));
        sprites.add(new Sprite(new Position(0, 0), "pics/Saab95.jpg"));
        sprites.add(new Sprite(new Position(0, 0), "pics/Scania.jpg"));

        try {
            volvoWorkshopImage = ImageIO.read(DrawPanel.class.getResourceAsStream("pics/VolvoBrand.jpg"));
        } catch (IOException ex)
        {
            ex.printStackTrace();
        }
    }

    // This method is called each time the panel updates/refreshes/repaints itself
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        for (Sprite sprite : sprites) {
            sprite.drawImage(g);
        }

        g.drawImage(volvoWorkshopImage, volvoWorkshopPoint.x, volvoWorkshopPoint.y, null);

    }
}
