package hu.oe.nik.autonomouscar.ParkingPilot;

import hu.oe.nik.autonomouscar.Environment.UserCar;
import hu.oe.nik.autonomouscar.Environment.WorldObject;
import hu.oe.nik.autonomouscar.Sensors.ultrasonic.UltraSonicSensorPosition;
import hu.oe.nik.autonomouscar.Sensors.ultrasonic.UltrasonicSensor;

import javax.xml.stream.XMLStreamException;
import java.util.HashMap;

/**
 * Created by judit on 2016. 11. 30..
 */
public class CheckFreeSpace {
    private UserCar car;
    private WorldObject firstsenzorfound;
    private static double EKKORAHELYRETUDUNKBEALLNI=8;

    public CheckFreeSpace(UserCar c){
        car=c;
    }

    public boolean IsFreeParkingSpace() throws XMLStreamException {
        HashMap<UltraSonicSensorPosition, UltrasonicSensor> sensors = car.getUltrasonicSensors();
        if(sensors.get(UltraSonicSensorPosition.FRONT_OUTER_RIGHT).getClosestWorldObjects().getClosestObject()!=null){
            firstsenzorfound = sensors.get(UltraSonicSensorPosition.FRONT_OUTER_RIGHT).getClosestWorldObjects().getClosestObject();
        }
        //csak akkor nézzük, hogy van-e hely, ha minden melett elmentünk, amit az első szenzor látott
        if (sensors.get(UltraSonicSensorPosition.REAR_OUTER_RIGHT).getClosestWorldObjects().getClosestObject() == firstsenzorfound) {
            firstsenzorfound = null;
        }
        if(firstsenzorfound!=null){
            //valami van mellettünk
            if(sensors.get(UltraSonicSensorPosition.FRONT_INNER_RIGHT).getClosestWorldObjects().getDistance()>=EKKORAHELYRETUDUNKBEALLNI ||
                    sensors.get(UltraSonicSensorPosition.REAR_INNER_RIGHT).getClosestWorldObjects().getDistance()>=EKKORAHELYRETUDUNKBEALLNI  ){
                return true;
            }
        }
        if(firstsenzorfound==null && sensors.get(UltraSonicSensorPosition.FRONT_OUTER_RIGHT).getClosestWorldObjects().getClosestObject()!=null){
            //valami nagy van mellettünk
            
            return false;
        }
        if (sensors.get(UltraSonicSensorPosition.FRONT_OUTER_RIGHT).getClosestWorldObjects().getClosestObject()==null &&
                sensors.get(UltraSonicSensorPosition.REAR_OUTER_RIGHT).getClosestWorldObjects().getClosestObject() == null && firstsenzorfound==null
                && sensors.get(UltraSonicSensorPosition.FRONT_INNER_RIGHT).getClosestWorldObjects().getClosestObject()==null &&
                sensors.get(UltraSonicSensorPosition.REAR_INNER_RIGHT).getClosestWorldObjects().getClosestObject() == null){
            //sehol semmi
            return true;
        }
        return false;
        //ha valamilyen eset kimaradt volna, ne legyen csatt
    }

}
