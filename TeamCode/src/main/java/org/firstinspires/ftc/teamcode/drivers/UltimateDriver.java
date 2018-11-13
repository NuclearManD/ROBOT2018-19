package org.firstinspires.ftc.teamcode.drivers;
import java.util.Math;

public class UltimateDriver extends Mecanum4WheelDriver {
    private double vfl;//vfl = "velocity front left" wheel; vbl, vfr, and vbr follow the same scheme
    private double vbl;
    private double vfl;
    private double vbl;
    private double robotAngle;
    public void autoDrive(double angleInRaidans, double speed) {//move the robot in any straight direction in any given angle (in radians) and speed (0 to 1)
        //0 radians is moving the robot straight ahead (+y direction) angleInRadians changes counter-clockwise from 0; @param angleInRadians must be [0, 6.28) radians
        if (angleInRaidans >= 0 || angleInRaidans < 2*Math.PI) {//if @param angleInRadians falls within 0 to 2pi
            robotAngle = angleInRaidans+(Math.PI/4);
            vfl = (speed * Math.cos(robotAngle))/2;
            vbl = (speed * Math.sin(robotAngle))/2;
            vfr = (speed * Math.sin(robotAngle))/2;
            vbr = (speed * Math.cos(robotAngle))/2;
            motorSet(vfl, vbl, vfr, vbr);
        }
    }
    public void manualDrive(double gamepadX, double gamepadY){//put in gamepad inputs for gamepadX and gamepadY
        double hypotenuse = Math.hypot(gamepadX, gamepad1Y);//finds the overall velocity desired based on the controller inputs
        robotAngle = Math.atan2(gamepadY, gamepadX) - Math.PI / 4;//turns the angle pi/4 clockwise to adjust for the orientation of the mecanum wheels
        vfl = (hypotenuse * Math.cos(robotAngle))/2;
        vbl = (hypotenuse * Math.sin(robotAngle))/2;
        vfr = (hypotenuse * Math.sin(robotAngle))/2;
        vbr = (hypotenuse * Math.cos(robotAngle))/2;
        motorSet(vfl, vbl, vfr, vbr);
    }
    public static void autoTurn(boolean turnLeft, boolean turnRight){//turn the robot in place; combined with motion it will turn?
        if (turnLeft&&!turnRight){
            vfl, vbl += .5;
            vfr, vbr += -.5;
            motorSet(vfl, vbl, vfr, vbr);
        }
        if (turnRight&&!turnLeft){
            vfl, vbl += -.5;
            vfr, vbr += .5;
            motorSet(vfl, vbl, vfr, vbr);
        }
    }
    public void manualTurn(boolean left_bumper, boolean right_bumper){
        autoTurn(left_bumper, right_bumper);
    }
}
