package Sensors.ultrasonic;

import Environment.Position;
import Environment.WorldObject;
import Environment.XMLParserMain;
import Visuals.Car;

import java.util.List;

/**
 * Created by levair on 2016.10.29..
 */
public class UltrasonicSensor {

    public static final int MAX_REACHABLE_DISTANCE = 50;
    public static final int WIDTH_ON_MAX_REACHABLE_DISTANCE = 70;

    private Car ownerCar;
    private UltraSonicSensorPosition positionOnCar;

    public UltrasonicSensor(Car ownerCar, UltraSonicSensorPosition positionOnCar) {
        this.ownerCar = ownerCar;
        this.positionOnCar = positionOnCar;
    }

    public List<WorldObject> getCurrentVisibleObjects() {
        Position currentBasePosition = getCurrentBasepoint();
        int centerX = currentBasePosition.getX();
        int centerY = currentBasePosition.getY();

        //TODO: calculate other 2 points of the triangle by the base point and MAX_REACHABLE_DISTANCE, WIDTH_ON_MAX_REACHABLE_DISTANCE properties
        int leftX = -1;
        int leftY = -1;
        int rightX = -1;
        int rightY = -1;

        return XMLParserMain.getInstance().getDetectedObjects(leftX, leftY, rightX, rightY, centerX, centerY);
    }

    private Position getCurrentBasepoint() {
        //TODO: calculate current base points for THIS (out of 8) sensor by:
        //TODO - ownerCar
        //TODO - positionOnCar

        int x = ownerCar.getWidth();
        int y = ownerCar.getLength();
        int carFacing = ownerCar.getDirection();
        double environmentX;
        double environmentY;
        double r;

        switch (positionOnCar) {
            case FRONT_INNER_LEFT:
            case FRONT_OUTER_LEFT:
            case FRONT_INNER_RIGHT:
            case FRONT_OUTER_RIGHT:
                r = x / 2;
                environmentX = Math.cos(carFacing) * r;
                environmentY = -Math.sin(carFacing) * r;
                return new Position(ownerCar.getXCoord() + (int) environmentX, ownerCar.getYCoord() + (int) environmentY);
            case REAR_INNER_LEFT:
            case REAR_OUTER_LEFT:
            case REAR_INNER_RIGHT:
            case REAR_OUTER_RIGHT:
                r = Math.sqrt(Math.pow(x / 2, 2) + Math.pow(y, 2));
                environmentX = Math.cos(carFacing) * r;
                environmentY = -Math.sin(carFacing) * r;
                return new Position(ownerCar.getXCoord() + (int) environmentX, ownerCar.getYCoord() + (int) environmentY);
            default:
                return new Position(0, 0);
        }
    }
}
