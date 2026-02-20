import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

/*
* This class represents the Controller part in the MVC pattern.
* It's responsibilities is to listen to the View and responds in a appropriate manner by
* modifying the model state and the updating the view.
 */

public class CarController {
    // member fields:
    private boolean engineON = false;
    // The delay (ms) corresponds to 20 updates a sec (hz)
    private final int delay = 50;
    // The timer is started with a listener (see below) that executes the statements
    // each step between delays.
    private Timer timer = new Timer(delay, new TimerListener());

    // The frame that represents this instance View of the MVC pattern
    CarView frame;
    // A list of cars, modify if needed
    ArrayList<Car> cars = new ArrayList<>();
    Garage<Volvo240> workshop = new Garage<>(300, 300);

    //methods:

    public static void main(String[] args) {
        // Instance of this class
        CarController cc = new CarController();

        cc.cars.add(new Volvo240(300));
        cc.cars.add(new Saab95(100));
        cc.cars.add(new Scania(200));

        // Start a new view and send a reference of self
        cc.frame = new CarView("CarSim 1.0", cc);

        // Start the timer
        cc.timer.start();
    }




    /* Each step the TimerListener moves all the cars in the list and tells the
    * view to update its images. Change this method to your needs.
    * */
    private class TimerListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            // Bytte till en mer traditionell for loop för att få garaget att fungera
            for (int i = 0; i < cars.size(); i++) {
                Car car = cars.get(i);
                car.move();

                int x = (int) Math.round(car.getPosition().getX());
                int y = (int) Math.round(car.getPosition().getY());

                checkEdgeCollision(car, x, y);
                frame.drawPanel.moveit(x, y, car);

                // Check workshop collision
                if (car instanceof Volvo240 && checkWorkshopCollision(workshop, car)) {
                    workshop.storeCar((Volvo240) car);
                    Car volvo = cars.remove(i);
                    frame.drawPanel.volvoInWorkhop = true; // this is some fuck ass code but it works and I dont wanna give any more shits about it
                    i--;
                }
            }
            // Repaint once after all movements are calculated
            frame.drawPanel.repaint();
        }
    }

    private void checkEdgeCollision(Car car, int x, int y) {
        if (frame.getWidth() < x + frame.drawPanel.volvoImage.getWidth()) {
            car.setDirection(Math.PI - car.getDirection());
            car.setX(frame.getWidth() - frame.drawPanel.volvoImage.getWidth());
        } else if (x < 0) {
            car.setDirection(Math.PI - car.getDirection());
            car.setX(0);
        }

        if (frame.getHeight() < y + frame.drawPanel.volvoImage.getHeight()) {
            car.setDirection(-car.getDirection());
            car.setY(frame.getHeight() - frame.drawPanel.volvoImage.getHeight());
        } else if (y < 0) {
            car.setDirection(-car.getDirection());
            car.setY(0);
        }
    }



    private boolean checkWorkshopCollision(Garage workshop, Car car) {
        Position workshopMidPoint = new Position((frame.drawPanel.volvoWorkshopPoint.x - frame.drawPanel.volvoWorkshopImage.getWidth() / 2),
                (frame.drawPanel.volvoWorkshopPoint.y - frame.drawPanel.volvoWorkshopImage.getHeight() / 2));
        Position carMidPoint = new Position((car.getPosition().getX() - frame.drawPanel.volvoImage.getWidth() / 2),
                car.getPosition().getX() - frame.drawPanel.volvoImage.getHeight() / 2);

        return car.getDistance(carMidPoint, workshopMidPoint) < 50;
    }

    // Calls the gas method for each car once
    void gas(int amount) {
        if (!engineON) { return; }
        double gas = ((double) amount) / 100;
            for (Car car : cars
                ) {
            car.gas(gas);
        }
    }
    void brake(int amount) {
        if (!engineON) { return; }
        double brake = ((double) amount) / 100;
        for (Car car : cars
        ) {
            car.brake(brake);
        }
    }
    void turboOn() {
        for (Car car : cars) {
            if (car instanceof Saab95){ ((Saab95) car).setTurboOn(); }
        }

    }
    void turboOff() {
        for (Car car : cars) {
            if (car instanceof Saab95) { ((Saab95) car).setTurboOff(); }
        }

    }
    void liftBed() {
        for (Car car : cars) {
            if (car instanceof Truck) { car.liftBed(); }
        }

    }
    void lowerBed() {
        for (Car car : cars) {
            if (car instanceof Truck) car.lowerBed();
        }
    }

    void startEngine() {
        engineON = true;
        for (Car car : cars) {
            car.startEngine();
        }
    }

    void stopEngine() {
        engineON = false;
        for (Car car : cars) {
            car.stopEngine();
        }
    }
}
