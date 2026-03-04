import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import javax.imageio.ImageIO;
import javax.swing.*;

// This panel represents the animated part of the view with the car images.

public class DrawPanel extends JPanel{

    BufferedImage volvoWorkshopImage;
    Point volvoWorkshopPoint = new Point(300,300);

    SpriteGroup carSprites = new SpriteGroup();
    SpriteGroup workshopSprites = new SpriteGroup();

    void moveit(int x, int y, Sprite sprite) {
        sprite.position.setPosition(new Position(x, y));
    }

    // Initializes the panel and reads the images
    public DrawPanel(int x, int y) {
        this.setDoubleBuffered(true);
        this.setPreferredSize(new Dimension(x, y));
        this.setBackground(Color.green);

        Sprite volvo = new Sprite(new Position(0, 0), "pics/Volvo240.jpg");
        Sprite saab = new Sprite(new Position(0, 0), "pics/Saab95.jpg");
        Sprite scania = new Sprite(new Position(0, 0), "pics/Scania.jpg");

        Sprite volvoWorkshop = new Sprite(new Position(300, 300), "pics/VolvoBrand.jpg");

        carSprites.addSprite(volvo);
        carSprites.addSprite(saab);
        carSprites.addSprite(scania);

        workshopSprites.addSprite(volvoWorkshop);
    }


    // This method is called each time the panel updates/refreshes/repaints itself
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        carSprites.drawImage(g);

        workshopSprites.drawImage(g);

        g.drawImage(volvoWorkshopImage, volvoWorkshopPoint.x, volvoWorkshopPoint.y, null);

    }
}
