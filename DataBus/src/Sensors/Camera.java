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
    private final double latotavCsucs = 111.8; //ezeket akár ki is lehetne számítani,
    private final double latotavSzog = 53.12;//  de ha 1x működik akkor többször úgysem kell buzerálni

    public Camera(Car car) {
        this.car = car;
        this.parser = XMLParserMain.getInstance();
        this.fullList = new ArrayList<WorldObject>();
        this.relevantList = new ArrayList<WorldObject>();
        //this.leftUpperCoordinatesOfFieldView = new int[2];
        //this.rightUpperCoordinatesOfFieldView = new int[2];
        //this.centerCoordinatesOfCar = new int[2];
        this.setLeftUpperCoordinatesOfFieldView(new int[2]);
        this.setRightUpperCoordinatesOfFieldView(new int[2]);
        this.setCenterCoordinatesOfCar(new int[2]);
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

    //    (old coordinates are (x, y) and the new coordinates are (x', y'))
    //    q = initial angle, f = angle of rotation.
    //    x = r cos q
    //    y = r sin q
    //    x' = r cos ( q + f ) = r cos q cos f - r sin q sin f
    //    y' = r sin ( q + f ) = r sin q cos f + r cos q sin f
    //    3szögbe lát, a két sarokba a 3szög oldalsó vonalainak hossza ~112
    //    kis pitagorasz tétel: ha 100 messze lát el és oldalra 50-el lát ki
    //    szóval az r:112
    private void setActualFieldView() {
        //this.centerCoordinatesOfCar[0] = car.getXCoord() + car.getWidth()/2;
        //this.centerCoordinatesOfCar[1] = car.getYCoord() + car.getLength()/2;
        //this.leftUpperCoordinatesOfFieldView[0] = this.centerCoordinatesOfCar[0] - 50;
        //this.leftUpperCoordinatesOfFieldView[1] = this.centerCoordinatesOfCar[1] - 100;
        //this.leftUpperCoordinatesOfFieldView[0] = this.centerCoordinatesOfCar[0] + 50;
        //this.leftUpperCoordinatesOfFieldView[1] = this.centerCoordinatesOfCar[1] - 100;
        this.getCenterCoordinatesOfCar()[0] = car.getXCoord() + car.getWidth()/2;
        this.getCenterCoordinatesOfCar()[1] = car.getYCoord() + car.getLength()/2;
        this.getLeftUpperCoordinatesOfFieldView()[0] = (int)(latotavCsucs*Math.cos(Math.toRadians(90+latotavSzog/2+car.getRotation()))+ this.getCenterCoordinatesOfCar()[0]);
        this.getLeftUpperCoordinatesOfFieldView()[1] = this.getCenterCoordinatesOfCar()[1]-(int)(latotavCsucs*Math.sin(Math.toRadians(90+latotavSzog/2+car.getRotation())));
        this.getRightUpperCoordinatesOfFieldView()[0] = (int)(latotavCsucs*Math.cos(Math.toRadians(90-latotavSzog/2+car.getRotation()))+ this.getCenterCoordinatesOfCar()[0]);
        this.getRightUpperCoordinatesOfFieldView()[1] = this.getCenterCoordinatesOfCar()[1]-(int)(latotavCsucs*Math.sin(Math.toRadians(car.getRotation()+90-latotavSzog/2)));
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

    public int[] getLeftUpperCoordinatesOfFieldView() {
        return leftUpperCoordinatesOfFieldView;
    }

    public void setLeftUpperCoordinatesOfFieldView(int[] leftUpperCoordinatesOfFieldView) {
        this.leftUpperCoordinatesOfFieldView = leftUpperCoordinatesOfFieldView;
    }

    public int[] getRightUpperCoordinatesOfFieldView() {
        return rightUpperCoordinatesOfFieldView;
    }

    public void setRightUpperCoordinatesOfFieldView(int[] rightUpperCoordinatesOfFieldView) {
        this.rightUpperCoordinatesOfFieldView = rightUpperCoordinatesOfFieldView;
    }

    public int[] getCenterCoordinatesOfCar() {
        return centerCoordinatesOfCar;
    }

    public void setCenterCoordinatesOfCar(int[] centerCoordinatesOfCar) {
        this.centerCoordinatesOfCar = centerCoordinatesOfCar;
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
