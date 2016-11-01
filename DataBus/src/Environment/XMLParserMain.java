package Environment;

import Environment.misc.*;
import Environment.road_signs.*;
import Environment.road_tiles.LaneAdvanced;
import Environment.road_tiles.LaneSimple;
import javax.swing.*;
import javax.xml.stream.*;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by ral2bp on 2016.09.29..
 */
public class XMLParserMain implements ISensor {

    private XMLInputFactory factory;
    public XMLInputFactory getXMLInputFactory() {
        return factory;
    }

    private JFileChooser fileChooser;
    public JFileChooser getFileChooser() { return fileChooser; }
    public void setFileChooser(JFileChooser fileChooser) { this.fileChooser = fileChooser; }

    public XMLStreamReader getGetStreamReader() { return getStreamReader; }
    public void setGetStreamReader(XMLStreamReader getStreamReader) { this.getStreamReader = getStreamReader; }

    private XMLStreamReader getStreamReader = null;

    private void setScene(Scene scene) { this.scene = scene; }
    private Scene scene = null;
    public Scene getScene() { return this.scene; }


    private int Id;
    private int getId() { return Id; }
    private void setId(int id) { this.Id = id; }

    private String Name;
    private String getName() { return Name; }
    private void setName(String Name) { this.Name = Name; }

    private String parameterGroupName;
    private String getParameterGroupName() { return parameterGroupName; }
    private void setParameterGroupName(String parameterGroupName) { this.parameterGroupName = parameterGroupName; }

    public List<WorldObject> getDynamicObjects() { return DynamicObjects; }
    public void setDynamicObjects(List<WorldObject> dynamicObjects) { DynamicObjects = dynamicObjects; }
    private List<WorldObject> DynamicObjects = null;

    public List<WorldObject> getWorld() throws XMLStreamException{
        if(getDynamicObjects()==null)
            Parser();

        return DynamicObjects;
    }

    private int[] Position;
    public void setPosition(int[] position) {
        this.Position = position;
    }
    public int[] getPosition() {
        return Position;
    }

    private double[] Transform;
    public double[] getTransform() {
        return Transform;
    }
    public void setTransform(double[] transform) {
        this.Transform = transform;
    }

    private int ZLevel;
    private int getZLevel() { return ZLevel; }
    private void setZLevel(int ZLevel) { this.ZLevel = ZLevel; }

    private int Opacity;
    private int getOpacity() { return Opacity; }
    private void setOpacity(int opacity) { this.Opacity = opacity; }

    private int[] RoadColor1; // 0 - r, 1 - g, 2 - b, 3 - a
    private int[] getRoadColor1() { return RoadColor1; }
    private void setRoadColor1(int[] roadColor1) { this.RoadColor1 = roadColor1; }

    private int[] RoadColor2; // 0 - g, 1 - type, 2 - index, 3 - r, 4 - a, 5 - name, 6 - b, (7 - text)
    private int[] getRoadColor2() { return RoadColor2; }
    private void setRoadColor2(int[] roadColor2) { RoadColor2 = roadColor2; }

    private int[] RoadColor3; // 0 - g, 1 - type, 2 - index, 3 - r, 4 - a, 5 - name, 6 - b, (7 - text)
    private int[] getRoadColor3() { return RoadColor3; }
    private void setRoadColor3(int[] roadColor3) { RoadColor3 = roadColor3; }

    private String RoadPainting1;
    private String getRoadPainting1() { return RoadPainting1; }
    private void setRoadPainting1(String roadPainting1) { RoadPainting1 = roadPainting1; }

    private String RoadPainting2;
    private String getRoadPainting2() { return RoadPainting2; }
    private void setRoadPainting2(String roadPainting2) { RoadPainting2 = roadPainting2; }

    private String RoadPainting3;
    private String getRoadPainting3() { return RoadPainting3; }
    private void setRoadPainting3(String roadPainting3) { RoadPainting3 = roadPainting3; }


    private static XMLParserMain instance = null;
    private JFileChooser jFileChooser = null;

