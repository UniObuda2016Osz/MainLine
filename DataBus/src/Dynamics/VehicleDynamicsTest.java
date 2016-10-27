package Dynamics;
import Bus.Bus;

import org.junit.Test;
import static org.junit.Assert.assertEquals;

/**
 * Created by BJudit on 2016. 10. 08..
 */
public class VehicleDynamicsTest {
    VehicleDynamics VD = VehicleDynamics.GetInstance();

    @org.junit.Before
    public void setUp() throws Exception {
        VD.bus = Bus.getInstance();
        VD.engine = Engine.GetInstance();
        VD.energyLoss = EnergyLoss.GetInstance();
    }

    @org.junit.Test
    public void isVehicleDynamicsSingleton()
    {
        VehicleDynamics first = VehicleDynamics.GetInstance();
        VehicleDynamics second = VehicleDynamics.GetInstance();

        assertEquals(first == second, true);
    }

    Bus bus = Bus.getInstance();

    @org.junit.Test
    public void testEngineBrakeDriveGearPosition() {
        bus.setGearPosition(Bus.GearPosition.DRIVE);
        double enginebrake = VD.calculateEngineBrakeDeceleration();
        assertEquals(enginebrake,VD.DRIVE_ENGINE_BREAK_DECLERATION,0);
    }

    @org.junit.Test
    public void testEngineBrakeParkGearPosition() {
        bus.setGearPosition(Bus.GearPosition.PARK);
        double enginebrake = VD.calculateEngineBrakeDeceleration();
        assertEquals(enginebrake, VD.PARK_ENGINE_BREAK_DECLERATION,0);
    }

    @org.junit.Test
    public void testEngineBrakeReverseGearPosition() {
        bus.setGearPosition(Bus.GearPosition.REVERSE);
        double enginebrake = VD.calculateEngineBrakeDeceleration();
        assertEquals(enginebrake, VD.REVERSE_ENGINE_BREAK_DECLERATION,0);
    }

    @org.junit.Test
    public void testEngineBrakeNeutralGearPosition() {
        bus.setGearPosition(Bus.GearPosition.NEUTRAL);
        double enginebrake = VD.calculateEngineBrakeDeceleration();
        assertEquals(enginebrake, VD.NEUTRAL_ENGINE_BREAK_DECLERATION,0);
    }

    @org.junit.Test
    public void isSpeedChanged()
    {
        //TODO:
       /* VD.bus.setCurrentSISpeed(10);
        VD.bus.setAcceleration(1);
        VD.updateSpeed();
        assertEquals(VD.bus.getCurrentSISpeed(),11,0);*/
    }

    @org.junit.Test
    public void isAccelerationChanged()
    {
        VD.bus.setAcceleration(100);
        VD.calculateAcceleration();
        assertEquals(VD.bus.getAcceleration()==100,true);
    }

    @org.junit.Test
    public void testSetSpeed() {
        //TODO:
        //bus.setCurrentSISpeed(20);
        //engine.setAcceleration(10);
        //double newSISpeed = bus.getCurrentSISpeed() + engine.getAcceleration();
        //assertEqueals(newSISpeed, VD.SetSpeed());
    }

    @org.junit.After
    public void tearDown() throws Exception {
        /*GCC will remove bus*/
    }
}
