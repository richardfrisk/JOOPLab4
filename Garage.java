import com.sun.jdi.CharType;

import java.util.ArrayList;

public class Garage<CarType extends Car> {
    private boolean isOpen;
    public Position pos;
    private final ArrayList<CarType> storedCars = new ArrayList<>(5);


    private final StorageEntity<CarType> storage = new StorageEntity<>(storedCars, pos);

    public Garage(Position position){
        this.isOpen = true;
        this.pos = position;
    }

    public void storeCar(CarType car) {
        if (isOpen) storage.storeCar(car);
        else throw new IllegalStateException();
    }

    public void removeCar(CarType car) {
        if (isOpen) storage.removeCar(car);
        else throw new IllegalStateException();
    }

    public ArrayList<CarType> getStoredCars() {
        return storage.getStoredCars();
    }


    public boolean getOpenState() {
        return isOpen;
    }

    public void openGarage(){ isOpen = true; }
    public void closeGarage(){ isOpen = false; }
}