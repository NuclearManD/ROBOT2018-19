package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;

import org.firstinspires.ftc.teamcode.drivers.ArmDriver;
import org.firstinspires.ftc.teamcode.drivers.Mecanum4WheelDriver;
import org.firstinspires.ftc.teamcode.drivers.Multitasker;


@TeleOp(name="Main Op Mode", group="2018")
public class ManualOp extends LinearOpMode {

    @Override
    public void runOpMode() {
        Mecanum4WheelDriver driver = new Mecanum4WheelDriver();
        ArmDriver arm = new ArmDriver(hardwareMap.dcMotor.get("pully"),hardwareMap.dcMotor.get("angle"));
        Multitasker multi = new Multitasker(this);
        DcMotor[] motors = {hardwareMap.dcMotor.get("fl"),hardwareMap.dcMotor.get("fr"),hardwareMap.dcMotor.get("bl"),hardwareMap.dcMotor.get("br")};
        driver.init(motors,-1,.1f);
        multi.addTask(driver);

        waitForStart();

        while(opModeIsActive()){
            float y = gamepad1.right_stick_y;
            float x = gamepad1.right_stick_x;
            float R = gamepad1.left_stick_x;

            driver.setY(y);
            driver.setX(x);
            driver.setR(R);
            multi.yield();
        }
        driver.motorSet(0,0,0,0);
    }
}
