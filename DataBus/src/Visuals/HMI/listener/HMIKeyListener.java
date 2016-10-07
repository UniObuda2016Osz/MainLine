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
            //FIXME levair - only propagate this info, if the value actually changed?
            Bus.getInstance().setGasPedal( CarInstrumentContainer.singleton().getGasPedal().increasePressure() );
        }
        else if((keyCode==KeyEvent.VK_DOWN))
        {
            //FIXME levair - only propagate this info, if the value actually changed?
            Bus.getInstance().setGasPedal( CarInstrumentContainer.singleton().getGasPedal().decreasePressure() );
        }


        hmiPanel.getlSpeed().setText( ""+ Bus.getInstance().getGasPedal() ); //FIXME levair - this is not the speed, but the pedal pressure
    }

    @Override
    public void keyReleased(KeyEvent e) {
        return;
    }
}
