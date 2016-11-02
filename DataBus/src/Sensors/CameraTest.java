package Sensors;

import Environment.WorldObject;
import Environment.XMLParserMain;
import Environment.road_signs.Speed;
import Visuals.Car;

import java.util.*;

import org.junit.Assert;
import org.junit.Test;

/**
 * Created by SzZoli on 2016.11.02..
 */


public class CameraTest {

    private Car auto = new Car(150, 100);
    private Camera kamera = new Camera(auto);
    private Speed tabla = new Speed(1, new int[]{200, 100}, new double[]{-1,0,0,-1}, 1, 1, Speed.SpeedType.Fifty);

    @Test
    public void isInstanceOfCameraNotNull(){
        Assert.assertNotNull(kamera);
    }

    @Test
    public void differentCameraCreation()
    {
        Assert.assertNotSame(new Camera(auto),kamera);
    }

    @Test
    public void isDistanceCalculationCorrect()
    {
        Assert.assertEquals(50,kamera.getCarDistanceFromObject(tabla),60);
    }
}
