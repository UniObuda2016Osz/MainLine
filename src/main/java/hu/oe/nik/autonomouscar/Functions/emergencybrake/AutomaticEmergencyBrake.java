package hu.oe.nik.autonomouscar.Functions.emergencybrake;

import hu.oe.nik.autonomouscar.Bus.Bus;
import hu.oe.nik.autonomouscar.Environment.UserCar;
import hu.oe.nik.autonomouscar.Sensors.Radar.DetectedObject;

import java.awt.image.BufferStrategy;

/**This module should sit on a UserCar instance
 * Created by levair on 2016. 11. 28..
 */
public class AutomaticEmergencyBrake {

    private UserCar ownerCar;
    private boolean turnedOn;
    private DetectedObject detectedObject;

    public AutomaticEmergencyBrake(UserCar ownerCar) {
        this.ownerCar = ownerCar;
        turnedOn = false;
    }

    public void turnOn() {
        this.turnedOn = true;
    }

    public void turnOff() {
        this.turnedOn = false;
    }

    /**
     * This is the MAIN function in AEB module.
     * This function should be called as many times as possible
     * (eg. in an infinite loop)
     */
    public void run() {
        if (turnedOn) {

           if(mustBreake()){
               Bus.getInstance().setEmergencyBrake(8); //parameterben a lassulás mértéke?
           }


        } else {
            //do nothing, because AEB is turned off
        }
    }

    private boolean mustBrake(){

        double realSpeed = convertToMeterPerSec(ownerCar.getSpeed() - detectedObject.getActualSpeed());
        double timeUntilImpact = detectedObject.getActualDistance() / realSpeed; // sec = meter / (m/s)

        double timeToZeroSpeed = realSpeed / 8; // sec = (m/s) / (m/s^2)
        // Deceleration must be > 4.5 m/s2, but below 10 m/s2

        if(timeToZeroSpeed <= timeUntilImpact)
            return true;
        else
            return false;
    }

    private double convertToMeterPerSec( double kmPerHour){
        return (kmPerHour*1000) / 3600;
    }

}
