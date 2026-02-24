import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;

public class Main {
    // member fields:
    // The delay (ms) corresponds to 20 updates a sec (hz)
    private final int delay = 50;
    // The timer is started with a listener (see below) that executes the statements
    // each step between delays.
    protected Timer timer = new Timer(delay, new TimerListener());

    // The frame that represents this instance View of the MVC pattern
    CarView frame;
    CarController cc = new CarController();
    // A list of cars, modify if needed
    //ArrayList<Car> cars = new ArrayList<>();
    Garage<Volvo240> workshop = new Garage<>(new Position(300, 300));





    public void main(String[] args) {
        // Instance of this class
        cc.cars.add(new Volvo240(300));
        cc.cars.add(new Saab95(100));
        cc.cars.add(new Scania(200));

        // Start a new view and send a reference of self
        frame = new CarView("CarSim 1.0", cc);

        // Start the timer
        timer.start();
    }

    /* Each step the TimerListener moves all the cars in the list and tells the
     * view to update its images. Change this method to your needs.
     * */
    private class TimerListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            // Bytte till en mer traditionell for loop för att få garaget att fungera
            for (int i = 0; i < cc.cars.size(); i++) {
                Car car = cc.cars.get(i);
                car.move();

                int x = (int) Math.round(car.pos.getPosition().getX());
                int y = (int) Math.round(car.pos.getPosition().getY());

                Position workshopMidPoint = new Position((frame.drawPanel.volvoWorkshopPoint.x - frame.drawPanel.volvoWorkshopImage.getWidth() / 2),
                        (frame.drawPanel.volvoWorkshopPoint.y - frame.drawPanel.volvoWorkshopImage.getHeight() / 2));

                Position carMidPoint = new Position((car.pos.getPosition().getX() - frame.drawPanel.volvoImage.getWidth() / 2),
                        car.pos.getPosition().getX() - frame.drawPanel.volvoImage.getHeight() / 2);

                cc.handleEdgeCollision(
                        car,
                        x,
                        y,
                        frame.getWidth(),
                        frame.getHeight(),
                        frame.drawPanel.volvoImage.getWidth(),
                        frame.drawPanel.volvoImage.getHeight()
                );
                frame.drawPanel.moveit(x, y, car);

                // Check workshop collision, currently bugged. I think that because every cars position needs to be set
                // to (0, 0) initially it also does this to the workshop and the checkDistance sees a distance of zero
                // and the volvo is placed in the workshop.
                // TODO: add a getCarType() in workshop to make this generalized
                if (car.pos.checkCollision(workshopMidPoint, carMidPoint, (frame.drawPanel.volvoWorkshopImage.getWidth() / 2))) {
                    try {
                        workshop.storeCar((Volvo240) car);
                        Car volvo = cc.cars.remove(i);
                        frame.drawPanel.volvoInWorkhop = true; // this is some fuck ass code but it works and I dont wanna give any more shits about it
                        i--;
                    } catch (ClassCastException _) {}
                }
            }
            // Repaint once after all movements are calculated
            frame.drawPanel.repaint();
        }

    }
}
