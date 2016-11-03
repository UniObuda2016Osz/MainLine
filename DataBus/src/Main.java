import Dynamics.VehicleDynamics;
import Environment.*;
import Sensors.Camera;
import Visuals.Car;

import javax.xml.stream.XMLStreamException;


/**
 * Created by ral2bp on 2016.09.29..
 */
public class Main {
    private static VehicleDynamics vehicleDynamics;
    public static void main(String[] args){
        System.out.println("Main has started");

        try {
            if (new XMLParserMain().Parser())
                System.out.println("Sikeres feldolgozás");
            else
                System.out.println("Sikertelen feldolgozás");
        }
        catch (XMLStreamException e) {
            System.out.println(String.format("%s\n" + "%s", "Sikertelen feldolgozás", e.getMessage()));
        }
        /*Call modules in the logical order here*/
        vehicleDynamics = VehicleDynamics.GetInstance();

        Car car = new Car(1200,1000);
        Camera cam = new Camera(car);
        System.out.println(cam.getEnvironmentRelevantObjects());

    }

}
