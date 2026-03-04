
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class GarageTest {
    Saab95 saab = new Saab95(0);


    @Test
    void storeCar() {
        Garage<Car> garage = new Garage<>(new Position(2, 2), Car.class);

        // IMPORTANT: place the car close enough
        saab.pos.setY(8);
        saab.pos.setX(8);

        garage.openGarage();
        garage.storeCar(saab);

        assertEquals(1, garage.getStoredCars().size());
        assertSame(saab, garage.getStoredCars().get(0));
    }

    @Test
    void removeCar() {
        Garage<Car> garage = new Garage<>(new Position(5, 5), Car.class);

        garage.pos.setPosition(new Position(7, 7));
        garage.openGarage();

        // IMPORTANT: place the car close enough
        saab.pos.setPosition(new Position(8, 8));

        garage.storeCar(saab);
        garage.removeCar(saab);

        assertTrue(garage.getStoredCars().isEmpty());
    }

    @Test
    void openGarage() {
        Garage<Car> garage = new Garage<>(new Position(2, 2), Car.class);

        garage.openGarage();
        assertTrue(garage.getOpenState());
    }

    @Test
    void closeGarage() {
        Garage<Car> garage = new Garage<>(new Position(2, 2), Car.class);

        garage.closeGarage();
        assertFalse(garage.getOpenState());
    }
}