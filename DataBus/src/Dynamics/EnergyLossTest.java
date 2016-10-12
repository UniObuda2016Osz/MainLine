package Dynamics;

import org.junit.Test;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;

/**
 * Created by fayez on 10/12/2016.
 */
public class EnergyLossTest {
    EnergyLoss energyLoss = EnergyLoss.GetInstance();

    @org.junit.Test
    public void IsEnergyLossSingleton()
    {
        EnergyLoss first = EnergyLoss.GetInstance();
        EnergyLoss second = EnergyLoss.GetInstance();

        assertEquals(first == second, true);
    }

    @Test
    public void GroundTypeGetterReturnsSetterGivenValue(){
        energyLoss.setGroundType(EnergyLoss.GroundType.Soft);
        assertEquals(EnergyLoss.GroundType.Soft, energyLoss.getGroundType());
        energyLoss.setGroundType(EnergyLoss.GroundType.Hard);
        assertEquals(EnergyLoss.GroundType.Hard, energyLoss.getGroundType());
    }

    @Test
    public void WeightGetterReturnsSetterGivenValue(){
        energyLoss.setWeight(77);
        assertEquals(energyLoss.getWeight(),77);
        energyLoss.setWeight(150);
        assertEquals(energyLoss.getWeight(),100);
    }

    @Test
    public void WindEnergyGetterReturnsSetterGivenValue(){
        energyLoss.setWindEnergy(77);
        assertEquals(energyLoss.getWindEnergy(),77);
        energyLoss.setWindEnergy(150);
        assertEquals(energyLoss.getWindEnergy(),100);
    }

    @Test
    public void CollisionGetterReturnsSetterGivenValue(){
        energyLoss.setCollision(true);
        assertEquals(energyLoss.isCollision(),true);
    }

}
