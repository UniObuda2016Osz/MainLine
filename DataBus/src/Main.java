import Dynamics.VehicleDynamics;

/**
 * Created by ral2bp on 2016.09.29..
 */
public class Main {
    private static VehicleDynamics vehicleDynamics;
    public static void main(String[] args){
        System.out.println("Main has started");
        /*Call modules in the logical order here*/
        vehicleDynamics = VehicleDynamics.GetInstance();

    }

}
