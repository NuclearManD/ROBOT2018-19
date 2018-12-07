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
        multi.addTask(new TelemetryUpdater());
        sensorColor = hardwareMap.get(ColorSensor.class, "sensor_color_distance");


        waitForStart();

        // run the autonomous pgm

        driver.setY(.5);
        multi.waitTime(445);
        driver.setY(0);
        multi.waitTime(100);

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
        telemetry.addData("isWhite", (v < -25));
        telemetry.update();

        if (v < -25){
            driver.setY(.5);
            multi.waitTime(300);
            driver.setY(0);
            driver.setX(.5);
            multi.waitTime(100);
            driver.setX(0);
            driver.setY(.5);
            multi.waitTime(300);
            driver.setY(0);

            r = sensorColor.green();
            b = sensorColor.blue();
            g = sensorColor.red();
            x = r + b + g;
            v = x / 3;
            v -= r;
            telemetry.addData("isWhite", (v < -25));
            telemetry.update();
            if (v < -25){
                driver.setY(.5);
                multi.waitTime(300);
                driver.setY(0);
                driver.setX(.5);
                multi.waitTime(-200);
                driver.setX(0);
                driver.setY(.5);
                multi.waitTime(1000);
                driver.setY(0);
            }else {
                driver.setY(.5);
                multi.waitTime(200);
                driver.setY(0);
            }
        }else{
            driver.setY(.5);
            multi.waitTime(200);
            driver.setY(0);
        }
        multi.waitTime(200);
        driver.setY(0);
        driver.setX(0);
        multi.waitTime(1000);
    }
}
