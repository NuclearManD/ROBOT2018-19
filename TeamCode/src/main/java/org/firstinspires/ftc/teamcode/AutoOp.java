package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.ColorSensor;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DistanceSensor;

import org.firstinspires.ftc.teamcode.drivers.ArmDriver;
import org.firstinspires.ftc.teamcode.drivers.Mecanum4WheelDriver;
import org.firstinspires.ftc.teamcode.drivers.Multitasker;
import org.firstinspires.ftc.teamcode.drivers.TelemetryUpdater;

/**
 * Created by SCRoboticsDev on 12/5/2018.
 */
@TeleOp(name="Sampling Auto Op", group="2018")
public class AutoOp extends LinearOpMode{

    @Override
    public void runOpMode() throws InterruptedException {

        // initailize here
        ColorSensor sensorColor;
        DistanceSensor sensorDistance;

        Mecanum4WheelDriver driver = new Mecanum4WheelDriver();
        ArmDriver arm = new ArmDriver(hardwareMap.dcMotor.get("pully"),hardwareMap.dcMotor.get("angle"),hardwareMap.crservo.get("goboi"));
        Multitasker multi = new Multitasker(this);
        DcMotor[] motors = {hardwareMap.dcMotor.get("fl"),hardwareMap.dcMotor.get("fr"),hardwareMap.dcMotor.get("bl"),hardwareMap.dcMotor.get("br")};
        driver.init(motors,-1,.1f);
        multi.addTask(driver);
        multi.addTask(arm);
        //multi.addTask(new TelemetryUpdater());
        sensorColor = hardwareMap.get(ColorSensor.class, "sensor_color_distance");


        waitForStart();


        driver.setY(.5);
        multi.waitTime(445);
        driver.setY(0);
        multi.waitTime(800);
        // it goes forward

        float x;
        float r;
        float b;
        float g;
        float v;
        r = sensorColor.green();
        b = sensorColor.blue();
        g = sensorColor.red();
        x = r + b + g;
        v = x / 3;
        v -= r;
        // it scans the minaral for color

        telemetry.addData("isWhite", (v < -25));
        telemetry.addLine("is White??"+ (v < -25));
        telemetry.update();
        // tells wether it is white or not

        //if it is white then ...
        if (v < -40){
            telemetry.addLine("going to next block");
            telemetry.update();
            multi.waitTime(1000);

            driver.setY(-.5);
            multi.waitTime(300);
            driver.setY(0);
            driver.setX(.5);
            multi.waitTime(600);
            driver.setX(0);
            driver.setY(.5);
            multi.waitTime(300);
            driver.setY(0);
            //it goes back then strafes then stops then goes forward (hopefully to next block)

            r = sensorColor.green();
            b = sensorColor.blue();
            g = sensorColor.red();
            x = r + b + g;
            v = x / 3;
            v -= r;
            // it dose another color check

            telemetry.addLine("executing color check");
            telemetry.update();
            multi.waitTime(1000);
            telemetry.addData("isWhite", (v < -40));
            telemetry.update();

            // if it's white again ...
            if (v < -40){
                telemetry.addLine("executing move to yellow");
                telemetry.update();
                multi.waitTime(1000);
                
                driver.setY(-.5);
                multi.waitTime(300);
                driver.setY(0);
                driver.setX(-.5);
                multi.waitTime(1200);
                driver.setX(0);
                driver.setY(.5);
                multi.waitTime(1000);
                driver.setY(0);
                // hopefully it should go to the last block

                // go forward
                driver.setY(.5);
                multi.waitTime(900);
                driver.setY(0);

                multi.waitTime(1500);

                // rotate arm and wait for finish
                arm.rotate(-50);
                multi.waitTime(5000);

                // extend some
                arm.extend();
                multi.waitTime(1000);
                arm.pullyoff();

            //if not ...
            }else {
                telemetry.addLine("found yellow");
                telemetry.update();
                multi.waitTime(1000);

                driver.setY(.5);
                multi.waitTime(900);
                driver.setY(0);

                multi.waitTime(1500);

                // rotate arm and wait for finish
                arm.rotate(-50);
                multi.waitTime(5000);

                // extend some`
                arm.extend();
                multi.waitTime(1000);
                arm.pullyoff();
            }
        // if not ALL that then ...
        }else{
            telemetry.addLine("found yellow");
            telemetry.update();
            multi.waitTime(1000);

            driver.setY(.5);
            multi.waitTime(900);
            driver.setY(0);

            multi.waitTime(1500);

            // rotate arm and wait for finish
            arm.rotate(-50);
            multi.waitTime(5000);

            // extend some
            arm.extend();
            multi.waitTime(1000);
            arm.pullyoff();
            // if the very first one is not white then it's yellow so it runs into it and extends arm
        }
        multi.waitTime(200);
        driver.setY(0);
        driver.setX(0);
        multi.waitTime(60000);
        // the drivers turn off
    }
}
