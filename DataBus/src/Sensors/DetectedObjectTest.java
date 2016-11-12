package Sensors;


import Environment.NPC.Cyclist;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Created by judit on 2016. 11. 12..
 */
public class DetectedObjectTest {

    DetectedObject DO = new DetectedObject();
    Cyclist npc = new Cyclist(10, new int[]{1,1}, 10, 10, new double[]{0,0, 0, 0}, 0, 0, 0,0);

    @Test
    public void ActualSpeedGetterReturnsSetterGivenValue(){
        float speed = 0.15f;
        DO.setActualSpeed(speed);
        assertEquals(DO.getActualSpeed(), speed, 0.0f);
    }

    @Test
    public void ActualDistanceGetterReturnsSetterGivenValue(){
        float distance=1.45f;
        DO.setActualDistance(distance);
        assertEquals(DO.getActualDistance(), distance, 0.0f);
    }

    @Test
    public void LeftOffsetGetterReturnsSetterGivenValue(){
        int offset = 25;
        DO.setLeftOffset(offset);
        assertEquals(offset, DO.getLeftOffset());
    }

    @Test
    public void RightOffsetGetterReturnsSetterGivenValue(){
        int offset = 25;
        DO.setRightOffset(offset);
        assertEquals(offset, DO.getRightOffset());
    }

    @Test
    public void NPCGetterReturnsSetterGivenValue(){
        DO.setNpc(npc);
        assertEquals(npc, DO.getNpc());
    }

    @Test
    public void NPCTypeGetterReturnsSetterGivenValue(){
        DO.setNpctype(RadarCalculator.NPCType.Cyclist);
        assertEquals(RadarCalculator.NPCType.Cyclist, DO.getNpctype());
    }
}
