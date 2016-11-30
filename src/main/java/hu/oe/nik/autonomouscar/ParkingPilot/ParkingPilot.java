package hu.oe.nik.autonomouscar.ParkingPilot;

import hu.oe.nik.autonomouscar.Bus.Bus;
import hu.oe.nik.autonomouscar.Environment.UserCar;
import hu.oe.nik.autonomouscar.Environment.WorldObject;
import hu.oe.nik.autonomouscar.Environment.misc.Parking;
import hu.oe.nik.autonomouscar.ParkingPilot.CheckFreeSpace;
import hu.oe.nik.autonomouscar.Sensors.ultrasonic.UltraSonicSensorPosition;
import hu.oe.nik.autonomouscar.Sensors.ultrasonic.UltrasonicSensor;
import sun.security.jca.GetInstance;

import javax.xml.stream.XMLStreamException;
import java.util.HashMap;
import java.util.Timer;
import java.util.TimerTask;


/**
 * Created by csegenyr on 2016.11.30..
 */
public class ParkingPilot {
    Bus bus;
    Timer timer;
    private final float ParkingSpeed = 5;
    private final double SpeedForPP = 8.333;
    private boolean SwitchState;
    boolean IsThereFreeSpace;
    CheckFreeSpace checkFreeSpace;
    UltrasonicSensor sensor;
    WorldObject firstsenzorfound;


    public void GetSwitchState(){
        SwitchState = bus.getParkingPilotSwitchState();
    }

    public boolean isSwitchState() {
        return SwitchState;
    }

    public void setSwitchState(boolean switchbutton) {
        this.SwitchState = switchbutton;
    }
    public void Parking(UserCar car){
        int angle =0;
        float distance = 0;
        try{
               while(bus.getCurrentSISpeed() > ParkingSpeed){
                bus.setBrakePedal(5);
                }
                bus.setBrakePedal(0);
                bus.setGearPosition(Bus.GearPosition.PARK);

                    angle = GetAngleToPark(car);
                    distance = GetDistanceToPark(car);

                bus.setSteeringWheelAngle(angle);
                bus.setDirectionIndicatorRightActive(true);
                bus.setGasPedal(5);

                while(distance > 0.5){ //méter
                    distance = GetDistanceToPark(car);
                }
                bus.setBrakePedal(5);
                while(distance > 0){
                    bus.setGasPedal(0);
                    bus.setBrakePedal(0);
                    bus.setGearPosition(Bus.GearPosition.NEUTRAL);
                }
            }
        catch (XMLStreamException ex){
            System.out.printf(ex.toString());
        }
       // bus.set
    }

    public ParkingPilot(UserCar car){
        bus = Bus.getInstance();
        checkFreeSpace = new CheckFreeSpace(car);
        TimerTask task = new TimerTask() {
            @Override
            public void run() {
                if (bus.getCurrentSISpeed() < SpeedForPP && bus.getParkingPilotSwitchState()){
                    try {
                        IsThereFreeSpace = checkFreeSpace.IsFreeParkingSpace();
                        if (IsThereFreeSpace){
                            Parking(car);
                        }
                    }
                    catch(XMLStreamException ex){
                            System.out.printf(ex.toString());
                    }
                }
            }
        };
    }

    public int GetAngleToPark(UserCar car){
        //Halszálkás parkolás 45 fok
        return 45;
    }
    public float GetDistanceToPark(UserCar car) throws XMLStreamException{
        HashMap<UltraSonicSensorPosition, UltrasonicSensor> sensors = car.getUltrasonicSensors();
        if(sensors.get(UltraSonicSensorPosition.FRONT_INNER_RIGHT).getClosestWorldObjects().getClosestObject()!=null){
            firstsenzorfound = sensors.get(UltraSonicSensorPosition.FRONT_INNER_RIGHT).getClosestWorldObjects().getClosestObject();
        }
        int[] coord =  {firstsenzorfound.getCenterPoint()[0]-car.getX(), firstsenzorfound.getCenterPoint()[1]-car.getY()};
        return (float) Math.sqrt(coord[0] * coord[0] + coord[1] * coord[1]);

    }
}
