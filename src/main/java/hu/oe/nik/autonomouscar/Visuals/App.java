package hu.oe.nik.autonomouscar.Visuals;

import hu.oe.nik.autonomouscar.Bus.Bus;
import hu.oe.nik.autonomouscar.Environment.UserCar;
import hu.oe.nik.autonomouscar.Functions.ACCMain;
import hu.oe.nik.autonomouscar.Visuals.HMI.component.HMIPanel;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.geom.AffineTransform;
import java.awt.image.ImageObserver;
import java.io.IOException;

// ...

public class App extends JFrame implements KeyListener {
    private static Timer time;
    ClassLoader classLoader = getClass().getClassLoader();

    private Image backgroundImage;
    private ImageObserver levelobs;
    private boolean jobb, bal, lent, fent = false;
    private UserCar mycar = new UserCar(130,400);
    private Pedestrian pedestrian_1 = new Pedestrian(150, 200, 255, 200);
    private Image carimage ;
    private Image pedestrianImage;

    Bus bus = Bus.getInstance();
    ACCMain acc = ACCMain.getInstance();

    private JFrame mainframe;

    private App() throws IOException {

        // it might return with null if no file exists with the provided name, add some checks
        backgroundImage = new ImageIcon(classLoader.getResource("level2.png")).getImage();
        carimage = new ImageIcon(classLoader.getResource(mycar.getImagePath())).getImage();
        mycar.setImagePath(mycar.getImagePath());
        pedestrianImage = new ImageIcon(classLoader.getResource(pedestrian_1.getImagePath())).getImage();

        mainframe = new JFrame();
        mainframe.addKeyListener(this);
        setTitle("Smart Auto Simulation App");
        setSize(backgroundImage.getWidth(levelobs), backgroundImage.getHeight(levelobs));
        setResizable(false);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        JPanel controlPanel = new JPanel();
        controlPanel.setLayout(new FlowLayout(FlowLayout.LEFT, 5, 5));

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

        controlPanel.add(startButton);
        controlPanel.add(stopButton);
        controlPanel.add(exitButton);
        add(controlPanel, BorderLayout.NORTH);
        
        //GUI elements to change the target speed of the car
        JButton targetSpeedMinusButton = new JButton("Target sp -");
        targetSpeedMinusButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (acc.getTargetSpeed() > 30)
                    acc.setTargetSpeed(acc.getTargetSpeed()-30);
            }
        });
        JButton targetSpeedPlusButton = new JButton("Target sp +");
        targetSpeedPlusButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (acc.getTargetSpeed() < 180)
                    acc.setTargetSpeed(acc.getTargetSpeed() + 30);
            }
        });
        
        JButton targetSpeedLabel = new JButton();
        targetSpeedLabel.setText(String.valueOf(acc.getTargetSpeed()));

        controlPanel.add(targetSpeedMinusButton);
        controlPanel.add(targetSpeedPlusButton);
        controlPanel.add(targetSpeedLabel);

        //GUI elements to change the timegap
        JButton timegapMinusButton = new JButton("Timegap -");
        timegapMinusButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                acc.setTimegap(-1);
            }
        });
        JButton timegapPlusButton = new JButton("Timegap +");
        timegapMinusButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                acc.setTimegap(1);
            }
        });

        JButton timegapLabel = new JButton();
        timegapLabel.setText(String.valueOf(acc.getTimegap()));

        controlPanel.add(timegapMinusButton);
        controlPanel.add(timegapPlusButton);
        controlPanel.add(timegapLabel);

        //GUI elements to turn on/turn off tempomat
        JButton tempomatSwitchButton = new JButton("Switch");
        tempomatSwitchButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (acc.isAccOn())
                    acc.setAccOff();
                else
                    acc.setAccOn(bus.getCurrentSISpeed());
            }
        });
        controlPanel.add(tempomatSwitchButton);

        setVisible(true);
        //Timer
        time = new Timer(40, e -> {
            repaint();
        });
    }

    @Override
    public void keyTyped(KeyEvent e) {



    }

    //gomb egyszeri lenyomását nem lehet vizsgálni, így flag-ekkel van megoldva
    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == java.awt.event.KeyEvent.VK_UP) {
            fent = true;
        } else if (e.getKeyCode() == java.awt.event.KeyEvent.VK_DOWN) {
            lent = true;
        } else if (e.getKeyCode() == java.awt.event.KeyEvent.VK_LEFT) {
            bal = true;
        } else if (e.getKeyCode() == java.awt.event.KeyEvent.VK_RIGHT)
            jobb = true;
        if(!fent && !lent) {
            mycar.DecreaseSpeed();
        }
        else {
            if (fent) {
                mycar.AccelerateAuto(1);
                if (jobb)
                    mycar.setRotation(mycar.getRotation() + 2);
                else if (bal)
                    mycar.setRotation(mycar.getRotation() - 2);
            }
            if (lent) {
                mycar.DecreaseAuto(1);
                if (jobb)
                    mycar.setRotation(mycar.getRotation() + 2);
                else if (bal)
                    mycar.setRotation(mycar.getRotation() - 2);
            }

            mycar.setVelocityX(Math.sin(mycar.getRotation() * Math.PI / 180) * mycar.getSpeed());
            mycar.setVelocityY(Math.cos(mycar.getRotation() * Math.PI / 180) * - mycar.getSpeed());

            mycar.setXCoord(mycar.getX() + (int) mycar.getVelocityX());
            mycar.setYCoord(mycar.getY() + (int) mycar.getVelocityY());
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_LEFT) {
            bal = false;
        }  if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
            jobb = false;
        }  if (e.getKeyCode() == KeyEvent.VK_UP) {
            fent = false;
        }  if (e.getKeyCode() == KeyEvent.VK_DOWN) {
            lent = false;
        }
    }

    @Override
    public void paint(Graphics g) {
            Image buffer;
            Graphics2D g2d;

            buffer = createImage(backgroundImage.getWidth(levelobs),backgroundImage.getHeight(levelobs));
            g2d = (Graphics2D)buffer.getGraphics();

            g2d.drawImage(backgroundImage, 0, 0, null);
            //Graphics2D g2d = (Graphics2D) g;

            g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                    RenderingHints.VALUE_ANTIALIAS_ON);
            AffineTransform old = g2d.getTransform();
            g2d.scale(0.75,0.75);


        g2d.rotate(mycar.getRotation()*Math.PI / 180,mycar.getX(), mycar.getY());
        g2d.drawImage(carimage, mycar.getX(), mycar.getY(), null);


            g2d.setTransform(old);

            g2d.drawImage(pedestrianImage, (int) pedestrian_1.getXPos(), (int) pedestrian_1.getYPos(), 30, 45, null);
            pedestrian_1.Move();

            g.drawImage(buffer, 0, 0, null);

            mainframe.setVisible(true);
            mainframe.paint(g);

    }

    public static void main(String[] args) throws IOException {
        new App();
        // A kamera működésének tesztelése
        /*Car car = new Car(500, 500);
        Camera cam = new Camera(car);
        java.util.List<WorldObject> relevantObjects = new ArrayList<>();
        relevantObjects = cam.getEnvironmentRelevantObjects();
        System.out.println(relevantObjects.get(0));*/
    }
}


