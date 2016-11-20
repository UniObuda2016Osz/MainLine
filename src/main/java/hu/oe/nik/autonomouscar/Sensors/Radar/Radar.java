package hu.oe.nik.autonomouscar.Sensors.Radar;

import hu.oe.nik.autonomouscar.Environment.NPC.Cyclist;
import hu.oe.nik.autonomouscar.Environment.NPC.NpcCar;
import hu.oe.nik.autonomouscar.Environment.NPC.People;
import hu.oe.nik.autonomouscar.Environment.UserCar;
import hu.oe.nik.autonomouscar.Environment.WorldObject;
import hu.oe.nik.autonomouscar.Environment.XMLParserMain;

import javax.xml.stream.XMLStreamException;

import java.util.ArrayList;

/**
 * Created by ral2bp on 2016.09.29..
 */


public class Radar {

    private RadarCalculator radarCalculator;
    private XMLParserMain xmlP;
    private ArrayList<WorldObject> detectedObjects;
    private static final int radarDistance = 200;
    private static final int radarRadiusInDegree = 30;
    private UserCar car;
    private double carRotation;
    private double[] RadarCoord;
    private int[][] Triangle;
    private double[] transformation;
    public Radar(UserCar car){
        detectedObjects = new ArrayList<>();
        this.car = car;
        radarCalculator = new RadarCalculator();
        transformation = new double[]{0,0};
        Triangle = new int[3][2];
        RadarCoord = new double[2];
    }

    public void RadarMain() {
        Refresh();
        CalculateTriangle();
        try {
            GetObjectsFromEnvironment();
        } catch (XMLStreamException e) {
            //ne akadjon ki az egész program attól, hogy a radar nem kap listát
        }
        radarCalculator.Main(detectedObjects, car);
    }


    private void Refresh(){
        RadarCoord[0] = (double)car.getX()+car.getWidth()/2*Math.cos(car.getRotation());
        RadarCoord[1] = (double)car.getY()*Math.cos(car.getRotation());
    }
    private void GetObjectsFromEnvironment() throws XMLStreamException{
        ArrayList<WorldObject> inTriangle = new ArrayList<>();
        try {
            inTriangle = (ArrayList) xmlP.getDetectedObjects(this.Triangle[1][0], this.Triangle[1][1], this.Triangle[2][0], this.Triangle[2][1], this.Triangle[0][0], this.Triangle[0][1]);
        } catch (Exception e){
        }

        for(WorldObject wo : inTriangle){
            if(wo.getClass()== Cyclist.class || wo.getClass() == People.class || wo.getClass()== NpcCar.class){
                detectedObjects.add(wo);
            }
        }
    }

    private void CalculateTriangle(){
        this.Triangle[0][0] = (int)Math.round(RadarCoord[0]);
        this.Triangle[0][1] = (int)Math.round(RadarCoord[1]);
        this.Triangle[1][0] = (int)Math.round(RadarCoord[0] + radarDistance*Math.cos(car.getRotation()));
        this.Triangle[1][1] = (int)Math.round(RadarCoord[1] + Math.tan(radarRadiusInDegree/2)*radarDistance*Math.cos(car.getRotation()));
        this.Triangle[2][0] = (int)Math.round(RadarCoord[0] - radarDistance*Math.cos(car.getRotation()));
        this.Triangle[2][1] = (int)Math.round(RadarCoord[1] - Math.tan(radarRadiusInDegree/2)*radarDistance*Math.cos(car.getRotation()));
    }

}