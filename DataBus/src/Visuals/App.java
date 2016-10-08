package Visuals;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.ImageObserver;
import java.io.IOException;

// ...

public class App extends JFrame implements KeyListener{


    private Image backgroundImage = new ImageIcon("./ref/level2.png").getImage();
    ImageObserver levelobs;
    Car car = new Car(130,400);

    private Image carimage = new ImageIcon(car.getImagePath()).getImage();

    private App() throws IOException {
        setTitle("Smart Auto Simulation App");
        setSize(backgroundImage.getWidth( levelobs), backgroundImage.getHeight(levelobs));
        setResizable(false);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);


        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout(FlowLayout.LEFT, 5, 5));

        JButton startButton = new JButton("Start Simulation");
        startButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // A gombnyomás elindítja a szimulációt
            }
        });

        JButton stopButton = new JButton("Stop Simulation");
        stopButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // A gombnyomás megállítja a szimulációt
            }
        });

        JButton exitButton = new JButton("Exit Simulation");
        exitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });

        buttonPanel.add(startButton);
        buttonPanel.add(stopButton);
        buttonPanel.add(exitButton);
        add(buttonPanel, BorderLayout.NORTH);

        JTextArea center = new JTextArea("Itt lesz majd a térkép ...");
        center.setBorder(new EmptyBorder(new Insets(10,10,10,10)));
        center.setBackground(Color.white);
        add(center, BorderLayout.CENTER);

        /*JPanel backgroundPanel = new JPanel();
        backgroundPanel.setSize(1024, 819);
        backgroundPanel.setBackground(Color.GREEN);
        add(backgroundPanel, BorderLayout.CENTER);*/

        setVisible(true);
    }

    public void paint(Graphics g)
    {
        // Draw the previously loaded image to Component.
        g.drawImage(backgroundImage, 0, 0, null);
        g.drawImage(carimage,car.getXCoord(),car.getYCoord(),null);
        // Draw sprites, and other things.
        // ....
    }

    public static void main(String[] args) throws IOException {
        new App();
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
    if (e.getKeyCode() == KeyEvent.VK_LEFT)
        {
            car.setxCoord(-1);
            repaint();
        }
    else if (e.getKeyCode() == KeyEvent.VK_RIGHT)
    {
        car.setxCoord(1);
        repaint();
    }
    else if (e.getKeyCode() == KeyEvent.VK_UP)
    {
        car.setyCoord(1);
        car.accelerateAuto(1);
        car.setMove(true);
        repaint();
    }
    else if (e.getKeyCode() == KeyEvent.VK_DOWN)
        {
            car.setyCoord(-1);
            car.accelerateAuto(-1);
            car.setMove(true);
            repaint();
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        //car.setMove(false);
        System.out.println("testmegye");
        repaint();

    }
}


