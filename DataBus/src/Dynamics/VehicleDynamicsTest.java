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
        double enginebrake = VD.getEngineBrakeDecleration();
        assertEquals(enginebrake, VD.DRIVE_ENGINE_BREAK_DECLERATION);
    }

    @org.junit.Test
    public void testEngineBrakeParkGearPosition() {
        bus.setGearPosition(Bus.GearPosition.PARK);
        double enginebrake = VD.getEngineBrakeDecleration();
        assertEquals(enginebrake, VD.PARK_ENGINE_BREAK_DECLERATION);
    }

    @org.junit.Test
    public void testEngineBrakeReverseGearPosition() {
        bus.setGearPosition(Bus.GearPosition.REVERSE);
        double enginebrake = VD.getEngineBrakeDecleration();
        assertEquals(enginebrake, VD.REVERSE_ENGINE_BREAK_DECLERATION);
    }

    @org.junit.Test
    public void testEngineBrakeNeutralGearPosition() {
        bus.setGearPosition(Bus.GearPosition.NEUTRAL);
        double enginebrake = VD.getEngineBrakeDecleration();
        assertEquals(enginebrake, VD.NEUTRAL_ENGINE_BREAK_DECLERATION);
    }

    @org.junit.Test
    public void isSpeedChanged()
    {
        VD.bus.setCurrentSISpeed(10);
        VD.engine.setAcceleration(1);
        VD.SetSpeed();
        assertEquals(VD.bus.getCurrentSISpeed()==11,true);
    }

    @org.junit.Test
    public void isAccelerationDecrese()
    {
        VD.engine.setAcceleration(100);
        VD.energyLoss.setGroundType(EnergyLoss.GroundType.Soft);
        VD.energyLoss.setWeight(80);
        VD.energyLoss.setWindEnergy(10);
        VD.energyLoss.setCollision(false);
        VD.AccelerationDecrese();
        assertEquals(VD.bus.getAcceleration()==9,true);
    }
    
    @org.junit.Test
    public void isAccelerationIncrease()
    {
        VD.engine.setAcceleration(100);
        VD.engine.setHorsePower(10);
        VD.bus.setGasPedal(100);
        VD.AccelerationIncrease();
        assertEquals(VD.bus.getAcceleration()==110,true);
    }

    @org.junit.After
    public void tearDown() throws Exception {
        /*GCC will remove bus*/
    }
}
