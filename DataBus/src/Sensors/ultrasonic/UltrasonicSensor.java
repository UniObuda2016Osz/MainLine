package Sensors.ultrasonic;

import Environment.Position;
import Environment.WorldObject;
import Environment.XMLParserMain;
import Visuals.Car;

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
        double distance=Double.MAX_VALUE;
        for (WorldObject commonWorldObject : getAllCurrentVisibleObjects()) {
            double temp_distance=Math.sqrt(Math.pow(getCurrentBasepoint().getX()-commonWorldObject.getPosition()[0],2)+Math.pow(getCurrentBasepoint().getY()-commonWorldObject.getPosition()[1],2)); //remélhetőleg a position vektor első eleme az x koordináta
            if(temp_distance<distance)
                distance=temp_distance;
        }
        return distance;
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

    //Javítva JaszyKitti
    public Position getFurthestVisibleRightSidePoint(Position sensorsPosition) {
        switch (getPositionOnCar()) {
            case FRONT_INNER_LEFT:
            case FRONT_OUTER_LEFT:
            case FRONT_INNER_RIGHT:
            case FRONT_OUTER_RIGHT:
                int frontRightX = sensorsPosition.getX() - (int) Math.round(VIEW_DISTANCE * Math.cos(Math.toRadians(LEFT_SIDE_ANGLE_OF_VIEW + ownerCar.getRotation())));
                int frontRightY = sensorsPosition.getY() - (int) Math.round(VIEW_DISTANCE * Math.sin(Math.toRadians(LEFT_SIDE_ANGLE_OF_VIEW + ownerCar.getRotation())));
                return new Position(frontRightX, frontRightY);
            case REAR_INNER_LEFT:
            case REAR_OUTER_LEFT:
            case REAR_INNER_RIGHT:
            case REAR_OUTER_RIGHT:
                int rearRightX = sensorsPosition.getX() + (int) Math.round(VIEW_DISTANCE * Math.cos(Math.toRadians(LEFT_SIDE_ANGLE_OF_VIEW + ownerCar.getRotation())));
                int rearRightY = sensorsPosition.getY() + (int) Math.round(VIEW_DISTANCE * Math.sin(Math.toRadians(LEFT_SIDE_ANGLE_OF_VIEW + ownerCar.getRotation())));
                return new Position(rearRightX, rearRightY);
            default:
                throw new RuntimeException("Unimplemented UltrasonicSensorPosition " + getPositionOnCar().name());
        }

    }

    /**
     * public for testing purposes
     */

    //Javítva JaszyKitti
    public Position getFurthestVisibleLeftSidePoint(Position sensorsPosition) {
        switch (getPositionOnCar()) {
            case FRONT_INNER_LEFT:
            case FRONT_OUTER_LEFT:
            case FRONT_INNER_RIGHT:
            case FRONT_OUTER_RIGHT:
                int frontLeftX = sensorsPosition.getX() + (int) Math.round(VIEW_DISTANCE * Math.cos(Math.toRadians(RIGHT_SIDE_ANGLE_OF_VIEW + ownerCar.getRotation())));
                int frontLeftY = sensorsPosition.getY() + (int) Math.round(VIEW_DISTANCE * Math.sin(Math.toRadians(RIGHT_SIDE_ANGLE_OF_VIEW + ownerCar.getRotation())));
                return new Position(frontLeftX, frontLeftY);
            case REAR_INNER_LEFT:
            case REAR_OUTER_LEFT:
            case REAR_INNER_RIGHT:
            case REAR_OUTER_RIGHT:
                int rearLeftX = sensorsPosition.getX() - (int) Math.round(VIEW_DISTANCE * Math.cos(Math.toRadians(RIGHT_SIDE_ANGLE_OF_VIEW + ownerCar.getRotation())));
                int rearLeftY = sensorsPosition.getY() - (int) Math.round(VIEW_DISTANCE * Math.sin(Math.toRadians(RIGHT_SIDE_ANGLE_OF_VIEW + ownerCar.getRotation())));
                return new Position(rearLeftX, rearLeftY);
            default:
                throw new RuntimeException("Unimplemented UltrasonicSensorPosition " + getPositionOnCar().name());
        }

    }

    /**
     * public for testing purposes
     */
    public Position getCurrentBasepoint() {

        int x = ownerCar.getWidth();
        int y = ownerCar.getLength();

        int XcarPos = ownerCar.getXCoord();
        int YcarPos = ownerCar.getYCoord();

        double carRotation = ownerCar.getRotation();
        double environmentX;
        double environmentY;
        double r;

        //FIXME levair: minden US-nek egy kicsit mashol kellene lenni, de ez raer kesobb
        //Javítva JaszyKitti
        switch (getPositionOnCar()) {
            case FRONT_INNER_LEFT:
            case FRONT_OUTER_LEFT:
            case FRONT_INNER_RIGHT:
            case FRONT_OUTER_RIGHT:
                r = y / 2;
                environmentY = YcarPos + (Math.sin(Math.toRadians(carRotation)) * r);
                environmentX = (XcarPos + Math.cos(Math.toRadians(carRotation)) * r);
                return new Position((int) Math.round(environmentX), (int) Math.round(environmentY));

            //Javítva JaszyKitti
            case REAR_INNER_LEFT:
            case REAR_OUTER_LEFT:
            case REAR_INNER_RIGHT:
            case REAR_OUTER_RIGHT:
                r = y / 2;
                environmentX = XcarPos - Math.cos(Math.toRadians(carRotation)) * r;
                environmentY = YcarPos - Math.sin(Math.toRadians(carRotation)) * r;
                return new Position((int) Math.round(environmentX), (int) Math.round(environmentY));
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
