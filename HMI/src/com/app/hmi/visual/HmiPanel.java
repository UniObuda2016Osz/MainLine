package com.app.hmi.visual;

import javax.swing.*;

/**
 * GUI for displaying and manipulating automated car data.
 *
 * Created by Roland Levai on 2016.10.05..
 */
public class HmiPanel extends JPanel {

    public HmiPanel() {

        JLabel label = new JLabel("This is the HMI panel...");
        add(label);
    }
}
