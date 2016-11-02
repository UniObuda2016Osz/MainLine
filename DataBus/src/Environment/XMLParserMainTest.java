package Environment;
import Environment.road_tiles.LaneSimple;
import org.junit.Assert;
import org.junit.Test;

import javax.xml.stream.XMLStreamException;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

/**
 * Created by nemet on 2016. 11. 01..
 */
public class XMLParserMainTest {
    XMLParserMain parser = XMLParserMain.getInstance();

    @org.junit.Before
    public void setUp() throws Exception {
        if(parser.getDynamicObjects()==null)
            parser.Parser();
    }

    @org.junit.Test
    public void doesClassExist() throws Exception{
        assert(parser.getClass() != null);
    }

    @org.junit.Test
    public void PositionGetterReturnsSetterGivenValue(){
        parser.setPosition(new int[]{0,1});
        Assert.assertArrayEquals(new int[]{0,1},parser.getPosition());
        parser.setPosition(new int[]{1,0});
        Assert.assertArrayEquals(new int[]{1,0}, parser.getPosition());
    }

    @org.junit.Test
    public void TransformGetterReturnsSetterGivenValue(){
        parser.setTransform(new double[]{0,1,1,0});
        Assert.assertArrayEquals(new double[]{0.00,1.00,1.00,0.00}, parser.getTransform(),0.01);
        parser.setTransform(new double[]{1,0,0,1});
        Assert.assertArrayEquals(new double[]{1,0,0,1}, parser.getTransform(),0.1);
    }

    @org.junit.Test
    public void IsDynamicObjectsNotNullAfterParsing() throws XMLStreamException {
        assert(parser.getWorld()!=null);
    }

    @org.junit.Test
    public void IsReadedObjectEqualsWithManualCreatedObject() throws XMLStreamException{
        //List<WorldObject> list = new ArrayList<>();
        //list.add(new LaneSimple(0,new int[]{3050,3950},350,350,new double[]{1,0,0,1},0,1,new int[]{255,255,255,255},new int[]{255,255,255,255},new int[]{255,255,255,255}, LaneSimple.RoadPaintings1.rp_sep_1_s, LaneSimple.RoadPaintings2.rp_sep_2_d, LaneSimple.RoadPaintings3.rp_sep_3_s, LaneSimple.LaneSimpleType.Straight));
        WorldObject obj = new LaneSimple(0,new int[]{3050,3950},350,350,new double[]{1,0,0,1},0,1,new int[]{255,255,255,255},new int[]{255,255,255,255},new int[]{255,255,255,255}, LaneSimple.RoadPaintings1.rp_sep_1_s, LaneSimple.RoadPaintings2.rp_sep_2_d, LaneSimple.RoadPaintings3.rp_sep_3_s, LaneSimple.LaneSimpleType.Straight);
        //WorldObject object2 = parser.getWorld().get(0);
        assertEquals( obj.toString(),parser.getWorld().get(0).toString());
        //assertEquals(list.get(0).toString(), parser.getWorld().get(0));
    }

    @org.junit.Test
    public void TestGetWorld() throws XMLStreamException
    {
        assertEquals(parser.getDynamicObjects(), parser.getWorld());
    }

    @org.junit.Test
    public void TestGetDetectedObjects() throws XMLStreamException
    {
        List<WorldObject> objs = parser.getDetectedObjects(3200,4120,3250,4120,3225,4140);
        System.out.println("Méret: " + objs.size());
        //Assert.assertEquals(parser.getDynamicObjects().get(0).toString().trim(), objs.toString().trim());
        assertEquals(parser.getDynamicObjects().get(0), objs.get(0));
    }

    /*@Test
    public void FileChooserGetterReturnsSetterGivenValue(){
        String dir = System.getProperty("user.dir");

        Assert.assertEquals(new JFileChooser(new File(dir + "/DataBus/src/Environment")),parser.getFileChooser());
    }*/

   /*@Test
    public void SceneGetterReturnsSetterGivenValue(){
        parser.setScene(new int[]{0,1});
        Assert.assertArrayEquals(new int[]{0,1},parser.getPosition());
        parser.setPosition(new int[]{1,0});
        Assert.assertArrayEquals(new int[]{1,0}, parser.getPosition());
    }*/


}