    /*public JFileChooser setjFileChooser(JFileChooser jFileChooser) {
        this.jFileChooser = jFileChooser; }
*/
    public static XMLParserMain getInstance() {
        if (instance == null) {
            instance = new XMLParserMain();
        }
        return instance;
    }

    public boolean Parser() throws XMLStreamException {
        if (XmlFileOpener()) {
            while (getStreamReader.hasNext()) {
                int event = getStreamReader.next();
                switch (event) {
                    case XMLStreamConstants.START_ELEMENT:
                        if(getScene() == null)
                            sceneCase();
                        if(getDynamicObjects()==null)
                            objectsTag();
                        else {
                            objectCase();
                            checkIfTagEqual();
                        }
                        break;
                    case XMLStreamConstants.END_ELEMENT:
                        objectCaseWithEndElement();
                        break;
                }
            }
            getStreamReader.close();
            writeOutTheObjects();
            return true;
        } else {
            getStreamReader.close();
            return false;
        }
    }

    private void objectCaseWithEndElement() throws XMLStreamException{
        if ("Object".equals(getStreamReader.getLocalName())) {
            String[] splitName = getName().split("/");
            if (getDynamicObjects() != null) {
                CreateClassElementByName(splitName[1], splitName[2], splitName[3]);
            } else {
                getStreamReader.close();
            }
        }
    }

    private boolean XmlFileOpener() {
        this.setFileChooser(new JFileChooser(new File(System.getProperty("user.dir") + "/DataBus/src/Environment")));
        int returnValue = fileChooser.showOpenDialog(fileChooser.getParent());
        if (returnValue == JFileChooser.APPROVE_OPTION) {
            File selectedFile = fileChooser.getSelectedFile();
            String[] parse = selectedFile.getPath().split("\\\\");
            String path = parse[parse.length - 2] + "\\" + parse[parse.length - 1];
            System.out.println("A feldolgozandó XML: " + parse[parse.length - 1]);
            try {
                factory = XMLInputFactory.newInstance();
                setGetStreamReader(factory.createXMLStreamReader(ClassLoader.getSystemResourceAsStream(path)));
                return true;

            } catch (XMLStreamException e) {
                System.out.println(String.format("%s\n" + "%s", "Valami nem jó", e.getMessage()));
                return false;
            }
        } else {
            System.out.println("Nem lett megadva file");
            return false;
        }
    }

    private void sceneCase() {
        if ("Scene".equals(getStreamReader.getLocalName())) {
            int sceneWidth = Integer.parseInt(getStreamReader.getAttributeValue("", "width"));
            int sceneHeihgt = Integer.parseInt(getStreamReader.getAttributeValue("", "height"));
            int sceneMeasureType = Integer.parseInt(getStreamReader.getAttributeValue("", "measureType"));
            String sceneColor = getStreamReader.getAttributeValue("", "color");
            setScene(new Scene(sceneWidth,sceneHeihgt,sceneMeasureType,sceneColor));
        }
    }

    //for test cases
    final protected void objectCase() {
        if ("Object".equals(getStreamReader.getLocalName())) {
            setName(getStreamReader.getAttributeValue("", "name"));
            setId(Integer.parseInt(getStreamReader.getAttributeValue("", "id")));
        }
    }

    private void objectsTag()
    {
        if ("Objects".equals(getStreamReader.getLocalName())) {
            setDynamicObjects(new ArrayList<>());
        }
    }

