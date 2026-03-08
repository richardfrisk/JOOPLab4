import java.awt.*;
import java.util.ArrayList;
//import java.lang.Math.*;

public abstract class Car implements Movable {
    //Abstract - går ej att göra objekt av car

    int width;
    int height;

    //Hjälper till för att se att vi verkligen override:ar
    @Override
    public void move() {
        pos.setY(pos.getY() + Math.sin(getDirection()) * currentSpeed);
        pos.setX(pos.getX() + Math.cos(getDirection()) * currentSpeed);
    }

    @Override
    public void turn_left(double amountInRads) {
        direction -= amountInRads;
    }

    @Override
    public void turn_right(double amountInRads) {
        direction += amountInRads;
    }

    private double direction = 0;

    public double setDirection(double inputDirection) {
        direction = inputDirection;
        return direction;
    }

    protected double getDirection() {
        direction %= Math.PI * 2;
        if (direction < 0) direction += Math.PI * 2;
        return direction;
    }

    //Utomstående ska inte kunna ändra positionen men alla nya bilar som skapas behöver använda och uppdatera positionen
    protected Position pos = new Position(0, 0);

    protected int nrDoors; // Number of doors on the car
    protected double enginePower; // Engine power of the car
    protected double currentSpeed; // The current speed of the car
    protected Color color; // Color of the car
    protected String modelName; // The car model name
    protected double turningForce;

    protected abstract double speedFactor();

    public void incrementSpeed(double amount) {
        currentSpeed = Math.min(getCurrentSpeed() + speedFactor() * amount, enginePower);
    }

    public void decrementSpeed(double amount) {
        currentSpeed = Math.max(getCurrentSpeed() - speedFactor() * amount, 0);
    }

    public void gas(double amount) {
        if (amount < 0 || amount > 1) return;
        incrementSpeed(amount);
    }

    public void brake(double amount) {
        if (amount < 0 || amount > 1) return;
        decrementSpeed(amount);
    }

    public int getNrDoors() {
        return nrDoors;
    }

    public double getEnginePower() {
        return enginePower;
    }

    public double getCurrentSpeed() {
        return currentSpeed;
    }

    public Color getColor() {
        return color;
    }
    protected void setColor(Color clr) {
        color = clr;
    }

    protected ArrayList<Integer> getSize() {
        ArrayList<Integer> size = new ArrayList<>(2);
        size.add(width);
        size.add(height);
        return size;
    }

    public void startEngine() {
        currentSpeed = 0.1;
    }

    public void stopEngine() {
        currentSpeed = 0;
    }

    public void turboOn() { }
    public void turboOff() { }
    public void liftBed() { }
    public void lowerBed() { }




}