package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;

import org.firstinspires.ftc.teamcode.drivers.ArmDriver;
import org.firstinspires.ftc.teamcode.drivers.LinearActuator;
import org.firstinspires.ftc.teamcode.drivers.Mecanum4WheelDriver;
import org.firstinspires.ftc.teamcode.drivers.Multitasker;
import org.firstinspires.ftc.teamcode.drivers.TelemetryUpdater;

/**
 * Created by SCRoboticsDev on 12/9/2018.
 */


@TeleOp(name="Main Op Mode Jack", group="2018")
public class MainOpJack extends LinearOpMode {

    @Override
    public void runOpMode() {
        Mecanum4WheelDriver driver = new Mecanum4WheelDriver();
        LinearActuator lift = new LinearActuator(hardwareMap.dcMotor.get("lift"));
        ArmDriver arm = new ArmDriver(hardwareMap.dcMotor.get("pully"),hardwareMap.dcMotor.get("angle"),hardwareMap.crservo.get("goboi"));
        Multitasker multi = new Multitasker(this);
        DcMotor[] motors = {hardwareMap.dcMotor.get("fl"),hardwareMap.dcMotor.get("fr"),hardwareMap.dcMotor.get("bl"),hardwareMap.dcMotor.get("br")};
        driver.init(motors,-1,.1f);
        multi.addTask(driver);
        multi.addTask(arm);
        multi.addTask(new TelemetryUpdater());

        waitForStart();

        hardwareMap.servo.get("parker").setPosition(1);

        float lastAngle = 0;

        while(opModeIsActive()){
            float y = -gamepad1.right_stick_y;
            float x = -gamepad1.right_stick_x;
            float R = gamepad1.left_stick_x*.5f;

            if(Math.abs(y)<0.1)y=0;
            if(Math.abs(x)<0.1)x=0;
            if(Math.abs(R)<0.1)R=0;

            if(gamepad1.right_trigger>0.01){
                y*=1.5-gamepad1.right_trigger;
                x*=1.5-gamepad1.right_trigger;
            }

            driver.setY(y);
            driver.setX(x);
            driver.setR(R);

            if(gamepad2.x){
                arm.ColectBoiOn();
            }else if(gamepad2.a){
                arm.ColectBoiBack();
            }else{
                arm.ColectBoiOff();
            }

            if(gamepad2.right_trigger>.01){
                arm.rotate(lastAngle-=   gamepad2.right_trigger);
            }else if(gamepad2.left_trigger>.01){
                arm.rotate(lastAngle+=gamepad2.left_trigger);
            }

            if(gamepad2.dpad_up)lift.setState(1);
            else if(gamepad2.dpad_down)lift.setState(-1);
            else lift.setState(0);

            if(gamepad2.b){
                arm.off();
            }

            if(gamepad2.right_bumper){
                arm.extend();
            }else if(gamepad2.left_bumper){
                arm.retract();
            }else
                arm.pullyoff();

            multi.waitTime(20);
        }
        driver.motorSet(0,0,0,0);
    }
}
