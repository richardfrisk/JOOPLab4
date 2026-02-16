import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ScaniaTest {
    Scania Scania = new Scania();

    @Test
    void setBedAngle() {
        double[] amounts = {1, -1, 80, 50.6};
        for (double i : amounts) {
            Scania.setBedAngle(i);
            if (i < 0 || i > 70) {
                assertTrue((Scania.getAngle() <= 0 || Scania.getAngle() >= 70));
            }
            else {
                assertEquals(i, Scania.getAngle());
            }
        }

    }

    @Test
    void gas() {
        double[] amounts = {1, -1, 10, 0.5};
        double orgSpeed = Scania.getCurrentSpeed();
        for (double i : amounts) {
            Scania.gas(i);
            assertTrue((Scania.getCurrentSpeed() > orgSpeed));
            assertTrue((Scania.getCurrentSpeed() < Scania.getEnginePower() || Scania.getCurrentSpeed() > 0));
        }

    }
    @Test
    void brake() {
        double[] amounts = {1, -1, 10, 0.5};
        double orgSpeed = Scania.getCurrentSpeed();
        for (double i : amounts) {
            Scania.brake(i);
            assertFalse((Scania.getCurrentSpeed() > orgSpeed));
            assertTrue((Scania.getCurrentSpeed() < Scania.getEnginePower() || Scania.getCurrentSpeed() > 0));
        }
    }
}