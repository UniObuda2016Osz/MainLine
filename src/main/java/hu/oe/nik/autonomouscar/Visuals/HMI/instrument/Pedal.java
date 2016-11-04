package hu.oe.nik.autonomouscar.Visuals.HMI.instrument;

/**
 * Created by levair on 2016.10.07..
 */
public class Pedal {

    public static final int PRESSURE_MODIFICATION_VALUE = 10;
    public static final int MAX_PRESSURE = 100;
    public static final int MIN_PRESSURE = 0;

    private int currentPressure;

    public Pedal () {
        this.currentPressure = MIN_PRESSURE;
    }

    public int increasePressure() {
        if (currentPressure < MAX_PRESSURE) {
            this.currentPressure += PRESSURE_MODIFICATION_VALUE;
        }
        return this.currentPressure;
    }

    public int decreasePressure() {
        if (currentPressure > MIN_PRESSURE) {
            this.currentPressure -= PRESSURE_MODIFICATION_VALUE;
        }
        return this.currentPressure;
    }

    public int stopPressing() {
        return this.currentPressure = MIN_PRESSURE;
    }

    public int pressMax() {
        return this.currentPressure = MAX_PRESSURE;
    }

    public int getCurrentPressure()
    {
        return this.currentPressure;
    }
}
