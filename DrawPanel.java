import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import javax.imageio.ImageIO;
import javax.swing.*;

// This panel represents the animated part of the view with the car images.

public class DrawPanel extends JPanel{

    Sprite volvo;
    Sprite saab;
    Sprite scania;
    Sprite volvoWorkshop;

    CarController cc;

    // Initializes the panel and reads the images
    public DrawPanel(int x, int y, CarController cc) {
        this.cc = cc;

        this.setDoubleBuffered(true);
        this.setPreferredSize(new Dimension(x, y));
        this.setBackground(Color.green);

        volvo = new Sprite("pics/Volvo240.jpg");
        saab = new Sprite("pics/Saab95.jpg");
        scania = new Sprite("pics/Scania.jpg");

        volvoWorkshop = new Sprite("pics/VolvoBrand.jpg");
    }


    // This method is called each time the panel updates/refreshes/repaints itself
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        for (Car car : cc.cars){
            Position carPosition = car.pos.getPosition();
            if (car instanceof Volvo240) {
                volvo.drawImage(g, carPosition);

            } else if (car instanceof Saab95) {
                saab.drawImage(g, carPosition);

            } else if (car instanceof Scania) {
                scania.drawImage(g, carPosition);
            }
        }

        volvoWorkshop.drawImage(g, new Position(300, 300));
    }
}
