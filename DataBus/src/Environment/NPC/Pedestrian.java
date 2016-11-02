package Environment.NPC;

import Environment.WorldObject;

/**
 * Created by forii on 2016. 10. 14..
 */
public class Pedestrian extends NPC {
    public Pedestrian(int Id, int[] startPosition, int Width, int Height, double[] Transform, int Zlevel, int Opacity, int movingDegree, int movingSpeed) {
        super(Id, startPosition, Width, Height, Transform, Zlevel, Opacity, movingDegree, movingSpeed);
    }
}
