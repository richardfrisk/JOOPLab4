import com.sun.jdi.CharType;

import java.util.ArrayList;

public class Garage<CarType extends Car> {
    private ArrayList<CarType> storedCars = new ArrayList<>(5);
    private boolean isOpen;
    private final Position pos;

    public Garage(Position position){
        this.isOpen = true;
        this.pos = position;
    }

    public void storeCar(CarType car) {
        if (isOpen) {
            storedCars.add(car);
            car.pos.setPosition(pos.getPosition());
        }
        else throw new IllegalStateException();
    }

    public boolean getOpenState() {
        return isOpen;
    }


    public CarType removeCar(){
        if (isOpen && !storedCars.isEmpty()) {
            int index = storedCars.size() - 1;
            storedCars.get(index).pos.setY(pos.getY() + 105);
            storedCars.get(index).pos.setX(pos.getX());
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