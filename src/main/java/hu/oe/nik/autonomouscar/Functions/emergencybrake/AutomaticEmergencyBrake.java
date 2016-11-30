package hu.oe.nik.autonomouscar.Functions.emergencybrake;

import hu.oe.nik.autonomouscar.Bus.Bus;
import hu.oe.nik.autonomouscar.Environment.UserCar;

import java.sql.Time;

/**This module should sit on a UserCar instance
 * Created by levair on 2016. 11. 28..
 */
public class AutomaticEmergencyBrake {

    private UserCar ownerCar;
    private boolean turnedOn;

    public AutomaticEmergencyBrake(UserCar ownerCar) {
        this.ownerCar = ownerCar;
        turnedOn = false;

        listen();
    }

    public void turnOn() {
        this.turnedOn = true;
    }

    public void turnOff() {
        this.turnedOn = false;
    }

    private void listen() {
        while (true) {

            if (turnedOn == true) {
                watchObjects();
            } else {
                //do nothing
            }

        }
    }

    private void watchObjects() {
        // not hit emergency braking car
        {

        }

        if(ownerCar.getSpeed()<50)//does not hit: pedestrian,cyclist, crossing car
        {}
        else if(ownerCar.getSpeed()<100)//does not hit: cutting in car
        {}


    }
    //for calculate the deceleration: must be > 4.5 m/s2, but below 10 m/s2
    Time last_time;
    int last_speed;
    private void Brake ()
    {}

}
