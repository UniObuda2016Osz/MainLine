package Sensors;

import Environment.WorldObject;
import Environment.XMLParserMain;
import com.sun.xml.internal.bind.v2.runtime.reflect.opt.Const;

import java.util.ArrayList;

/**
 * Created by ral2bp on 2016.09.29..
 */

public class Radar {


    private ArrayList<WorldObject> detectedObjects;
    private static final int radarDistance = 200;
    private static final int radarRadiusInDegree = 30;
    private double RadarXcoord;
    private double RadarYcoord;
    public Radar(){
        detectedObjects = new ArrayList<>();


    }

    private void GetObjectsFromEnvironment(){
        // this.detectedObjects =
    }


}