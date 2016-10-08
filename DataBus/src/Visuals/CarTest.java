package Visuals;

import org.junit.Assert;
import org.junit.Test;

/**
 * Created by Mitró Tamás on 2016.10.08..
 */
public class CarTest {

    Car auto = new Car(0,0);

    @Test
    public void isInstanceOfAutoNotNull(){
        Assert.assertNotNull(auto);
    }

    @Test
    public void areXAndYCoordinateZero(){
        Assert.assertEquals(0, auto.getXCoord());
        Assert.assertEquals(0, auto.getYCoord());
    }

    @Test
    public void isAutoNotCrashed(){
        Assert.assertEquals(false, auto.isCrashed());
    }

    @Test
    public void isAutoStarted(){
        auto.startAuto(10);
        Assert.assertEquals(true, auto.isMove());
        Assert.assertEquals(10, auto.getSpeed());
    }

    @Test
    public void isAccelerationTen(){
        auto.accelerateAuto(10);
        Assert.assertEquals(10, auto.getSpeed());
    }

    @Test
    public void isAutoMoved(){
        auto.accelerateAuto(10);
        Assert.assertEquals(true, auto.isMove());
    }

    @Test
    public void isAutoStopped(){
        auto.stopAuto();
        Assert.assertEquals(false, auto.isMove());
        Assert.assertEquals(0, auto.getSpeed());
    }
}
