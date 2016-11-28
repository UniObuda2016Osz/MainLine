package hu.oe.nik.autonomouscar.Environment;

import hu.oe.nik.autonomouscar.Bus.Bus;
import hu.oe.nik.autonomouscar.Environment.road_signs.Speed;
import hu.oe.nik.autonomouscar.Environment.road_tiles.RoadTile;
import hu.oe.nik.autonomouscar.Sensors.Camera;

import java.util.List;

/**
 * Created by Labancz Kristóf on 2016. 11. 17..
 */
public class LaneKeeping {

    private static final double SAV_SZELESSEGE = (350/2); //350 az egyenes ut szélessége
    private static final double MAXIMUM_ELTERES = 20; //itt már korigálni kell

    List<WorldObject> vilag = XMLParserMain.getInstance().getDynamicObjects();

    UserCar car;
    RoadTile melyikUtonVagyunk;

    public void Uton() //Melyik uton vagyunk rajta
    {
        for (WorldObject wo : vilag) {
            if(wo instanceof RoadTile) {
                int x = wo.getPosition()[0];
                int y = wo.getPosition()[1];
                if (x >= car.getX() && car.getX() <= x + wo.getWidth()) {  //megvan, hogy melyik úton vagyunk rajta
                    if (y >= car.getY() && car.getY() <= y + wo.getHeight()) {
                        {
                            melyikUtonVagyunk = (RoadTile) wo;
                        }
                    }
                }
            }
        }
    }

    public void SavKovetes()
    {
//        if(Bus.getInstance().isLaneKeepingOn())
//        {
//            if(melyikUtonVagyunk)//tullógunk jobboldalt az úton
//                Bus.getInstance().setSteeringWheelAngle(-10);//akkor balra kormányzunk
//            else
//                Bus.getInstance().setSteeringWheelAngle(+10);//különben jobbra kormányzunk
//        }

//        ?meg kell jelenítenünk a sebesség korlátot, vagyis a táblákat? //User interface(Traffic Sign display) available
//        for(WorldObject wo : Camera.getRelevantObjects) {
//            if(wo.equals(Speed))
//                Bus.getInstance().setSpeedLimitValue((Speed)wo.getSpeedType().toString());
//        }

    }
}
