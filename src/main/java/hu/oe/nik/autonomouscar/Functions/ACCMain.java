package hu.oe.nik.autonomouscar.Functions;

/**
 * Created by ral2bp on 2016.09.29..
 */
public class ACCMain {

    private double targetSpeed;
    private double timegap;
    private double acceleration;
    private boolean isAccOn;

    public ACCMain(double actualSpeed){
        this.timegap = 1.0;
        setTargetSpeed(actualSpeed);
        this.acceleration = 0.0;
        this.isAccOn = false;
    }

    public double getTargetSpeed(){
        return this.targetSpeed;
    }

    public void setTargetSpeed(double targetSpeed) {
        if(targetSpeed >= 30.0 && targetSpeed <= 180.0){
            this.targetSpeed = targetSpeed;
        } else if (targetSpeed < 30.0) {
            this.targetSpeed = 30.0;
        } else {
            this.targetSpeed = 180.0;
        }

    }

    public double getTimegap() {
        return timegap;
    }

    /**
     * If the passed parameter is above 0, this method will increase the timegap up to 2.0 with 0.2 steps,
     * if the passed parameter is below 0, this method will decrease the timegap down to 1.0 with 0.2 steps.
     * @param timegap
     */
    public void setTimegap(double timegap) {
        if(timegap > 0 && this.timegap < 2.0){
            this.timegap += 0.2;
        } else if(timegap < 0 && this.timegap > 1.0){
            this.timegap -= 0.2;
        }
    }

    public double getAcceleration() {
        return acceleration;
    }

    /**
     * If the passed parameter is above 0, this method will increase the acceleration up to 3.5 m/s2 with 1.0 steps,
     * if the passed parameter is below 0, this method will decrease the acceleration down to -3.5 m/s2 with 1.0 steps.
     * @param acceleration
     */
    public void setAcceleration(double acceleration) {
        if(acceleration > 0 && this.acceleration < 3.5){
            this.acceleration += 1.0;
        } else if (acceleration < 0 && this.acceleration > -3.5){
            this.acceleration -= 1.0;
        }
    }

    public boolean isAccOn() {
        return isAccOn;
    }

    public void setAccOn() {
        this.isAccOn = true;
    }

    public void setAccOff() {
        this.isAccOn = false;
    }

    @Override
    public String toString() {
        return "ACCMain{" +
                ", targetSpeed=" + targetSpeed +
                ", timegap=" + timegap +
                ", acceleration=" + acceleration +
                ", isAccOn=" + isAccOn +
                '}';
    }
}
