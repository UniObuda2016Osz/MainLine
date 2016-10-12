package Visuals.HMI.mock;

import Bus.Bus;
import Visuals.HMI.component.HMIPanel;
import Visuals.HMI.component.HMIPanelUpdater;

import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by Rav on 2016.10.12..
 */
public class EngineMock {

    public static final int REFRESH_RATE_MS = 100;
    public static final int WAIT_BEFORE_START_MS = 100; //panel initialization takes time too

    public static void mock() {

        Timer timer = new Timer();
        timer.schedule(new MockEngineTimerTask(), WAIT_BEFORE_START_MS, REFRESH_RATE_MS);
    }

    static class MockEngineTimerTask extends TimerTask {

        @Override
        public void run() {

            int gas = Bus.getInstance().getGasPedal();
            if (gas <= 0)
            {
                Bus.getInstance().setCurrentSISpeed( 0 );
            }
            else if (gas < 20)
            {
                Bus.getInstance().setCurrentSISpeed( Bus.getInstance().getCurrentSISpeed() + 1 );
            }
            else if (gas < 50 )
            {
                Bus.getInstance().setCurrentSISpeed( Bus.getInstance().getCurrentSISpeed() + 3 );
            }
            else if (gas < 80)
            {
                Bus.getInstance().setCurrentSISpeed( Bus.getInstance().getCurrentSISpeed() + 7 );
            }
            else
            {
                Bus.getInstance().setCurrentSISpeed( Bus.getInstance().getCurrentSISpeed() + 10 );
            }

        }
    }
}
