package hu.oe.nik.autonomouscar.Sensors.Radar;

import hu.oe.nik.autonomouscar.Environment.NPC.NPC;

/**
 * Created by judit on 2016. 11. 12..
 */
public class DetectedObject implements Comparable {

    private float actualSpeed;
    private float actualDistance;
    private int leftOffset;
    private int rightOffset;
    private NPC npc;
    private RadarCalculator.NPCType npctype;

    //public DetectedObject(NPC npc) {
    //this.npc = npc;
    //this.leftOffset = npc.getCenterPoint()[1] - npc.getWidth()/2;
    //this.rightOffset = npc.getCenterPoint()[1] + npc.getWidth()/2;
    //}

    public float getActualSpeed() {
        return actualSpeed;
    }

    public void setActualSpeed(float actualSpeed) {
        this.actualSpeed = actualSpeed;
    }

    public float getActualDistance() {
        return actualDistance;
    }

    public void setActualDistance(float actualDistance) {
        this.actualDistance = actualDistance;
    }

    public int getLeftOffset() {
        return leftOffset;
    }

    public void setLeftOffset(int leftOffset) {
        this.leftOffset = leftOffset;
    }

    public int getRightOffset() {
        return rightOffset;
    }

    public void setRightOffset(int rightOffset) {
        this.rightOffset = rightOffset;
    }

    public NPC getNpc() {
        return npc;
    }

    public void setNpc(NPC npc) {
        this.npc = npc;
    }

    public RadarCalculator.NPCType getNpctype() {
        return npctype;
    }

    public void setNpctype(RadarCalculator.NPCType npctype) {
        this.npctype = npctype;
    }

    @Override
    public int compareTo(Object o) {
        return Math.round(actualDistance - ((DetectedObject)o).getActualDistance());
    }
}
