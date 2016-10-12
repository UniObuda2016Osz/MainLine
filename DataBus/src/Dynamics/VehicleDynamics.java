package Dynamics;
import Bus.Bus;

import Dynamics.EnergyLoss;


/**
 * Created by ral2bp on 2016.09.29..
 */
public class VehicleDynamics {
    Bus bus;
    Engine engine;
    EnergyLoss energyLoss;
    private static VehicleDynamics instance;

    public static VehicleDynamics GetInstance(){
        if(instance==null){
            instance=new VehicleDynamics();
        }
        return instance;
    }

    private VehicleDynamics(){

    }

    // erre nincs még TDD teszt
    private void Setspeed()
    {
        float NewSpeed =  bus.getCurrentSISpeed() + engine.getAcceleration() ;
        bus.setCurrentSISpeed(NewSpeed);
    }

    // erre nincs még TDD teszt
    private void AccelerationDecrese() {
        int NewAcceleration = engine.getAcceleration() - energyLoss.LossFromGround() - energyLoss.LossFromWeight() - energyLoss.LossFromWind() - energyLoss.LossFromCollision();
        bus.setAcceleration( NewAcceleration );
    }

    // erre nincs még TDD teszt
    private void AccelerationIncrease() {
        int NewAcceleration = engine.getAcceleration() + bus.getGasPedal()/100 * engine.getHorsePower();
        bus.setAcceleration( NewAcceleration );
    }







}
