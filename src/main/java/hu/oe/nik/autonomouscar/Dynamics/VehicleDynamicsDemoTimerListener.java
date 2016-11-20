package hu.oe.nik.autonomouscar.Dynamics;

import hu.oe.nik.autonomouscar.Bus.Bus;
/**
 * Created by Beci on 2016.10.12..
 */
public class VehicleDynamicsDemoTimerListener implements TimerListener {

    private static VehicleDynamicsDemoTimerListener instance = new VehicleDynamicsDemoTimerListener();

    public static VehicleDynamicsDemoTimerListener getInstance() {
        return VehicleDynamicsDemoTimerListener.instance;
    }

    private VehicleDynamics vehicledynamics = VehicleDynamics.GetInstance();
    @Override
    public void OnTimerTick() {
        vehicledynamics.update();
        System.out.print("Gas: " + bus.getGasPedal());
        System.out.print("Brake: " + bus.getBrakePedal());
        System.out.print("Gear: " + bus.getGearPosition());
        System.out.print("Acceleration: " + bus.getAcceleration());
        System.out.print("Speed: " + bus.getCurrentSISpeed());
        System.out.print("\n");
    }  private Bus bus = Bus.getInstance();


}
