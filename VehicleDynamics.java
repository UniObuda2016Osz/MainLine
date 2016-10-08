package Dynamics;

/**
 * Created by ral2bp on 2016.09.29..
 */
public class VehicleDynamics {
    private static VehicleDynamics instance;

    public static VehicleDynamics GetInstance(){
        if(instance==null){
            instance=new VehicleDynamics();
        }
        return instance;
    }


    private VehicleDynamics(){}

}
