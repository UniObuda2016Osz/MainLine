package Sensors.ultrasonic;

import Environment.Position;
import Visuals.Car;

/**
 * Created by levair on 2016.11.01..
 */
public class UltrasonicSensorDemo {

    /**
     * The purporse of this function is to demo the ultrasonic sensor module
     */
    public static void main(String[] args) {

        //CASE1
        System.out.print("Case 1\t");
        Position case1_carCenterPosition = new Position(10,10);
        Car car = new Car(case1_carCenterPosition.getX(),case1_carCenterPosition.getY());
        System.out.print("Placing car at 10,10\t");
        car.setDirection(0);
        System.out.print("Car direction set to 0 degrees\n");
        UltrasonicSensor us = new UltrasonicSensor(car, UltraSonicSensorPosition.FRONT_INNER_LEFT);
        Position case1_sensorBasePosition = us.getCurrentBasepoint();
        Position case1_sensorFurthestLeftPosition = us.getFurthestVisibleLeftSidePoint(case1_sensorBasePosition);
        Position case1_sensorFurthestRightPosition = us.getFurthestVisibleRightSidePoint(case1_sensorBasePosition);
        printMap(case1_carCenterPosition, case1_sensorBasePosition, case1_sensorFurthestLeftPosition, case1_sensorFurthestRightPosition);

    }

    private static void printMap(Position carCenterPosition, Position sensorBasePosition, Position sensorFurthestLeftPosition, Position sensorFurthestRightPosition) {
        for (int y = 50; y > 0; y--) {
            for (int x = 0; x < 50; x++) {

                if (carCenterPosition.getX() == x && carCenterPosition.getY() == y)
                {
                    System.out.print("C "); //car
                }
                else if (sensorBasePosition.getX() == x && sensorBasePosition.getY() == y)
                {
                    System.out.print("B "); //base point of sensor
                }
                else if (sensorFurthestLeftPosition.getX() == x && sensorFurthestLeftPosition.getY() == y)
                {
                    System.out.print("L "); //furthest left
                }
                else if (sensorFurthestRightPosition.getX() == x && sensorFurthestRightPosition.getY() == y)
                {
                    System.out.print("R "); //furthest right
                }
                else
                {
                    System.out.print("_ ");
                }
            }
            System.out.print("\n");
        }
    }
}
