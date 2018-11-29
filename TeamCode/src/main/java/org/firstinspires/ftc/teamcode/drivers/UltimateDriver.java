package org.firstinspires.ftc.teamcode.drivers;

import java.lang.Math;
import com.qualcomm.robotcore.hardware.DcMotor;

public class UltimateDriver extends Mecanum4WheelDriver {
    public DcMotor fl=null;
    public DcMotor fr=null;
    public DcMotor bl=null;
    public DcMotor br=null;
    private double vfl=0;//vfl = "velocity front left" wheel before adding rotational movement; vbl, vfr, and vbr follow the same scheme
    private double vbl=0;
    private double vfr=0;
    private double vbr=0;
    private double vflRot=0;//velocity of a wheel AFTER rotational movement is added
    private double vblRot=0;
    private double vfrRot=0;
    private double vbrRot=0;
    public void init(DcMotor[] l){
        fl = l[0];
        fr = l[1];
        bl = l[2];
        br = l[3];
    }
    public void motorUpdate(){
        fl.setPower((vfl+vflRot));
        bl.setPower((vbl+vblRot));
        fr.setPower(-(vfr+vfrRot));
        br.setPower(-(vbr+vbrRot));
    }
    public void autoDrive(double angleInDegrees, double speed) {//move the robot in any straight direction in any given angle (in degrees) and speed (0 to 1)
        if (angleInDegrees >= 0 && angleInDegrees < 360) {//if @param angleInDegrees falls within 0 to 360 degrees
            double robotAngle = angleInDegrees+45;
            double angleInRadians = robotAngle*2*(Math.PI)/360;//converts degrees to radians
            vfl = .75*(speed * (Math.cos(angleInRadians)));//front left wheel
            vbl = .75*(speed * (Math.sin(angleInRadians)));//back left wheel
            vfr = .75*(speed * (Math.sin(angleInRadians)));//front right wheel
            vbr = .75*(speed * (Math.cos(angleInRadians)));//back right wheel
            motorUpdate();
        }
    }
    public void manualDrive(float gamepadX, float gamepadY){//put in gamepad inputs for gamepadX and gamepadY
        double hypotenuse = Math.hypot(gamepadX, gamepadY);//finds the overall velocity desired based on the controller inputs
        double robotAngle = Math.atan2(gamepadY, gamepadX) - Math.PI / 4;//turns the angle pi/4 clockwise to adjust for the orientation of the mecanum wheels
        vfl = .75*(hypotenuse * (Math.cos(robotAngle)));
        vbl = .75*(hypotenuse * (Math.sin(robotAngle)));
        vfr = .75*(hypotenuse * (Math.sin(robotAngle)));
        vbr = .75*(hypotenuse * (Math.cos(robotAngle)));
        motorUpdate();
    }
    public void autoTurn(boolean turnLeft, boolean turnRight){//turn the robot in place; combined with motion it will turn?
        if (turnLeft&&!turnRight){
            vflRot = -.25;//.25 is the amount out of 1 that the wheels dedicate to rotation instead of directional movement
            vblRot = -.25;
            vfrRot = .25;
            vbrRot = .25;
            motorUpdate();
        }
        if (turnRight&&!turnLeft){
            vflRot = .25;
            vblRot = .25;
            vfrRot = -.25;
            vbrRot = -.25;
            motorUpdate();
        }
    }
    public void manualTurn(float left_trigger, float right_trigger){
        if (left_trigger>right_trigger){
            vflRot = -.25*left_trigger;//.25 is the amount out of 1 that the wheels dedicate to rotation instead of directional movement
            vblRot = -.25*left_trigger;
            vfrRot = .25*left_trigger;
            vbrRot = .25*left_trigger;
            motorUpdate();
        }
        if (right_trigger>left_trigger) {
            vflRot = .25 * right_trigger;
            vblRot = .25 * right_trigger;
            vfrRot = -.25 * right_trigger;
            vbrRot = -.25 * right_trigger;
            motorUpdate();
        }
    }
    public void update() {}
}