    private void checkIfTagEqual() {

        if ("Position".equals(getStreamReader.getLocalName())) {
            int[] tmpPosition = new int[2];
            tmpPosition[0] = (int) Math.round(Double.parseDouble(getStreamReader.getAttributeValue("", "x")));
            tmpPosition[1] = (int) Math.round(Double.parseDouble(getStreamReader.getAttributeValue("", "y")));
            setPosition(tmpPosition);
        }
        if ("Transform".equals(getStreamReader.getLocalName())) {
            double[] tmpTransform = new double[4];
            tmpTransform[0] = Double.parseDouble(getStreamReader.getAttributeValue("", "m11"));
            tmpTransform[1] = Double.parseDouble(getStreamReader.getAttributeValue("", "m12"));
            tmpTransform[2] = Double.parseDouble(getStreamReader.getAttributeValue("", "m21"));
            tmpTransform[3] = Double.parseDouble(getStreamReader.getAttributeValue("", "m22"));
            setTransform(tmpTransform);
        }
        if ("ZLevel".equals(getStreamReader.getLocalName())) {
            setZLevel(Integer.parseInt(getStreamReader.getAttributeValue("", "ZLevel")));
        }
        if ("Opacity".equals(getStreamReader.getLocalName())) {
            setOpacity(Integer.parseInt(getStreamReader.getAttributeValue("", "Opacity")));
        }
        if ("ParameterGroup".equals(getStreamReader.getLocalName())) {
            parameterGroupNameSwitch();
        }
        if ("Parameter".equals(getStreamReader.getLocalName())) {
            getParameterByGroupName();
        }
    }

    private void getParameterByGroupName() {
        switch (getParameterGroupName()) {
            case "RoadColor_1":
                int[] tmpRoadColor1 = new int[4];
                tmpRoadColor1[0] = Integer.parseInt(getStreamReader.getAttributeValue("", "r"));
                tmpRoadColor1[1] = Integer.parseInt(getStreamReader.getAttributeValue("", "g"));
                tmpRoadColor1[2] = Integer.parseInt(getStreamReader.getAttributeValue("", "b"));
                tmpRoadColor1[3] = Integer.parseInt(getStreamReader.getAttributeValue("", "a"));
                setRoadColor1(tmpRoadColor1);
                break;
            case "RoadColor_2":
                int[] tmpRoadColor2 = new int[4];
                tmpRoadColor2[0] = Integer.parseInt(getStreamReader.getAttributeValue("", "r"));
                tmpRoadColor2[1] = Integer.parseInt(getStreamReader.getAttributeValue("", "g"));
                tmpRoadColor2[2] = Integer.parseInt(getStreamReader.getAttributeValue("", "b"));
                tmpRoadColor2[3] = Integer.parseInt(getStreamReader.getAttributeValue("", "a"));
                setRoadColor2(tmpRoadColor2);
                break;
            case "RoadColor_3":
                int[] tmpRoadColor3 = new int[4];
                tmpRoadColor3[0] = Integer.parseInt(getStreamReader.getAttributeValue("", "r"));
                tmpRoadColor3[1] = Integer.parseInt(getStreamReader.getAttributeValue("", "g"));
                tmpRoadColor3[2] = Integer.parseInt(getStreamReader.getAttributeValue("", "b"));
                tmpRoadColor3[3] = Integer.parseInt(getStreamReader.getAttributeValue("", "a"));
                setRoadColor3(tmpRoadColor3);
                break;
            case "RoadPainting_1":
                if ("true".equals(getStreamReader.getAttributeValue("", "value"))) {
                    setRoadPainting1(getStreamReader.getAttributeValue("", "name"));
                }
                break;
            case "RoadPainting_2":
                if ("true".equals(getStreamReader.getAttributeValue("", "value"))) {
                    setRoadPainting2(getStreamReader.getAttributeValue("", "name"));
                }
                break;
            case "RoadPainting_3":
                if ("true".equals(getStreamReader.getAttributeValue("", "value"))) {
                    setRoadPainting3(getStreamReader.getAttributeValue("", "name"));
                }
                break;
            case "":
                break;
            default:
                break;
        }
    }

    private void parameterGroupNameSwitch() {
        switch (getStreamReader.getAttributeValue("", "name")) {
            case "RoadColor_1":
                setParameterGroupName("RoadColor_1");
                break;
            case "RoadColor_2":
                setParameterGroupName("RoadColor_2");
                break;
            case "RoadColor_3":
                setParameterGroupName("RoadColor_3");
                break;
            case "RoadPainting_1":
                setParameterGroupName("RoadPainting_1");
                break;
            case "RoadPainting_2":
                setParameterGroupName("RoadPainting_2");
                break;
            case "RoadPainting_3":
                setParameterGroupName("RoadPainting_3");
                break;
            default:
                setParameterGroupName("");
                break;
        }
    }


