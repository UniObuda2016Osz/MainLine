package Sensors;


import Environment.NPC.NPC;
import Environment.WorldObject;
import Visuals.Car;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Balazs on 2016.10.31..
 */
public class RadarCalculator {

    private List<DetectedObject> calculatedObjects = new ArrayList<DetectedObject>();

    public List<DetectedObject> getCalculatedObject() {
        return calculatedObjects;
    }

    public void setCalculatedObject(List<DetectedObject> calculatedObjects) {
        this.calculatedObjects = calculatedObjects;
    }

    public void calculateActualDistance(ArrayList<WorldObject> detectedObjects, Car car){
        for(WorldObject obj : detectedObjects) {
            DetectedObject detectedObject = new DetectedObject();
            detectedObject.setNpc((NPC) obj);
            int[]tempCoordinates={obj.getCenterPoint()[0]-car.getXCoord(), obj.getCenterPoint()[1]-car.getYCoord()};
            detectedObject.setActualDistance((float) Math.sqrt(tempCoordinates[0]*tempCoordinates[0]+tempCoordinates[1]*tempCoordinates[1]));
            calculatedObjects.add(detectedObject);
        }
    }

    public void calculateActualSpeed(Car car){
        for(DetectedObject obj : calculatedObjects) {
            obj.setActualSpeed((float) (car.getSpeed()-obj.getNpc().getMovingSpeed()));
            int a;
        }
    }

    public void calculateActualOffset(Car car) {
        double carRotation = car.getRotation();
        double carXRightCorner = car.getXCoord() + car.getWidth() / 2;
        double carXLeftCorner = car.getXCoord() - car.getWidth() / 2;
        double carYRightCorner = car.getYCoord() + car.getLength() / 2;
        double carYLeftCorner = car.getYCoord() - car.getLength() / 2;
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

            int leftoffset = (int)(carRotatedLeftCorner - objRightCorner);
            int rightoffset = (int)(objLeftCorner - carRotatedRightCorner);

            obj.setLeftOffset(leftoffset);
            obj.setRightOffset(rightoffset);
        }
    }


    public class DetectedObject {

        private float actualSpeed;
        private float actualDistance;
        private int leftOffset;
        private int rightOffset;
        private NPC npc;

       //public DetectedObject(NPC npc) {
            //this.npc = npc;
            //this.leftOffset = npc.getCenterPoint()[1] - npc.getWidth()/2;
            //this.rightOffset = npc.getCenterPoint()[1] + npc.getWidth()/2;
       //}

        public float getActualSpeed() {
            return actualSpeed;
        }

        public void setActualSpeed(float actualSpeed) {
            this.actualSpeed = actualSpeed;
        }

        public float getActualDistance() {
            return actualDistance;
        }

        public void setActualDistance(float actualDistance) {
            this.actualDistance = actualDistance;
        }

        public int getLeftOffset() {
            return leftOffset;
        }

        public void setLeftOffset(int leftOffset) {
            this.leftOffset = leftOffset;
        }

        public int getRightOffset() {
            return rightOffset;
        }

        public void setRightOffset(int rightOffset) {
            this.rightOffset = rightOffset;
        }

        public NPC getNpc() {
            return npc;
        }

        public void setNpc(NPC npc) {
            this.npc = npc;
        }
    }

}
