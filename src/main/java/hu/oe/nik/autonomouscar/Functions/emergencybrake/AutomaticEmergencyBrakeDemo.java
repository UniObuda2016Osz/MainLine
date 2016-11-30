package hu.oe.nik.autonomouscar.Functions.emergencybrake;

import hu.oe.nik.autonomouscar.Environment.UserCar;

/**
 * The purpose of this module is to demo the AEB function.
 * Created by levair on 2016. 11. 30..
 */
public class AutomaticEmergencyBrakeDemo {

    public static final int USERCAR_STARTING_POS_X = 50;
    public static final int USERCAR_STARTING_POS_Y = 50;

    public static void main(String[] args) {
        UserCar userCar = new UserCar(USERCAR_STARTING_POS_X, USERCAR_STARTING_POS_Y);

        while(true) {

            userCar.getAutomaticEmergencyBrake().turnOn();
            userCar.getAutomaticEmergencyBrake().run();

        }
    }


}
