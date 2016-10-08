package Visuals;

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
    private int speed;
    private boolean isMove;
    private boolean isCrashed;

    public String getImagePath() {
        return imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }

    public int getXCoord() {
        return xCoord;
    }

    public void setxCoord(int xCoord) {
        this.xCoord = xCoord;
    }

    public int getYCoord() {
        return yCoord;
    }

    public void setyCoord(int yCoord) {
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

    public int getSpeed() {
        return speed;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }

    public Car() {
        length = 240;
        width = 100;
        imagePath = "car.png";
        xCoord = 0;
        yCoord = 0;
        this.speed = 0;
        this.isMove = false;
        this.isCrashed = false;
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

    public void Move(int verticalMovement, int horizontalMovement) {
        xCoord += horizontalMovement;
        yCoord += verticalMovement;
    }

    public void RotateCar(int rotationDegree) {
        direction += rotationDegree;
    }

    public void startAuto(int initialSpeed){
        this.speed = initialSpeed;
        this.isMove = true;
    }

    public void stopAuto(){
        this.speed = 0;
        this.isMove = false;
    }

    public void crash(){
        this.isCrashed = true;
    }

}
