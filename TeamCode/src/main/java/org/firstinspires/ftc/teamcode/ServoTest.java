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

@TeleOp(name="Servo Tegszt", group="2018")
public class ServoTest extends LinearOpMode {
    // Declare OpMode members.
    private ElapsedTime runtime = new ElapsedTime();
    private Servo ColAng = null;

    @Override
    public void runOpMode() {
        telemetry.addData("Status", "Initialized");
        telemetry.update();
        ColAng =hardwareMap.servo.get("ColAng");

        // Wait for the game to start (driver presses PLAY)
        waitForStart();
        runtime.reset();
        ColAng.setPosition(.5);
       try {
           Thread.sleep(4000);
       }catch (Exception e){
           System.out.println("oog something got gooned");
       }
    }
}
