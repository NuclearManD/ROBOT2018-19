
package org.firstinspires.ftc.teamcode.drivers;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;

/**
 * Created by SCRoboticsDev on 1/5/2019.
 */

@TeleOp(name="LiftAutoOp", group="2018")
public class LiftAutoOp extends LinearOpMode{

    @Override
    public void runOpMode() throws InterruptedException {
    }

    DcMotor grab;

    int x = 1 ;
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
