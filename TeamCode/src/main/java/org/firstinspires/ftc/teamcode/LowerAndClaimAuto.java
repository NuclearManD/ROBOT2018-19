package org.firstinspires.ftc.teamcode;

import android.graphics.Color;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.ColorSensor;
import com.qualcomm.robotcore.hardware.DcMotor;

import org.firstinspires.ftc.teamcode.drivers.ArmDriver;
import org.firstinspires.ftc.teamcode.drivers.LinerActuator;
import org.firstinspires.ftc.teamcode.drivers.Mecanum4WheelDriver;
import org.firstinspires.ftc.teamcode.drivers.Multitasker;
import org.firstinspires.ftc.teamcode.drivers.TelemetryUpdater;

@TeleOp(name="Auto Op Claim, our crater")
public class LowerAndClaimAuto extends AutoHelper {

    @Override
    public void runOpMode() {
        initHardware();

        waitForStart();

        lower();


        // get into position to claim
        multi.waitTime(1500);
        turn(-70);
        multi.waitTime(1000);
        driver.setY(1);
        multi.waitTime(550);
        driver.setY(0);

        // claim
        arm.ColectBoiBack();
        multi.waitTime(1800);
        arm.ColectBoiOff();

        // leave
        driver.setY(-.6);
        multi.waitTime(400);
        driver.setY(0);
        multi.waitTime(1000);

        // turn
        turn(90);

        // go into crater
        driver.setY(.75);
        multi.waitTime(1000);
        driver.setY(0);

        // extend arm into crater
        arm.rotate(-80);
        multi.waitTime(5000);
        arm.extend();
        multi.waitTime(1500);
        arm.pullyoff();
        arm.off();
        multi.waitTime(1000);
    }
}
