package Visuals.HMI.listener;

import Bus.Bus;
import Visuals.HMI.component.CarInstrumentContainer;
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
        int keyCode=e.getKeyCode();

        System.out.println(String.format("key pressed by keyCode %s", keyCode));

        if((keyCode==KeyEvent.VK_UP))
        {
            int brakePressure = CarInstrumentContainer.singleton().getBrakePedal().stopPressing();
            Bus.getInstance().setBrakePedal(brakePressure);
            hmiPanel.getBrakePedalPressureBar().setValue(brakePressure);

            //FIXME levair - only propagate this info, if the value actually changed?
            int gasPressure = CarInstrumentContainer.singleton().getGasPedal().increasePressure();
            Bus.getInstance().setGasPedal( gasPressure );
            hmiPanel.getGasPedalPressureBar().setValue( gasPressure );
        }
        else if((keyCode==KeyEvent.VK_DOWN))
        {
            //FIXME levair - only propagate this info, if the value actually changed?
            int gasPressure = CarInstrumentContainer.singleton().getGasPedal().decreasePressure();
            Bus.getInstance().setGasPedal( gasPressure );
            hmiPanel.getGasPedalPressureBar().setValue( gasPressure );
        }
        else if ((keyCode==KeyEvent.VK_SPACE))
        {
            //FIXME levair - only propagate this info, if the value actually changed?
            int noGasPedal = CarInstrumentContainer.singleton().getGasPedal().stopPressing();
            Bus.getInstance().setGasPedal(noGasPedal);
            hmiPanel.getGasPedalPressureBar().setValue( noGasPedal );

            int brakePressure = CarInstrumentContainer.singleton().getBrakePedal().increasePressure();
            Bus.getInstance().setBrakePedal(brakePressure);
            hmiPanel.getBrakePedalPressureBar().setValue(brakePressure);

        }
        else if ((keyCode==KeyEvent.VK_LEFT))
        {
            int angle = CarInstrumentContainer.singleton().getSteeringWheel().turnLeft();
            hmiPanel.rotateSteeringWheel(angle);
        }
        else if ((keyCode==KeyEvent.VK_RIGHT))
        {
            int angle = CarInstrumentContainer.singleton().getSteeringWheel().turnRight();
            hmiPanel.rotateSteeringWheel(angle);
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        return;
    }
}
