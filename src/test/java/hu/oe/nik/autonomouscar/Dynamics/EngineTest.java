package hu.oe.nik.autonomouscar.Dynamics;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Created by fayez on 10/12/2016.
 */
public class EngineTest {

    Engine engine = Engine.GetInstance();

    @org.junit.Test
    public void IsEngineSingleton()
    {
        Engine first = Engine.GetInstance();
        Engine second = Engine.GetInstance();

        assertEquals(first == second, true);
    }

    @Test
    public void HorsePowerGetterReturnsSetterGivenValue(){
        engine.setHorsePower(10);
        assertEquals(engine.getHorsePower(), 10);
    }

    @Test
    public void AccelerationGetterReturnsSetterGivenValue(){
        engine.setAcceleration(35);
        assertEquals(engine.getAcceleration(), 35);
    }
}
