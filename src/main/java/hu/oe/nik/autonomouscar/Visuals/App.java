package hu.oe.nik.autonomouscar.Visuals;

import javax.swing.*;
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
    private boolean jobb, bal, lent, fent;
    private Car car = new Car(130, 400);
    private Image carimage = new ImageIcon(car.getImagePath()).getImage();

    //Creating the pedestrian
    // overview
    private Pedestrian pedestrian_1 = new Pedestrian(150, 200, 255, 200);
    private Image pedestrianImage = new ImageIcon(pedestrian_1.getImagePath()).getImage();

    private JFrame mainframe;

    private App() throws IOException {
        // FIXME more elegent solution to read from the resources folder
        // it might return with null if no file exists with the provided name, add some checks
        backgroundImage = new ImageIcon(classLoader.getResource("level2.png")).getImage();

        mainframe = new JFrame();
        mainframe.addKeyListener(this);
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
        center.setBorder(new EmptyBorder(new Insets(10, 10, 10, 10)));
        center.setBackground(Color.white);
        add(center, BorderLayout.CENTER);

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
        if (e.getKeyCode() == KeyEvent.VK_UP) {
            fent = true;
        } else if (e.getKeyCode() == KeyEvent.VK_DOWN) {
            lent = true;
        } else if (e.getKeyCode() == KeyEvent.VK_LEFT) {
            bal = true;
        } else if (e.getKeyCode() == KeyEvent.VK_RIGHT)
            jobb = true;
        if(!fent && !lent) {
            car.setSpeed(0);
        }else {
            if (fent) {
                car.setSpeed(5);
                if (jobb)
                    car.setRotation(car.getRotation() + 2);
                else if (bal)
                    car.setRotation(car.getRotation() - 2);
            }
            if (lent) {
                car.setSpeed(-5);
                if (jobb)
                    car.setRotation(car.getRotation() + 2);
                else if (bal)
                    car.setRotation(car.getRotation() - 2);
            }

            car.setVelocityX(Math.sin(car.getRotation() * Math.PI / 180) * car.getSpeed());
            car.setVelocityY(Math.cos(car.getRotation() * Math.PI / 180) * -car.getSpeed());

            car.setXCoord(car.getXCoord() + (int) car.getVelocityX());
            car.setYCoord(car.getYCoord() + (int) car.getVelocityY());
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
            g2d.rotate(car.getRotation()*Math.PI / 180,car.getXCoord(), car.getYCoord());
            g2d.drawImage(carimage, car.getXCoord(), car.getYCoord(), null);
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


