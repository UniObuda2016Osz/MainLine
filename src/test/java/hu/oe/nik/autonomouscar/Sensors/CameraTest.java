package hu.oe.nik.autonomouscar.Sensors;

import hu.oe.nik.autonomouscar.Environment.road_signs.Speed;
import hu.oe.nik.autonomouscar.Visuals.Car;
import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;
import org.junit.Assert;

/**
 * Created by SzZoli on 2016.11.02..
 */


public class CameraTest extends TestCase {

    Car car = new Car(10,10);

    public CameraTest(String name) {
        super(name);
    }

    public void setUp() throws Exception {
        super.setUp();
    }

    public void tearDown() throws Exception {
        super.tearDown();
    }

    /**
     *
     * Method: getEnvironmentRelevantObjects()
     *
     */
     public void testGetEnvironmentRelevantObjects() throws Exception {
        // waiting for UD method
     }

    /**
     *
     * Method: getRelevantObjects()
     *
     */
     public void testGetRelevantObjects() throws Exception {

     }

    /**
     *
     * Method: getCarDistanceFromObject(WorldObject wo)
     *
     */
    public void testGetCarDistanceFromObject() throws Exception {
        double carFrontMiddleXCoord = car.getXCoord()+car.getWidth()/2*Math.cos(car.getRotation());
        double carFrontMiddleYCoord = car.getYCoord()+car.getWidth()/2*Math.sin(car.getRotation());
        double woBackMiddleXCoord = car.getYCoord() + car.getLength()/2;
        double woBackMiddleYCoord = car.getXCoord() + car.getWidth()/2;
        double distance = Math.sqrt(Math.pow(woBackMiddleXCoord - carFrontMiddleXCoord, 2) + Math.pow(woBackMiddleYCoord - carFrontMiddleYCoord, 2)); //expect 25
        assertEquals(distance,25.0);
    }

    /**
     *
     * Method: setActualFieldView()
     *
     */
    public void testSetActualFieldView() throws Exception {
        Camera camera = new Camera(car);
        assertEquals(car.getYCoord() + car.getLength()/2,35); // centercoord

        /*
        try {
            Method method = Camera.getClass().getMethod("setActualFieldView");
            method.setAccessible(true);
            method.invoke(<Object>, <Parameters>);
        } catch(NoSuchMethodException e) {
        } catch(IllegalAccessException e) {
        } catch(InvocationTargetException e) {
        }
        */
    }

    public static Test suite() {
        return new TestSuite(CameraTest.class);
    }


    private Car auto = new Car(150, 100);
    private Camera kamera = new Camera(auto);
    private Speed tabla = new Speed(1, new int[]{200, 100}, new double[]{-1,0,0,-1}, 1, 1, Speed.SpeedType.Fifty);

    public void isInstanceOfCameraNotNull(){
        Assert.assertNotNull(kamera);
    }

    public void differentCameraCreation()
    {
        Assert.assertNotSame(new Camera(auto),kamera);
    }

    public void isDistanceCalculationCorrect()
    {
        Assert.assertEquals(50,kamera.getCarDistanceFromObject(tabla),60);
    }
}
