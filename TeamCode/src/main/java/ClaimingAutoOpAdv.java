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
@TeleOp(name="Claiming Auto Op", group="2018")
public class ClaimingAutoOpAdv extends LinearOpMode{

    ColorSensor sensorColor;
    DistanceSensor sensorDistance;

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
        //multi.addTask(new TelemetryUpdater());
        sensorColor = hardwareMap.get(ColorSensor.class, "sensor_color_distance");


        waitForStart();


        driver.setY(.5);
        multi.waitTime(500);
        driver.setY(0);
        multi.waitTime(800);
        // it goes forward

        boolean isWhite = getColorIsWhite();
        // it scans the minaral for color

        //telemetry.addData("isWhite", isWhite);
        //telemetry.update();
        // tells wether it is white or not

        //if it is white then ...
        if (isWhite){
            //telemetry.addLine("going to next block");
            //telemetry.update();
            multi.waitTime(1000);

            driver.setY(-.4);
            multi.waitTime(110);
            driver.setY(0);
            multi.waitTime(50);
            driver.setX(.5);
            multi.waitTime(800);
            driver.setX(0);
            multi.waitTime(50);
            driver.setY(.4);
            multi.waitTime(200);
            driver.setY(0);
            //it goes back then strafes then stops then goes forward (hopefully to next block)

            isWhite = getColorIsWhite();
            // it dose another color check
            multi.waitTime(1000);
            //telemetry.addData("isWhite", isWhite);
            //telemetry.update();

            // if it's white again ...
            if (isWhite){
                //telemetry.addLine("executing move to yellow");
                //telemetry.update();
                multi.waitTime(1000);

                driver.setY(-.4);
                multi.waitTime(110);
                driver.setY(0);
                multi.waitTime(50);
                driver.setX(-.5);
                multi.waitTime(1600);
                driver.setX(0);
                multi.waitTime(50);
                driver.setY(.5);
                multi.waitTime(800);
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
                //telemetry.addLine("found yellow");
                //telemetry.update();
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
            //telemetry.addLine("found yellow");
            //telemetry.update();
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
        telemetry.update();
        multi.waitTime(60000);
        // the drivers turn off
    }

    public boolean getColorIsWhite() {

        //telemetry.addLine("executing color check");
        //telemetry.update();
        float r = sensorColor.green();
        float b = sensorColor.blue();

        telemetry.addLine("R="+r+" && B="+b);
        //telemetry.update();
        float x = r-b;
        return x<15;
    }
}
