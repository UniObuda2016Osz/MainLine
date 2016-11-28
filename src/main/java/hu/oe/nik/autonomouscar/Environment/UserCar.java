package hu.oe.nik.autonomouscar.Environment;

import hu.oe.nik.autonomouscar.Sensors.Radar.Radar;





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


}
