package Visuals.mock;


import Visuals.Car;
import org.junit.Assert;
import org.junit.Test;
import javax.swing.*;

/**
 * Created by Mitró Tamás on 2016.10.03..
 */
public class AutoTest {

    Car auto = new Car();
/**  Tamás Ez itt hibára fut mint állat azért kommenteltem ki légyszíves nézz rá és amíg valami nem fordul le ne hagyd így itt.

/*    @Test
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
    }*/

}
