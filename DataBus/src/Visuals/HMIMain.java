package Visuals;

import Visuals.HMI.component.HMIPanel;
import Visuals.HMI.mock.EngineMock;

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

        frame.setTitle("HMI panel demo (FJKKL csapat)");
        frame.pack();
        frame.setVisible(true);

        //FIXME levair - mocking engine behaviour on the Bus, so we can demo this thing
        EngineMock.mock();
    }

}
