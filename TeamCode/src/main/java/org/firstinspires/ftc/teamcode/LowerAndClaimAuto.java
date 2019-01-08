package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;

import org.firstinspires.ftc.teamcode.drivers.ArmDriver;
import org.firstinspires.ftc.teamcode.drivers.LinerActuator;
import org.firstinspires.ftc.teamcode.drivers.Mecanum4WheelDriver;
import org.firstinspires.ftc.teamcode.drivers.Multitasker;
import org.firstinspires.ftc.teamcode.drivers.TelemetryUpdater;

@TeleOp(name="LowerAndClaimAutoOp2")
public class LowerAndClaimAuto extends LinearOpMode {

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

        long ref = lm.getCurrentPosition();

        lift.setState(1);
        multi.waitTime(850);
        lift.setState(0);

        telemetry.addLine("="+(lm.getCurrentPosition()-ref));
        telemetry.update();
        multi.waitTime(1000);
        driver.setY(-.5);
        multi.waitTime(300);
        driver.setY(0);
        multi.waitTime(10000);
        lift.setState(-1);
        multi.waitTime(800);
        lift.setState(0);


        try{
            Thread.sleep(100000000);
        }catch(Exception e){

        }

    }
}
