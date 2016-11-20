package hu.oe.nik.autonomouscar.Environment;

import hu.oe.nik.autonomouscar.Bus.Bus;
import hu.oe.nik.autonomouscar.Environment.road_signs.Speed;
import hu.oe.nik.autonomouscar.Sensors.Camera;

/**
 * Created by Labancz Kristóf on 2016. 11. 17..
 */
public class LaneKeeping {

    private static final double SAV_SZELESSEGE = (350/2); //350 az egyenes ut szélessége
    private static final double MAXIMUM_ELTERES = 20; //itt már korigálni kell

    public void SavKovetes()
    {
        if(Bus.getInstance().isLaneKeepingOn())
        {
//            if(tullógunk jobboldalt az úton)
//                akkor balra kormányzunk
//            else
//                különben jobbra kormányzunk
        }

//        ?meg kell jelenítenünk a sebesség korlátot, vagyis a táblákat? //User interface(Traffic Sign display) available
//        for(WorldObject wo : Camera.getRelevantObjects) {
//            if(wo.equals(Speed))
//                Bus.getInstance().setSpeedLimitValue((Speed)wo.getSpeedType().toString());
//        }

    }
}
