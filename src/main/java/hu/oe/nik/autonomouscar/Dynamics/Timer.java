package hu.oe.nik.autonomouscar.Dynamics;

import java.util.ArrayList;

/**
 * Created by fayez on 10/12/2016.
 */
public class Timer extends Thread {

    private static Timer instance;
    private int Tick;
    private ArrayList<TimerListener> listener;
    private boolean isenabled = false;
    private int sleeptime = 100;

    public static Timer GetInstance(){
        if(instance==null){
            instance=new Timer();
        }
        return instance;
    }

    @Override
    public void run()  {
        while (isenabled)  {
            for (TimerListener l : listener) {
                l.OnTimerTick();
            }
            try {
                Thread.sleep(this.sleeptime);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public ArrayList<TimerListener> getListener() {
        return listener;
    }

    public boolean isenabled() {
        return isenabled;
    }

    public int getSleeptime() {
        return sleeptime;
    }

    public void setListener(ArrayList<TimerListener> listener) {
        this.listener = listener;
    }

    public void setIsenabled(boolean isenabled) {
        this.isenabled = isenabled;
        if (this.isenabled) {
            this.start();
        }
    }

    public void setSleeptime(int sleeptime) {
        this.sleeptime = sleeptime;
    }
}

