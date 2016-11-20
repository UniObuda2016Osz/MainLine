package hu.oe.nik.autonomouscar.Environment.misc;

import hu.oe.nik.autonomouscar.Environment.WorldObject;

/**
 * Created by Németh Kriszitna on 2016.11.03..
 */
public abstract class Misc extends WorldObject {
    public Misc(int Id, int[] startPosition, int width, int height, double[] Transform, int Zlevel, int Opacity, boolean CanStuckOnIt) {
        super(Id, startPosition, width, height, Transform, Zlevel, Opacity, CanStuckOnIt);
    }
}
