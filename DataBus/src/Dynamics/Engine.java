package Dynamics;

/**
 * Created by fayez on 10/12/2016.
 */
public class Engine {
    private static Engine instance;

    public static Engine GetInstance(){
        if(instance==null){
            instance=new Engine();
        }
        return instance;
    }


    private int HorsePower;

    public int getHorsePower() {
        return HorsePower;
    }

    public void setHorsePower(int horsePower) {
        HorsePower = horsePower;
    }



    private int Acceleration;

    public int getAcceleration() {
        return Acceleration;
    }

    public void setAcceleration(int acceleration) {
        Acceleration = acceleration;
    }




}
