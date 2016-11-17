package Sensors.ultrasonic;

import Environment.Position;
import Environment.WorldObject;
import Visuals.Car;

import javax.swing.*;
import javax.xml.stream.XMLStreamException;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeListener;

/**
 * Created by levair on 2016.11.01..
 */
public class UltrasonicSensorDemo {


    public static final int MAP_WIDTH = 100;
    public static final int MAP_HEIGHT = 100;
    public static final int GRID_SIZE = 15;

    /**
     * The purpose of this function is to demo the ultrasonic sensor module
     */
    public static void main(String[] args) throws XMLStreamException {

        //CASE1
        Position case1_carCenterPosition = new Position(30,30);
        Car case1_car = new Car(case1_carCenterPosition.getX(),case1_carCenterPosition.getY());

        case1_car.setRotation(0);

        UltrasonicSensor us = new UltrasonicSensor(case1_car, UltraSonicSensorPosition.FRONT_INNER_LEFT);
        renderMap(case1_carCenterPosition, us);
    }

    private static void renderMap(Position carCenterPosition, UltrasonicSensor us) throws XMLStreamException {

        UltrasonicDemoFrame demoFrame = new UltrasonicDemoFrame(carCenterPosition, us);
        demoFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        demoFrame.setLayout(new GridLayout(MAP_WIDTH,MAP_HEIGHT));
        demoFrame.pack();
        demoFrame.setVisible(true);

    }

    private static class UltrasonicDemoFrame extends JFrame {

        public UltrasonicDemoFrame(Position carCenterPosition, UltrasonicSensor us) throws XMLStreamException {
            Position sensorBasePosition = us.getCurrentBasepoint();
            Position sensorFurthestLeftPosition = us.getFurthestVisibleLeftSidePoint(sensorBasePosition);
            Position sensorFurthestRightPosition = us.getFurthestVisibleRightSidePoint(sensorBasePosition);
            java.util.List<WorldObject> detectedObjects = us.getAllCurrentVisibleObjects();


            for (int y = MAP_HEIGHT; y > 0; y--) {
                for (int x = 1; x < MAP_WIDTH; x++) {

                    JButton grid = new JButton();
                    getContentPane().add(grid);
                    grid.setPreferredSize(new Dimension(GRID_SIZE, GRID_SIZE));
                    grid.setMargin(new Insets(0, 0, 0, 0));
                    if (carCenterPosition.getX() == x && carCenterPosition.getY() == y)
                    {
                        grid.setBackground(Color.YELLOW);
                        grid.setText("C");
                    }
                    else if (sensorBasePosition.getX() == x && sensorBasePosition.getY() == y)
                    {
                        grid.setBackground(Color.WHITE);
                        grid.setText("B");
                    }
                    else if (sensorFurthestLeftPosition.getX() == x && sensorFurthestLeftPosition.getY() == y)
                    {
                        grid.setBackground(Color.CYAN);
                        grid.setText("L");
                    }
                    else if (sensorFurthestRightPosition.getX() == x && sensorFurthestRightPosition.getY() == y)
                    {
                        grid.setBackground(Color.RED);
                        grid.setText("R");
                    }
                    else
                    {
                        for (WorldObject wo : detectedObjects) {
                            if (wo.getCenterPoint()[0] == x && wo.getCenterPoint()[1] == y)
                            {
                                grid.setBackground(Color.BLACK);
                                grid.setText("x");
                                break;
                            }
                        }
                    }

                    final String popupText = "X = " + x + "; Y = " + y;
                    ActionListener actionListener=new ActionListener(){
                        public void actionPerformed(ActionEvent ae)
                        {
                            JOptionPane.showMessageDialog(null, popupText + " [ " + grid.getText() + " ]");
                        }
                    };

                    grid.addActionListener(actionListener);
                }
            }
        }

    }
}
