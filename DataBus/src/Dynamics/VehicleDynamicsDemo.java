package Dynamics;

import Bus.Bus;

import java.util.ArrayList;

/**
 * Created by Beci on 2016.10.12..
 */
public class VehicleDynamicsDemo {

    public static void main(String[] args) {
        try {
            Bus bus = Bus.getInstance();

            bus.setBrakePedal(0);
            bus.setGasPedal(0);
            bus.setGearPosition(Bus.GearPosition.NEUTRAL);

            Timer demotimer = Timer.GetInstance();

            ArrayList<TimerListener> demolisteners = new ArrayList<TimerListener>();
            demolisteners.add(VehicleDynamicsDemoTimerListener.getInstance());

            demotimer.setListener(demolisteners);
            demotimer.setSleeptime(100);

            demotimer.setIsenabled(true);

            Thread.sleep(200);

            bus.setGearPosition(Bus.GearPosition.DRIVE);
            bus.setGasPedal(100);
            Thread.sleep(500);

            bus.setGasPedal(0);
            Thread.sleep(300);

            bus.setBrakePedal(100);
            Thread.sleep(200);

            demotimer.setIsenabled(false);

        } catch (InterruptedException intex) {

        }
    }

}
