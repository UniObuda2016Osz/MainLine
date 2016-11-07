package hu.oe.nik.autonomouscar.Dynamics;
/**
 * Created by fayez on 10/12/2016.
 */
public class EnergyLoss {

    Engine engine = new Engine();
    private static EnergyLoss instance;

    public static EnergyLoss GetInstance(){
        if(instance==null){
            instance=new EnergyLoss();
        }
        return instance;
    }

    public enum GroundType{
        Soft,Hard
    }

    public GroundType getGroundType() {
        return Groundtype;
    }

    public void setGroundType(GroundType groundtype) {
        this.Groundtype = groundtype;
    }

    private GroundType Groundtype;

    private int Weight;

    public int getWeight() {
        return Weight;
    }

    public void setWeight(int weight) {
        if(weight< 100 && weight > 0)
            this.Weight = weight;
        else if(weight > 100)
            this.Weight = 100;
        else
            this.Weight = 0;
    }

    private int WindEnergy;

    public int getWindEnergy() {
        return WindEnergy;
    }

    public void setWindEnergy(int windenergy) {
        if(windenergy< 100 && windenergy > 0)
            this.WindEnergy = windenergy;
        else if(windenergy > 100)
            this.WindEnergy = 100;
        else
            this.WindEnergy = 0;
    }

    private boolean collision = false;

    public boolean isCollision() {
        return collision;
    }

    public void setCollision(boolean collision) {
        this.collision = collision;
    }



    public int LossFromWind()
    {
        int loss = getWindEnergy();
        return loss;
    }

    public int LossFromGround()
    {
        int loss=0;
        GroundType ground = getGroundType();
        if ( ground == GroundType.Soft){loss= 1; }
        else if (ground == GroundType.Hard){loss=2;}

        return loss;
    }

    public int LossFromWeight()
    {
        int loss = getWeight();
        return loss;
    }

    public int LossFromCollision()
    {
        int loss=0;
        if (this.collision){ loss = engine.getAcceleration() * 100 ;}

        return loss;
    }






}
