package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;

import org.firstinspires.ftc.teamcode.drivers.PictureReader;

@TeleOp(name="Test fl", group="2018")
public class TestMotorfl extends LinearOpMode {
    public void runOpMode() throws InterruptedException {
        // edit
        PictureReader picture = new PictureReader();
        telemetry.addData();
        System.out.print(picture.readPicture());
        // edit
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
            m.setPower(.25);
            Thread.sleep(5000);
            m.setPower(-.25);
            Thread.sleep(10);
        }catch (Exception e) {
            e.printStackTrace();
        }
    }
}
