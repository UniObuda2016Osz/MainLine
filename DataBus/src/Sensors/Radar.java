package Sensors;

import Environment.WorldObject;
import Environment.XMLParserMain;
import com.sun.xml.internal.bind.v2.runtime.reflect.opt.Const;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ral2bp on 2016.09.29..
 */

public class Radar {

    private WorldObject WO;
    private XMLParserMain xml;
    private ArrayList<WorldObject> detectedObjects;
    private static final int radarDistance = 200;
    private static final int radarRadiusInDegree = 30;
    public double[] RadarCoord = new double[2];
    public int[][] Triangle = new int[3][2];
    public double[] transformation;
    public Radar(){
        detectedObjects = new ArrayList<>();

    }

    private void Refresh(){
        transformation = WO.getTransform();
        RadarCoord[0] = (double)WO.getCenterPoint()[0]+WO.getHeight()*transformation[0];
        RadarCoord[1] = (double)WO.getCenterPoint()[1]+WO.getHeight()*transformation[1];
    }
    private void GetObjectsFromEnvironment(){
        detectedObjects = (ArrayList)xml.getDetectedObjects(this.Triangle[2][1],this.Triangle[2][2],this.Triangle[3][1],this.Triangle[3][2],this.Triangle[1][1],this.Triangle[1][2]);
    }

    private void CalculateTriangle(){
        this.Triangle[1][1] = (int)Math.round(RadarCoord[1]);
        this.Triangle[1][2] = (int)Math.round(RadarCoord[2]);
        this.Triangle[2][1] = (int)Math.round(RadarCoord[1] + radarDistance*transformation[0]);
        this.Triangle[2][2] = (int)Math.round(RadarCoord[2] + Math.tan(radarRadiusInDegree/2)*radarDistance*transformation[1]);
        this.Triangle[3][1] = (int)Math.round(RadarCoord[1] - radarDistance*transformation[0]);
        this.Triangle[3][2] = (int)Math.round(RadarCoord[2] - Math.tan(radarRadiusInDegree/2)*radarDistance*transformation[1]);
    }
}