package Dynamics;

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

    }
}
