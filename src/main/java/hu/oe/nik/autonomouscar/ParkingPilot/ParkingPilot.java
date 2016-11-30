package hu.oe.nik.autonomouscar.ParkingPilot;

import hu.oe.nik.autonomouscar.Bus.Bus;
import hu.oe.nik.autonomouscar.Dynamics.Timer;
import sun.security.jca.GetInstance;

/**
 * Created by csegenyr on 2016.11.30..
 */
public class ParkingPilot {
    Bus bus;
    private final float ParkingSpeed = 5;
    private boolean SwitchState;


    public void GetSwitchState(){
        SwitchState = bus.getParkingPilotSwitchState();
    }

    public boolean ssSwitchState() {
        return SwitchState;
    }

    public void setSwitchState(boolean switchbutton) {
        this.SwitchState = switchbutton;
    }
    public void Parking(){
        bus.setCurrentSISpeed(ParkingSpeed);
        bus.setBrakePedal(5);
    }

    public ParkingPilot(){
        bus = Bus.getInstance();
        GetSwitchState();
        if (bus.getCurrentSISpeed() < 30){
          //valami terület = CheckFreeSpace();

        }

    }
}
