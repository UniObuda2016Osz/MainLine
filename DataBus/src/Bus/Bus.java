package Bus;

import Bus.listener.IBusListener;

import java.util.ArrayList;
import java.util.List;

public class Bus {
    /*data members, getters, setters*/

    List<IBusListener> listeners = new ArrayList<IBusListener>();

    public void subscribe(IBusListener listener) {
        this.listeners.add(listener);
    }

    public void unSubscribe(IBusListener listener) {
        this.listeners.remove(listener);
    }

    private boolean ACCMainSwitchState;

    public boolean getACCMainSwitchState() {
        return ACCMainSwitchState;
    }

    public void setACCMainSwitchState(boolean ACCMainSwitchState) {
        this.ACCMainSwitchState = ACCMainSwitchState;
        for (IBusListener listener : listeners)
        {
            listener.onACCMainSwitchStateChanged(ACCMainSwitchState);
        }
    }

    private int gasPedal;

    public int getGasPedal(){ return gasPedal;}

    public void setGasPedal(int gasPedal){
        if(gasPedal< 100 && gasPedal > 0)
            this.gasPedal = gasPedal;
        else if(gasPedal > 0)
            this.gasPedal = 100;
        else
            this.gasPedal = 0;
        for (IBusListener listener : listeners)
        {
            listener.onGasPedalChanged(gasPedal);
        }
    }

    private int brakePedal;

    public int getBrakePedal(){ return brakePedal;}

    public void setBrakePedal(int brakePedal){
        if(brakePedal< 100 && brakePedal > 0)
            this.brakePedal = brakePedal;
        else if(brakePedal > 0)
            this.brakePedal = 100;
        else
            this.brakePedal = 0;
        for (IBusListener listener : listeners)
        {
            listener.onBrakePedalChanged(brakePedal);
        }
    }

    private int steeringWheelAngle;

    public int getSteeringWheelAngle(){
        return steeringWheelAngle;
    }

    public void setSteeringWheelAngle(int steeringWheelAngle){
        if(steeringWheelAngle< 100 && steeringWheelAngle > 0)
            this.steeringWheelAngle = steeringWheelAngle;
        else if(steeringWheelAngle > 0)
            this.steeringWheelAngle = 100;
        else
            this.steeringWheelAngle = 0;
        for (IBusListener listener : listeners)
        {
            listener.onSteeringWheelAngleChanged(steeringWheelAngle);
        }
    }

    public boolean isDirectionIndicatorLeftActive() {
        return directionIndicatorLeftActive;
    }

    public void setDirectionIndicatorLeftActive(boolean directionIndicatorLeftActive) {
        this.directionIndicatorLeftActive = directionIndicatorLeftActive;
        for (IBusListener listener : listeners)
        {
            listener.onDirectionIndicatorLeftActiveChanged(directionIndicatorLeftActive);
        }
    }

    private boolean directionIndicatorLeftActive;


    public boolean isDirectionIndicatorRightActive() {
        return DirectionIndicatorRightActive;
    }

    public void setDirectionIndicatorRightActive(boolean directionIndicatorRightActive) {
        DirectionIndicatorRightActive = directionIndicatorRightActive;
        for (IBusListener listener : listeners)
        {
            listener.onDirectionIndicatorRightActiveChanged(directionIndicatorRightActive);
        }
    }

    private boolean DirectionIndicatorRightActive;

    public enum GearPosition{
        PARK,REVERSE,NEUTRAL,DRIVE
    }

    public GearPosition getGearPosition() {
        return gearPosition;
    }

    public void setGearPosition(GearPosition gearPosition) {
        this.gearPosition = gearPosition;
        for (IBusListener listener : listeners)
        {
            listener.onGearPositionChanged(gearPosition);
        }
    }

    private GearPosition gearPosition;

    public float getCurrentSISpeed() {
        return currentSISpeed;
    }

    public void setCurrentSISpeed(float currentSISpeed) {
        this.currentSISpeed = currentSISpeed;
        for (IBusListener listener : listeners)
        {
            listener.onCurrentSISpeedChanged(currentSISpeed);
        }
    }

    private float currentSISpeed;

    /*Singleton parts*/

    public static Bus getInstance(){
        if(instance == null)
            instance = new Bus();

        return instance;
    }

    private static Bus instance = null;

    private Bus(){
        ACCMainSwitchState = false; //default main switch state value: off
    }

}
