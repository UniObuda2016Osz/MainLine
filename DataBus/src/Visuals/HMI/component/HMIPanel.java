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

        //TODO levair - move steering wheel to a separate panel, so only it can repainted separately
        if (steeringWheelImage == null)
        {
            try {
                steeringWheelImage = ImageIO.read(getClass().getResourceAsStream("../resources/steeringwheel-transparent-small.png"));
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }

        int x = (getWidth() - steeringWheelImage.getWidth()) / 2;
        int y = (getHeight() - steeringWheelImage.getHeight()) / 2;
        AffineTransform at = new AffineTransform();
        double angleRadians = Math.toRadians( CarInstrumentContainer.singleton().getSteeringWheel().getCurrentAngle() );
        at.setToRotation(angleRadians, x + (steeringWheelImage.getWidth() / 2), y + (steeringWheelImage.getHeight() / 2));
        at.translate(x, y);

        Graphics2D g2d = (Graphics2D) g.create();
        g2d.setTransform(at);
        g2d.drawImage(steeringWheelImage, 0, 0, this);
        g2d.dispose();
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
