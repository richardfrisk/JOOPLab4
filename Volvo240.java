import java.awt.*;

public class Volvo240 extends PassengerCar{

    private final static double trimFactor = 1.25;


    public Volvo240(Position startPosition){
        nrDoors = 4;
        color = Color.black;
        enginePower = 100;
        modelName = "Volvo240";
        turningForce = 1;
        pos.setPosition(startPosition);
        width = 100;
        height = 60;
        stopEngine();
    }


    @Override
    protected double speedFactor(){
        return enginePower * 0.01 * trimFactor;
    }

}