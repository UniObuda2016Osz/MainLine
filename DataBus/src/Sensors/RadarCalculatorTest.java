package Sensors;

import Environment.NPC.NpcCar;
import Environment.WorldObject;
import Visuals.Car;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * Created by Balazs on 2016.10.31..
 */
public class RadarCalculatorTest {

    RadarCalculator calculator = new RadarCalculator();

    @Test
    public void testDetectedObjectListGetterSetter() {
        List<RadarCalculator.DetectedObject> list = new ArrayList<RadarCalculator.DetectedObject>();
        calculator.setCalculatedObject(list);

        assertTrue(calculator.getCalculatedObject() != null);

    }

    @Test
    public void testCalculateDistance() {
        ArrayList<WorldObject> detectedObjects = new ArrayList<WorldObject>();
        int[]startPosition = {10,10};
        double[] transform = {0,0,0,0};
        WorldObject obj = new NpcCar(1,startPosition,5,20,transform,0,0,0,0);
        detectedObjects.add(obj);

        Car car = new Car(1,1);
        calculator.calculateActualDistance(detectedObjects,car);

        assertEquals(calculator.getCalculatedObject().get(0).getActualDistance(),12.727922439575195,0);
    }
}
