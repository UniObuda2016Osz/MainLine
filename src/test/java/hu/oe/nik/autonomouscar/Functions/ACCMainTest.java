package hu.oe.nik.autonomouscar.Functions;

import hu.oe.nik.autonomouscar.Bus.Bus;
import hu.oe.nik.autonomouscar.Environment.WorldObject;
import hu.oe.nik.autonomouscar.Sensors.Radar.DetectedObject;
import hu.oe.nik.autonomouscar.Visuals.App;
import junit.framework.TestCase;
import junit.framework.TestSuite;
import hu.oe.nik.autonomouscar.Functions.ACCMain;
import junit.framework.Test;
import org.junit.Assert;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;



public class ACCMainTest extends TestCase {
    ACCMain acc = ACCMain.getInstance();
    ArrayList<DetectedObject> list= new ArrayList<>();
    Bus bus = Bus.getInstance();
    public void setUp() throws Exception {
        super.setUp();
        DetectedObject DO = new DetectedObject();
        DO.setActualSpeed(100);
        DO.setActualDistance((float)10);
        list.add(DO);
        bus.setFourNearestFromRadar(list);
        bus.setCurrentSISpeed(100);
        bus.setAcceleration(2);
        acc.setAccOn();

    }
    public void tearDown() throws Exception {
        super.tearDown();
    }
    public void testAccIsOn(){
        assertEquals(acc.isAccOn(),true);
    }
    public void testdetectedObj(){
        assertEquals(bus.getFourNearestFromRadar(), list);
    }
    public void testGetNearestObj(){
        System.out.println("currspeed: "+bus.getCurrentSISpeed());
        System.out.println("acce "+bus.getAcceleration());
        System.out.println("inicializáláskor timegap: "+acc.getTimegap());
        acc.definitionOfClosestObject();
        assertEquals(bus.getFourNearestFromRadar().get(0).getActualDistance(),(float)10);
        //assertEquals(acc.getClosestTargetDistance(),(float)10);
        System.out.println("acce "+bus.getAcceleration());
    }
    public void testTargetSpeed(){
        acc.setTargetSpeed(true);
    }
    public static Test suite() {
        return new TestSuite(ACCMainTest.class);
    }
}
