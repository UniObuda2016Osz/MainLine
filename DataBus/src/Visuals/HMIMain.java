package Visuals;

import Visuals.HMI.component.HMIPanel;

import javax.swing.*;

/**
 * Created by ral2bp on 2016.09.29..
 */
public class HMIMain extends JFrame {

    //* Only for testing purposes.
    //* HMIPanel should be integrated into the same Frame as the route panel.
    //* TODO delete this method when the panel is integrated!
    public static void main(String[] args) {
        HMIMain frame = new HMIMain();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        HMIPanel hmiPanel = new HMIPanel();
        frame.getContentPane().add(hmiPanel);

        frame.pack();
        frame.setVisible(true);
    }

}
