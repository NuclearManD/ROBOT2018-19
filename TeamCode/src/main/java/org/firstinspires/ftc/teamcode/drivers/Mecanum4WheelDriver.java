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
    public void init(DcMotor[] l) {
        fl = l[0];
        fr = l[1];
        bl = l[2];
        br = l[3];
    }
    public void update() {

    }
    private float torad(float x){
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
        x=q;
        motorUpdate();
    }
    public void setY(double q){
        y=q;
        motorUpdate();
    }
    public void setR(double q){
        R=q;
        motorUpdate();
    }
}