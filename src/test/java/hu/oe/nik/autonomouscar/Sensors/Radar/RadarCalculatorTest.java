package hu.oe.nik.autonomouscar.Sensors.Radar;

import hu.oe.nik.autonomouscar.Environment.NPC.Cyclist;
import hu.oe.nik.autonomouscar.Environment.NPC.NPC;
import hu.oe.nik.autonomouscar.Environment.NPC.NpcCar;
import hu.oe.nik.autonomouscar.Environment.UserCar;
import hu.oe.nik.autonomouscar.Environment.WorldObject;
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
        List<DetectedObject> list = new ArrayList<DetectedObject>();
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

        UserCar car = new UserCar(1,1);
        calculator.calculateActualDistance(detectedObjects,car);

        assertEquals(calculator.getCalculatedObject().get(0).getActualDistance(),12.727922439575195,0);
    }

    @Test
    public void testCalculateSpeed() {
        ArrayList<WorldObject> detectedObjects = new ArrayList<WorldObject>();
        int[]startPosition = {10,10};
        double[] transform = {0,0,0,0};
        NpcCar obj = new NpcCar(1,startPosition,5,20,transform,0,0,0,0);
        obj.setMovingSpeed(10);
        detectedObjects.add(obj);

        UserCar car = new UserCar(1,1);
        car.setSpeed(70);
        calculator.calculateActualDistance(detectedObjects,car);
        calculator.calculateActualSpeed(car);

        assertEquals(calculator.getCalculatedObject().get(0).getActualSpeed(),60,0);
    }

    @Test
    public void testCalculateTypeOfNPCList(){
        NPC npc = new Cyclist(10, new int[]{1,1}, 10, 10, new double[]{0,0, 0, 0}, 0, 0, 0,0);
        ArrayList<DetectedObject> list = new ArrayList<>();
        DetectedObject DO = new DetectedObject();
        DO.setNpc(npc);
        list.add(DO);
        calculator.setCalculatedObject(list);
        calculator.CalculateTypeOfNPCList();
        assertEquals(calculator.getCalculatedObject().get(0).getNpctype(), RadarCalculator.NPCType.Cyclist);
    }
}
