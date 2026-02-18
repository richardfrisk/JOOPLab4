import java.awt.*;

public class Scania extends Truck {

    private boolean hasBed;
    private double bedAngle;

    public Scania(double startY) {
        nrDoors = 2;
        color = Color.pink;
        enginePower = 100;
        modelName = "Scania";
        turningForce = 1;
        y = startY;
        hasBed = true;
        if (hasBed) bedAngle = 0;
        stopEngine();
    }

    public void setBedAngle(double inAngle) {
        if (inAngle < 0) bedAngle = 0;
        else if (inAngle > 70) bedAngle = 70;
        else bedAngle = inAngle;
    }

    public double getAngle() { return bedAngle; }

    @Override
    protected double speedFactor() {
        if (bedAngle != 0) return 0;
        else return enginePower * 0.01;
    }
}