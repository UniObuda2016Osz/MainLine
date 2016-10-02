package Environment.road_signs;

import Environment.Position;
import Environment.WorldObject;

/**
 * Created by nemet on 2016. 09. 30..
 */
public class Direction extends WorldObject {
    public Direction(int Id, Position startPosition, int[] Transform, int Zlevel, int Opacity, DirectionType directionType) {
        super(Id, startPosition, Transform, Zlevel, Opacity);
        this.directionType = directionType;
    }

    public enum DirectionType {Forward,Left,Right,ForwardLeft,ForwardRight,Round}
    DirectionType directionType;

}
