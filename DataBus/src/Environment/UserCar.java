package Environment;

import Sensors.Radar;

/**
 * Created by Akos on 2016. 11. 02..
 */
public class UserCar extends WorldObject {
    private String imagePath;
    //private Camera camera;
    private Radar radar;
    //private Ultrasonic ultrasonic;

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }

    public UserCar(int Id, int[] startPosition, int width, int height, double[] Transform, int Zlevel, int Opacity, boolean CanStuckOnIt) {
        super(Id, startPosition, width, height, Transform, Zlevel, Opacity, CanStuckOnIt);
    }






}
