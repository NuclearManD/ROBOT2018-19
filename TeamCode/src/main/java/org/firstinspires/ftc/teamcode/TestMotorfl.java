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
        }catch (Exception e) {
            e.printStackTrace();
        }
        while (opModeIsActive()) {
            if(System.currentTimeMillis()%2000 == 0){
                m.setPower(-1);
            }
            if(System.currentTimeMillis()%2000 == 1000){
                m.setPower(1);
            }
        }
    }
}
