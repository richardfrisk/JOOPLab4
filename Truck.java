public abstract class Truck extends Car {
    protected boolean drivableWithBedState = true;

    @Override
    public void startEngine() {
        if (drivableWithBedState) currentSpeed = 0.1;
    }
}
