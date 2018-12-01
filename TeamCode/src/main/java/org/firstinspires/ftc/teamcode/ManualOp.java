package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;

import org.firstinspires.ftc.teamcode.drivers.ArmDriver;
import org.firstinspires.ftc.teamcode.drivers.Mecanum4WheelDriver;
import org.firstinspires.ftc.teamcode.drivers.Multitasker;
import org.firstinspires.ftc.teamcode.drivers.TelemetryUpdater;


@TeleOp(name="Main Op Mode", group="2018")
public class ManualOp extends LinearOpMode {

    @Override
    public void runOpMode() {
        Mecanum4WheelDriver driver = new Mecanum4WheelDriver();
        ArmDriver arm = new ArmDriver(hardwareMap.dcMotor.get("pully"),hardwareMap.dcMotor.get("angle"),hardwareMap.crservo.get("goboi"));
        Multitasker multi = new Multitasker(this);
        DcMotor[] motors = {hardwareMap.dcMotor.get("fl"),hardwareMap.dcMotor.get("fr"),hardwareMap.dcMotor.get("bl"),hardwareMap.dcMotor.get("br")};
        driver.init(motors,-1,.1f);
        multi.addTask(driver);
        multi.addTask(arm);
        multi.addTask(new TelemetryUpdater());

        waitForStart();

        float lastAngle = 0;

        while(opModeIsActive()){
            float y = -gamepad1.right_stick_y;
            float x = -gamepad1.right_stick_x;
            float R = gamepad1.left_stick_x*.5f;

            if(Math.abs(y)<0.1)y=0;
            if(Math.abs(x)<0.1)x=0;
            if(Math.abs(R)<0.1)R=0;

            driver.setY(y);
            driver.setX(x);
            driver.setR(R);

            if(gamepad2.x){
                arm.ColectBoiOn();
            }else if(gamepad2.y){
                arm.ColectBoiOff();
            }else if(gamepad2.a){
                arm.ColectBoiBack();
            }

            if(gamepad2.dpad_left){
                arm.rotate(lastAngle--);
            }else if(gamepad2.dpad_right){
                arm.rotate(lastAngle++);
            }

            if(gamepad2.dpad_up){
                arm.extend();
            }else if(gamepad2.dpad_down){
                arm.retract();
            }else
                arm.pullyoff();

            multi.waitTime(20);
        }
        driver.motorSet(0,0,0,0);
    }
}