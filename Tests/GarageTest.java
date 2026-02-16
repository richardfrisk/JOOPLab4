import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class GarageTest {
    Saab95 saab = new Saab95();


    @Test
    void storeCar() {
        Garage<Car> garage = new Garage<>(2, 2);

        // IMPORTANT: place the car close enough
        saab.x = 8;
        saab.y = 8;

        garage.openGarage();
        garage.storeCar(saab);

        assertEquals(1, garage.getStoredCars().size());
        assertSame(saab, garage.getStoredCars().get(0));
    }

    @Test
    void removeCar() {
        Garage<Car> garage = new Garage<>(5, 5);

        garage.openGarage();

        // IMPORTANT: place the car close enough
        saab.x = 8;
        saab.y = 8;

        garage.storeCar(saab);
        garage.removeCar();

        assertTrue(garage.getStoredCars().isEmpty());
    }

    @Test
    void openGarage() {
        Garage<Car> garage = new Garage<>(2, 2);

        garage.openGarage();
        assertTrue(garage.getOpenState());
    }

    @Test
    void closeGarage() {
        Garage<Car> garage = new Garage<>(2, 2);

        garage.closeGarage();
        assertFalse(garage.getOpenState());
    }
}