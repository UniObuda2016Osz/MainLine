package Environment;
import Environment.road_tiles.LaneSimple;
import org.junit.Assert;
import org.junit.Test;

import javax.xml.stream.XMLStreamException;

import static org.junit.Assert.assertEquals;

/**
 * Created by nemet on 2016. 11. 01..
 */
public class XMLParserMainTest {
    XMLParserMain parser = XMLParserMain.getInstance();

    @org.junit.Before
    public void setUp() throws Exception {
    }

    @org.junit.Test
    public void doesClassExist() throws Exception{
        assert(parser.getClass() != null);
    }

    @Test
    public void PositionGetterReturnsSetterGivenValue(){
        parser.setPosition(new int[]{0,1});
        Assert.assertArrayEquals(new int[]{0,1},parser.getPosition());
        parser.setPosition(new int[]{1,0});
        Assert.assertArrayEquals(new int[]{1,0}, parser.getPosition());
    }

    @Test
    public void TransformGetterReturnsSetterGivenValue(){
        parser.setTransform(new double[]{0,1,1,0});
        Assert.assertArrayEquals(new double[]{0.00,1.00,1.00,0.00}, parser.getTransform(),0.01);
        parser.setTransform(new double[]{1,0,0,1});
        Assert.assertArrayEquals(new double[]{1,0,0,1}, parser.getTransform(),0.1);
    }

    @Test
    public void IsDynamicObjectsNotNullAfterParsing() throws XMLStreamException {
        parser.Parser();
        assert(parser.getWorld()!=null);
    }

    @Test
    public void IsReadedObjectEqualsWithManualCreatedObject() throws XMLStreamException{
        parser.Parser();
        LaneSimple object = new LaneSimple(0,new int[]{3050,3950},350,350,new double[]{1,0,0,1},0,1,new int[]{255,255,255,255},new int[]{255,255,255,255},new int[]{255,255,255,255}, LaneSimple.RoadPaintings1.rp_sep_1_s, LaneSimple.RoadPaintings2.rp_sep_2_d, LaneSimple.RoadPaintings3.rp_sep_3_s, LaneSimple.LaneSimpleType.Straight);
        assertEquals(object.toString(),parser.getWorld().get(0).toString());
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
