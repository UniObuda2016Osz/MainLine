package hu.oe.nik.autonomouscar.Environment.misc;

import java.util.Arrays;

/**
 * Created by nemeth on 2016. 09. 30..
 */
public class Tree extends Misc{
    public Tree(int Id, int[] startPosition, double[] Transform, int Zlevel, int Opacity) {
        super(Id, startPosition, 145, 160, Transform, Zlevel, Opacity, true);
    }

    @Override
    public String toString() {
        return "\n" + this.getClass().getSimpleName() + " elem adatai:  " +
                "\n   Id: " + this.getId() +
                "\n   Position: " + Arrays.toString(this.getPosition()) +
                "\n   Középpont: " + Arrays.toString(this.getCenterPoint()) +
                "\n   Transform: " + Arrays.toString(this.getTransform()) +
                "\n   Zlevel: " + this.getZLevel() +
                "\n   Opacity: " + this.getOpacity() +
                "\n   CanStuckOnIt: " + this.getCanStuckOnIt() +
                "\n";
    }
}
