package Sensors;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertTrue;

/**
 * Created by Balazs on 2016.10.31..
 */
public class RadarCalculatorTest {

    RadarCalculator calculator = new RadarCalculator();

    @org.junit.Test
    public void testDetectedObjectListGetterSetter() {
        List<RadarCalculator.DetectedObject> list = new ArrayList<RadarCalculator.DetectedObject>();
        calculator.setDetectedObject(list);

        assertTrue(calculator.getDetectedObject() != null);

    }
}
