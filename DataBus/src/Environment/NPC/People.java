package Environment.NPC;

import Environment.WorldObject;

import java.util.Arrays;

/**
 * Created by Akos on 2016. 09. 27..
 */
public class People extends WorldObject implements IMovable{


    int Width = 80;
    int Height = 80;
    public People(int Id, int[] startPosition, double[] Transform, int Zlevel, int Opacity) {
        super(Id, startPosition, 75, 80, Transform, Zlevel, Opacity, true);
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

    @Override
    public void startNPC() {

    }

    @Override
    public void stopNPC() {

    }

    @Override
    public void MoveObject() {

    }
}
