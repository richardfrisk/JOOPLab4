import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class Volvo240Test {
    Volvo240 Volvo = new Volvo240();

    @Test
    void incrementSpeed() {
        Volvo.currentSpeed = 0;
        Volvo.incrementSpeed(10);
        assertEquals(12.5, Volvo.getCurrentSpeed());
    }

    @Test
    void decrementSpeed() {
        Volvo.currentSpeed = 20;
        Volvo.decrementSpeed(10);

        assertEquals(7.5, Volvo.getCurrentSpeed());
    }

    @Test
    void gas() {
        double[] amounts = {1, -1, 10, 0.5};
        double orgSpeed = Volvo.getCurrentSpeed();
        for (double i : amounts) {
            Volvo.gas(i);
            assertTrue((Volvo.getCurrentSpeed() > orgSpeed));
            assertTrue((Volvo.getCurrentSpeed() < Volvo.getEnginePower() || Volvo.getCurrentSpeed() > 0));
        }

    }

    @Test
    void brake() {
        double[] amounts = {1, -1, 10, 0.5};
        double orgSpeed = Volvo.getCurrentSpeed();
        for (double i : amounts) {
            Volvo.brake(i);
            assertFalse((Volvo.getCurrentSpeed() > orgSpeed));
            assertTrue((Volvo.getCurrentSpeed() < Volvo.getEnginePower() || Volvo.getCurrentSpeed() > 0));
        }
    }

}