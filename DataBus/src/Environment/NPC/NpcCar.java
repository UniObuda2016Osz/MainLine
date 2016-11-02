package Environment.NPC;

import Sensors.Camera;
import Sensors.Radar;

/**
 * Created by forii on 2016. 10. 14..
 */
public class NpcCar extends NPC {

    private String imagePath;
    private Camera camera;
    private Radar radar;
    //private Ultrasonic ultrasonic;


    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }

    public NpcCar(int Id, int[] startPosition, int Width, int Height, double[] Transform, int Zlevel, int Opacity, int movingDegree, int movingSpeed) {
        super(Id, startPosition, Width, Height, Transform, Zlevel, Opacity, movingDegree, movingSpeed);
    }


}