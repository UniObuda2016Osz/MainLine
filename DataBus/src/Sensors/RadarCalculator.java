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
