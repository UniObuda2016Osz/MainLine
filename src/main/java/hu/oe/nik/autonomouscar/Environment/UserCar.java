package hu.oe.nik.autonomouscar.Environment;

import hu.oe.nik.autonomouscar.Sensors.Radar;





/**
 * Created by Akos on 1991. 11. 02..
 */
public class UserCar {

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

    private int direction;
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

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }

    public UserCar(int x, int y) {
        this.x=x;
        this.y=y;
        this.direction = direction;
    }

    public void AccelerateAuto(int howmuch){
        setSpeed(getSpeed() + howmuch);
        this.setMove(true);
    }

    public void DecreaseAuto(int howmancs) {
        if (getSpeed() > 0) {
            setSpeed(getSpeed() - howmancs);
            this.setMove(true);
        }
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
