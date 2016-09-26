package com.app.visual;

import javax.swing.*;

/**
 * Created by Rákóczi Botond on 2016. 09. 26..
 * hibás package ????
 */
public class App {
    private JPanel MainPanel;
    public App () {
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("App");
        frame.setContentPane(new App().MainPanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }

}
