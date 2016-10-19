package Visuals.HMI.instrument;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

/**
 * Created by levair on 2016.10.12..
 */
public class DirectionIndicatingLight extends JPanel {

    private Color lastColor = Color.YELLOW;
    Timer timer;

    public DirectionIndicatingLight()
    {
        timer = new Timer(1000, new AbstractAction() {
           @Override
            public void actionPerformed(ActionEvent e) {
                //repaint();
            }
        });
    }

    public void on() {
        timer.start();
    }

    public void off() {
        timer.stop();
    }

    @Override
    public void paintComponent(Graphics g)
    {
        super.paintComponents(g);
        if(lastColor.equals(Color.YELLOW))
        {
            lastColor = Color.WHITE;
        }
        else
        {
            lastColor = Color.YELLOW;
        }
        g.setColor(lastColor);
        g.fillOval(25, 25, 10, 10);
    }
}
