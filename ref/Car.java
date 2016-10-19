/**
 * Created by preil on 2016. 10. 05..
 */
public class Car {
    private String imagePath;
    private int xCoord;
    private int yCoord;
    private int width;
    private int length;
    private int facing;

    public String getImagePath() {
        return imagePath;
    }

    public int getXCoord() {
        return xCoord;
    }

    public int getYCoord() {
        return yCoord;
    }

    public int getWidth() {
        return width;
    }

    public int getLength() {
        return length;
    }

    public int getFacing() {
        return facing;
    }

    private Car() {
        length = 20;
        width = 10;
        imagePath = "imagePath";
        xCoord = 0;
        yCoord = 0;
    }

    public void Move(int verticalMovement, int horizontalMovement) {
        xCoord += horizontalMovement;
        yCoord += verticalMovement;
    }

    public void RotateCar(int rotationDegree) {
        facing += rotationDegree;
    }
}
