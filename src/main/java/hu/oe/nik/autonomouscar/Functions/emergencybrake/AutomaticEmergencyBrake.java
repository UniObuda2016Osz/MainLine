package hu.oe.nik.autonomouscar.Functions.emergencybrake;

import hu.oe.nik.autonomouscar.Bus.Bus;
import hu.oe.nik.autonomouscar.Environment.UserCar;
import hu.oe.nik.autonomouscar.Environment.WorldObject;
import hu.oe.nik.autonomouscar.Sensors.Radar.DetectedObject;
import hu.oe.nik.autonomouscar.Sensors.Radar.Radar;

import java.sql.Time;
import java.util.ArrayList;

/**
 * This module should sit on a UserCar instance
 * Created by levair on 2016. 11. 28..
 */
public class AutomaticEmergencyBrake {

    //a main ugye folyamatosan meghívja ezt, nekünk tudnunk kell az előző sebességet az akkori időt, hogy a mostani állapothoz képest tudjunk lassulást számolni
    //plussz a vezető gázadását le kéne tiltani vészfékezés esetén
    //for calculate the deceleration: must be > 4.5 m/s2, but below 10 m/s2

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

            if (turnedOn == true) {
                //if(vészfékezünk) Brake()
                //else
                watchObjects();
            } else {
                //do nothing
            }

        }
    }

    private void watchObjects() {
        ownerCar.getRadar().RadarMain();
        ArrayList<DetectedObject> objectsbeforethecar = Bus.getInstance().getFourNearestFromRadar();
        for (DetectedObject object: objectsbeforethecar) {


            if(object.getNpctype().name() == "NpcCar" )
            {
                if(ownerCar.getSpeed() < 50 /*&& crossing Car*/ ){

                }
                else if(ownerCar.getSpeed() < 100 /*&& cutting Car*/ ){

                }
                else if( /*&& Veszfekezo Car*/  ){

                }
            }

            else if ( ownerCar.getSpeed() < 50 && ( object.getNpctype().name() == "People" ||   object.getNpctype().name() == "Cyclist"))//does not hit: pedestrian,cyclist, crossing car
            {

            }

        }


    }
    Time last_time;
    int last_speed;
    private void Brake() {
        //user gázadás elvétele
        //lassulást kiolvasása a korábbi adatokból=>fékerő állítás
    }

    private boolean mustBrake(DetectedObject object) {

        double realSpeed = convertToMeterPerSec(ownerCar.getSpeed() - object.getActualSpeed());//sebességkülönbség
        double timeUntilImpact = object.getActualDistance() / realSpeed; // sec = meter / (m/s) -ütközésig az idő

        double timeToZeroSpeed = realSpeed / 8; // sec = (m/s) / (m/s^2)
        // sebességkülönbség nullára csökkenéséhez az idő vészfékezés mellett
        // Deceleration must be > 4.5 m/s2, but below 10 m/s2

        if (timeToZeroSpeed >= timeUntilImpact)//ha nagyobb a megálláshoz szükséges idő mint ahogy vészfékezés mellett meg tudnánk állni akkor vészfékezünk??? WTF?
            return true;
        else
            return false;
    }

    private double convertToMeterPerSec(double kmPerHour) {
        return (kmPerHour * 1000) / 3600;
    }

}
