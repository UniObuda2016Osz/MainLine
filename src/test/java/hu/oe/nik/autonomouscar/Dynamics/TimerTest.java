package hu.oe.nik.autonomouscar.Dynamics;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Created by fayez on 10/12/2016.
 */
public class TimerTest {
    Timer timer = Timer.GetInstance();

    @org.junit.Before
    public void setUp() throws Exception {
    }

    @org.junit.Test
    public void isTimerSingleton()
    {
        Timer first = Timer.GetInstance();
        Timer second = Timer.GetInstance();

        assertEquals(first == second, true);
    }

    @Test
    public void isenabledGetterReturnsSetterGivenValue(){
        timer.setIsenabled(true);
        assertEquals(timer.isenabled(), true);
    }

    @Test
    public void sleeptimeGetterReturnsSetterGivenValue(){
        timer.setSleeptime(1);
        assertEquals(timer.getSleeptime(), 1);
    }

}
