package hu.oe.nik.autonomouscar.Functions;

import hu.oe.nik.autonomouscar.Bus.Bus;
import hu.oe.nik.autonomouscar.Sensors.Radar.DetectedObject;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by ral2bp on 2016.09.29. and by Mitró Tamás on 2016.11.24.
 */
public class ACCMain {

    private static ACCMain instance = null;
    public static ACCMain getInstance(){
        if (instance == null){
            instance = new ACCMain();
        }
        return instance;
    }
    private double targetSpeed;
    private double timegap;
    private double acceleration;
    private boolean isAccOn;
    private Bus bus;
    private List<DetectedObject> nearestFourObjects;
    private float ClosestTargetDistance;

    private ACCMain(){
        this.targetSpeed = 0;
        this.timegap = 1.5;
        this.acceleration = 0.0;
        this.ClosestTargetDistance=0;
        this.isAccOn = false;
        this.bus = Bus.getInstance();
        nearestFourObjects = new ArrayList<DetectedObject>();
    }

    public double getTargetSpeed(){
        return this.targetSpeed;
    }
// a legnagyobb baj, hogy nem az environment car object dolgait állítja itt a fügvény hanem az osztályon belül létrezohozz változókat és semmi kapcsolat nincs itt az igazi objectel. Sehol.
    public void setTargetSpeed(double targetSpeed) {
        if(isAccOn){
            bus.setGearPosition(Bus.GearPosition.DRIVE); // pl ez kell
            if(targetSpeed >= 30.0 && targetSpeed <= 180.0){
                //this.targetSpeed = targetSpeed;  // itt is inkább ez kéne bus.setGasPedal(100);
                bus.setGasPedal(100);

            } else if (targetSpeed < 30.0) {
//                this.targetSpeed = 30.0;
                bus.setGasPedal(0);
            } else {
                //this.targetSpeed = 180.0; ez itt mit csinál ?

            }
        }
    }

    public double getTimegap() {
        return timegap;
    }

    /**
     * If the passed parameter is above 0, this method will increase the timegap up to 2.0 with 0.2 steps,
     * if the passed parameter is below 0, this method will decrease the timegap down to 1.0 with 0.2 steps.
     * @param givenTimegap
     */
    public void setTimegap(int givenTimegap) {
        if(isAccOn) {
            if (givenTimegap > 0 && this.timegap < 2.0) {
                this.timegap += 0.25;
            } else if (givenTimegap < 0 && this.timegap > 1.0) {
                this.timegap -= 0.25;
            }
        }
    }

    public double getAcceleration() {
        return acceleration;
    } // itt is simán bus.getacceleration(); ez is már létezik

    /**
     * If the passed parameter is above 0, this method will increase the acceleration up to 3.5 m/s2 with 1.0 steps,
     * if the passed parameter is below 0, this method will decrease the acceleration down to -3.5 m/s2 with 1.0 steps.
     * @param actualAcceleration
     */
    public void setAcceleration(int actualAcceleration) {
        if(isAccOn) {
            if (actualAcceleration > 0 && this.acceleration < 3.5) {
                // when the car is accelerating
                //this.acceleration += 1.0;
                bus.setAcceleration(1);
            } else if (actualAcceleration < 0 && this.acceleration > -3.5) {
                // when the car is slowing down
                //this.acceleration -= 1.0;
                bus.setAcceleration(-1);
            }
        }
    }

    public boolean isAccOn() {
        return isAccOn;

    }

    /**
     * This method can be started the ACC function
     * @param actualSpeedOfCar
     */
    // At this invoke the ACCMain exists already
    // a Usercar osztályban már ez is és a többi acc, speed dolgok léteznek és a dynamicsban is. azokat kellene csak hívogatni semmi mást.
    public void setAccOn(double actualSpeedOfCar) {
        if(!isAccOn){
            this.isAccOn = true;
            this.targetSpeed = bus.getCurrentSISpeed();
            bus.setGearPosition(Bus.GearPosition.DRIVE);
            getDetectedObjectsFromRadar();
        }
    }

    public void setAccOff() {
        if(isAccOn){
            this.isAccOn = false;
            bus.setGearPosition(Bus.GearPosition.NEUTRAL);
        }
    }

    private void getDetectedObjectsFromRadar(){
        this.nearestFourObjects = bus.getFourNearestFromRadar();
    }


    private void definitionOfClosestObject(){
        // kikeresem a legközelebbi objectet ami a sávomban van és megnézem milyen messze van. Ha közeledett akkor akkor növelem a Timegap-et így lassul az autó és fordítva meg gyorsulok ha
        // előttem is gyorsult az autó és távolodik. Remélem erre gondoltunk mind.

        float Temp_actualdistancefromnearest=nearestFourObjects.get(0).getActualDistance(); //closest object distance
        for (int i=1; i<nearestFourObjects.size();i++)
        {
            if (Temp_actualdistancefromnearest<nearestFourObjects.get(i).getActualDistance());
            {
                Temp_actualdistancefromnearest=nearestFourObjects.get(i).getActualDistance();
            }
        }

        if ( ClosestTargetDistance < Temp_actualdistancefromnearest )
        {
            setTimegap(-1);
        }
        else
        {
            setTimegap(1);
        }

        ClosestTargetDistance=Temp_actualdistancefromnearest;
    }

    @Override
    public String toString() {
        return "ACCMain{" +
                ", targetSpeed=" + targetSpeed +
                ", timegap=" + timegap +
                ", acceleration=" + acceleration +
                ", isAccOn=" + isAccOn +
                '}';
    }
}
