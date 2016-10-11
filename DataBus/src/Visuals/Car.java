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

    public void turnRight()
    {
        if (direction >= 15){
            direction = 0;
        }
        else {
            direction += 1;
        }
    }

    public void turnLeft()
    {
        if (direction <= 0){
            direction = 16;
        }
        else {
            direction -= 1;
        }
    }

    public int getSpeed() {
        return speed;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }

    public Car(int xCord, int yCord) {
        length = 50;
        width = 50;
        imagePath = "./ref/car.png";
        xCoord = xCord;
        yCoord = yCord;
        this.speed = 0;
        this.isMove = false;
        this.isCrashed = false;
        direction = 0;
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

    public void accelerateAuto(int howmuch){
        speed+=howmuch;
        this.isMove = true;
    }

    public void MoveForward() {
        switch (direction){
            case 0:{
                yCoord-=4;
                break;
            }
            case 1:{
                xCoord+=1;
                yCoord-=3;
                break;
            }
            case 2:{
                xCoord+=2;
                yCoord-=2;
                break;
            }
            case 3:{
                xCoord+=3;
                yCoord-=1;
                break;
            }
            case 4:{
                xCoord+=4;
                yCoord-=0;
                break;
            }
            case 5:{
                xCoord+=3;
                yCoord+=1;
                break;
            }
            case 6:{
                xCoord+=2;
                yCoord+=2;
                break;
            }
            case 7:{
                xCoord+=1;
                yCoord+=3;
                break;
            }
            case 8:{
                yCoord+=4;
                break;
            }
            case 9:{
                xCoord-=1;
                yCoord+=3;
                break;
            }
            case 10:{
                xCoord-=2;
                yCoord+=2;
                break;
            }
            case 11:{
                xCoord-=3;
                yCoord+=1;
                break;
            }
            case 12:{
                xCoord-=4;
                break;
            }
            case 13:{
                xCoord-=3;
                yCoord-=1;
                break;
            }
            case 14:{
                xCoord-=2;
                yCoord-=2;
                break;
            }
            case 15:{
                xCoord-=1;
                yCoord-=3;
                break;
            }
        }
    }

    public void MoveBack() {
        switch (direction){
            case 0:{
                yCoord+=4;
                break;
            }
            case 1:{
                xCoord-=1;
                yCoord+=3;
                break;
            }
            case 2:{
                xCoord-=2;
                yCoord+=2;
                break;
            }
            case 3:{
                xCoord-=3;
                yCoord+=1;
                break;
            }
            case 4:{
                xCoord-=4;
                yCoord+=0;
                break;
            }
            case 5:{
                xCoord-=3;
                yCoord-=1;
                break;
            }
            case 6:{
                xCoord-=2;
                yCoord-=2;
                break;
            }
            case 7:{
                xCoord-=1;
                yCoord-=3;
                break;
            }
            case 8:{
                yCoord-=4;
                break;
            }
            case 9:{
                xCoord+=1;
                yCoord-=3;
                break;
            }
            case 10:{
                xCoord+=2;
                yCoord-=2;
                break;
            }
            case 11:{
                xCoord+=3;
                yCoord-=1;
                break;
            }
            case 12:{
                xCoord+=4;
                break;
            }
            case 13:{
                xCoord+=3;
                yCoord+=1;
                break;
            }
            case 14:{
                xCoord+=2;
                yCoord+=2;
                break;
            }
            case 15:{
                xCoord+=1;
                yCoord+=3;
                break;
            }
        }
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
