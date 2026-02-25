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

    private boolean engineON = false;
    ArrayList<Car> cars = new ArrayList<>();

    public void update(CarView frame, Garage<Volvo240> workshop) {
        // Bytte till en mer traditionell for loop för att få garaget att fungera
        for (int i = 0; i < cars.size(); i++) {
            Car car = cars.get(i);
            car.move();

            int x = (int) Math.round(car.pos.getPosition().getX());
            int y = (int) Math.round(car.pos.getPosition().getY());



            handleEdgeCollision(
                    car,
                    x,
                    y,
                    frame.getWidth(),
                    frame.getHeight(),
                    frame.drawPanel.volvoImage.getWidth(),
                    frame.drawPanel.volvoImage.getHeight()
            );

            frame.drawPanel.moveit(x, y, car);

        }
        handleWorkshopCollision(frame, workshop);
        // Repaint once after all movements are calculated
        frame.drawPanel.repaint();
    }

    //methods:
    //TODO: Fråga TA hur man kan göra denna generell. Så den funkar med andra typer av garage.
    public void handleWorkshopCollision(CarView view, Garage<Volvo240> workshop) {
        for (int i = 0; i < cars.size(); i++) {
            Car car = cars.get(i);

            Position workshopMidPoint = new Position((view.drawPanel.volvoWorkshopPoint.x - view.drawPanel.volvoWorkshopImage.getWidth() / 2),
                    (view.drawPanel.volvoWorkshopPoint.y - view.drawPanel.volvoWorkshopImage.getHeight() / 2));

            Position carMidPoint = new Position((car.pos.getPosition().getX() - view.drawPanel.volvoImage.getWidth() / 2),
                    car.pos.getPosition().getX() - view.drawPanel.volvoImage.getHeight() / 2);

            // Check workshop collision, currently bugged. I think that because every cars position needs to be set
            // to (0, 0) initially it also does this to the workshop and the checkDistance sees a distance of zero
            // and the volvo is placed in the workshop.
            if (car.pos.checkCollision(workshopMidPoint, carMidPoint, (view.drawPanel.volvoWorkshopImage.getWidth() / 2))) {
                try {
                    workshop.storeCar((Volvo240) car);
                    Car volvo = cars.remove(i);
                    view.drawPanel.volvoInWorkhop = true; // this is some fuck ass code but it works and I dont wanna give any more shits about it
                    i--;
                } catch (ClassCastException ignored) {}
            }
        }
    }

    public void handleEdgeCollision(Car car, int x, int y, int width, int height, int imgW, int imgH) {
        if (x + imgW > width) {
            car.setDirection(Math.PI - car.getDirection());
            car.pos.setX(width - imgW);
        } else if (x < 0) {
            car.setDirection(Math.PI - car.getDirection());
            car.pos.setX(0);
        }

        if (y + imgH > height) {
            car.setDirection(-car.getDirection());
            car.pos.setY(height - imgH);
        } else if (y < 0) {
            car.setDirection(-car.getDirection());
            car.pos.setY(0);
        }
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
