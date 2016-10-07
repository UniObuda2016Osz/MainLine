package Bus;

import Bus.listener.IBusListener;
import Bus.Bus;

/**
 * Created by levair on 2016.10.07..
 */
public class BusListenerDemo {

    public static void main(String[] args) {


        //BCM and HMI modules should IMPLEMENT this interface. Let's use an anonymous class now.
        IBusListener listener = new IBusListener() {
            @Override
            public void onCurrentSISpeedChanged(float currentSISpeed) {}
            @Override
            public void onACCMainSwitchStateChanged(boolean ACCMainSwitchState) {}
            @Override
            public void onGasPedalChanged(int gasPedal) {
                System.out.println("whooaaaa gas pedal at " + gasPedal);
            }
            @Override
            public void onBrakePedalChanged(int brakePedal) {}
            @Override
            public void onSteeringWheelAngleChanged(int steeringWheelAngle) {}
            @Override
            public void onDirectionIndicatorLeftActiveChanged(boolean directionIndicatorLeftActive) {}
            @Override
            public void onDirectionIndicatorRightActiveChanged(boolean directionIndicatorRightActive) {}
            @Override
            public void onGearPositionChanged(Bus.GearPosition gearPosition) {}
        };


        System.out.println("Subscribe for events");
        Bus.getInstance().subscribe(listener);

        System.out.println("Pressing gas pedal");

        for (int i = 0; i < 150; i++) {

            System.out.println("Pressing gas pedal");
            Bus.getInstance().setGasPedal(i);

            try {
                Thread.sleep(150);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }



    }



}

