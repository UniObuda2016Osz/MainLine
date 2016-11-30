package hu.oe.nik.autonomouscar.ParkingPilot;

import hu.oe.nik.autonomouscar.Bus.Bus;
import sun.security.jca.GetInstance;

import java.util.Timer;
import java.util.TimerTask;


/**
 * Created by csegenyr on 2016.11.30..
 */
public class ParkingPilot {
    Bus bus;
    Timer timer;
    private final float ParkingSpeed = 5;
    private boolean SwitchState;


    public void GetSwitchState(){
        SwitchState = bus.getParkingPilotSwitchState();
    }

    public boolean isSwitchState() {
        return SwitchState;
    }

    public void setSwitchState(boolean switchbutton) {
        this.SwitchState = switchbutton;
    }
    public void Parking(){
       while(bus.getCurrentSISpeed() > ParkingSpeed){
           bus.setBrakePedal(5);
       }
        bus.setBrakePedal(0);
        bus.setGearPosition(Bus.GearPosition.PARK);
       // bus.set
    }

    public ParkingPilot(){
        bus = Bus.getInstance();
        TimerTask task = new TimerTask() {
            @Override
            public void run() {
                if (bus.getCurrentSISpeed() < 30 && bus.getParkingPilotSwitchState()){
                    //valami terület = CheckFreeSpace();

                }
            }
        };



    }
}
