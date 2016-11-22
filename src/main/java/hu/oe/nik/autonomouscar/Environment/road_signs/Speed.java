package hu.oe.nik.autonomouscar.Environment.road_signs;

import hu.oe.nik.autonomouscar.Environment.WorldObject;

import java.util.Arrays;

/**
 * Created by nemeth on 2016. 09. 30..
 */
public class Speed extends RoadSign{

    public Speed(int Id, int[] startPosition, double[] Transform, int Zlevel, int Opacity, SpeedType speedType) {
        super(Id, startPosition, 80, 80, Transform, Zlevel, Opacity, false);
        this.speedType = speedType;
    }

    public enum SpeedType { Ten, Twenty, Forty, Fifty, Seventy, Ninety, Hundred}
    SpeedType speedType;

    public SpeedType getSpeedType() {
        return speedType;
    }

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
                "\n   DirectionType: " + this.speedType +
                "\n";
    }
}
