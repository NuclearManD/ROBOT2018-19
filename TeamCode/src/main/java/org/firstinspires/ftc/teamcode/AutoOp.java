package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;

import org.firstinspires.ftc.teamcode.drivers.ArmDriver;
import org.firstinspires.ftc.teamcode.drivers.Mecanum4WheelDriver;
import org.firstinspires.ftc.teamcode.drivers.Multitasker;
import org.firstinspires.ftc.teamcode.drivers.TelemetryUpdater;

/**
 * Created by SCRoboticsDev on 12/5/2018.
 */
@TeleOp(name="Auto Op", group="2018")
public class AutoOp extends LinearOpMode{

    @Override
    public void runOpMode() throws InterruptedException {

        // initailize here

        Mecanum4WheelDriver driver = new Mecanum4WheelDriver();
        ArmDriver arm = new ArmDriver(hardwareMap.dcMotor.get("pully"),hardwareMap.dcMotor.get("angle"),hardwareMap.crservo.get("goboi"));
        Multitasker multi = new Multitasker(this);
        DcMotor[] motors = {hardwareMap.dcMotor.get("fl"),hardwareMap.dcMotor.get("fr"),hardwareMap.dcMotor.get("bl"),hardwareMap.dcMotor.get("br")};
        driver.init(motors,-1,.1f);
        multi.addTask(driver);
        multi.addTask(arm);
        multi.addTask(new TelemetryUpdater());

        waitForStart();

        // run the autonomous pgm

        
    }
}