package Visuals.mock;

import org.junit.Assert;
import org.junit.Test;

import javax.swing.*;

/**
 * Created by Mitró Tamás on 2016.10.03..
 */
public class AutoTest {

    Auto auto = new Auto();

    @Test
    public void isAutoReadyToGo(){
        Assert.assertEquals(auto.isMove(), false);
        Assert.assertEquals(auto.isCrashed(), false);
        Assert.assertEquals(auto.getSpeed(), Integer.valueOf(0));
        Assert.assertEquals(auto.getDirection(), Direction.STAND);
    }

    @Test
    public void isSpeedIsTen(){
        auto.setSpeed(10);

        Assert.assertEquals(auto.getSpeed(), Integer.valueOf(10));
    }

    @Test
    public void isSpeedIsFifty(){
        auto.setSpeed(50);

        Assert.assertEquals(auto.getSpeed(), Integer.valueOf(50));
    }

}
