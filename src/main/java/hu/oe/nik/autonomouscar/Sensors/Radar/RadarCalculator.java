package hu.oe.nik.autonomouscar.Sensors.Radar;


import hu.oe.nik.autonomouscar.Environment.NPC.Cyclist;
import hu.oe.nik.autonomouscar.Environment.NPC.NPC;
import hu.oe.nik.autonomouscar.Environment.NPC.NpcCar;
import hu.oe.nik.autonomouscar.Environment.NPC.People;
import hu.oe.nik.autonomouscar.Environment.UserCar;
import hu.oe.nik.autonomouscar.Environment.WorldObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Balazs on 2016.10.31..
 */
public class RadarCalculator {

    public void setCalculatedObject(List<DetectedObject> list) {
        calculatedObjects = list;
    }

    public enum NPCType {Cyclist, Car, Pedestrian, Other}

    private List<DetectedObject> calculatedObjects = new ArrayList<DetectedObject>();

    public List<DetectedObject> getCalculatedObject() {
        return calculatedObjects;
    }

    public void Main(ArrayList<WorldObject> list, UserCar car){
        calculateActualDistance(list, car);
        calculateActualSpeed(car);
        calculateActualOffset(car);
        CalculateTypeOfNPCList();
    }

    private NPCType GetType(WorldObject WO) {
        NPCType returnValue = NPCType.Other;

        Class c = WO.getClass();

        if (c == Cyclist.class)
            returnValue = NPCType.Cyclist;
        else if (c == People.class)
            returnValue = NPCType.Pedestrian;
        else if (c == NpcCar.class)
            returnValue = NPCType.Car;

        return returnValue;
    }

    void CalculateTypeOfNPCList() {
        for (DetectedObject obj : calculatedObjects) {
            obj.setNpctype(GetType(obj.getNpc()));
        }
    }

    void calculateActualDistance(ArrayList<WorldObject> detectedObjects, UserCar car) {
        for (WorldObject obj : detectedObjects) {
            DetectedObject detectedObject = new DetectedObject();
            detectedObject.setNpc((NPC) obj);
            int[] tempCoordinates = {obj.getCenterPoint()[0] - car.getX(), obj.getCenterPoint()[1] - car.getY()};
            detectedObject.setActualDistance((float) Math.sqrt(tempCoordinates[0] * tempCoordinates[0] + tempCoordinates[1] * tempCoordinates[1]));
            calculatedObjects.add(detectedObject);
        }
    }

    void calculateActualSpeed(UserCar car) {
        for (DetectedObject obj : calculatedObjects) {
            obj.setActualSpeed((float) (car.getSpeed() - obj.getNpc().getMovingSpeed()));
        }
    }

    void calculateActualOffset(UserCar car) {
        double carRotation = car.getRotation();
        double carXRightCorner = car.getX() + car.getWidth() / 2;
        double carXLeftCorner = car.getX() - car.getWidth() / 2;
        double carYRightCorner = car.getY() + car.getHeight() / 2;
        double carYLeftCorner = car.getY() - car.getHeight() / 2;
        double carRotatedRightCorner = carXRightCorner * Math.cos(carRotation) + carYRightCorner * Math.sin(carRotation);
        double carRotatedLeftCorner = carXLeftCorner * Math.cos(carRotation) + carYLeftCorner * Math.sin(carRotation);

        for (DetectedObject obj : calculatedObjects) {
            double objX = obj.getNpc().getCenterPoint()[0];
            double objY = obj.getNpc().getCenterPoint()[1];
            double objWidth = obj.getNpc().getWidth();
            double objLength = obj.getNpc().getHeight();

            double objXRightCorner = objX + objWidth / 2;
            double objXLeftCorner = objX - objWidth / 2;
            double objYRightCorner = objY + objLength / 2;
            double objYLeftCorner = objY - objLength / 2;

            double objRightCorner = objXRightCorner * Math.cos(carRotation) + objYRightCorner * Math.sin(carRotation);
            double objLeftCorner = objXLeftCorner * Math.cos(carRotation) + objYLeftCorner * Math.sin(carRotation);

            int leftoffset = (int) (carRotatedLeftCorner - objRightCorner);
            int rightoffset = (int) (objLeftCorner - carRotatedRightCorner);

            obj.setLeftOffset(leftoffset);
            obj.setRightOffset(rightoffset);
        }
    }
}
