package hu.oe.nik.autonomouscar.Environment.road_tiles;

import hu.oe.nik.autonomouscar.Environment.WorldObject;

/**
 * Created by Németh Kriszitna on 2016.11.03..
 */
public class RoadTile extends WorldObject{
    public RoadTile(int Id, int[] startPosition, int width, int height, double[] Transform, int Zlevel, int Opacity, boolean CanStuckOnIt, int[] roadColor1, int[] roadColor2, int[] roadColor3, RoadPaintings1 roadPaintings1, RoadPaintings2 roadPaintings2, RoadPaintings3 roadPaintings3) {
        super(Id, startPosition, width, height, Transform, Zlevel, Opacity, CanStuckOnIt);
        this.roadPaintings1 = roadPaintings1;
        this.roadPaintings2 = roadPaintings2;
        this.roadPaintings3 = roadPaintings3;
        this.roadColor1 = roadColor1;
        this.roadColor2 = roadColor2;
        this.roadColor3 = roadColor3;
    }

    public enum RoadPaintings1 {rp_none,rp_sep_1_s,rp_sep_1_sb}
    public enum RoadPaintings2 {rp_none,rp_sep_2_bnd,rp_sep_2_d,rp_sep_2_dbnd,rp_sep_2_ds,rp_sep_2_s,rp_sep_2_sd,rp_sep_2_ss}
    public enum RoadPaintings3 {rp_none,rp_sep_3_s,rp_sep_3_sb}

    // rp_sep_1 bal szél
    // rp_sep_2 közép
    // rp_sep_3 jobb szél
    // none - nincs
    // s - sima vonal (vékony)
    // sb - sima vonal (vastag - bold)
    // d - szaggatott vonal
    // ss - dupla sima vonal (dupla záróvonal)
    // ds - szaggatott záró
    // sd - záró szaggatott
    // bnd
    // dbnd - double pontozott (talán a fényvisszaverő)

    RoadPaintings1 roadPaintings1;
    RoadPaintings2 roadPaintings2;
    RoadPaintings3 roadPaintings3;

    int[] roadColor1;
    int[] roadColor2;
    int[] roadColor3;

}
