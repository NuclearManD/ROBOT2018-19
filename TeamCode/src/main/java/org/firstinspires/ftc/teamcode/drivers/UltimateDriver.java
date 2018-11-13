package org.firstinspires.ftc.teamcode.drivers;
import java.util.Math;

public class UltimateDriver extends Mecanum4WheelDriver {
    /*public void motorUpdate(){
        fl.setPower(y+x+R);
        bl.setPower(y-x+R);
        fr.setPower(-(y-x-R));
        br.setPower(-(y+x-R));
    }*/
    public void linearDrive(double angleInRaidans, double speed) {//move the robot in any straight direction in any given angle (in radians) and speed (0 t0 1)
        double velocityX = 0;
        double velocityY = 0;
        //0 radians is moving the robot straight ahead (+y direction) angleInRadians changes counter-clockwise from 0; @param angleInRadians must be [0, 6.28) radians
        if (angleInRaidans >= 0 || angleInRaidans < 2*Math.PI) {//if @param angleInRadians falls within 0 to 2pi
            velocityX = speed*Math.cos(angleInRaidans+((Math.PI)/2));//breaks velocity into the x component
            velocityY = speed*Math.sin(angleInRaidans+((Math.PI)/2));//breaks velocity into the y component
        }
        fl.setPower();//front left wheel speed
        bl.setPower();//back left wheel speed
        fr.setPower();//front right wheel speed
        br.setPower();//back right wheel speed
        //set motors to match the direction desired
    }
    public void turn(double radius, boolean direction, double speed){//turn the robot in a curve
        //set motors to match the turn desired
    }
}
