import java.awt.*;
import java.util.ArrayList;

public class CarTransport extends Truck{

    private ArrayList<PassengerCar> loadedCars = new ArrayList<>(8);
    private boolean isRampUp;


    public CarTransport() {
        nrDoors = 2;
        color = Color.blue;
        enginePower = 250;
        modelName = "BMW";
        turningForce = 1;
        stopEngine();
        isRampUp = false;
    }

    public void raiseRamp() {
        if (!isRampUp) isRampUp = true;
    }

    public void lowerRamp() {
        if (isRampUp) isRampUp = false;
    }

    public void loadCar(PassengerCar Car){
        if (getDistance(Car.x, Car.y) < 5.0 && !isRampUp) {
            loadedCars.add(Car);
            Car.x = x;
            Car.y = y;
        }
        else throw new IllegalStateException();
    }

    public PassengerCar removeCar(){
        if (!loadedCars.isEmpty() && !isRampUp) {
            PassengerCar unLoadedCar = loadedCars.removeLast();
            unLoadedCar.y = y + 5;
            unLoadedCar.x = x;
            return unLoadedCar;
        }
        else throw new IllegalStateException();
    }

    public ArrayList<PassengerCar> getLoadedCars() {
        return loadedCars;
    }

    public boolean getRampState() { return isRampUp; }

    @Override
    public void brake(double amount) {
        super.brake(amount);
    }

    @Override
    public void gas(double amount) {
        super.gas(amount);
        raiseRamp();
    }

    @Override
    protected double speedFactor() {
        return enginePower * 0.01;
    }
}
