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
            frame.update(workshop);
        }

    }
}
