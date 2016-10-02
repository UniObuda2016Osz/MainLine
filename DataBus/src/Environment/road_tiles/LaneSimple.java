package Environment.road_tiles;

import Environment.Position;
import Environment.WorldObject;

/**
 * Created by nemet on 2016. 09. 30..
 */
public class LaneSimple extends WorldObject{
    public enum RoadPaintings1 {undefinied1,undefinied2,undefinied3}
    public enum RoadPaintings2 {undefinied1,undefinied2,undefinied3,undefinied4,undefinied5,undefinied6,undefinied7,undefinied8}
    public enum RoadPaintings3 {undefinied1,undefinied2,undefinied3}
    public enum LaneSimpleType {Left45,Left65,Left90,Right45,Right65,Right90,Straight}

    public LaneSimple(int Id, String Name, int Type, Position startPosition,
                      int[] Transform, int Zlevel, int Opacity) {
            super(Id, Name, Type, startPosition,
            Transform, Zlevel, Opacity);
    }
}
