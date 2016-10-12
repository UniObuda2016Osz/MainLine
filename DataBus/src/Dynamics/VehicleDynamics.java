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


    public final int DRIVE_ENGINE_BREAK_DECLERATION = 2;
    public final int PARK_ENGINE_BREAK_DECLERATION = 1;
    public final int REVERSE_ENGINE_BREAK_DECLERATION = 1;
    public final int NEUTRAL_ENGINE_BREAK_DECLERATION = 0;
    public int getEngineBrakeDecleration() {
        Bus.GearPosition gear = bus.getGearPosition();

        if (gear == Bus.GearPosition.DRIVE) {
            return DRIVE_ENGINE_BREAK_DECLERATION;
        }

        if (gear == Bus.GearPosition.PARK) {
            return PARK_ENGINE_BREAK_DECLERATION;
        }

        if (gear == Bus.GearPosition.REVERSE) {
            return REVERSE_ENGINE_BREAK_DECLERATION;
        }

        if (gear == Bus.GearPosition.NEUTRAL) {
            return NEUTRAL_ENGINE_BREAK_DECLERATION;
        }

        return 0;
    }

    private final int BRAKE_PEDAL_MAXIMUM_DECLERATION = 15;
    private void declerationByBrakePedal() {
        int pedal = bus.getBrakePedal();
        int brakingdecleration = BRAKE_PEDAL_MAXIMUM_DECLERATION * pedal / 100;

        int currentacceleration = bus.getAcceleration();
        int accelerationdecreasedbybraking = currentacceleration - brakingdecleration;
        bus.setAcceleration(accelerationdecreasedbybraking);
    }

}
