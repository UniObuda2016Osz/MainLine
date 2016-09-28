package com.app.visual;

import javax.swing.*;

/**
 * Created by Rákóczi Botond on 2016. 09. 26..
 * hibás package ????
 */
public class WorldGui {
    private JPanel MainPanel;
    public WorldGui() {
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("WorldGui");
        frame.setContentPane(new WorldGui().MainPanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }

}
