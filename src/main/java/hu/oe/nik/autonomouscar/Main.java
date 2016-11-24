package hu.oe.nik.autonomouscar;

import hu.oe.nik.autonomouscar.Dynamics.VehicleDynamics;
import hu.oe.nik.autonomouscar.Environment.XMLParserMain;
import hu.oe.nik.autonomouscar.Functions.ACCMain;
import hu.oe.nik.autonomouscar.Sensors.Camera;
import hu.oe.nik.autonomouscar.Visuals.Car;

import javax.xml.stream.XMLStreamException;

/**
 * Created by ral2bp on 2016.09.29..
 */
public class Main {
    private static VehicleDynamics vehicleDynamics;
    private static XMLParserMain xmlParser;

    public static void main(String[] args) throws XMLStreamException {
        System.out.println("Main has started");
        xmlParser = XMLParserMain.getInstance();
        try {
            if (xmlParser.Parser())
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
