import java.awt.*;

public class Volvo240 extends PassengerCar{

    private final static double trimFactor = 1.25;


    public Volvo240(double startY){
        nrDoors = 4;
        color = Color.black;
        enginePower = 100;
        modelName = "Volvo240";
        turningForce = 1;
        pos.setY(startY);
        stopEngine();
    }


    @Override
    protected double speedFactor(){
        return enginePower * 0.01 * trimFactor;
    }

}