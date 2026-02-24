
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class GarageTest {
    Saab95 saab = new Saab95(0);


    @Test
    void storeCar() {
        Garage<Car> garage = new Garage<>(new Position(2, 2));

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
        Garage<Car> garage = new Garage<>(new Position(5, 5));

        garage.openGarage();

        // IMPORTANT: place the car close enough
        saab.pos.setPosition(new Position(8, 8));

        garage.storeCar(saab);
        garage.removeCar();

        assertTrue(garage.getStoredCars().isEmpty());
    }

    @Test
    void openGarage() {
        Garage<Car> garage = new Garage<>(new Position(2, 2));

        garage.openGarage();
        assertTrue(garage.getOpenState());
    }

    @Test
    void closeGarage() {
        Garage<Car> garage = new Garage<>(new Position(2, 2));

        garage.closeGarage();
        assertFalse(garage.getOpenState());
    }
}