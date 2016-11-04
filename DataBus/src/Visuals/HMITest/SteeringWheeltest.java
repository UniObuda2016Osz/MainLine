package Visuals.HMITest;


import Visuals.HMI.instrument.SteeringWheel;

import static org.junit.Assert.assertEquals;

/**
 * Created by pkiki94 on 2016.10.20..
 */
public class SteeringWheeltest {
    public SteeringWheel wheel = new SteeringWheel();

    @org.junit.Before
    public void setUp() throws Exception {
    }

    @org.junit.Test
    public void turnRightTestNormal(){
        wheel = new SteeringWheel();
        wheel.turnRight();
        int actual = wheel.getCurrentAngle();
        assertEquals(18,actual);
    }

    @org.junit.Test
    public void turnRightTestMax(){
        wheel = new SteeringWheel();
        for (int i = 0; i<15; i++){
            wheel.turnRight();
        }
        int actual = wheel.getCurrentAngle();
        assertEquals(180,actual);
    }

    @org.junit.Test
    public void turnLeftTestNormal(){
        wheel = new SteeringWheel();
        wheel.turnLeft();
        int actual = wheel.getCurrentAngle();
        assertEquals(-18,actual);
    }

    @org.junit.Test
    public void turnLeftTestMax(){
        wheel = new SteeringWheel();
        for (int i = 0; i<15; i++){
            wheel.turnLeft();
        }
        int actual = wheel.getCurrentAngle();
        assertEquals(-180,actual);
    }

}
