import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CarTransportTest {
    @Test
    void raiseRamp(){
        CarTransport Lastbil = new CarTransport();

        Lastbil.raiseRamp();
        assertTrue(Lastbil.getRampState());
    }

    @Test
    void lowerRamp() {
        CarTransport Lastbil = new CarTransport();

        Lastbil.lowerRamp();
        assertFalse(Lastbil.getRampState());
    }

    @Test
    void loadCar() {
        CarTransport Lastbil = new CarTransport();
        Saab95 saab = new Saab95(0);

        Lastbil.lowerRamp();
        Lastbil.storeCar(saab);
        int index = Lastbil.getStoredCars().indexOf(saab);
        assertEquals(saab,Lastbil.getStoredCars().get(index));
    }

    @Test
    void removeCar() {
        CarTransport Lastbil = new CarTransport();
        Saab95 saab = new Saab95(0);

        Lastbil.lowerRamp();

        Lastbil.storeCar(saab);
        Lastbil.removeCar(saab);

        assertTrue(Lastbil.getStoredCars().isEmpty());

    }

    @Test
    void gas() {
        CarTransport Lastbil = new CarTransport();

        double[] amounts = {1, -1, 10, 0.5};
        double orgSpeed = Lastbil.getCurrentSpeed();
        for (double i : amounts) {
            Lastbil.gas(i);
            assertTrue((Lastbil.getCurrentSpeed() > orgSpeed));
            assertTrue((Lastbil.getCurrentSpeed() < Lastbil.getEnginePower() || Lastbil.getCurrentSpeed() > 0));
        }

    }

    @Test
    void brake() {
        CarTransport Lastbil = new CarTransport();

        double[] amounts = {1, -1, 10, 0.5};
        double orgSpeed = Lastbil.getCurrentSpeed();
        for (double i : amounts) {
            Lastbil.brake(i);
            assertFalse((Lastbil.getCurrentSpeed() > orgSpeed));
            assertTrue((Lastbil.getCurrentSpeed() < Lastbil.getEnginePower() || Lastbil.getCurrentSpeed() > 0));
        }
    }

}