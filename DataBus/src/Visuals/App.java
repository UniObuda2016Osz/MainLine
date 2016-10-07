package Visuals;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;

// ...

public class App extends JFrame {

    Image img = ImageIO.read(new File("C:\\Users\\Rákóczi Botond\\AutoSimulator\\DataBus\\src\\road_2.png"));







    private App() throws IOException {
        setTitle("Smart Auto Simulation App");
        // A méret a térkép tényleges mérete lesz majd
        setSize(1024, 819);
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

        setVisible(true);
    }

    public void paint(Graphics g)
    {
        // Draw the previously loaded image to Component.
        g.drawImage(img, 0, 0, null);

        // Draw sprites, and other things.
        // ....
    }
    public static void main(String[] args) throws IOException {
        new App();
    }
}