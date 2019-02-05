package org.firstinspires.ftc.teamcode.drivers;

import com.qualcomm.robotcore.hardware.DcMotor;

public class Mecanum4WheelDriver extends Task {
    public DcMotor fl = null;
    public DcMotor fr = null;
    public DcMotor bl = null;
    public DcMotor br = null;
    private double x = 0;
    private double y = 0;
    private double R = 0;
    private double targx = 0;
    private double targy = 0;
    private double targR = 0;

    private float strafeCoef = 1;
    public float agility = 0.01f;
    private int fl_lspos, fr_lspos;

    public float rotation = 0;
    public double distance = 0;
    private int dist_enc = 0;

    /**
     * init with default strafing coefficient
     *
     * @param l all the motors in the order of fl, fr, bl, br
     */
    public void init(DcMotor[] l) {
        fl = l[0];
        fr = l[1];
        bl = l[2];
        br = l[3];

        bl.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        br.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
    }

    /**
     * Init with specified strafing coefficient
     *
     * @param l          all the motors in the order of fl, fr, bl, br
     * @param strafeCoef specified strafing coefficient
     */
    public void init(DcMotor[] l, float strafeCoef) {
        fl = l[0];
        fr = l[1];
        bl = l[2];
        br = l[3];
        this.strafeCoef = strafeCoef;

        bl.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        br.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
    }

    /**
     * Init with specified strafing coefficient
     *
     * @param l          all the motors in the order of fl, fr, bl, br
     * @param strafeCoef specified strafing coefficient
     * @param agilityVal specified agility
     */
    public void init(DcMotor[] l, float strafeCoef, float agilityVal) {
        fl = l[0];
        fr = l[1];
        bl = l[2];
        br = l[3];
        this.strafeCoef = strafeCoef;
        agility = agilityVal;

        fl.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        fr.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
    }

    public float torad(float x) {
        return x / 57.2958f;
    }

    private static double round(double value, int precision) {
        int scale = (int) Math.pow(10, precision);
        return (double) (Math.round(value * scale) / scale);
    }

    public void motorUpdate() {
        fl.setPower(y + x + R);
        bl.setPower(y - x + R);
        fr.setPower(-(y - x - R));
        br.setPower(-(y + x - R));
    }

    public void setX(double q) {
        targx = q * strafeCoef;
    }

    public void setY(double q) {
        if (targy==0 && Math.copySign(q,1)>.05) {
            dist_enc = fl.getCurrentPosition() - fr.getCurrentPosition();
        }
        targy = q;
    }

    public void setR(double q) {
        if (targR == 0 && q != 0) {
            fl_lspos = fl.getCurrentPosition();
            fr_lspos = fr.getCurrentPosition();
        }
        targR = q;
    }

    @Deprecated
    public void motorSet(double flSpeed, double blSpeed, double frSpeed, double brSpeed) {
        fl.setPower(flSpeed);
        bl.setPower(blSpeed);
        fr.setPower(-(frSpeed));
        br.setPower(-(brSpeed));
    }

    public void update(Multitasker man) {
        // this will run the Agile Movement thread.
        if (Math.abs(targx - x) > agility * 3)
            x += Math.copySign(agility, targx - x);
        else
            x = targx;
        if (Math.abs(targy - y) > agility * 3)
            y += Math.copySign(agility, targy - y);
        else
            y = targy;
        if (Math.abs(targR - R) > agility * 3) {
            R += Math.copySign(agility, targR - R);
        } else
            R = targR;
        rotation = ((fl_lspos - fl.getCurrentPosition()) + (fr_lspos - fr.getCurrentPosition())) * .0511f;
        distance = (dist_enc - fl.getCurrentPosition() + fr.getCurrentPosition())/1870.0;
        motorUpdate();
        man.taskSleep(10);
    }

    public void setAgility(int agility) {
        this.agility = agility;
    }
}