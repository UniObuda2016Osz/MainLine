package Visuals.HMI.component;

import Bus.Bus;

import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by levair on 2016.10.12..
 */
public class HMIPanelUpdater {

    public static final int REFRESH_RATE_MS = 100;

    public static void keepUpdating(HMIPanel panel) {

        Timer timer = new Timer();
        timer.schedule(new TimerTask() {

            @Override
            public void run() {
                float currentSpeed = Bus.getInstance().getCurrentSISpeed();
                panel.getSpeedBar().setValue( Math.round(currentSpeed) );
            }

        }, 0, REFRESH_RATE_MS);
    }
}
