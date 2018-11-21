package org.firstinspires.ftc.teamcode;

/**
 * Created by SCRoboticsDev on 11/14/2018.
 * GamePad Documentation: ftckey...com
 */
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.teamcode.drivers.Mecanum4WheelDriver;
import org.firstinspires.ftc.teamcode.drivers.UltimateDriver;
import com.qualcomm.robotcore.hardware.Gamepad;

@TeleOp(name="GamepadTest", group="2018")
public class GamepadTest extends LinearOpMode {
    static final float FORWARD_SPEED = -0.6f;
    public void runOpMode(){
        UltimateDriver driver = new UltimateDriver();
        DcMotor[] motors;
        Gamepad movePad = gamepad1;
        try {
            motors = {hardwareMap.dcMotor.get("fl"),hardwareMap.dcMotor.get("fr"),hardwareMap.dcMotor.get("bl"),hardwareMap.dcMotor.get("br")};
            driver.init(motors, -1);
        }catch (Exception e){
            System.out.println("\n------    HARDWARE ERROR IN INIT!   ------\n");
            e.printStackTrace();
        }
        try {
            waitForStart();
        }catch (Exception e) {
            e.printStackTrace();
        }
        while (opModeIsActive()) {
            try {
                driver.manualDrive(movePad.left_stick_x, movePad.left_stick_y);
                driver.manualTurn(movePad.left_trigger, movePad.right_trigger);
            }
            catch (Exception e){
                telemetry.addLine("ERROR IN MAIN CODE!!!!");
                return;
            }
        }
    }
}
