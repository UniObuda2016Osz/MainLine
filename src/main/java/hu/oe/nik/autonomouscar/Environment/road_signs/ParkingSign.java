package hu.oe.nik.autonomouscar.Environment.road_signs;

import java.util.Arrays;

/**
 * Created by nemeth on 2016.10.07..
 */
public class ParkingSign extends RoadSign {

    public ParkingSign(int Id, int[] startPosition, double[] Transform, int Zlevel, int Opacity, ParkingSignType parkingSignType) {
        super(Id, startPosition, 80, 80, Transform, Zlevel, Opacity, false);
        this.parkingSignType = parkingSignType;
    }

    public enum ParkingSignType {ParkingLeft, ParkingRight}
    ParkingSignType parkingSignType;

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
                "\n   DirectionType: " + this.parkingSignType +
                "\n";
    }
}
