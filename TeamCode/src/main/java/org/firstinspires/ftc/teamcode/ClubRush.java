package org.firstinspires.ftc.teamcode;

import com.qualcomm.hardware.modernrobotics.ModernRoboticsI2cColorSensor;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.robotcore.internal.opmode.TelemetryImpl;
import org.firstinspires.ftc.teamcode.drivers.MovementDriver;
import org.firstinspires.ftc.teamcode.drivers.ServoFlapDriver1712;
/*
 MainOp 2017 Edition

 Made with:
 * MovementDriver 1.x.x
 * FiringMechanism 1.0.0
*/

@TeleOp(name="Club Rush", group="2018")
public class ClubRush extends LinearOpMode {
    private MovementDriver drive;
    Servo arm;
    public Telemetry telemetry = new TelemetryImpl(this);
    public void runOpMode(){
        try {
            DcMotor[] tmp={hardwareMap.dcMotor.get("fl"),hardwareMap.dcMotor.get("fr"),hardwareMap.dcMotor.get("bl"),hardwareMap.dcMotor.get("br")}; //this is our rear wheel motors.
            drive=new MovementDriver();
            arm=hardwareMap.servo.get("arm");
            arm.setPosition(0);
        }catch (Exception e){
            System.out.println("\n------    HARDWARE ERROR IN INIT!   ------\n");
            e.printStackTrace();
            telemetry.addData("Main op", "%s error", e.toString());
            telemetry.update();

        }
        try {
            waitForStart();
        }catch (Exception e) {
            e.printStackTrace();
        }
        boolean state=false;
        boolean ls=false;
        while (opModeIsActive()) {
            drive.setRotspeed(gamepad2.left_stick_x*.7f);
            drive.setSpeed(gamepad2.right_stick_y*.7f);
            if(gamepad2.a!=ls){
                ls=gamepad2.a;
                if(ls==true){
                     if(state){
                        arm.setPosition(180);
                    }else
                        arm.setPosition(0);
                    state=!state;
                }
            }
        }
    }
}

