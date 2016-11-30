package hu.oe.nik.autonomouscar.Bus;

import hu.oe.nik.autonomouscar.Sensors.Radar.DetectedObject;

import java.util.ArrayList;

public class Bus {
    /*data members, getters, setters*/
    private boolean ACCMainSwitchState;

    public boolean getACCMainSwitchState() {
        return ACCMainSwitchState;
    }

    public void setACCMainSwitchState(boolean ACCMainSwitchState) {
        this.ACCMainSwitchState = ACCMainSwitchState;
    }

    private boolean ParkingPilotSwitchState;

    public boolean getParkingPilotSwitchState() {
        return ParkingPilotSwitchState;
    }

    public void setParkingPilotSwitchState(boolean ParkingPilotSwitchState) {
        this.ParkingPilotSwitchState = ParkingPilotSwitchState;
    }


    private int Velocity;

    public int getVelocity() {
        return Velocity;
    }

    public void setVelocity(int velocity) {
        Velocity = velocity;
    }

    private int Acceleration;
    
    public int getAcceleration() {
        return Acceleration;
    }

    public void setAcceleration(int acceleration) {
        Acceleration = acceleration;
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
    }

    private int emergencyBrake;

    public int getEmergencyBrake(){
        return emergencyBrake;
    }

    public void setEmergencyBrake(int emergencyBrake){
        if(emergencyBrake< 100 && emergencyBrake > 0)
            this.emergencyBrake = emergencyBrake;
        else if(emergencyBrake > 0)
            this.emergencyBrake = 100;
        else
            this.emergencyBrake = 0;
    }

    private boolean emergencyBrakeSwitchedOn;

    public boolean isEmergencyBrakeSwitchedOn() { return emergencyBrakeSwitchedOn; }

    public void setEmergencyBrakeSwitchedOn(boolean emergencyBrakeSwitchedOn ) { this.emergencyBrakeSwitchedOn = emergencyBrakeSwitchedOn; }

    public boolean isDirectionIndicatorLeftActive() {
        return directionIndicatorLeftActive;
    }

    public void setDirectionIndicatorLeftActive(boolean directionIndicatorLeftActive) {
        this.directionIndicatorLeftActive = directionIndicatorLeftActive;
    }

    private boolean directionIndicatorLeftActive;


    public boolean isDirectionIndicatorRightActive() {
        return DirectionIndicatorRightActive;
    }

    public void setDirectionIndicatorRightActive(boolean directionIndicatorRightActive) {
        DirectionIndicatorRightActive = directionIndicatorRightActive;
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
    }

    private GearPosition gearPosition;

    public float getCurrentSISpeed() {
        return currentSISpeed;
    }

    public void setCurrentSISpeed(float currentSISpeed) {
        this.currentSISpeed = currentSISpeed;
    }

    private float currentSISpeed;

    /*Singleton parts*/

    public static Bus getInstance(){
        if(instance == null)
            instance = new Bus();

        return instance;
    }

    private ArrayList<DetectedObject> fourNearestFromRadar;

    public ArrayList<DetectedObject> getFourNearestFromRadar() {
        return fourNearestFromRadar;
    }

    public void setFourNearestFromRadar(ArrayList<DetectedObject> fourNearestFromRadar) {
        this.fourNearestFromRadar = fourNearestFromRadar;
    }


    private static Bus instance = null;

    private Bus(){
        ACCMainSwitchState = false; //default main switch state value: off
    }

    private boolean laneKeeping;

    public boolean isLaneKeepingOn() {
        return laneKeeping;
    }

    public void setLaneKeeping(boolean laneKeeping) {
        this.laneKeeping = laneKeeping;
    }

    private String speedLimitValue;

    public String getSpeedLimitValue() {
        return speedLimitValue;
    }

    public void setSpeedLimitValue(String speedLimitValue) {
        this.speedLimitValue = speedLimitValue;
    }





}
