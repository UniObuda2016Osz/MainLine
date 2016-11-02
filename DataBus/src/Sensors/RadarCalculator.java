package Sensors;


import Environment.WorldObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Balazs on 2016.10.31..
 */
public class RadarCalculator {

    private List<DetectedObject> calculatedObjects;

    public List<DetectedObject> getCalculatedObject() {
        return calculatedObjects;
    }

    public void setCalculatedObject(List<DetectedObject> calculatedObjects) {
        this.calculatedObjects = calculatedObjects;
    }

    public void calculateActualDistance(ArrayList<WorldObject> detectedObjects){
        for(WorldObject obj : detectedObjects) {
            //TODO: calculate distance
            DetectedObject detectedObject = null;
            calculatedObjects.add(detectedObject);
        }
    }

    public void calculateActualSpeed(){
        for(DetectedObject obj : calculatedObjects) {
            //TODO: calculate speed
            obj.setActualSpeed(100);
        }
    }


    public class DetectedObject {

        private float actualSpeed;
        private float actualDistance;
        private int leftOffset;
        private int rightOffset;
        private WorldObject worldObject;

        public DetectedObject(WorldObject worldObject) {
            this.worldObject = worldObject;
            this.leftOffset = worldObject.getCenterPoint()[1] - worldObject.getWidth()/2;
            this.rightOffset = worldObject.getCenterPoint()[1] + worldObject.getWidth()/2;
        }

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

        public WorldObject getWorldObject() {
            return worldObject;
        }

        public void setWorldObject(WorldObject worldObject) {
            this.worldObject = worldObject;
        }
    }

}
