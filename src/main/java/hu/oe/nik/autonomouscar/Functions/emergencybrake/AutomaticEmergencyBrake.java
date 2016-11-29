package hu.oe.nik.autonomouscar.Functions.emergencybrake;

import hu.oe.nik.autonomouscar.Bus.Bus;
import hu.oe.nik.autonomouscar.Environment.UserCar;

/**This module should sit on a UserCar instance
 * Created by levair on 2016. 11. 28..
 */
public class AutomaticEmergencyBrake {

    private UserCar ownerCar;
    private boolean turnedOn;

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

            //TODO: implement AEB

        } else {
            //do nothing, because AEB is turned off
        }
    }
}