    private void CreateClassElementByName(String collection, String collectionType, String elementType) {
        switch (collectionType) {
            //misc mappában lévők
            case "crosswalks":
                getDynamicObjects().add(new Crosswalk(getId(), getPosition(), getTransform(), getZLevel(), getOpacity()));
                break;
            case "people": {
                getDynamicObjects().add(new People(getId(), getPosition(), getTransform(), getZLevel(), getOpacity())); //int Id,  startPosition, int[] Transform, int Zlevel, int Opacity)
            }
            break;
            case "trees":
                getDynamicObjects().add(new Tree(getId(), getPosition(), getTransform(), getZLevel(), getOpacity()));
                break;
            case "parking":
                parkingCollectionSwitch(collection, collectionType, elementType);
                break;
            //road_signs mappában lévők kivéve a parking-ot
            case "direction":
                createDirectionTable(elementType);
                break;
            case "priority":
                createPriorityTable(elementType);
                break;
            case "speed":
                createSpeedTable(elementType);
                break;
            //road_tiles mappában lévők
            case "2_lane_simple":
                create2laneSimple(elementType);
                break;
            case "2_lane_advanced":
                create2LaneAdvanced(elementType);
                break;
            default:
                break;
        }
    }

    private void createDirectionTable(String elementType) {
        switch (elementType) {
            case "209-30_.svg":
                getDynamicObjects().add(new Direction(getId(), getPosition(), getTransform(), getZLevel(), getOpacity(), Direction.DirectionType.Forward));
                break;
            case "211-10_.svg":
                getDynamicObjects().add(new Direction(getId(), getPosition(), getTransform(), getZLevel(), getOpacity(), Direction.DirectionType.Left));
                break;
            case "211-20_.svg":
                getDynamicObjects().add(new Direction(getId(), getPosition(), getTransform(), getZLevel(), getOpacity(), Direction.DirectionType.Right));
                break;
            case "214-10_.svg":
                getDynamicObjects().add(new Direction(getId(), getPosition(), getTransform(), getZLevel(), getOpacity(), Direction.DirectionType.ForwardLeft));
                break;
            case "214-20_.svg":
                getDynamicObjects().add(new Direction(getId(), getPosition(), getTransform(), getZLevel(), getOpacity(), Direction.DirectionType.ForwardRight));
                break;
            case "215_.svg":
                getDynamicObjects().add(new Direction(getId(), getPosition(), getTransform(), getZLevel(), getOpacity(), Direction.DirectionType.Round));
                break;
            default:
                break;
        }
    }

    private void createPriorityTable(String elementType) {
        switch (elementType) {
            case "205_.svg":
                getDynamicObjects().add(new Priority(getId(), getPosition(), getTransform(), getZLevel(), getOpacity(), Priority.PriorityType.Yield));
                break;
            case "206_.svg":
                getDynamicObjects().add(new Priority(getId(), getPosition(), getTransform(), getZLevel(), getOpacity(), Priority.PriorityType.Stop));
                break;
            case "306_.svg":
                getDynamicObjects().add(new Priority(getId(), getPosition(), getTransform(), getZLevel(), getOpacity(), Priority.PriorityType.Priority));
                break;
            default:
                break;
        }
    }

