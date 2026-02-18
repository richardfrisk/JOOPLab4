import java.awt.*;
//import java.lang.Math.*;

public abstract class Car implements Movable{
    //Abstract - går ej att göra objekt av car

    //Hjälper till för att se att vi verkligen override:ar
    @Override
    public void move() {
        y += Math.sin(getDirection()) * currentSpeed;
        x += Math.cos(getDirection()) * currentSpeed;

        if (x < 0) {
            x = 0;
            direction = Math.PI - direction;
        }

        else if (x > 700) {
            x = 700;
            direction = Math.PI - direction;
        }

        if (y < 0) {
            y = 0;
            direction = - direction;
        }

        else if (y > 500) {
            y = 500;
            direction = - direction;
        }
    }

    @Override
    public void turn_left(double amountInRads) {direction -= amountInRads;}

    @Override
    public void turn_right(double amountInRads) { direction += amountInRads; }

    private double direction = 0;
    protected double getDirection() {
        direction %= Math.PI * 2;
        if (direction < 0) direction += Math.PI * 2;
        return direction;
    }

    protected Position getPosition() {
        return new Position(x, y);
    }



    //Utomstående ska inte kunna ändra positionen men alla nya bilar som skapas behöver använda och uppdatera positionen
    protected double x, y;

    public double getDistance(double x1, double y1) {
        return Math.sqrt(((x-x1)+(y-y1)));
    }

    protected int nrDoors; // Number of doors on the car
    protected double enginePower; // Engine power of the car
    protected double currentSpeed; // The current speed of the car
    protected Color color; // Color of the car
    protected String modelName; // The car model name
    protected double turningForce;

    protected abstract double speedFactor();

    public void incrementSpeed(double amount){
        currentSpeed = Math.min(getCurrentSpeed() + speedFactor() * amount, enginePower);
    }

    public void decrementSpeed(double amount){
        currentSpeed = Math.max(getCurrentSpeed() - speedFactor() * amount, 0);
    }

    public void gas(double amount){
        if(amount < 0 || amount > 1) return;
        incrementSpeed(amount);
    }

    public void brake(double amount){
        if(amount < 0 || amount > 1) return;
        decrementSpeed(amount);
    }

    public int getNrDoors(){
        return nrDoors;
    }

    public double getEnginePower(){
        return enginePower;
    }

    public double getCurrentSpeed(){
        return currentSpeed;
    }

    public Color getColor(){
        return color;
    }

    protected void setColor(Color clr){
        color = clr;
    }

    public void startEngine(){
        currentSpeed = 0.1;
    }

    public void stopEngine(){
        currentSpeed = 0;
    }
}
