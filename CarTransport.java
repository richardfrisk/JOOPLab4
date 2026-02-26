import java.awt.*;
import java.util.ArrayList;

public class CarTransport extends Truck{

    private ArrayList<PassengerCar> storedCars = new ArrayList<>(8);
    private boolean isRampUp;

    private final StorageEntity<PassengerCar> storage = new StorageEntity<>(storedCars, pos);



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


    public void storeCar(PassengerCar car) {
        if (isRampUp) storage.storeCar(car);
        else throw new IllegalStateException();
    }

    public void removeCar(PassengerCar car) {
        if (isRampUp) storage.removeCar(car);
        else throw new IllegalStateException();
    }

    public ArrayList<PassengerCar> getStoredCars() {
        return storage.getStoredCars();
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
