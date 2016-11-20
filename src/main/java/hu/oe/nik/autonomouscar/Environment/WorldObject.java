package hu.oe.nik.autonomouscar.Environment;
/**
 * Created by Akos on 2016. 09. 27..
 */
 public class WorldObject {

    public static WorldObject getInstance() {
        if (instance == null)
            instance = new WorldObject(0,new int[] {3050,3950}, 350, 350, new double[] {1,0,0,1}, 0, 1, true);

        return instance;
    }

    public static void setInstance(WorldObject instance) {
        WorldObject.instance = instance;
    }

    private static WorldObject instance = null;

    //id
    private int id;

    public int getId() {
        return this.id;
    }

    //position
    private int[] position;

    public int[] getPosition() {
        return this.position;
    }

    public void setPosition(int[] position) {
        this.position = position;
    }

    private int width;

    public int getWidth() {
        return this.width;
    }

    private int height;

    public int getHeight() {
        return this.height;
    }

    //transform
    private double[] transform;

    public double[] getTransform() {
        return this.transform;
    }

    public void setTransform(double[] transform) {
        this.transform = transform;
    }

    //zlevel
    private int zLevel;

    public int getZLevel() {
        return this.zLevel;
    }

    //opacity
    private int opacity;

    public int getOpacity() {
        return this.opacity;
    }

    private boolean canStuckOnIt;

    public boolean getCanStuckOnIt() {
        return this.canStuckOnIt;
    }

    public WorldObject(int Id, int[] startPosition, int width, int height, double[] Transform, int Zlevel, int Opacity, boolean CanStuckOnIt) {
        this.id = Id;
        this.position = startPosition;
        this.width = width;
        this.height = height;
        this.transform = Transform;
        this.zLevel = Zlevel;
        this.opacity = Opacity;
        this.canStuckOnIt = CanStuckOnIt;
    }

    public int[] getCenterPoint() {
     
        int[] position = this.getPosition();
        int[] centerPoint = new int[2];
        double[] transformation = this.getTransform();

        int felSzelesseg = this.getWidth() / 2;
        int felMagassag = this.getHeight() / 2;

        centerPoint[0] = (int) (felSzelesseg * transformation[0] + felMagassag * transformation[1]);
        centerPoint[1] = (int) (felSzelesseg * transformation[2] + felMagassag * transformation[3]);

        centerPoint[0]+=position[0];
        centerPoint[1]+=position[1];
        return centerPoint;
    }
}
