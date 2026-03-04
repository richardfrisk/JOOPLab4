import com.sun.jdi.CharType;

import java.util.ArrayList;

public class Garage<CarType extends Car> {
    private boolean isOpen;
    public Position pos;
    private final ArrayList<CarType> storedCars = new ArrayList<>(5);
    private final Class<CarType> type;

    private final StorageEntity<CarType> storage;

    public Garage(Position position, Class<CarType> type){
        this.isOpen = true;
        this.pos = position;
        this.type = type;
        storage = new StorageEntity<>(storedCars, pos);
    }

    public void storeCar(Car car) {
        if (type.isInstance(car) && isOpen) {
            storedCars.add(type.cast(car));
        } else {
            throw new IllegalArgumentException("Wrong car type");
        }
    }

    public void removeCar(Car car) {
        if (type.isInstance(car) && isOpen) {
            storage.removeCar(type.cast(car));
        } else {
            throw new IllegalArgumentException("Wrong car type");
        }
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