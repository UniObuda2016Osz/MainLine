package Sensors.ultrasonic;

import Environment.misc.Parking;

import java.util.Set;

/**
 * The module responsible for controlling the car through the Bus when in "Parking Pilot" mode
 *
 * Created by levair on 2016.10.29..
 */
public class ParkingPilot {

    private static ParkingPilot instance;

    private Set<UltrasonicSensor> ultraSonicSensors;

    private ParkingPilot(Set<UltrasonicSensor> ultraSonicSensors)
    {
        this.ultraSonicSensors = ultraSonicSensors;
    }

    public static ParkingPilot getSingleton(Set<UltrasonicSensor> ultraSonicSensors)
    {
        if (instance == null)
        {
            instance = new ParkingPilot(ultraSonicSensors);
        }
        return instance;
    }

    public void Park()
    {
        //TODO: park by the information on the sensors...

    }

}
