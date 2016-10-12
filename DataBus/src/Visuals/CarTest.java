package Visuals;

import org.junit.Assert;
import org.junit.Test;

/**
 * Created by Mitró Tamás on 2016.10.08..
 */
public class CarTest {

    Car auto = new Car(150, 100);

    @Test
    public void isInstanceOfAutoNotNull(){
        Assert.assertNotNull(auto);
    }

    @Test
    public void areXAndYCoordinateNotZero(){
        Assert.assertEquals(150, auto.getXCoord());
        Assert.assertEquals(100, auto.getYCoord());
    }

    @Test
    public void isDirectionFourtyFive(){
        Assert.assertEquals(45, auto.getDirection());
    }

    @Test
    public void isDirectionNinety(){
        auto.setDirection(90);
        Assert.assertEquals(90, auto.getDirection());
    }

    @Test
    public void isAutoNotCrashed(){
        Assert.assertEquals(false, auto.isCrashed());
    }

    @Test
    public void isAutoStarted(){
        auto.startAuto(10);
        Assert.assertEquals(true, auto.isMove());
        Assert.assertEquals(10, auto.getSpeed(), 10);
    }

    @Test
    public void isAccelerationTen(){
        auto.accelerateAuto(10);
        Assert.assertEquals(10, auto.getSpeed(), 10);
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
        Assert.assertEquals(0, auto.getSpeed(), 0);
    }
}
