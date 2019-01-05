package org.firstinspires.ftc.teamcode.drivers;

import com.qualcomm.robotcore.hardware.DcMotor;

/**
 * Created by SCRoboticsDev on 1/3/2019.
 */

public class LinerActuator {

    DcMotor grab;

    int x;
    double ExLenghth = x;
// do this bc dont kn how far to extened
// **plz change when you figuer out



    public void extend(){
        grab.setPower(x);
        try{
            Thread.sleep(500);
        }catch (Exception e) {
            System.out.println("oog something got gooned");
        }
        grab.setPower(0);
    }

    public void NegExtend(){
        grab.setPower(-x);
        try{
            Thread.sleep(450);
        }catch (Exception e) {
            System.out.println("oog something got gooned");
        }
        grab.setPower(0);
    }

}