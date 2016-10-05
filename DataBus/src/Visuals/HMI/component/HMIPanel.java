package Visuals.HMI.component;

import Visuals.HMI.listener.HMIKeyListener;

import javax.swing.*;
import java.awt.*;

/**
 * Main container for the HMI related items (eg. buttons, indicators, etc...)
 *
 * Created by Roland Levai on 2016.10.05..
 */
public class HMIPanel extends JPanel {

    public static int PANEL_WIDTH_PX = 1000;
    public static int PANEL_HEIGHT_PX = 250;

    public static String LABEL_SPEED = "speed: ";
    public static String LABEL_STEERING_WHEEL_ANGLE = "steering wheel angle: ";
    public static String LABEL_ACCELERATOR_DEGREE = "accelerator degree: ";
    public static String LABEL_BREAK_DEGREE = "break degree: ";
    public static String LABEL_INDICATOR_FEEDBACK = "indicator feedback: ";
    public static String LABEL_CURRENT_GEAR = "current gear: ";

    private JLabel testLabel = new JLabel("press key UP to change me ! :)"); //FIXME remove later
    private JLabel lSpeed = new JLabel();
    private JLabel lSteeringWheelAngle = new JLabel();
    private JLabel lAcceleratorDegree = new JLabel();
    private JLabel lBreakDegree = new JLabel();
    private JLabel lIndicatorFeedback = new JLabel();
    private JLabel lCurrentGear = new JLabel();

    public HMIPanel() {
        init();
    }

    private void init() {
        System.out.println("Initializing HMI panel...");

        setPreferredSize(new Dimension(PANEL_WIDTH_PX,PANEL_HEIGHT_PX));
        setLayout(new BoxLayout(this,BoxLayout.PAGE_AXIS));

        addKeyListener(new HMIKeyListener(this));
        setFocusable(true); //key listeners only work on panels, if they're on focus!

        add(testLabel); //FIXME remove later

        add(new JLabel(LABEL_SPEED));
        add(getlSpeed());

        add(new JLabel(LABEL_STEERING_WHEEL_ANGLE));
        add(getlSteeringWheelAngle());

        add(new JLabel(LABEL_ACCELERATOR_DEGREE));
        add(getlAcceleratorDegree());

        add(new JLabel(LABEL_BREAK_DEGREE));
        add(getlBreakDegree());

        add(new JLabel(LABEL_INDICATOR_FEEDBACK));
        add(getlIndicatorFeedback());

        add(new JLabel(LABEL_CURRENT_GEAR));
        add(getlCurrentGear());

        System.out.println("HMI panel initialized");
    }


    public JLabel getTestLabel() {
        return testLabel;
    }

    public void setTestLabel(JLabel testLabel) {
        this.testLabel = testLabel;
    }

    public JLabel getlSpeed() {
        return lSpeed;
    }

    public void setlSpeed(JLabel lSpeed) {
        this.lSpeed = lSpeed;
    }

    public JLabel getlSteeringWheelAngle() {
        return lSteeringWheelAngle;
    }

    public void setlSteeringWheelAngle(JLabel lSteeringWheelAngle) {
        this.lSteeringWheelAngle = lSteeringWheelAngle;
    }

    public JLabel getlAcceleratorDegree() {
        return lAcceleratorDegree;
    }

    public void setlAcceleratorDegree(JLabel lAcceleratorDegree) {
        this.lAcceleratorDegree = lAcceleratorDegree;
    }

    public JLabel getlBreakDegree() {
        return lBreakDegree;
    }

    public void setlBreakDegree(JLabel lBreakDegree) {
        this.lBreakDegree = lBreakDegree;
    }

    public JLabel getlIndicatorFeedback() {
        return lIndicatorFeedback;
    }

    public void setlIndicatorFeedback(JLabel lIndicatorFeedback) {
        this.lIndicatorFeedback = lIndicatorFeedback;
    }

    public JLabel getlCurrentGear() {
        return lCurrentGear;
    }

    public void setlCurrentGear(JLabel lCurrentGear) {
        this.lCurrentGear = lCurrentGear;
    }
}
