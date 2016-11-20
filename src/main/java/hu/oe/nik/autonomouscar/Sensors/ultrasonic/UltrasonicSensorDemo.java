package hu.oe.nik.autonomouscar.Sensors.ultrasonic;

import hu.oe.nik.autonomouscar.Environment.Position;
import hu.oe.nik.autonomouscar.Environment.WorldObject;
import hu.oe.nik.autonomouscar.Visuals.Car;

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
        Position case1_carCenterPosition = new Position(50,50);
        Car case1_car = new Car(case1_carCenterPosition.getX(), case1_carCenterPosition.getY());

        case1_car.setRotation(0);
        UltrasonicSensor[] us = new UltrasonicSensor[8];
        us[0] = new UltrasonicSensor(case1_car, UltraSonicSensorPosition.FRONT_OUTER_LEFT); //bal elejétől az óra járásával megegyezően növekszik a sorszám
        us[1] = new UltrasonicSensor(case1_car, UltraSonicSensorPosition.FRONT_INNER_LEFT);
        us[2] = new UltrasonicSensor(case1_car, UltraSonicSensorPosition.FRONT_INNER_RIGHT);
        us[3] = new UltrasonicSensor(case1_car, UltraSonicSensorPosition.FRONT_OUTER_RIGHT);
        us[4] = new UltrasonicSensor(case1_car, UltraSonicSensorPosition.REAR_OUTER_RIGHT);
        us[5] = new UltrasonicSensor(case1_car, UltraSonicSensorPosition.REAR_INNER_RIGHT);
        us[6] = new UltrasonicSensor(case1_car, UltraSonicSensorPosition.REAR_INNER_LEFT);
        us[7] = new UltrasonicSensor(case1_car, UltraSonicSensorPosition.REAR_OUTER_LEFT);

        //UltrasonicSensor us1 = new UltrasonicSensor(case1_car, UltraSonicSensorPosition.FRONT_INNER_RIGHT);
        //renderMap(case1_carCenterPosition, us);
        renderMap(case1_carCenterPosition, us);
    }

    private static void renderMap(Position carCenterPosition, UltrasonicSensor[] us) throws XMLStreamException {

        UltrasonicDemoFrame demoFrame = new UltrasonicDemoFrame(carCenterPosition, us);
        demoFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        demoFrame.setLayout(new GridLayout(MAP_WIDTH, MAP_HEIGHT));
        demoFrame.pack();
        demoFrame.setVisible(true);

    }

    private static class UltrasonicDemoFrame extends JFrame {

        public UltrasonicDemoFrame(Position carCenterPosition, UltrasonicSensor[] us) throws XMLStreamException {
            for (int y = MAP_HEIGHT; y > 0; y--) {
                for (int x = 1; x < MAP_WIDTH; x++) {

                    JButton grid = new JButton();
                    getContentPane().add(grid);
                    grid.setPreferredSize(new Dimension(GRID_SIZE, GRID_SIZE));
                    grid.setMargin(new Insets(0, 0, 0, 0));
                    if (carCenterPosition.getX() == x && carCenterPosition.getY() == y) {
                        grid.setBackground(Color.YELLOW);
                        grid.setText("C");
                    } else {
                        for (int k = 0; k < us.length; k++) {
                            Position sensorBasePosition = us[k].getCurrentBasepoint();
                            Position sensorFurthestLeftPosition = us[k].getFurthestVisibleLeftSidePoint(sensorBasePosition);
                            Position sensorFurthestRightPosition = us[k].getFurthestVisibleRightSidePoint(sensorBasePosition);
                            java.util.List<WorldObject> detectedObjects = us[k].getAllCurrentVisibleObjects();
                            if (sensorBasePosition.getX() == x && sensorBasePosition.getY() == y) {
                                grid.setBackground(Color.WHITE);
                                grid.setText("B"+k);
                            } else if (sensorFurthestLeftPosition.getX() == x && sensorFurthestLeftPosition.getY() == y) {
                                grid.setBackground(Color.CYAN);
                                grid.setText("L"+k);
                            } else if (sensorFurthestRightPosition.getX() == x && sensorFurthestRightPosition.getY() == y) {
                                grid.setBackground(Color.RED);
                                grid.setText("R"+k);
                            }
                            for (WorldObject wo : detectedObjects) {
                                if (wo.getCenterPoint()[0] == x && wo.getCenterPoint()[1] == y) {
                                    grid.setBackground(Color.BLACK);
                                    grid.setText("x");
                                    break;
                                }
                            }
                        }
                    }
                    final String popupText = "X = " + x + "; Y = " + y;
                    ActionListener actionListener = new ActionListener() {
                        public void actionPerformed(ActionEvent ae) {
                            JOptionPane.showMessageDialog(null, popupText + " [ " + grid.getText() + " ]");
                        }
                    };

                    grid.addActionListener(actionListener);
                }
            }
        }

    }
}
