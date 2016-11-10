package Sensors.ultrasonic;

import Environment.WorldObject;

/**
 * Created by Anonimus on 2016.11.10..
 */
public class ClosestWorldObjectWithDistance {
    private WorldObject closestObject;
    private double distance;

    public WorldObject getClosestObject() {
        return closestObject;
    }

    public void setClosestObject(WorldObject closestObject) {
        this.closestObject = closestObject;
    }

    public double getDistance() {
        return distance;
    }

    public void setDistance(double distance) {
        this.distance = distance;
    }
}