    private void createSpeedTable(String elementType) {
        switch (elementType) {
            case "274_51_.svg": //10
                getDynamicObjects().add(new Speed(getId(), getPosition(), getTransform(), getZLevel(), getOpacity(), Speed.SpeedType.Ten));
                break;
            case "274_52_.svg": //20
                getDynamicObjects().add(new Speed(getId(), getPosition(), getTransform(), getZLevel(), getOpacity(), Speed.SpeedType.Twenty));
                break;
            case "274_54_.svg": //400
                getDynamicObjects().add(new Speed(getId(), getPosition(), getTransform(), getZLevel(), getOpacity(), Speed.SpeedType.Forty));
                break;
            case "274_55_.svg": //50
                getDynamicObjects().add(new Speed(getId(), getPosition(), getTransform(), getZLevel(), getOpacity(), Speed.SpeedType.Fifty));
                break;
            case "274_57_.svg": //70
                getDynamicObjects().add(new Speed(getId(), getPosition(), getTransform(), getZLevel(), getOpacity(), Speed.SpeedType.Seventy));
                break;
            case "274_59_.svg": //90
                getDynamicObjects().add(new Speed(getId(), getPosition(), getTransform(), getZLevel(), getOpacity(), Speed.SpeedType.Ninety));
                break;
            case "274_60_.svg": //100
                getDynamicObjects().add(new Speed(getId(), getPosition(), getTransform(), getZLevel(), getOpacity(), Speed.SpeedType.Hundred));
                break;
            default:
                break;
        }
    }

    private void create2LaneAdvanced(String elementType) {
        switch (elementType) {
            case "2_t_junction_l.tile":
                getDynamicObjects().add(new LaneAdvanced(getId(), getPosition(), 890, 1400, getTransform(), getZLevel(), getOpacity(), getRoadColor1(), getRoadColor2(), getRoadColor3(), LaneAdvanced.RoadPaintings1.valueOf(getRoadPainting1()), LaneAdvanced.RoadPaintings2.valueOf(getRoadPainting2()), LaneAdvanced.RoadPaintings3.valueOf(getRoadPainting3()), LaneAdvanced.LaneAdvancedType.TJunctionLeft));
                break;
            case "2_t_junction_r.tile":
                getDynamicObjects().add(new LaneAdvanced(getId(), getPosition(), 890, 1400, getTransform(), getZLevel(), getOpacity(), getRoadColor1(), getRoadColor2(), getRoadColor3(), LaneAdvanced.RoadPaintings1.valueOf(getRoadPainting1()), LaneAdvanced.RoadPaintings2.valueOf(getRoadPainting2()), LaneAdvanced.RoadPaintings3.valueOf(getRoadPainting3()), LaneAdvanced.LaneAdvancedType.TJunctionRight));
                break;
            case "2_rotary.tile":
                getDynamicObjects().add(new LaneAdvanced(getId(), getPosition(), 1400, 1400, getTransform(), getZLevel(), getOpacity(), getRoadColor1(), getRoadColor2(), getRoadColor3(), LaneAdvanced.RoadPaintings1.valueOf(getRoadPainting1()), LaneAdvanced.RoadPaintings2.valueOf(getRoadPainting2()), LaneAdvanced.RoadPaintings3.valueOf(getRoadPainting3()), LaneAdvanced.LaneAdvancedType.Rotary));
                break;
            case "2_crossroads_2.tile":
                getDynamicObjects().add(new LaneAdvanced(getId(), getPosition(), 1400, 1400, getTransform(), getZLevel(), getOpacity(), getRoadColor1(), getRoadColor2(), getRoadColor3(), LaneAdvanced.RoadPaintings1.valueOf(getRoadPainting1()), LaneAdvanced.RoadPaintings2.valueOf(getRoadPainting2()), LaneAdvanced.RoadPaintings3.valueOf(getRoadPainting3()), LaneAdvanced.LaneAdvancedType.CrossRoads));
                break;
            default:
                break;
        }
    }

