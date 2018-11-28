package org.firstinspires.ftc.teamcode.drivers;

import com.qualcomm.robotcore.hardware.DcMotor;
import java.lang.Runnable;

public class Mecanum4WheelDriver extends Driver implements Runnable{
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
    private float agility = 0.01f;

    private boolean running = true;

    /**
     * init with default strafing coefficient
     * @param l  all the motors in the order of fl, fr, bl, br
     */
    public void init(DcMotor[] l) {
        fl = l[0];
        fr = l[1];
        bl = l[2];
        br = l[3];

        startThread();
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

        startThread();
    }
    /**
     * Init with specified strafing coefficient
     * @param l  all the motors in the order of fl, fr, bl, br
     * @param strafeCoef specified strafing coefficient
     * @param agilityVal specified agility
     */
    public void init(DcMotor[] l, float strafeCoef, float agilityVal) {
        fl = l[0];
        fr = l[1];
        bl = l[2];
        br = l[3];
        this.strafeCoef=strafeCoef;
        agility=agilityVal;

        startThread();
    }

    protected void startThread(){
        Thread t = new Thread(this);
        t.setDaemon(true);
        t.start();
    }

    public void stopDriver(){
        running = false;
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
    }
    public void setY(double q){
        targy=q;
    }
    public void setR(double q){
        targR=q;
    }
    @Deprecated
    public void motorSet(double flSpeed, double blSpeed, double frSpeed, double brSpeed){
        fl.setPower(flSpeed);
        bl.setPower(blSpeed);
        fr.setPower(-(frSpeed));
        br.setPower(-(brSpeed));
    }

    @Override
    public void run() {
        // this will run the Agile Movement thread.
        while(running){
            x+=Math.copySign(agility,targx-x);
            y+=Math.copySign(agility,targy-y);
            R+=Math.copySign(agility,targR-R);
            motorUpdate();
            try{
                Thread.sleep(10);
            }catch(Exception e){
                break;
            }
        }
    }
}