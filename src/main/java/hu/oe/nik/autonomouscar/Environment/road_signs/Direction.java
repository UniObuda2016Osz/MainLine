package hu.oe.nik.autonomouscar.Environment.road_signs;

import java.util.Arrays;

/**
 * Created by nemeth on 2016. 09. 30..
 */
public class Direction extends RoadSign {
    public Direction(int Id, int[] startPosition, double[] Transform, int Zlevel, int Opacity, DirectionType directionType) {
        super(Id, startPosition, 80, 80, Transform, Zlevel, Opacity, false);
        this.directionType = directionType;
    }

    public enum DirectionType {Forward,Left,Right,ForwardLeft,ForwardRight,Round}
    DirectionType directionType;

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
                "\n   DirectionType: " + this.directionType +
                "\n";
    }
}
