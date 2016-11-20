package hu.oe.nik.autonomouscar.Environment;

import hu.oe.nik.autonomouscar.Environment.road_signs.Speed;
import org.junit.Assert;
import org.junit.Test;

/**
 * Created by Labancz Kristóf on 2016. 10. 28..
 */
public class WorldObjectTest {

    @org.junit.Before
    public void setUp() throws Exception {
    }

    @Test
    public void isGetCenterPointWorking() {
        Speed tabla = new Speed(1, new int[]{900, 300}, new double[]{-1,0,0,-1}, 1, 1, Speed.SpeedType.Fifty);
        Assert.assertArrayEquals(new int[]{860, 260},tabla.getCenterPoint());
    }
}
