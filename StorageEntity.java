import java.util.ArrayList;

public class StorageEntity<CarType extends Car> {
    private final ArrayList<CarType> storedCars;
    private final Position pos;


    public StorageEntity(ArrayList<CarType> storedCars, Position pos) {
        this.storedCars = storedCars;
        this.pos = pos;
    }

    public void storeCar(CarType car) {
            storedCars.add(car);
            car.pos.setPosition(pos.getPosition());
    }

    public CarType removeCar(CarType car){
        if (!storedCars.isEmpty()) {
            int index = storedCars.indexOf(car);
            if (index == -1)  throw new IllegalStateException("Car not in storage");

            storedCars.get(index).pos.setY(pos.getY() + 105);
            storedCars.get(index).pos.setX(pos.getX());
            return storedCars.remove(index);
        }
        else throw new IllegalStateException();
    }

    public ArrayList<CarType> getStoredCars() {
        return storedCars;
    }
}
