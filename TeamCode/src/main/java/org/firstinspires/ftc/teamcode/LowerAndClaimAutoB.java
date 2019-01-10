package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;

import org.firstinspires.ftc.teamcode.drivers.ArmDriver;
import org.firstinspires.ftc.teamcode.drivers.LinerActuator;
import org.firstinspires.ftc.teamcode.drivers.Mecanum4WheelDriver;
import org.firstinspires.ftc.teamcode.drivers.Multitasker;
import org.firstinspires.ftc.teamcode.drivers.TelemetryUpdater;

@TeleOp(name="LowerAndClaimAutoOp Crater")
public class LowerAndClaimAutoB extends LinearOpMode {

    @Override
    public void runOpMode() {
        DcMotor lm = hardwareMap.dcMotor.get("lift");

        Mecanum4WheelDriver driver = new Mecanum4WheelDriver();
        LinerActuator lift = new LinerActuator(lm);
        ArmDriver arm = new ArmDriver(hardwareMap.dcMotor.get("pully"), hardwareMap.dcMotor.get("angle"), hardwareMap.crservo.get("goboi"));
        Multitasker multi = new Multitasker(this);
        DcMotor[] motors = {hardwareMap.dcMotor.get("fl"), hardwareMap.dcMotor.get("fr"), hardwareMap.dcMotor.get("bl"), hardwareMap.dcMotor.get("br")};
        driver.init(motors, -1, .1f);
        multi.addTask(driver);
        multi.addTask(arm);
        multi.addTask(new TelemetryUpdater());

        waitForStart();

        // save start position
        long ref = lm.getCurrentPosition();

        // drop
        lift.setState(1);
        // this loop makes the linear actuator displacement independent of battery life using encoders.
        while((lm.getCurrentPosition()-ref)>-2400){
            multi.yield();
        }
        lift.setState(0);

        // unlatch
        multi.waitTime(500);
        driver.setY(-.5);
        multi.waitTime(300);
        driver.setY(0);
        multi.waitTime(1500);

        // retract
        lift.setState(-1);
        while((lm.getCurrentPosition()-ref)<-10){
            multi.yield();
        }
        lift.setState(0);
        multi.waitTime(1500);
        // straef out and turn
        driver.setX(-1);
        multi.waitTime(500);
        driver.setX(0);
        driver.setR(-1);
        multi.waitTime(290);
        driver.setR(0);
        multi.waitTime(400);
        driver.setY(1);
        multi.waitTime(400);
        driver.setY(0);

        multi.waitTime(400);
    }
}
