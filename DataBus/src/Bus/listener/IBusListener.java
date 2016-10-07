package Bus.listener;

import Bus.Bus;

/**
 * Created by levair on 2016.10.07..
 */
public interface IBusListener {

    public void onCurrentSISpeedChanged(float currentSISpeed);
    public void onACCMainSwitchStateChanged(boolean ACCMainSwitchState);
    public void onGasPedalChanged(int gasPedal);
    public void onBrakePedalChanged(int brakePedal);
    public void onSteeringWheelAngleChanged(int steeringWheelAngle);
    public void onDirectionIndicatorLeftActiveChanged(boolean directionIndicatorLeftActive);
    public void onDirectionIndicatorRightActiveChanged(boolean directionIndicatorRightActive);
    public void onGearPositionChanged(Bus.GearPosition gearPosition);

}
