package Sensors.ultrasonic;

import Environment.Position;
import Visuals.Car;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeListener;

/**
 * Created by levair on 2016.11.01..
 */
public class UltrasonicSensorDemo {

    /**
     * The purpose of this function is to demo the ultrasonic sensor module
     */
    public static void main(String[] args) {

        //CASE1
        System.out.print("Case 1\t");
        Position case1_carCenterPosition = new Position(10,10);
        Car case1_car = new Car(case1_carCenterPosition.getX(),case1_carCenterPosition.getY());
        System.out.print("Placing car at " + case1_carCenterPosition.toString() + "\t");
        case1_car.setRotation(0);
        System.out.print("Car rotation set to " + case1_car.getRotation() + " degrees\n");
        UltrasonicSensor us = new UltrasonicSensor(case1_car, UltraSonicSensorPosition.FRONT_INNER_LEFT);
        Position case1_sensorBasePosition = us.getCurrentBasepoint();
        Position case1_sensorFurthestLeftPosition = us.getFurthestVisibleLeftSidePoint(case1_sensorBasePosition);
        Position case1_sensorFurthestRightPosition = us.getFurthestVisibleRightSidePoint(case1_sensorBasePosition);
        renderMap(case1_carCenterPosition, case1_sensorBasePosition, case1_sensorFurthestLeftPosition, case1_sensorFurthestRightPosition);

    }

    private static void renderMap(Position carCenterPosition, Position sensorBasePosition, Position sensorFurthestLeftPosition, Position sensorFurthestRightPosition) {

        UltrasonicDemoFrame demoFrame = new UltrasonicDemoFrame(carCenterPosition, sensorBasePosition, sensorFurthestLeftPosition, sensorFurthestRightPosition);
        demoFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        demoFrame.setLayout(new GridLayout(50,50));
        demoFrame.pack();
        demoFrame.setVisible(true);

    }

    private static class UltrasonicDemoFrame extends JFrame {

        public UltrasonicDemoFrame(Position carCenterPosition, Position sensorBasePosition, Position sensorFurthestLeftPosition, Position sensorFurthestRightPosition) {
            for (int y = 50; y > 0; y--) {
                for (int x = 1; x < 50; x++) {

                    JButton grid = new JButton();
                    getContentPane().add(grid);
                    grid.setPreferredSize(new Dimension(20, 20));
                    grid.setMargin(new Insets(0, 0, 0, 0));
                    if (carCenterPosition.getX() == x && carCenterPosition.getY() == y)
                    {
                        System.out.print("C "); //car
                        grid.setBackground(Color.YELLOW);
                        grid.setText("C");
                    }
                    else if (sensorBasePosition.getX() == x && sensorBasePosition.getY() == y)
                    {
                        System.out.print("B "); //base point of sensor
                        grid.setBackground(Color.WHITE);
                        grid.setText("B");
                    }
                    else if (sensorFurthestLeftPosition.getX() == x && sensorFurthestLeftPosition.getY() == y)
                    {
                        System.out.print("L "); //furthest left
                        grid.setBackground(Color.CYAN);
                        grid.setText("L");
                    }
                    else if (sensorFurthestRightPosition.getX() == x && sensorFurthestRightPosition.getY() == y)
                    {
                        System.out.print("R "); //furthest right
                        grid.setBackground(Color.RED);
                        grid.setText("R");
                    }
                    else
                    {
                        System.out.print("_ ");
                    }

                    final String popupText = "X = " + x + "; Y = " + y;
                    ActionListener actionListener=new ActionListener(){
                        public void actionPerformed(ActionEvent ae)
                        {
                            JOptionPane.showMessageDialog(null, popupText);
                        }
                    };

                    grid.addActionListener(actionListener);
                }
                System.out.print("\n");
            }
        }

    }
}
