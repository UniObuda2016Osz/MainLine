package Visuals;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.ImageObserver;
import java.io.IOException;
import java.util.TimerTask;

// ...

public class App extends JFrame {
    private static Timer time;
    private Image backgroundImage = new ImageIcon("./ref/level2.png").getImage();
    ImageObserver levelobs;
    Car car = new Car(130,400);
    private Image carimage = new ImageIcon(car.getImagePath()).getImage();

    //Creating the pedestrian
    Pedestrian pedestrian_1 = new Pedestrian(150, 200, 255, 200);
    private Image pedestrianImage = new ImageIcon(pedestrian_1.getImagePath()).getImage();

    JFrame mainframe;

    private App() throws IOException {
        mainframe = new JFrame();
        mainframe.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                super.keyPressed(e);
                if (e.getKeyCode() == KeyEvent.VK_LEFT)
                {
                    car.turnLeft();
                }
                else if (e.getKeyCode() == KeyEvent.VK_RIGHT)
                {
                    car.turnRight();
                }
                else if (e.getKeyCode() == KeyEvent.VK_UP)
                {
                    car.MoveForward();
                    //car.accelerateAuto(1); furán viselkedik
                    car.setMove(true);
                }
                else if (e.getKeyCode() == KeyEvent.VK_DOWN)
                {
                    car.MoveBack();
                    //car.accelerateAuto(-1); furán viselkedik
                    car.setMove(true);
                }
            }
        });
        setTitle("Smart Auto Simulation App");
        setSize(backgroundImage.getWidth(levelobs), backgroundImage.getHeight(levelobs));
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
                time.start();
            }
        });

        JButton stopButton = new JButton("Stop Simulation");
        stopButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // A gombnyomás megállítja a szimulációt
                time.stop();
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



        setVisible(true);
        //Timer
        time = new Timer(40, e -> {
            repaint();
        });
    }

    @Override
    public void paint(Graphics g)
    {
        // Draw the previously loaded image to Component.
        g.drawImage(backgroundImage, 0, 0, null);
        g.drawImage(carimage,car.getXCoord(),car.getYCoord(),null);

        //Draw the pedestrian
        g.drawImage(pedestrianImage, (int)pedestrian_1.getXPos(), (int)pedestrian_1.getYPos(), 30, 45, null);
        pedestrian_1.Move();

        g.setColor(Color.BLACK);
        g.setFont(new Font("Arial Black", Font.BOLD, 15));
        g.drawString("X: "+car.getXCoord() +" Y: "+ car.getYCoord()+ " Direction: "+car.getDirection(), 750,80);

        mainframe.setVisible(true);
        mainframe.paint(g);

    }

    public static void main(String[] args) throws IOException {
        new App();
    }
}


