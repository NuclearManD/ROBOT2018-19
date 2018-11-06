package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;

@TeleOp(name="Test fl", group="2018")
public class TestMotorfl extends LinearOpMode {
    public void runOpMode() throws InterruptedException {
        DcMotor m = null;
        try {
            m = hardwareMap.dcMotor.get("fl");
        }catch (Exception e){
            System.out.println("\n------    HARDWARE ERROR IN INIT!   ------\n");
            e.printStackTrace();
            telemetry.addData("Main op", "%s error", e.toString());
            telemetry.update();

        }
        try {
            waitForStart();
            m.setPower(1);
            Thread.sleep(5000);
            m.setPower(0);
        }catch (Exception e) {
            e.printStackTrace();
        }
    }
}
