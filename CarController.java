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
    private CarModel model;

    public CarController(CarModel model){
        this.model = model;
    }

    public CarModel getModel(){
        return model;
    }

    // Calls the gas method for each car once
    void gas(int amount) {
        model.gas(amount);
    }
    void brake(int amount) {
        model.brake(amount);
    }
    void turboOn() {
        model.turboOn();
    }
    void turboOff() {
        model.turboOff();
    }
    void liftBed() {
        model.liftBed();

    }
    void lowerBed() {
        model.lowerBed();
    }

    void startEngine() {
        model.startEngine();
    }

    void stopEngine() {
        model.stopEngine();
    }

    void addCar(){
        model.addCar();
    }

    void removeCar(){
        model.removeCar();
    }
}
