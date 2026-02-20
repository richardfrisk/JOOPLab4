import com.sun.jdi.CharType;

import java.util.ArrayList;

public class Garage<CarType extends Car> {
    private ArrayList<CarType> storedCars = new ArrayList<>(5);
    private boolean isOpen;
    private final double x,y;

    public Garage(double x, double y){
        this.isOpen = true;
        this.x = x;
        this.y = y;
    }

    public void storeCar(CarType car) {
        if (isOpen) {
            storedCars.add(car);
            car.x = x;
            car.y = y;
        }
        else throw new IllegalStateException();
    }

    public boolean getOpenState() {
        return isOpen;
    }

    public Position getPosition() {
        return new Position(x, y);
    }

    public CarType removeCar(){
        if (isOpen && !storedCars.isEmpty()) {
            int index = storedCars.size() - 1;
            storedCars.get(index).y = y + 105;
            storedCars.get(index).x = x;
            return storedCars.remove(index);
        }
        else throw new IllegalStateException();
    }

    public ArrayList<CarType> getStoredCars() {
        return storedCars;
    }

    public void openGarage(){ isOpen = true; }
    public void closeGarage(){ isOpen = false; }
}