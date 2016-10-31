package Sensors;


import Environment.WorldObject;

import java.util.List;

/**
 * Created by Balazs on 2016.10.31..
 */
public class RadarCalculator {

    private List<DetectedObject> detectedObject;

    public List<DetectedObject> getDetectedObject() {
        return detectedObject;
    }

    public void setDetectedObject(List<DetectedObject> detectedObject) {
        this.detectedObject = detectedObject;
    }


    public class DetectedObject {

        private int xCoordinate;
        private int yCoordinate;
        private float actualSpeed;
        private float actualDistance;
        private WorldObject worldObject;

        public DetectedObject(WorldObject worldObject) {
            this.worldObject = worldObject;
            setXCoordinate(worldObject.getCenterPoint()[0]);
            setYCoordinate(worldObject.getCenterPoint()[1]);
        }

        public int getXCoordinate() {
            return xCoordinate;
        }

        public void setXCoordinate(int xCoordinate) {
            this.xCoordinate = xCoordinate;
        }

        public int getYCoordinate() {
            return yCoordinate;
        }

        public void setYCoordinate(int yCoordinate) {
            this.yCoordinate = yCoordinate;
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
    }

}
