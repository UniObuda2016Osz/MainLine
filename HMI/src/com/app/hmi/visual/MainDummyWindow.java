package com.app.hmi.visual;

import javax.swing.*;

/**
 * This window is only for the purpose of testing the HMI panel.
 * Should be deleted once we have the main window for the application!
 *
 * Created by Roland Levai on 2016.10.05..
 */
public class MainDummyWindow extends JFrame {

    public static void main(String[] args) {
        MainDummyWindow frame = new MainDummyWindow();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        HmiPanel hmiPanel = new HmiPanel();
        frame.getContentPane().add(hmiPanel);


        //fixing size
        frame.pack();

        frame.setVisible(true);
    }

}
