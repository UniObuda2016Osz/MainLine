package hu.oe.nik.autonomouscar.Visuals.HMI.instrument;

/**
 * Created by levair on 2016.10.07..
 */
public class CarInstrumentContainer {

    private Pedal gasPedal;
    private Pedal brakePedal;
    private SteeringWheel steeringWheel;

    private static CarInstrumentContainer instance = null;

    public static CarInstrumentContainer singleton(){
        if(instance == null)
            instance = new CarInstrumentContainer();

        return instance;
    }

    private CarInstrumentContainer(){
        this.setGasPedal(new Pedal());
        this.setBrakePedal(new Pedal());
        this.setSteeringWheel(new SteeringWheel());
    }


    public Pedal getGasPedal() {
        return gasPedal;
    }

    public void setGasPedal(Pedal gasPedal) {
        this.gasPedal = gasPedal;
    }

    public Pedal getBrakePedal() {
        return brakePedal;
    }

    public void setBrakePedal(Pedal brakePedal) {
        this.brakePedal = brakePedal;
    }

    public SteeringWheel getSteeringWheel() {
        return steeringWheel;
    }

    public void setSteeringWheel(SteeringWheel steeringWheel) {
        this.steeringWheel = steeringWheel;
    }
}
