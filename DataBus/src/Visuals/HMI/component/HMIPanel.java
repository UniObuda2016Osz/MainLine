package Visuals.HMI.component;

import Visuals.HMI.listener.HMIKeyListener;
import Visuals.HMI.util.HMILabel;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

/**
 * Main container for the HMI related items (eg. buttons, indicators, etc...)
 *
 * Created by Roland Levai on 2016.10.05..
 */
public class HMIPanel extends JPanel {

    public static final int PANEL_WIDTH_PX = 1000;
    public static final int PANEL_HEIGHT_PX = 250;

    private final JProgressBar gasPedalPressureBar = new JProgressBar(Pedal.MIN_PRESSURE, Pedal.MAX_PRESSURE);
    private final JProgressBar brakePedalPressureBar = new JProgressBar(Pedal.MIN_PRESSURE, Pedal.MAX_PRESSURE);

    private BufferedImage steeringWheelImage;

    private final JLabel lSpeed = new JLabel();
    private final JLabel lSteeringWheelAngle = new JLabel();
    private final JLabel lAcceleratorDegree = new JLabel();
    private final JLabel lBreakDegree = new JLabel();
    private final JLabel lIndicatorFeedback = new JLabel();
    private final JLabel lCurrentGear = new JLabel();

    public HMIPanel() {
        init();
    }

    private void init() {
        System.out.println("Initializing HMI panel...");

        //general panel settings
        setPreferredSize(new Dimension(PANEL_WIDTH_PX,PANEL_HEIGHT_PX));
        setLayout(new BoxLayout(this,BoxLayout.PAGE_AXIS));
        setFocusable(true); //key listeners only work on panels, if they're on focus!

        //key listeners
        addKeyListener(new HMIKeyListener(this));

        //panel components
        add(new JLabel(HMILabel.GAS_PEDAL_PRESSURE), BorderLayout.WEST);
        add(getGasPedalPressureBar(), BorderLayout.EAST);

        add(new JLabel(HMILabel.BRAKE_PEDAL_PRESSURE), BorderLayout.WEST);
        add(getBrakePedalPressureBar(), BorderLayout.EAST);

        System.out.println("HMI panel initialized");


    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        if (steeringWheelImage == null)
        {
            try {
                steeringWheelImage = ImageIO.read(getClass().getResourceAsStream("../resources/steeringwheel.png"));
                g.drawImage(steeringWheelImage, PANEL_WIDTH_PX / 2 - steeringWheelImage.getWidth() / 2, PANEL_HEIGHT_PX / 2 - steeringWheelImage.getHeight() / 2,null);
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
    }

    public void rotateSteeringWheel(int angle) {
        double rotationRequired = Math.toRadians (angle);
        double locationX = steeringWheelImage.getWidth() / 2;
        double locationY = steeringWheelImage.getHeight() / 2;
        AffineTransform tx = AffineTransform.getRotateInstance(rotationRequired, locationX, locationY);
        AffineTransformOp op = new AffineTransformOp(tx, AffineTransformOp.TYPE_BILINEAR);

        //FIXME repaint steering wheel

        getGraphics().drawImage(op.filter(steeringWheelImage, null),PANEL_WIDTH_PX / 2 - steeringWheelImage.getWidth() / 2, PANEL_HEIGHT_PX / 2 - steeringWheelImage.getHeight() / 2, null);
    }

    public JLabel getlSpeed() {
        return lSpeed;
    }

    public JLabel getlSteeringWheelAngle() {
        return lSteeringWheelAngle;
    }

    public JLabel getlAcceleratorDegree() {
        return lAcceleratorDegree;
    }

    public JLabel getlBreakDegree() {
        return lBreakDegree;
    }

    public JLabel getlIndicatorFeedback() {
        return lIndicatorFeedback;
    }

    public JLabel getlCurrentGear() {
        return lCurrentGear;
    }

    public JProgressBar getGasPedalPressureBar() {
        return gasPedalPressureBar;
    }

    public JProgressBar getBrakePedalPressureBar() {
        return brakePedalPressureBar;
    }
}
