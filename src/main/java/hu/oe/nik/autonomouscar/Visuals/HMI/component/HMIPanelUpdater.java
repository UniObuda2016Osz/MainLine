package hu.oe.nik.autonomouscar.Visuals.HMI.component;

import hu.oe.nik.autonomouscar.Bus.Bus;

import java.awt.*;
import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by levair on 2016.10.12..
 */
public class HMIPanelUpdater {

    public static final int REFRESH_RATE_MS = 100;
    public static final int WAIT_BEFORE_START_MS = 100; //panel initialization takes time too

    public static void keepUpdating(HMIPanel panel) {

        Timer timer = new Timer();
        timer.schedule(new HMIPanelRefreshingTask(panel), WAIT_BEFORE_START_MS, REFRESH_RATE_MS);
    }

    static class HMIPanelRefreshingTask extends TimerTask {

        private final HMIPanel panel;

        public HMIPanelRefreshingTask(HMIPanel panel) {
            this.panel = panel;
        }

        @Override
        public void run() {
            updateSpeed();
            updateGearbox();
        }

        private void updateSpeed()
        {
            int currentSpeed = Math.round( Bus.getInstance().getCurrentSISpeed() );
            panel.getSpeedBar().setValue( currentSpeed );
            panel.getSpeedBar().setString( currentSpeed + " km/h");

        }

        private void updateGearbox()
        {
            Bus.GearPosition gearPosition = Bus.getInstance().getGearPosition();
            if (gearPosition == Bus.GearPosition.PARK)
            {
                panel.getLabelGearboxPark().setBackground(Color.cyan);
                panel.getLabelGearboxReverse().setBackground(Color.white);
                panel.getLabelGearboxNeutral().setBackground(Color.white);
                panel.getLabelGearboxDrive().setBackground(Color.white);
            }
            else if (gearPosition ==  Bus.GearPosition.REVERSE)
            {
                panel.getLabelGearboxPark().setBackground(Color.white);
                panel.getLabelGearboxReverse().setBackground(Color.cyan);
                panel.getLabelGearboxNeutral().setBackground(Color.white);
                panel.getLabelGearboxDrive().setBackground(Color.white);
            }
            else if (gearPosition == Bus.GearPosition.NEUTRAL)
            {
                panel.getLabelGearboxPark().setBackground(Color.white);
                panel.getLabelGearboxReverse().setBackground(Color.white);
                panel.getLabelGearboxNeutral().setBackground(Color.cyan);
                panel.getLabelGearboxDrive().setBackground(Color.white);
            }
            else if (gearPosition == Bus.GearPosition.DRIVE)
            {
                panel.getLabelGearboxPark().setBackground(Color.white);
                panel.getLabelGearboxReverse().setBackground(Color.white);
                panel.getLabelGearboxNeutral().setBackground(Color.white);
                panel.getLabelGearboxDrive().setBackground(Color.cyan);
            }
            else
            {
                panel.getLabelGearboxPark().setBackground(Color.white);
                panel.getLabelGearboxReverse().setBackground(Color.white);
                panel.getLabelGearboxNeutral().setBackground(Color.white);
                panel.getLabelGearboxDrive().setBackground(Color.white);
            }
        }
    }
}
