package Visuals.HMI.listener;

import Bus.Bus;
import Visuals.HMI.instrument.CarInstrumentContainer;
import Visuals.HMI.component.HMIPanel;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

/**
 * Handles all key events on the HMI Panel (eg. brake, accelerate, etc...)
 *
 * Created by Roland Levai on 2016.10.05..
 */
public class HMIKeyListener implements KeyListener {

    private HMIPanel hmiPanel;

    public HMIKeyListener(HMIPanel hmiPanel) {
        this.hmiPanel = hmiPanel;
    }

    @Override
    public void keyTyped(KeyEvent e) {
        return;
    }

    @Override
    public void keyPressed(KeyEvent e) {
        int keyCode = e.getKeyCode();

        if(keyCode == KeyEvent.VK_UP)
        {
            System.out.println(String.format("increasing GAS by keyCode %s", keyCode));
            int brakePressure = CarInstrumentContainer.singleton().getBrakePedal().stopPressing();
            Bus.getInstance().setBrakePedal(brakePressure);
            hmiPanel.getBrakePedalPressureBar().setValue(brakePressure);

            //FIXME levair - only propagate this info, if the value actually changed?
            int gasPressure = CarInstrumentContainer.singleton().getGasPedal().increasePressure();
            Bus.getInstance().setGasPedal( gasPressure );
            hmiPanel.getGasPedalPressureBar().setValue( gasPressure );
        }
        else if(keyCode == KeyEvent.VK_DOWN)
        {
            System.out.println(String.format("decreasing GAS by keyCode %s", keyCode));
            //FIXME levair - only propagate this info, if the value actually changed?
            int gasPressure = CarInstrumentContainer.singleton().getGasPedal().decreasePressure();
            Bus.getInstance().setGasPedal( gasPressure );
            hmiPanel.getGasPedalPressureBar().setValue( gasPressure );
        }
        else if (keyCode == KeyEvent.VK_SPACE)
        {
            System.out.println(String.format("pressing BRAKE by keyCode %s", keyCode));
            //FIXME levair - only propagate this info, if the value actually changed?
            int noGasPedal = CarInstrumentContainer.singleton().getGasPedal().stopPressing();
            Bus.getInstance().setGasPedal(noGasPedal);
            hmiPanel.getGasPedalPressureBar().setValue( noGasPedal );

            int brakePressure = CarInstrumentContainer.singleton().getBrakePedal().increasePressure();
            Bus.getInstance().setBrakePedal(brakePressure);
            hmiPanel.getBrakePedalPressureBar().setValue(brakePressure);

        }
        else if (keyCode == KeyEvent.VK_LEFT)
        {
            System.out.println(String.format("turning left by keyCode %s", keyCode));
            int angle = CarInstrumentContainer.singleton().getSteeringWheel().turnLeft();
            Bus.getInstance().setSteeringWheelAngle(angle);
            hmiPanel.repaint();
        }
        else if (keyCode == KeyEvent.VK_RIGHT)
        {
            System.out.println(String.format("turning right by keyCode %s", keyCode));
            int angle = CarInstrumentContainer.singleton().getSteeringWheel().turnRight();
            Bus.getInstance().setSteeringWheelAngle(angle);
            hmiPanel.repaint();
        }
        else if (keyCode == KeyEvent.VK_1)
        {
            System.out.println(String.format("set gearbox to PARK by keyCode %s", keyCode));
            Bus.getInstance().setGearPosition(Bus.GearPosition.PARK);
        }
        else if (keyCode == KeyEvent.VK_2)
        {
            System.out.println(String.format("set gearbox to REVERSE by keyCode %s", keyCode));
            Bus.getInstance().setGearPosition(Bus.GearPosition.REVERSE);
        }
        else if (keyCode == KeyEvent.VK_3)
        {
            System.out.println(String.format("set gearbox to NEUTRAL by keyCode %s", keyCode));
            Bus.getInstance().setGearPosition(Bus.GearPosition.NEUTRAL);
        }
        else if (keyCode == KeyEvent.VK_4)
        {
            System.out.println(String.format("set gearbox to DRIVE by keyCode %s", keyCode));
            Bus.getInstance().setGearPosition(Bus.GearPosition.DRIVE);
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        return;
    }
}
