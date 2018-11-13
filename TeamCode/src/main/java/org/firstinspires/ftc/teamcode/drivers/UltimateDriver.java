package org.firstinspires.ftc.teamcode.drivers;
import java.util.Math;

public class UltimateDriver extends Mecanum4WheelDriver {
    private double vfl;//vfl = "velocity front left" wheel before adding rotational movement; vbl, vfr, and vbr follow the same scheme
    private double vbl;
    private double vfl;
    private double vbl;
    private double vflFinal;//velocity of a wheel AFTER rotational movement is added
    private double vblFinal;
    private double vfrFinal;
    private double vbrFinal;
    private double robotAngle;
    public void autoDrive(double angleInRaidans, double speed) {//move the robot in any straight direction in any given angle (in radians) and speed (0 to 1)
        //0 radians is moving the robot straight ahead (+y direction) angleInRadians changes counter-clockwise from 0; @param angleInRadians must be [0, 6.28) radians
        if (angleInRaidans >= 0 || angleInRaidans < 2*Math.PI) {//if @param angleInRadians falls within 0 to 2pi
            robotAngle = angleInRaidans+(Math.PI/4);
            vfl = 3(speed * Math.cos(robotAngle))/4;
            vbl = 3(speed * Math.sin(robotAngle))/4;
            vfr = 3(speed * Math.sin(robotAngle))/4;
            vbr = 3(speed * Math.cos(robotAngle))/4;
            motorSet(vfl, vbl, vfr, vbr);
        }
    }
    public void manualDrive(double gamepadX, double gamepadY){//put in gamepad inputs for gamepadX and gamepadY
        double hypotenuse = Math.hypot(gamepadX, gamepad1Y);//finds the overall velocity desired based on the controller inputs
        robotAngle = Math.atan2(gamepadY, gamepadX) - Math.PI / 4;//turns the angle pi/4 clockwise to adjust for the orientation of the mecanum wheels
        vfl = 3(hypotenuse * Math.cos(robotAngle))/4;
        vbl = 3(hypotenuse * Math.sin(robotAngle))/4;
        vfr = 3(hypotenuse * Math.sin(robotAngle))/4;
        vbr = 3(hypotenuse * Math.cos(robotAngle))/4;
        motorSet(vfl, vbl, vfr, vbr);
    }
    public static void autoTurn(boolean turnLeft, boolean turnRight){//turn the robot in place; combined with motion it will turn?
        if (turnLeft&&!turnRight){
            vflFinal, vblFinal = vf1-.25;//.25 is the amount out of 1 that the wheels dedicate to rotation instead of directional movement
            vfrFinal, vbrFinal = vfr+.25;
            motorSet(vflFinal, vblFinal, vfrFinal, vbrFinal);
        }
        if (turnRight&&!turnLeft){
            vflFinal, vblFinal = vf1+.25;
            vfrFinal, vbrFinal = vfr-.25;
            motorSet(vflFinal, vblFinal, vfrFinal, vbrFinal);
        }
    }
    public void manualTurn(boolean left_bumper, boolean right_bumper){
        autoTurn(left_bumper, right_bumper);
    }
}
