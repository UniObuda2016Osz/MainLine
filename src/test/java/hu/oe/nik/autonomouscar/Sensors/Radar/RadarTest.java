package hu.oe.nik.autonomouscar.Sensors.Radar;

import hu.oe.nik.autonomouscar.Environment.UserCar;

/**
 * Created by csegenyr on 2016.10.31..
 */
public class RadarTest {

    Radar radar = new Radar(new UserCar(100,100));
    //XMLParserMain xml = new XMLParserMain();

//    @Test
/*    public void DemoTest()
    {
        Demo();
    }
*/

/*    public void Demo(){
        radar.setRadarCoord(1,1);
        Car car = new Car(1,1);
        radar.CalculateTriangle(car);
        //radar.GetObjectsFromEnvironment();
        //Mock
        ArrayList<WorldObject> detectedObjects = new ArrayList<WorldObject>();
        detectedObjects.add(new NpcCar(1,new int[]{10,10},10,25,new double[]{0,0,0,0},0,0,0,45));
        detectedObjects.add(new NpcCar(2,new int[]{15,20},10,25,new double[]{0,0,0,0},0,0,0,55));


        car.setSpeed(60);

        System.out.println("\nAutó sebessége: " + car.getSpeed() + " km/h pozíciója: [1,1]\n");

        radar.getRadarCalculator().calculateActualDistance(detectedObjects,car);
        radar.getRadarCalculator().calculateActualSpeed(car);
        radar.getRadarCalculator().calculateActualOffset(car);
    }*/


}
