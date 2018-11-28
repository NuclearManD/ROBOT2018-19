package org.firstinspires.ftc.teamcode.drivers;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;


/**
 * Created by SCRoboticsDev on 11/26/2018.
 */
@TeleOp(name="Other Arm Test Op", group="2018")
public class OtherArmTest extends LinearOpMode{

    DcMotor pully;
    DcMotor ang;

    public void runOpMode() {

        try {
            pully = hardwareMap.dcMotor.get("pully");
            ArmDriver driver = new ArmDriver(pully, hardwareMap.dcMotor.get("angle"));
            driver.extend(30);
        } catch (Exception e) {
            System.out.println("oog something got gooned");
        }
        try {
            Thread.sleep(4000);
        } catch (Exception e) {
            System.out.println("oog something got gooned");

        }
    }
}