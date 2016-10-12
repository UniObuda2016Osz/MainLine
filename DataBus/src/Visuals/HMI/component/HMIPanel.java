package Visuals.HMI.component;

import Visuals.HMI.instrument.CarInstrumentContainer;
import Visuals.HMI.instrument.DirectionIndicatingLight;
import Visuals.HMI.instrument.Pedal;
import Visuals.HMI.listener.HMIKeyListener;
import Visuals.HMI.util.HMILabel;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.io.IOException;

/**
 * Main container for the HMI related items (eg. buttons, indicators, etc...)
 *
 * Created by Roland Levai on 2016.10.05..
 */
public class HMIPanel extends JPanel {

    public static final int PANEL_WIDTH_PX = 1000;
    public static final int PANEL_HEIGHT_PX = 250;
    public static final int MAX_SPEED_KMH = 300;

    private JProgressBar gasPedalPressureBar;
    private JProgressBar brakePedalPressureBar;
    private JProgressBar speedBar;

    private JLabel labelGearboxPark;
    private JLabel labelGearboxReverse;
    private JLabel labelGearboxNeutral;
    private JLabel labelGearboxDrive;

    private DirectionIndicatingLight leftDirectionIndicatingLight;

    private BufferedImage steeringWheelImage;


    public HMIPanel() {
        init();
    }

    private void init() {
        System.out.println("Initializing HMI panel...");

        //general panel settings
        setPreferredSize(new Dimension(PANEL_WIDTH_PX,PANEL_HEIGHT_PX));
        setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
        setFocusable(true); //key listeners only work on panels, if they're on focus!

        //key listeners
        addKeyListener(new HMIKeyListener(this));

        //panel updater
        HMIPanelUpdater.keepUpdating(this);

        //panel components
        gasPedalPressureBar = new JProgressBar(JProgressBar.VERTICAL, Pedal.MIN_PRESSURE, Pedal.MAX_PRESSURE);
        add(new JLabel(HMILabel.GAS_PEDAL_PRESSURE));
        add(gasPedalPressureBar);

        brakePedalPressureBar = new JProgressBar(JProgressBar.VERTICAL, Pedal.MIN_PRESSURE, Pedal.MAX_PRESSURE);
        add(new JLabel(HMILabel.BRAKE_PEDAL_PRESSURE));
        add(brakePedalPressureBar);

        add(new JLabel(HMILabel.SPEED));
        speedBar = new JProgressBar(JProgressBar.HORIZONTAL, 0, MAX_SPEED_KMH);
        speedBar.setMaximumSize(new Dimension(400,10));
        speedBar.setStringPainted(true);
        add(speedBar, BorderLayout.CENTER);

        add(labelGearboxPark = new JLabel(HMILabel.GEARBOX_PARK));
        add(labelGearboxReverse = new JLabel(HMILabel.GEARBOX_REVERSE));
        add(labelGearboxNeutral = new JLabel(HMILabel.GEARBOX_NEUTRAL));
        add(labelGearboxDrive = new JLabel(HMILabel.GEARBOX_DRIVE));
        labelGearboxDrive.setOpaque(true);
        labelGearboxNeutral.setOpaque(true);
        labelGearboxPark.setOpaque(true);
        labelGearboxReverse.setOpaque(true);

        //FIXME levair - fix the component below...
        //leftDirectionIndicatingLight = new DirectionIndicatingLight();
        //add(leftDirectionIndicatingLight);
        //leftDirectionIndicatingLight.on();


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

    public JProgressBar getGasPedalPressureBar() {
        return gasPedalPressureBar;
    }

    public JProgressBar getBrakePedalPressureBar() {
        return brakePedalPressureBar;
    }

    public JProgressBar getSpeedBar() {
        return speedBar;
    }


    public JLabel getLabelGearboxPark() {
        return labelGearboxPark;
    }

    public JLabel getLabelGearboxReverse() {
        return labelGearboxReverse;
    }

    public JLabel getLabelGearboxNeutral() {
        return labelGearboxNeutral;
    }

    public JLabel getLabelGearboxDrive() {
        return labelGearboxDrive;
    }

}
