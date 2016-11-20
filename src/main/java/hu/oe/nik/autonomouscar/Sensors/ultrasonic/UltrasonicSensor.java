package hu.oe.nik.autonomouscar.Sensors.ultrasonic;

import hu.oe.nik.autonomouscar.Environment.Position;
import hu.oe.nik.autonomouscar.Environment.WorldObject;
import hu.oe.nik.autonomouscar.Environment.XMLParserMain;
import hu.oe.nik.autonomouscar.Visuals.Car;

import javax.xml.stream.XMLStreamException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by levair on 2016.10.29..
 */
public class UltrasonicSensor {

    public static final int VIEW_DISTANCE = 5;
    public static final int VIEW_ANGLE = 90;

    public static final double RIGHT_SIDE_ANGLE_OF_VIEW = (180 - VIEW_ANGLE) / 2;
    public static final double LEFT_SIDE_ANGLE_OF_VIEW = 180 - RIGHT_SIDE_ANGLE_OF_VIEW;


    private Car ownerCar;
    private UltraSonicSensorPosition positionOnCar;

    public UltrasonicSensor(Car ownerCar, UltraSonicSensorPosition positionOnCar) {
        this.ownerCar = ownerCar;
        this.setPositionOnCar(positionOnCar);
    }

    /**
     * @return all the SOLID objects this sensor detects
     * Parking Pilot should use this and only this function directly.
     */
    public List<WorldObject> getSolidWorldObjects() throws XMLStreamException {
        List<WorldObject> solidWorldObjects = new ArrayList<>();

        for (WorldObject commonWorldObject : getAllCurrentVisibleObjects()) {
            if (commonWorldObject.getCanStuckOnIt()) {
                solidWorldObjects.add(commonWorldObject);
            }
        }
        return solidWorldObjects;
    }

    /**
     * @return all the objects this sensor detects (public for testing purposes)
     */
    public List<WorldObject> getAllCurrentVisibleObjects() throws XMLStreamException {
        Position currentBasePosition = getCurrentBasepoint();
        int centerX = currentBasePosition.getX();
        int centerY = currentBasePosition.getY();

        Position furthestVisibleLeftSidePoint = getFurthestVisibleLeftSidePoint(currentBasePosition);
        int leftX = furthestVisibleLeftSidePoint.getX();
        int leftY = furthestVisibleLeftSidePoint.getY();

        Position furthestVisibleRightSidePoint = getFurthestVisibleRightSidePoint(currentBasePosition);
        int rightX = furthestVisibleRightSidePoint.getX();
        int rightY = furthestVisibleRightSidePoint.getY();

        return XMLParserMain.getInstance().getDetectedObjects(leftX, leftY, rightX, rightY, centerX, centerY);
    }

    /**
     * public for testing purposes
     */
    public Position getFurthestVisibleRightSidePoint(Position sensorsPosition) {
        int rightX = sensorsPosition.getX() + (int)Math.round(VIEW_DISTANCE * Math.cos( Math.toRadians(RIGHT_SIDE_ANGLE_OF_VIEW + ownerCar.getRotation()) ));
        int rightY = sensorsPosition.getY() + (int)Math.round(VIEW_DISTANCE * Math.sin( Math.toRadians(RIGHT_SIDE_ANGLE_OF_VIEW + ownerCar.getRotation()) ));
        return new Position(rightX, rightY);
    }

    /**
     * public for testing purposes
     */
    public Position getFurthestVisibleLeftSidePoint(Position sensorsPosition) {
        int leftX = sensorsPosition.getX() + (int)Math.round(VIEW_DISTANCE * Math.cos( Math.toRadians(LEFT_SIDE_ANGLE_OF_VIEW + ownerCar.getRotation()) ));
        int leftY = sensorsPosition.getY() + (int)Math.round(VIEW_DISTANCE * Math.sin( Math.toRadians(LEFT_SIDE_ANGLE_OF_VIEW + ownerCar.getRotation()) ));
        return new Position(leftX, leftY);
    }

    /**
     * public for testing purposes
     */
    public Position getCurrentBasepoint() {

        int x = ownerCar.getWidth();
        int y = ownerCar.getLength();

        double carRotation = ownerCar.getRotation();
        double environmentX;
        double environmentY;
        double r;

        //FIXME levair: minden US-nek egy kicsit mashol kellene lenni, de ez raer kesobb
        switch (getPositionOnCar()) {
            case FRONT_INNER_LEFT:
            case FRONT_OUTER_LEFT:
            case FRONT_INNER_RIGHT:
            case FRONT_OUTER_RIGHT:
                r = x / 2;
                environmentY = Math.cos(carRotation) * r; //FIXME levair: itt felcsereltem az Y-t az X-szel, te tovabbra sem jo teljesen...rework
                environmentX = -Math.sin(carRotation) * r;
                return new Position(ownerCar.getXCoord() + (int) environmentX, ownerCar.getYCoord() + (int) environmentY);
            case REAR_INNER_LEFT:
            case REAR_OUTER_LEFT:
            case REAR_INNER_RIGHT:
            case REAR_OUTER_RIGHT:
                r = Math.sqrt(Math.pow(x / 2, 2) + Math.pow(y, 2));
                environmentX = Math.cos(carRotation) * r;
                environmentY = -Math.sin(carRotation) * r;
                return new Position(ownerCar.getXCoord() + (int) environmentX, ownerCar.getYCoord() + (int) environmentY);
            default:
                throw new RuntimeException("Unimplemented UltrasonicSensorPosition " + getPositionOnCar().name());
        }
    }

    public UltraSonicSensorPosition getPositionOnCar() {
        return positionOnCar;
    }

    public void setPositionOnCar(UltraSonicSensorPosition positionOnCar) {
        this.positionOnCar = positionOnCar;
    }
}