    private void create2laneSimple(String elementType) {
        switch (elementType) {
            case "2_simple_45l.tile":
                getDynamicObjects().add(new LaneSimple(getId(), getPosition(), 390, 370, getTransform(), getZLevel(), getOpacity(), getRoadColor1(), getRoadColor2(), getRoadColor3(), LaneSimple.RoadPaintings1.valueOf(getRoadPainting1()), LaneSimple.RoadPaintings2.valueOf(getRoadPainting2()), LaneSimple.RoadPaintings3.valueOf(getRoadPainting3()), LaneSimple.LaneSimpleType.Left45));
                break;
            case "2_simple_45r.tile":
                getDynamicObjects().add(new LaneSimple(getId(), getPosition(), 390, 370, getTransform(), getZLevel(), getOpacity(), getRoadColor1(), getRoadColor2(), getRoadColor3(), LaneSimple.RoadPaintings1.valueOf(getRoadPainting1()), LaneSimple.RoadPaintings2.valueOf(getRoadPainting2()), LaneSimple.RoadPaintings3.valueOf(getRoadPainting3()), LaneSimple.LaneSimpleType.Right45));
                break;
            case "2_simple_65l.tile":
                getDynamicObjects().add(new LaneSimple(getId(), getPosition(), 450, 475, getTransform(), getZLevel(), getOpacity(), getRoadColor1(), getRoadColor2(), getRoadColor3(), LaneSimple.RoadPaintings1.valueOf(getRoadPainting1()), LaneSimple.RoadPaintings2.valueOf(getRoadPainting2()), LaneSimple.RoadPaintings3.valueOf(getRoadPainting3()), LaneSimple.LaneSimpleType.Left65));
                break;
            case "2_simple_65r.tile":
                getDynamicObjects().add(new LaneSimple(getId(), getPosition(), 450, 475, getTransform(), getZLevel(), getOpacity(), getRoadColor1(), getRoadColor2(), getRoadColor3(), LaneSimple.RoadPaintings1.valueOf(getRoadPainting1()), LaneSimple.RoadPaintings2.valueOf(getRoadPainting2()), LaneSimple.RoadPaintings3.valueOf(getRoadPainting3()), LaneSimple.LaneSimpleType.Right65));
                break;
            case "2_simple_90l.tile":
                getDynamicObjects().add(new LaneSimple(getId(), getPosition(), 530, 530, getTransform(), getZLevel(), getOpacity(), getRoadColor1(), getRoadColor2(), getRoadColor3(), LaneSimple.RoadPaintings1.valueOf(getRoadPainting1()), LaneSimple.RoadPaintings2.valueOf(getRoadPainting2()), LaneSimple.RoadPaintings3.valueOf(getRoadPainting3()), LaneSimple.LaneSimpleType.Left90));
                break;
            case "2_simple_90r.tile":
                getDynamicObjects().add(new LaneSimple(getId(), getPosition(), 530, 530, getTransform(), getZLevel(), getOpacity(), getRoadColor1(), getRoadColor2(), getRoadColor3(), LaneSimple.RoadPaintings1.valueOf(getRoadPainting1()), LaneSimple.RoadPaintings2.valueOf(getRoadPainting2()), LaneSimple.RoadPaintings3.valueOf(getRoadPainting3()), LaneSimple.LaneSimpleType.Right90));
                break;
            case "2_simple_s.tile":
                getDynamicObjects().add(new LaneSimple(getId(), getPosition(), 350, 350, getTransform(), getZLevel(), getOpacity(), getRoadColor1(), getRoadColor2(), getRoadColor3(), LaneSimple.RoadPaintings1.valueOf(getRoadPainting1()), LaneSimple.RoadPaintings2.valueOf(getRoadPainting2()), LaneSimple.RoadPaintings3.valueOf(getRoadPainting3()), LaneSimple.LaneSimpleType.Straight));
                break;
            default:
                break;
        }
    }

    private void parkingCollectionSwitch(String collection, String collectionType, String elementType) {
        switch (collection) {
            case "road_signs":
                createParkingSign(elementType);
                break;
            case "misc":
                createParkingMisc(elementType);
                break;
            default:
                break;
        }
    }

    private void createParkingSign(String elementType) {
        switch (elementType) {
            case "314_10_.svg": //bal
                getDynamicObjects().add(new ParkingSign(getId(), getPosition(), getTransform(), getZLevel(), getOpacity(), ParkingSign.ParkingSignType.ParkingLeft));
                break;
            case "314_20_.svg": //jobb
                getDynamicObjects().add((new ParkingSign(getId(), getPosition(), getTransform(), getZLevel(), getOpacity(), ParkingSign.ParkingSignType.ParkingRight)));
                break;
            default:
                break;
        }
    }

