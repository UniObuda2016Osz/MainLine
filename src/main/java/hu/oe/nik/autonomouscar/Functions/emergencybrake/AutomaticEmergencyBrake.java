package hu.oe.nik.autonomouscar.Functions.emergencybrake;

import hu.oe.nik.autonomouscar.Bus.Bus;
import hu.oe.nik.autonomouscar.Environment.UserCar;
import hu.oe.nik.autonomouscar.Sensors.Radar.DetectedObject;
import hu.oe.nik.autonomouscar.Sensors.Radar.RadarCalculator;

import java.util.ArrayList;

/**
 * This module should sit on a UserCar instance
 * Created by levair on 2016. 11. 28..
 */
public class AutomaticEmergencyBrake {

    //a main ugye folyamatosan meghívja ezt, nekünk tudnunk kell az előző sebességet az akkori időt, hogy a mostani állapothoz képest tudjunk lassulást számolni
    //plussz a vezető gázadását le kéne tiltani vészfékezés esetén
    //for calculate the deceleration: must be > 4.5 m/s2, but below 10 m/s2

    float last_time;
    double last_speed;
    boolean braking;
    private UserCar ownerCar;
    private boolean turnedOn;

    public AutomaticEmergencyBrake(UserCar ownerCar) {
        this.ownerCar = ownerCar;
        turnedOn = false;
        braking = false;
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
            if (braking)
                brake();
            else
                watchObjects();
        }
    }

    private void watchObjects() {
        ownerCar.getRadar().RadarMain();
        ArrayList<DetectedObject> objectsInFrontOfTheCar = Bus.getInstance().getFourNearestFromRadar();
        for (DetectedObject object : objectsInFrontOfTheCar) {


            if (object.getNpctype() == RadarCalculator.NPCType.Car) {
                if (ownerCar.getSpeed() < 50 /*&& crossing Car*/) {

                } else if (ownerCar.getSpeed() < 100 /*&& cutting Car*/) {

                }
                //else if( /*&& Veszfekezo Car*/  ){

                //}
            } else if (ownerCar.getSpeed() < 50 && (object.getNpctype() == RadarCalculator.NPCType.Pedestrian || object.getNpctype() == RadarCalculator.NPCType.Cyclist))//does not hit: pedestrian,cyclist, crossing car
            {
                if (visualWarning(object)) {
                    showWarning();
                    if (mustBrake(object))
                        brake();
                }
            }

        }


    }

    private void brake() {
        //user gázadás elvétele
        Bus.getInstance().setAcceleration(0);
        braking = true;
        //lassulást kiolvasása a korábbi adatokból=>fékerő állítás
        if (last_speed == 0) {
            last_speed = ownerCar.getSpeed();
            Bus.getInstance().setBrakePedal(60);//kezdetben 60%al fékezünk
        }
        if (last_time != 0 && last_speed == 0) //nem vészfékezünk tovább, mert megálltunk
        {
            braking = false;
            return;
        }
        if (last_time == 0) {
            last_time = System.currentTimeMillis();
            return;
        }
        double deceleration = (last_speed - ownerCar.getSpeed() / (System.currentTimeMillis() - last_time)) * 1000;
        //the deceleration: must be > 4.5 m/s2, but below 10 m/s2
        if (deceleration < 4.5)
            Bus.getInstance().setAcceleration((int) 1.1 * Bus.getInstance().getAcceleration());
        else if (deceleration < 9.5)
            Bus.getInstance().setAcceleration((int) 0.9 * Bus.getInstance().getAcceleration());

        last_speed = ownerCar.getSpeed();
        last_time = System.currentTimeMillis();
    }

    private boolean mustBrake(DetectedObject object) {

        double realSpeed = convertToMeterPerSec(ownerCar.getSpeed() - object.getActualSpeed());//sebességkülönbség
        double timeUntilImpact = object.getActualDistance() / realSpeed; // sec = meter / (m/s) -ütközésig az idő

        double timeToZeroSpeed = realSpeed / 4.5; // sec = (m/s) / (m/s^2)
        // sebességkülönbség nullára csökkenéséhez az idő vészfékezés mellett
        // Deceleration must be > 4.5 m/s2, but below 10 m/s2

        if (timeToZeroSpeed >= timeUntilImpact)//ha nagyobb a megálláshoz szükséges idő mint ahogy vészfékezés mellett meg tudnánk állni akkor vészfékezünk??? WTF?
            return true;
        else
            return false;
    }

    private boolean visualWarning(DetectedObject object) {
        //reaction time >2.0s

        double realSpeed = convertToMeterPerSec(ownerCar.getSpeed() - object.getActualSpeed());
        double timeUntilImpact = object.getActualDistance() / realSpeed;

        double timeToZeroSpeed = realSpeed / 4.5;

        // ha a veszfekezesig 4-2 mp akkor Warning kivillan
        if (timeUntilImpact - timeToZeroSpeed > 2 && timeUntilImpact - timeToZeroSpeed < 4) {
            return true;
        } else
            return false;

    }

    private void showWarning() {
    }

    private double convertToMeterPerSec(double kmPerHour) {
        return (kmPerHour * 1000) / 3600;
    }

}
