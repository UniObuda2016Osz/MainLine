package hu.oe.nik.autonomouscar.Environment.misc;

import java.util.Arrays;

/**
 * Created by Akos on 2016. 09. 27..
 */
public class Parking extends Misc{

    public Parking(int Id, int[] startPosition, int width, int height, double[] Transform, int Zlevel, int Opacity, ParkingElement parkingElement, boolean CanStuckOnIt) {
        super(Id, startPosition, width, height, Transform, Zlevel, Opacity, CanStuckOnIt);
        this.parkingElement = parkingElement;
    }

    public enum ParkingElement {Bollard,ParallelParking,PerpendicularParking}
    ParkingElement parkingElement;

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
                "\n   ParkingElement: " + this.parkingElement +
                "\n";}
}