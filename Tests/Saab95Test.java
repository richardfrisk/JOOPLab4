import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class Saab95Test {
    Saab95 Saab = new Saab95();

    @Test
    void setTurboOn() {
        Saab.setTurboOn();
        assertTrue(Saab.getTurbo());
    }

    @Test
    void setTurboOff() {
        Saab.setTurboOff();
        assertFalse(Saab.getTurbo());
    }

    @Test
    void incrementSpeed() {
        Saab.currentSpeed = 0;
        Saab.incrementSpeed(10);

        if (Saab.getTurbo()) assertEquals(16.25, Saab.getCurrentSpeed());
        else assertEquals(12.5, Saab.getCurrentSpeed());
    }

    @Test
    void decrementSpeed() {
        Saab.currentSpeed = 20;
        Saab.decrementSpeed(10);

        if (Saab.getTurbo()) assertEquals(3.75, Saab.getCurrentSpeed());
        assertEquals(7.5, Saab.getCurrentSpeed());
    }

    @Test
    void gas() {
        double[] amounts = {1, -1, 10, 0.5};
        double orgSpeed = Saab.getCurrentSpeed();
        for (double i : amounts) {
            Saab.gas(i);
            assertTrue((Saab.getCurrentSpeed() > orgSpeed));
            assertTrue((Saab.getCurrentSpeed() < Saab.getEnginePower() || Saab.getCurrentSpeed() > 0));
        }

    }

    @Test
    void brake() {
        double[] amounts = {1, -1, 10, 0.5};
        double orgSpeed = Saab.getCurrentSpeed();
        for (double i : amounts) {
            Saab.brake(i);
            assertFalse((Saab.getCurrentSpeed() > orgSpeed));
            assertTrue((Saab.getCurrentSpeed() < Saab.getEnginePower() || Saab.getCurrentSpeed() > 0));
        }
    }
}