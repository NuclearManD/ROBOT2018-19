package org.firstinspires.ftc.teamcode;

import com.qualcomm.hardware.bosch.BNO055IMU;
import com.qualcomm.hardware.modernrobotics.ModernRoboticsI2cCompassSensor;
import com.qualcomm.hardware.modernrobotics.ModernRoboticsI2cGyro;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.ColorSensor;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.IntegratingGyroscope;

import org.firstinspires.ftc.robotcontroller.external.samples.SensorMRGyro;
import org.firstinspires.ftc.teamcode.drivers.ArmDriver;
import org.firstinspires.ftc.teamcode.drivers.LinerActuator;
import org.firstinspires.ftc.teamcode.drivers.Mecanum4WheelDriver;
import org.firstinspires.ftc.teamcode.drivers.Multitasker;
import org.firstinspires.ftc.teamcode.drivers.TelemetryUpdater;

public abstract class AutoHelper extends LinearOpMode {

    DcMotor lm;
    Mecanum4WheelDriver driver;
    LinerActuator lift;
    ArmDriver arm;
    Multitasker multi;
    DcMotor[] motors;
    ModernRoboticsI2cCompassSensor compass;
    public void initHardware(){
        lm = hardwareMap.dcMotor.get("lift");
        //sensorColor = hardwareMap.get(ColorSensor.class, "sensor_color_distance");
        driver = new Mecanum4WheelDriver();
        lift = new LinerActuator(lm);
        arm = new ArmDriver(hardwareMap.dcMotor.get("pully"), hardwareMap.dcMotor.get("angle"), hardwareMap.crservo.get("goboi"));
        multi = new Multitasker(this);
        motors = new DcMotor[]{hardwareMap.dcMotor.get("fl"), hardwareMap.dcMotor.get("fr"), hardwareMap.dcMotor.get("bl"), hardwareMap.dcMotor.get("br")};
        compass = hardwareMap.get(ModernRoboticsI2cCompassSensor.class, "compass");
        //gyro = (IntegratingGyroscope) modernRoboticsI2cGyro;
        driver.init(motors, -1, .1f);
        multi.addTask(driver);
        multi.addTask(arm);
        multi.addTask(new TelemetryUpdater());

    }
    void turn(float angle){
        float mag = (float)Math.copySign(.5,angle);
        driver.setR(mag);
        multi.waitTime(10);
        angle = Math.abs(angle);
        while(Math.abs(driver.rotation)<angle){
            multi.yield();
            if(Math.abs(driver.rotation)-angle>-.5f)driver.setR(mag/5);
        }
        driver.setR(0);
    }
    void lower(){

        // save start position
        long ref = lm.getCurrentPosition();

        // drop
        lift.setState(1);
        // this loop makes the linear actuator displacement independent of battery life using encoders.
        while (opModeIsActive() && (lm.getCurrentPosition() - ref) > -2900) {
            multi.yield();
        }
        lift.setState(0);
        if (isStopRequested()) {
            return;
        }

        // unlatch
        multi.waitTime(500);
        driver.setY(-.3);
        multi.waitTime(600);
        driver.setY(0);
        multi.waitTime(1500);

        // retract
        lift.setState(-1);
        while (opModeIsActive() && (lm.getCurrentPosition() - ref) < -10) {
            multi.yield();
        }
        lift.setState(0);
        if (isStopRequested()) {
            return;
        }
    }
}
