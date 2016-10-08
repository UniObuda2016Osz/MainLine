package Dynamics;

import org.junit.Test;
import static org.junit.Assert.assertEquals;

/**
 * Created by BJudit on 2016. 10. 08..
 */
public class VehicleDynamicsTest {
    VehicleDynamics VD = VehicleDynamics.GetInstance();

    @org.junit.Before
    public void setUp() throws Exception {
    }

    @org.junit.Test
    public void isVehicleDynamicsSingleton()
    {
        VehicleDynamics first = VehicleDynamics.GetInstance();
        VehicleDynamics second = VehicleDynamics.GetInstance();

        assertEquals(first == second, true);
    }




    @org.junit.After
    public void tearDown() throws Exception {
        /*GCC will remove bus*/
    }
}
