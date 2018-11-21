package org.firstinspires.ftc.teamcode.drivers;

import com.qualcomm.robotcore.hardware.DcMotor;

public class Mecanum4WheelDriver extends Driver {
    public DcMotor fl=null;
    public DcMotor fr=null;
    public DcMotor bl=null;
    public DcMotor br=null;
    private double x=0;
    private double y=0;
    private double R=0;
    private double targx=0;
    private double targy=0;
    private double targR=0;

    private float strafeCoef = 1;
    private float agility = 1;

    /**
     * init with default strafing coefficient
     * @param l  all the motors in the order of fl, fr, bl, br
     */
    public void init(DcMotor[] l) {
        fl = l[0];
        fr = l[1];
        bl = l[2];
        br = l[3];
    }

    /**
     * Init with specified strafing coefficient
     * @param l  all the motors in the order of fl, fr, bl, br
     * @param strafeCoef specified strafing coefficient
     */
    public void init(DcMotor[] l, float strafeCoef) {
        fl = l[0];
        fr = l[1];
        bl = l[2];
        br = l[3];
        this.strafeCoef=strafeCoef;
    }
    /**
     * Init with specified strafing coefficient
     * @param l  all the motors in the order of fl, fr, bl, br
     * @param strafeCoef specified strafing coefficient
     * @param agility 
     */
    public void init(DcMotor[] l, float strafeCoef, float agility) {
        fl = l[0];
        fr = l[1];
        bl = l[2];
        br = l[3];
        this.strafeCoef=strafeCoef;
        this.agility = agility;
    }

    public void stopDriver(){

    }
    public void update() {}

    public float torad(float x){
        return x/57.2958f;
    }
    private static double round (double value, int precision) {
        int scale = (int) Math.pow(10, precision);
        return (double) (Math.round(value * scale) / scale);
    }
    public void motorUpdate(){
        fl.setPower(y+x+R);
        bl.setPower(y-x+R);
        fr.setPower(-(y-x-R));
        br.setPower(-(y+x-R));
    }
    public void setX(double q){
        targx=q*strafeCoef;
        motorUpdate();
    }
    public void setY(double q){
        targy=q;
        motorUpdate();
    }
    public void setR(double q){
        targR=q;
        motorUpdate();
    }
    public void motorSet(double flSpeed, double blSpeed, double frSpeed, double brSpeed){
        fl.setPower(flSpeed);
        bl.setPower(blSpeed);
        fr.setPower(-(frSpeed));
        br.setPower(-(brSpeed));
    }
}