package org.firstinspires.ftc.teamcode;

import android.graphics.Color;

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
        driver.setX(-.5);
        multi.waitTime(500);
        driver.setY(0);
        driver.setX(0);
        multi.waitTime(1000);
        // it goes forward

        long timer = 5000;
        driver.setX(.25);
        float highestValue = -10;
        long highestTiming = 20;
        float hsvValues[] = {0F, 0F, 0F};
        long start_time = System.currentTimeMillis();
        while(timer<System.currentTimeMillis()){
            if(System.currentTimeMillis()%3 == 0){
                Color.RGBToHSV((int) (sensorColor.red() * 255.0), (int) (sensorColor.green() * 255.0), (int) (sensorColor.blue() * 255.0), hsvValues);
                float v = hsvValues[0];
                if(v>highestValue){
                    highestTiming = System.currentTimeMillis()-start_time;
                    highestValue=v;
                }
            }
            multi.waitTime(1);
        }
        driver.agility = 1;
        driver.setX(-.25);
        multi.waitTime(highestTiming);
        driver.setX(0);
        driver.setY(.25);
        multi.waitTime(300);

        telemetry.update();
        multi.waitTime(200);
        driver.setY(0);
        driver.setX(0);
        multi.waitTime(5000);
        // the drivers turn off
    }

    public boolean getColorIsWhite() {
        float hsvValues[] = {0F, 0F, 0F};
        Color.RGBToHSV((int) (sensorColor.red() * 255.0), (int) (sensorColor.green() * 255.0), (int) (sensorColor.blue() * 255.0), hsvValues);

        telemetry.addLine("hue = "+hsvValues[0]);
        return hsvValues[0]>50;
    }
}
