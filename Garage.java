import java.util.ArrayList;

public class Garage<CarType extends Car> {
    private ArrayList<CarType> storedCars = new ArrayList<>(5);
    private boolean isOpen;
    private final double x,y;

    public Garage(double x, double y){
        this.isOpen = false;
        this.x = x;
        this.y = y;
    }

    public void storeCar(CarType Car) {
        if (Car.getDistance(x, y) < 5.0 && isOpen) {
            storedCars.add(Car);
            Car.x = x;
            Car.y = y;
        }
        else throw new IllegalStateException();
    }

    public boolean getOpenState() {
        return isOpen;
    }

    public CarType removeCar(){
        if (isOpen && !storedCars.isEmpty()) {
            int index = storedCars.size() - 1;
            storedCars.get(index).y = y + 5;
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