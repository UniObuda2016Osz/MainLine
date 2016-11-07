package hu.oe.nik.autonomouscar.Visuals;

/**
 * Created by preil on 2016. 10. 05..
 */
public class Car {
    private String imagePath;
    private int xCoord;
    private int yCoord;
    private int width;
    private int length;
    private int direction;
    private double speed;
    private boolean isMove;
    private boolean isCrashed;
    private double steering = 0;
    private double velocityX = 0;
    private double velocityY = 0;
    private double rotation=0;

    public String getImagePath() {
        return imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }


    public Car(int xCord, int yCord) {
        setLength(50);
        setWidth(50);
        // TODO will not work because the image file is moved to the resources folder
        setImagePath("./ref/car.png");
        setXCoord(xCord);
        setYCoord(yCord);
        this.setSpeed(0);
        this.setMove(false);
        this.setCrashed(false);
        this.setDirection(45);
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

    public int getXCoord() {
        return xCoord;
    }

    public void setXCoord(int xCoord) {
        this.xCoord = xCoord;
    }

    public int getYCoord() {
        return yCoord;
    }

    public void setYCoord(int yCoord) {
        this.yCoord = yCoord;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getLength() {
        return length;
    }

    public void setLength(int length) {
        this.length = length;
    }

    public int getDirection() {
        return direction;
    }

    public void setDirection(int direction) {
        this.direction = direction;
    }

    public double getSpeed() {
        return speed;
    }

    public void setSpeed(double speed) {
        this.speed = speed;
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

    public void accelerateAuto(int howmuch){
        setSpeed(getSpeed() + howmuch);
        this.setMove(true);
    }

    public void Move(int verticalMovement, int horizontalMovement) {
        setXCoord(getXCoord() + horizontalMovement);
        setYCoord(getYCoord() + verticalMovement);
        this.setMove(true);
    }
    public void CalcNextPosition(int elore, int hatra, int balra, int jobbra){
        // Ez még nincs kidolgozva.
    }
    public void RotateCar(int rotationDegree) {
        setDirection(getDirection() + rotationDegree);
    }

    public void startAuto(int initialSpeed){
        this.setSpeed(initialSpeed);
        this.setMove(true);
    }

    public void stopAuto(){
        this.setSpeed(0);
        this.setMove(false);
    }

    public void crash(){
        this.setCrashed(true);
    }

}
