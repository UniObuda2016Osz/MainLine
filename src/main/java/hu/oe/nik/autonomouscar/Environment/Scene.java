package hu.oe.nik.autonomouscar.Environment;

/**
 * Created by forii on 2016. 10. 09..
 */
public class Scene {
    private static Scene instance = null;

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public int getMeasureType() {
        return measureType;
    }

    public void setMeasureType(int measureType) {
        this.measureType = measureType;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    private int width;
    private int height;
    private int measureType;
    private String color;

    public static Scene getInstance()
    {
        if(instance == null)
            instance = new Scene();
        return instance;
    }

    private Scene() {
        setWidth(0);
        setHeight(0);
        setMeasureType(0);
        setColor(null);
    }
}
