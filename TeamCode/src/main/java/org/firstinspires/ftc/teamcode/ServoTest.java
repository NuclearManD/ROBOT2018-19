package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.ElapsedTime;
import com.qualcomm.robotcore.util.Range;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.robotcore.internal.opmode.TelemetryImpl;
import org.firstinspires.ftc.teamcode.drivers.MovementDriver;

/**
 * Created by SCRoboticsDev on 11/5/2018.
 */

@TeleOp(name="Servo teszgt", group="2018")
public class ServoTest extends LinearOpMode {
    // Declare OpMode members.
    private ElapsedTime runtime = new ElapsedTime();
    private DcMotor dc = null;

    @Override
    public void runOpMode() {
        telemetry.addData("Status", "Initialized");
        telemetry.update();
        dc = hardwareMap.get(DcMotor.class, "Core Hex Motor");

        // Wait for the game to start (driver presses PLAY)
        waitForStart();
        runtime.reset();

        dc.setPower(1);
        try{
            Thread.sleep(3200);
        }catch(Exception e){

        }
        dc.setPower(0);
    }
}
