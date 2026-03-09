import java.util.ArrayList;

public class CarModel {
    private boolean engineON = false;
    private ArrayList<Car> cars = new ArrayList<>();
    private ArrayList<Garage<Volvo240>> workshops = new ArrayList<>();

    public CarModel() {
        cars.add(new Volvo240(new Position(0, 300)));
        cars.add(new Saab95(new Position(0, 100)));
        cars.add(new Scania(new Position(0, 200)));

        workshops.add(new Garage<Volvo240>(new Position(300, 300), Volvo240.class));;
    }


    public ArrayList<Car> getCars() { return cars; }

    public void update() {
        for (Car car : cars) {
            car.move();

            int x = (int) Math.round(car.pos.getPosition().getX());
            int y = (int) Math.round(car.pos.getPosition().getY());

            handleEdgeCollision(
                    car,
                    x,
                    y,
                    800,
                    500
            );
        }
        for (Garage workshop : workshops) handleWorkshopCollision(workshop);
    }

    //methods:
    // Lagarar bilar i verkstan, tar bort från listan, tar bort dess sprite
    public <CarType extends Car> void handleWorkshopCollision(Garage<CarType> workshop) {
        for (int i = 0; i < cars.size(); i++) {
            Car car = cars.get(i);

            if (car.pos.checkCollision(workshop.pos, car.pos, 40)) {
                try {
                    workshop.storeCar((CarType) car);
                    Car volvo = cars.remove(i);
                    i--;
                } catch (IllegalArgumentException ignored) {}
            }
        }
    }
    //Vad som händer när bilarna nuddar väggarna
    public void handleEdgeCollision(Car car, int x, int y, int width, int height) {
        int carW = car.getSize().get(0);
        int carH = car.getSize().get(1);

        if (x + carW > width) {
            car.setDirection(Math.PI - car.getDirection());
            car.pos.setX(width - carW);
        } else if (x < 0) {
            car.setDirection(Math.PI - car.getDirection());
            car.pos.setX(0);
        }

        if (y + carH > height) {
            car.setDirection(-car.getDirection());
            car.pos.setY(height - carH);
        } else if (y < 0) {
            car.setDirection(-car.getDirection());
            car.pos.setY(0);
        }
    }

    void addCar() {
        if (cars.size() < 10) {
            Position pos = new Position(Math.round(Math.random() * 800), Math.round(Math.random() * 500));
            PassengerCar saab = new Saab95(pos.getPosition());
            cars.add(saab);
        }
    }

    void removeCar() {
        if (cars.size() != 0) {
            int index = Math.toIntExact(Math.round(Math.random() * cars.size()));
            cars.remove(index);
        }
   }

    void gas(int amount) {
        if (!engineON) { return; }
        double gas = ((double) amount) / 100;
        for (Car car : cars
        ) {
            car.gas(gas);
        }
    }
    void brake(int amount) {
        if (!engineON) { return; }
        double brake = ((double) amount) / 100;
        for (Car car : cars
        ) {
            car.brake(brake);
        }
    }
    void turboOn() {
        for (Car car : cars) {
            if (car instanceof Saab95){ ((Saab95) car).setTurboOn(); }
        }

    }
    void turboOff() {
        for (Car car : cars) {
            if (car instanceof Saab95) { ((Saab95) car).setTurboOff(); }
        }

    }
    void liftBed() {
        for (Car car : cars) {
            if (car instanceof Truck) { car.liftBed(); }
        }

    }
    void lowerBed() {
        for (Car car : cars) {
            if (car instanceof Truck) car.lowerBed();
        }
    }

    void startEngine() {
        engineON = true;
        for (Car car : cars) {
            car.startEngine();
        }
    }

    void stopEngine() {
        engineON = false;
        for (Car car : cars) {
            car.stopEngine();
        }
    }
}
