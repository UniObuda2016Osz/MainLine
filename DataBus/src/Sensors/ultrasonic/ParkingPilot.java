package Sensors.ultrasonic;

import Bus.Bus;
import Environment.WorldObject;
import Environment.misc.Parking;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
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

        Map<UltraSonicSensorPosition, List<WorldObject>> visibleObjects = new HashMap<>();

        for (UltrasonicSensor us : ultraSonicSensors)
        {
            visibleObjects.put(us.getPositionOnCar(), us.getCurrentVisibleObjects());
        }

        if (visibleObjects.get(UltraSonicSensorPosition.FRONT_OUTER_LEFT).contains("bazi nagy szikla"))
        {
            Bus.getInstance().setSteeringWheelAngle(90);
            //TODO: levair: lehet hogy itt a HMI-re kellene egy singleton, es a fizikai kormanyt tekerni
            //TODO ugyanigy minden mas
        }

        //TODO etc...
    }

}
