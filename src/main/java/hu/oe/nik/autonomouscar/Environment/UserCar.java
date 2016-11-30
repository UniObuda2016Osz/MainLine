package hu.oe.nik.autonomouscar.Environment;

import hu.oe.nik.autonomouscar.Bus.Bus;
import hu.oe.nik.autonomouscar.Dynamics.VehicleDynamics;
import hu.oe.nik.autonomouscar.Environment.road_tiles.RoadTile;
import hu.oe.nik.autonomouscar.Sensors.Radar.Radar;

import javax.xml.stream.XMLStreamException;
import java.util.Arrays;
import java.util.List;


/**
 * Created by Akos on 1991. 11. 02..
 */
public class UserCar {

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getDirection() {
        return direction;
    }

    public void setDirection(int direction) {
        this.direction = direction;
    }

    public String getImagePath() {
        return imagePath;
    }

    public Radar getRadar() {
        return radar;
    }

    public void setRadar(Radar radar) {
        this.radar = radar;
    }

    public double getSpeed() {
        return speed;
    }

    public void setSpeed(double speed) {
        this.speed = speed;
    }

    public boolean isMove() {
        return isMove;
    }

    public void setMove(boolean move) {
        isMove = move;
    }

    public boolean isCrashed() {
        return isCrashed;
    }

    public void setCrashed(boolean crashed) {
        isCrashed = crashed;
    }

    public double getSteering() {
        return steering;
    }

    public void setSteering(double steering) {
        this.steering = steering;
    }

    public double getVelocityX() {
        return velocityX;
    }

    public void setVelocityX(double velocityX) {
        this.velocityX = velocityX;
    }

    public double getVelocityY() {
        return velocityY;
    }

    public void setVelocityY(double velocityY) {
        this.velocityY = velocityY;
    }

    public double getRotation() {
        return rotation;
    }

    public void setRotation(double rotation) {
        this.rotation = rotation;
    }

    //public void getXCoord() { return this. }

    public void setXCoord(int newXposition) {
        x=newXposition;
    }

    public void setYCoord(int newYposition) {
        y=newYposition;
    }

