package Environment.road_tiles;

import Environment.WorldObject;

import java.util.Arrays;

/**
 * Created by nemeth on 2016. 09. 30..
 */
public class LaneSimple extends RoadTile{

    public LaneSimple(int Id, int[] startPosition, int width, int height, double[] Transform, int Zlevel, int Opacity, int[] roadColor1, int[] roadColor2, int[] roadColor3, RoadPaintings1 roadPaintings1, RoadPaintings2 roadPaintings2, RoadPaintings3 roadPaintings3, LaneSimpleType laneSimpleType) {
        super(Id, startPosition, width, height, Transform, Zlevel, Opacity, false, roadColor1, roadColor2, roadColor3, roadPaintings1, roadPaintings2, roadPaintings3);
        this.laneSimpleType = laneSimpleType;
    }

    public enum LaneSimpleType {Left45,Left65,Left90,Right45,Right65,Right90,Straight}
    LaneSimpleType laneSimpleType;



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
                "\n   LaneSimpleType: " + this.laneSimpleType +
                "\n   RoadColor1: " + Arrays.toString(roadColor1) +
                "\n   RoadColor2: " + Arrays.toString(roadColor2) +
                "\n   RoadColor3: " + Arrays.toString(roadColor3) +
                "\n   RoadPaintigs1: " + this.roadPaintings1 +
                "\n   RoadPaintings2: " + this.roadPaintings2 +
                "\n   RoadPaintings3: " + this.roadPaintings3 +
                "\n";
    }
}
