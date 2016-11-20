package hu.oe.nik.autonomouscar.Visuals.HMITest;

import hu.oe.nik.autonomouscar.Visuals.HMI.instrument.Pedal;

import static org.junit.Assert.assertEquals;
/**
 * Created by pszotak on 2016.10.20..
 */
public class PedalTest {
    public Pedal pedal = new Pedal();

    @org.junit.Before
    public void setUp() throws Exception {
    }

    @org.junit.Test
    public void increasePressureTestNormal()
    {
        pedal = new Pedal();
        int original = pedal.getCurrentPressure();
        pedal.increasePressure();
        int actual = pedal.getCurrentPressure();

        assertEquals(original+10, actual);
    }

    @org.junit.Test
    public void increasePressureTestMax()
    {
        pedal = new Pedal();
        for(int i = 0; i < 15; i++)
        {
            pedal.increasePressure();
        }
        int actual = pedal.getCurrentPressure();

        assertEquals(100, actual);
    }

    @org.junit.Test
    public void decreasePressureTestNormal()
    {
        pedal = new Pedal();
        pedal.increasePressure();
        pedal.increasePressure();
        int original = pedal.getCurrentPressure();
        pedal.decreasePressure();
        int actual = pedal.getCurrentPressure();

        assertEquals(original-10, actual);
    }

    @org.junit.Test
    public void decreasePressureTestMin()
    {
        pedal = new Pedal();
        pedal.decreasePressure();
        pedal.decreasePressure();
        int actual = pedal.getCurrentPressure();

        assertEquals(0, actual);
    }

    @org.junit.Test
    public void StopPressingtest(){
        pedal = new Pedal();
        pedal.increasePressure();
        pedal.increasePressure();
        pedal.increasePressure();
        pedal.stopPressing();
        int actual = pedal.getCurrentPressure();
        assertEquals(0, actual);
    }

    @org.junit.Test
    public void pressMaxTest(){
        pedal = new Pedal();
        pedal.pressMax();
        int actual = pedal.getCurrentPressure();
        assertEquals(100,actual);
    }
}
