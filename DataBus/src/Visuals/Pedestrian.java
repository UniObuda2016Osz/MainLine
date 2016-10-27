package Visuals;

/**
 * Created by preil on 2016. 10. 10..
 */
public class Pedestrian {
    private double startXPos;
    private double startYPos;
    private double endXPos;
    private double endYPos;
    private String imagePath;
    private double xPos;
    private double yPos;

    private double directionX;
    private double directionY;
    private double distance;

    public Pedestrian(double startXPos, double startYPos, double endXPos, double endYPos) {
        this.startXPos=startXPos;
        this.endXPos=endXPos;
        this.startYPos=startYPos;
        this.endYPos=endYPos;
        xPos = startXPos;
        yPos = startYPos;
        imagePath = "./ref/pedestrian.png";

        distance = Math.sqrt(Math.pow(endXPos-startXPos,2)+Math.pow(endYPos-startYPos,2));
        directionX = (endXPos-startXPos) / distance;
        directionY = (endYPos-startYPos) / distance;
    }

    public void DirectionChange() {
        directionX = -directionX;
        directionY = -directionY;
    }

    public void Move() {
        double speed = 1;
        xPos = getXPos() + directionX * speed;
        yPos = getYPos() + directionY * speed;
        if((Math.sqrt(Math.pow(getXPos() -startXPos,2)+Math.pow(getYPos() -startYPos,2)) >= distance) ||
                (Math.sqrt(Math.pow(getXPos() -endXPos,2)+Math.pow(getYPos() -endYPos,2)) >= distance)) {
            DirectionChange();
        }
    }

    public String getImagePath() {
        return imagePath;
    }

    public double getXPos() {
        return xPos;
    }

    public double getYPos() {
        return yPos;
    }
}
