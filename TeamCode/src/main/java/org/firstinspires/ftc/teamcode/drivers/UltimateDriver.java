package org.firstinspires.ftc.teamcode.drivers;

import java.lang.Math;

public class UltimateDriver extends Mecanum4WheelDriver {
    private double vfl;//vfl = "velocity front left" wheel before adding rotational movement; vbl, vfr, and vbr follow the same scheme
    private double vbl;
    private double vfr;
    private double vbr;
    private double vflFinal;//velocity of a wheel AFTER rotational movement is added
    private double vblFinal;
    private double vfrFinal;
    private double vbrFinal;
    private double robotAngle;
    public void autoDrive(double angleInDegrees, double speed) {//move the robot in any straight direction in any given angle (in degrees) and speed (0 to 1)
        double angleInRadians = angleInDegrees*2*Math.PI/360;//converts degrees to radians
        //0 radians is moving the robot straight ahead (+y direction) angleInRadians changes counter-clockwise from 0; @param angleInRadians must be [0, 6.28) radians
        if (angleInRadians >= 0 && angleInRadians < 2*Math.PI) {//if @param angleInRadians falls within 0 to 2pi
            robotAngle = angleInRadians+(Math.PI/4);
            vfl = .75*(speed * Math.cos(robotAngle));
            vbl = .75*(speed * Math.sin(robotAngle));
            vfr = .75*(speed * Math.sin(robotAngle));
            vbr = .75*(speed * Math.cos(robotAngle));
            motorSet(vfl, vbl, vfr, vbr);
        }
    }
    public void manualDrive(double gamepadX, double gamepadY){//put in gamepad inputs for gamepadX and gamepadY
        double hypotenuse = Math.hypot(gamepadX, gamepadY);//finds the overall velocity desired based on the controller inputs
        robotAngle = Math.atan2(gamepadY, gamepadX) - Math.PI / 4;//turns the angle pi/4 clockwise to adjust for the orientation of the mecanum wheels
        vfl = .75*(hypotenuse * Math.cos(robotAngle));
        vbl = .75*(hypotenuse * Math.sin(robotAngle));
        vfr = .75*(hypotenuse * Math.sin(robotAngle));
        vbr = .75*(hypotenuse * Math.cos(robotAngle));
        motorSet(vfl, vbl, vfr, vbr);
    }
    public void autoTurn(boolean turnLeft, boolean turnRight){//turn the robot in place; combined with motion it will turn?
        if (turnLeft&&!turnRight){
            vflFinal = vfl-.25;//.25 is the amount out of 1 that the wheels dedicate to rotation instead of directional movement
            vblFinal = vbl-.25;
            vfrFinal = vfr+.25;
            vbrFinal = vbr+.25;
            motorSet(vflFinal, vblFinal, vfrFinal, vbrFinal);
        }
        if (turnRight&&!turnLeft){
            vflFinal = vfl-.25;
            vblFinal = vbl-.25;
            vfrFinal = vfr+.25;
            vbrFinal = vbr+.25;
            motorSet(vflFinal, vblFinal, vfrFinal, vbrFinal);
        }
    }
    public void manualTurn(boolean left_bumper, boolean right_bumper){
        autoTurn(left_bumper, right_bumper);
    }
}
