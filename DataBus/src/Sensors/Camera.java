package Sensors;

import Environment.WorldObject;
import Environment.XMLParserMain;
import Visuals.Car;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Mitró Tamás on 2016.10.31..
 */
public class Camera {

    private List<WorldObject> fullList;
    private List<WorldObject> relevantList;
    private int[] leftUpperCoordinatesOfFieldView;
    private int[] rightUpperCoordinatesOfFieldView;
    private int[] centerCoordinatesOfCar;
    private Car car;
    private XMLParserMain parser;

    public Camera(Car car) {
        this.car = car;
        this.parser = new XMLParserMain();
        this.fullList = new ArrayList<WorldObject>();
        this.relevantList = new ArrayList<WorldObject>();
        this.leftUpperCoordinatesOfFieldView = new int[2];
        this.rightUpperCoordinatesOfFieldView = new int[2];
        this.centerCoordinatesOfCar = new int[2];
    }

    public List<WorldObject> getEnvironmentRelevantObjects(){
        setActualFieldView();
        this.fullList = this.parser.getDetectedObjects(
                leftUpperCoordinatesOfFieldView[0], leftUpperCoordinatesOfFieldView[1],
                rightUpperCoordinatesOfFieldView[0], rightUpperCoordinatesOfFieldView[1],
                centerCoordinatesOfCar[0], centerCoordinatesOfCar[1]);
        //Leszűrés a releváns objektumokra
        //getRelevantObjects();
        return this.relevantList;
    }

    private void setActualFieldView() {
        this.centerCoordinatesOfCar[0] = car.getXCoord() + car.getWidth()/2;
        this.centerCoordinatesOfCar[1] = car.getYCoord() + car.getLength()/2;
        this.leftUpperCoordinatesOfFieldView[0] = this.centerCoordinatesOfCar[0] - 50;
        this.leftUpperCoordinatesOfFieldView[1] = this.centerCoordinatesOfCar[1] - 100;
        this.leftUpperCoordinatesOfFieldView[0] = this.centerCoordinatesOfCar[0] + 50;
        this.leftUpperCoordinatesOfFieldView[1] = this.centerCoordinatesOfCar[1] - 100;
    }

    public void getRelevantObjects(){
        for(WorldObject wo : fullList){
            // A full lista leszűrése utakra és táblákra.
            // a relevantList-hez hozzá kell adni a wo-t, ha a típusa tábla, vagy út.
            //if (wo.equals(valamilyen út) || wo.equals(valamilyen tábla)){
            //    relevantList.add(wo);
            //}
        }
    }

    public double getCarDistanceFromObject(WorldObject wo) {
        int[] woPosition=wo.getPosition();
        double carFrontMiddleXCoord = car.getXCoord()+car.getWidth()/2*Math.cos(car.getRotation());
        double carFrontMiddleYCoord = car.getYCoord()+car.getWidth()/2*Math.sin(car.getRotation());
        double woBackMiddleXCoord = woPosition[0] + wo.getWidth()/2;
        double woBackMiddleYCoord = woPosition[1] - wo.getHeight();
        double distance = Math.sqrt(Math.pow(woBackMiddleXCoord - carFrontMiddleXCoord, 2) + Math.pow(woBackMiddleYCoord - carFrontMiddleYCoord, 2));
        return distance;
    }

}
