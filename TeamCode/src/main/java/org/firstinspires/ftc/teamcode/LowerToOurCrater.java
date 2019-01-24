package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;

import org.firstinspires.ftc.teamcode.drivers.ArmDriver;
import org.firstinspires.ftc.teamcode.drivers.LinerActuator;
import org.firstinspires.ftc.teamcode.drivers.Mecanum4WheelDriver;
import org.firstinspires.ftc.teamcode.drivers.Multitasker;
import org.firstinspires.ftc.teamcode.drivers.TelemetryUpdater;

/**
 * Created by SCRoboticsDev on 1/11/2019.
 */

@TeleOp(name = "Auto Op Crater")
public class LowerToOurCrater extends AutoHelper {

    @Override
    public void runOpMode() {
        initHardware();
        waitForStart();

        lower();

        // get into position to claim
        multi.waitTime(1500);
        turn(-70);
        multi.waitTime(1000);

        // go forward
        driver.setY(1);
        multi.waitTime(450);
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
