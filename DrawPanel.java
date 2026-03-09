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

    ArrayList<Car> cars;

    // Initializes the panel and reads the images
    public DrawPanel(int x, int y, ArrayList<Car> cars) {
        this.cars = cars;

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

        for (ReadCar car : cars){//ändra till carViewInterface
            Position carPosition = car.getPosition();
            if (car.getModel().equals("Volvo240")) {
                volvo.drawImage(g, carPosition);

            } else if (car.getModel().equals("Saab95")) {
                saab.drawImage(g, carPosition);

            } else if (car.getModel().equals("Scania")) {
                scania.drawImage(g, carPosition);
            }
        }

        volvoWorkshop.drawImage(g, new Position(300, 300));
    }
}
