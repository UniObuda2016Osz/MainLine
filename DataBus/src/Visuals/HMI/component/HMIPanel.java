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

    JLabel testLabel = new JLabel("press key UP to change me ! :)");

    public JLabel getTestLabel() {
        return testLabel;
    }

    public void setTestLabel(JLabel testLabel) {
        this.testLabel = testLabel;
    }

    public HMIPanel() {
        init();
    }

    private void init() {
        System.out.println("Initializing HMI panel...");

        setPreferredSize(new Dimension(PANEL_WIDTH_PX,PANEL_HEIGHT_PX));
        addKeyListener(new HMIKeyListener(this));
        setFocusable(true); //key listeners only work on panels, if they're on focus!

        add(testLabel);

        System.out.println("HMI panel initialized");
    }
}