    private int direction = 0;
    private String imagePath;
    private Radar radar;
    private double speed;
    private boolean isMove;
    private boolean isCrashed;
    private double steering = 0;
    private double velocityX = 0;
    private double velocityY = 0;
    private double rotation=0;
    private int x;
    private int y;
    private int width;
    private int height;
    UserCarControlling controlling;
    Bus bus;
    VehicleDynamics dynamics;

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }

    public UserCar(int x, int y) {
        this.width = 100;
        this.height = 240;
        this.x = x;
        this.y = y;
        controlling = new UserCarControlling(this);
        setImagePath("car.png");
        bus = Bus.getInstance();
        dynamics = VehicleDynamics.GetInstance();
    }

    public void AccelerateAuto2(int howmuch)
    {
        bus.setBrakePedal(0);
        bus.setGearPosition(Bus.GearPosition.DRIVE);
        bus.setGasPedal(5);
        dynamics.update();
    }

    public void AccelerateAuto(int howmuch){
        setSpeed(getSpeed() + howmuch);
        this.setMove(true);
    }

    public void DecreaseAuto(int howmancs) {
        double aktSebesseg=getSpeed()-howmancs;
        if (aktSebesseg > 0) {
            setSpeed(aktSebesseg);
            this.setMove(true);
        }
        else
            this.setMove(false);
    }

    public void DecreaseSpeed() {
        while (getSpeed() > 0) {
            setSpeed(getSpeed() - 1);
            this.setMove(true);
        }
    }

    public void Move(int verticalMovement, int horizontalMovement) {
        setXCoord(getX() + horizontalMovement);
        setYCoord(getY() + verticalMovement);
        this.setMove(true);
    }

    public void startAuto(int initialSpeed){
        this.setSpeed(initialSpeed);
        this.setMove(true);
    }

    public void Crashed()
    {
        this.setCrashed(true);
    }

    public boolean isOnRoadObject()
    {
        XMLParserMain parser = XMLParserMain.getInstance();
        List<WorldObject> objectsList = null;
        try
        {
            objectsList = parser.getWorld();
        }
        catch(XMLStreamException e)
        {
            System.out.println("UserCar IsOnRoadObject() nem kap világot!");
        }
        boolean onRoad = false;
        int halfOfCarWidth = this.getWidth()/2;
        int[] carCenterPoint = this.getCenterPoint();
        int[] roadSize;
        double[] roadTransform;

        for(WorldObject wo: objectsList)
        {
            if((wo instanceof RoadTile))
            {
                roadTransform = wo.getTransform();
                int[] roadPosition = wo.getPosition();
                int roadX = roadPosition[0];
                int roadY = roadPosition[1];
                int[] roadPos = {roadX, roadY};
                int roadWidth = wo.getWidth();
                int roadHeight = wo.getHeight();

                //int[] leftUpper = {roadX + halfOfCarWidth, roadY};
                //int[] leftBottom = {roadX + halfOfCarWidth,roadY + roadHeight};
                //int[] rightUpper = {roadX + roadWidth - halfOfCarWidth, roadY};
                //int[] rightBottom = {roadX + roadWidth - halfOfCarWidth, roadY + roadHeight}

                int[] leftUpper = {roadX, roadY};
                int[] leftBottom = {roadX,roadY + roadHeight};
                int[] rightUpper = {roadX + roadWidth, roadY};
                int[] rightBottom = {roadX + roadWidth, roadY + roadHeight};

                leftUpper = transformCoordinate(leftUpper, roadTransform, roadPos);
                leftBottom = transformCoordinate(leftBottom, roadTransform, roadPos);
                rightUpper = transformCoordinate(rightUpper, roadTransform, roadPos);
                rightBottom = transformCoordinate(rightBottom, roadTransform, roadPos);

                if(
                        ((carCenterPoint[0]>=leftUpper[0] && carCenterPoint[0]<=rightBottom[0])
                                ||
                                (carCenterPoint[0]>=leftBottom[0] && carCenterPoint[0]<=rightUpper[0]))
                                &&
                                ((carCenterPoint[1]>=leftUpper[1] && carCenterPoint[1]<=rightBottom[1])
                                        ||
                                        (carCenterPoint[1]<=leftBottom[1] && carCenterPoint[1]>=rightUpper[1]))
                        )//end if
                {
                    onRoad=true;
                    this.setCrashed(false);
                }
            }//if(wo instanceof ...) end
        } //for ciklus vége
        if(onRoad==false)
        {
            this.setCrashed(true);
            System.out.println("Crashed whit carCenterPoint: " + Arrays.toString(carCenterPoint));
        }
        return onRoad;
    }

    private int[] transformCoordinate(int[] coordinate, double[] transform, int[] roadPos)
    {
        int[] coords = {coordinate[0]-roadPos[0], coordinate[1]-roadPos[1]};
        int[] newCoordinate = new int[2];
        newCoordinate[0] = (int)(((coords[0]*transform[0])-(coords[1]*transform[1]))+roadPos[0]);
        newCoordinate[1] = (int)(((coords[0]*transform[2])+(coords[1]*transform[3]))+roadPos[1]);
        return newCoordinate;
    }

    public int[] getCenterPoint() {
        int[] position = new int[]{this.getX(), this.getY()};
        int[] centerPoint = new int[2];
        double[] transformation = new double[]{Math.cos(this.getRotation()), (Math.sin(this.getRotation())*-1), Math.sin(this.getRotation()), Math.cos(this.getRotation())};

        int felSzelesseg = this.getWidth() / 2;
        int felMagassag = this.getHeight() / 2;

        centerPoint[0] = (int) (felSzelesseg * transformation[0] + felMagassag * transformation[1]);
        centerPoint[1] = (int) (felSzelesseg * transformation[2] + felMagassag * transformation[3]);

        centerPoint[0]+=position[0];
        centerPoint[1]+=position[1];
        return centerPoint;
    }

}