    private void createParkingMisc(String elementType) {
        switch (elementType) {
            case "parking_0.svg":
                getDynamicObjects().add(new Parking(getId(), getPosition(), 150, 600, getTransform(), getZLevel(), getOpacity(), Parking.ParkingElement.ParallelParking, false));
                break;
            case "parking_90.svg":
                getDynamicObjects().add(new Parking(getId(), getPosition(), 300, 460, getTransform(), getZLevel(), getOpacity(), Parking.ParkingElement.PerpendicularParking, false));
                break;
            case "parking_bollard.pix":
                getDynamicObjects().add(new Parking(getId(), getPosition(), 80, 75, getTransform(), getZLevel(), getOpacity(), Parking.ParkingElement.Bollard, true));
                break;
            default:
                break;
        }
    }

    @Override
    public List<WorldObject> getDetectedObjects(int leftX, int leftY, int rightX, int rightY, int centerX, int centerY) throws XMLStreamException {
        if(getDynamicObjects()==null)
            Parser();
        //left - távolabbi bal pont
        //right - távolabbi jobb pont
        //center - az autón lévő pont

        int[] leftPoint = {leftX, leftY};
        int[] rightPoint = {rightX, rightY};
        int[] centerPoint = {centerX, centerY};

        int[] vectorCenterToLeft = new int[2];
        vectorCenterToLeft[0] = leftX - centerX;
        vectorCenterToLeft[1] = leftY - centerY;

        int[] vectorCenterToRight = new int[2];
        vectorCenterToRight[0] = rightX - centerX;
        vectorCenterToRight[1] = rightY - centerY;

        int[] vectorLeftToRight = new int[2];
        vectorLeftToRight[0] = rightX - leftX;
        vectorLeftToRight[1] = rightY - leftX;

        List<WorldObject> DetectedObjects = new ArrayList<>();
        for (WorldObject object : DynamicObjects) {
           int[] objectCenter = object.getCenterPoint();

            if(pointBetweenLines(vectorCenterToLeft, objectCenter, leftPoint, rightPoint)) //jobb pont    //paraméterek: vector, alapegyenesen lévő pont, párhuzamos egyenesen lévő pont
                if(pointBetweenLines(vectorCenterToRight, objectCenter, rightPoint, leftPoint)) //balpont
                    if(pointBetweenLines(vectorLeftToRight, objectCenter, leftPoint, centerPoint)) //centerpont
                        DetectedObjects.add(object);
        }
        return DetectedObjects;
    }

    private boolean pointBetweenLines(int[] basicVector, int[] objectCenter, int[] basicPoint, int[] parallelPoint) {
        int basicLineValue = valueOfLineEquation(basicVector, basicPoint);
        int parallelLineValue = valueOfLineEquation(basicVector, parallelPoint);
        int centerPointLineValue = valueOfLineEquation(basicVector, objectCenter);

        return (((centerPointLineValue-basicLineValue>=0) && (centerPointLineValue-parallelLineValue<=0))||((centerPointLineValue-basicLineValue<=0 && centerPointLineValue-parallelLineValue>=0)));
    }

    private int valueOfLineEquation(int[] vector, int[] pointOnLine)
    {
        return vector[0]*pointOnLine[0]+vector[1]*pointOnLine[1];
    }

    private void writeOutDetectedObjects() throws XMLStreamException {
        List<WorldObject> Detected = getDetectedObjects(3220, 4124, 3230, 4124, 3225, 4130);
        System.out.println("Látott objektumok: " + Detected.size() + " db");
        for (WorldObject object :Detected)
        {
            System.out.println(object.toString() + "\n");
        }
    }

    private void writeOutTheObjects() {
        for (WorldObject object : DynamicObjects) {
            System.out.println("Objektum: " + object.toString());
        }
    }
}