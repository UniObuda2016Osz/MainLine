package Environment.road_signs;

import Environment.WorldObject;

import java.util.Arrays;

/**
 * Created by nemeth on 2016. 09. 30..
 */
public class Priority extends RoadSign {
    public Priority(int Id, int[] startPosition, double[] Transform, int Zlevel, int Opacity, PriorityType priorityType) {
        super(Id, startPosition, 80, 80, Transform, Zlevel, Opacity, false);
        this.priorityType = priorityType;
    }

    public enum PriorityType { Priority, Stop, Yield}
    PriorityType priorityType;

    @Override
    public String toString() {
        return "\n" + this.getClass().getSimpleName()+  " elem adatai:  " +
                "\n   Id: " + this.getId() +
                "\n   Position: " + Arrays.toString(this.getPosition()) +
                "\n   Középpont: " + Arrays.toString(this.getCenterPoint()) +
                "\n   Transform: "  + Arrays.toString(this.getTransform()) +
                "\n   Zlevel: " + this.getZLevel() +
                "\n   Opacity: " + this.getOpacity() +
                "\n   CanStuckOnIt: " + this.getCanStuckOnIt() +
                "\n   DirectionType: " + this.priorityType +
                "\n";
    }
}
