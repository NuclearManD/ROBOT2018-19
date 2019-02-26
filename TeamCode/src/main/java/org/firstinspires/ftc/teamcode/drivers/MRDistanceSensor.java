package org.firstinspires.ftc.teamcode.drivers;

import com.qualcomm.hardware.modernrobotics.ModernRoboticsI2cRangeSensor;

import org.firstinspires.ftc.robotcore.external.navigation.DistanceUnit;

public class MRDistanceSensor {

    public ModernRoboticsI2cRangeSensor sensor;
    public void setup(ModernRoboticsI2cRangeSensor sensor){
        this.sensor=sensor;
    }
    public double getDistance(){
        return sensor.getDistance(DistanceUnit.CM);
    }
}
