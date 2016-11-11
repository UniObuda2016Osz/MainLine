package hu.oe.nik.autonomouscar.Environment;

import java.awt.event.KeyEvent;


/**
 * Created by Akos on 2016. 11. 11..
 */
public class UserCarControlling {

    UserCar userCar;
    private boolean fent,lent,bal,jobb=false;

    public UserCarControlling()
    {
        this.userCar=new UserCar(300,300);
    }


    public void KeyPressed(KeyEvent e)
    {
        if (e.getKeyCode() == java.awt.event.KeyEvent.VK_UP) {
            fent = true;
        } else if (e.getKeyCode() == java.awt.event.KeyEvent.VK_DOWN) {
            lent = true;
        } else if (e.getKeyCode() == java.awt.event.KeyEvent.VK_LEFT) {
            bal = true;
        } else if (e.getKeyCode() == java.awt.event.KeyEvent.VK_RIGHT)
            jobb = true;
        if(!fent && !lent) {
            userCar.DecreaseSpeed();
        }
        else {
            if (fent) {
                userCar.AccelerateAuto(1);
                if (jobb)
                    userCar.setRotation(userCar.getRotation() + 2);
                else if (bal)
                    userCar.setRotation(userCar.getRotation() - 2);
            }
            if (lent) {
                userCar.DecreaseAuto(1);
                if (jobb)
                    userCar.setRotation(userCar.getRotation() + 2);
                else if (bal)
                    userCar.setRotation(userCar.getRotation() - 2);
            }

            userCar.setVelocityX(Math.sin(userCar.getRotation() * Math.PI / 180) * userCar.getSpeed());
            userCar.setVelocityY(Math.cos(userCar.getRotation() * Math.PI / 180) * - userCar.getSpeed());

            userCar.setXCoord(userCar.getX() + (int) userCar.getVelocityX());
            userCar.setYCoord(userCar.getY() + (int) userCar.getVelocityY());
        }
    }
}
