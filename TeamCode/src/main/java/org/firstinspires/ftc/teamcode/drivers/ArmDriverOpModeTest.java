package org.firstinspires.ftc.teamcode.drivers;


import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;

@TeleOp(name="Arm Test Op", group="2018")
public class ArmDriverOpModeTest extends LinearOpMode {

    DcMotor pully;
    DcMotor ang;

    public void runOpMode() {
        try {
            pully = hardwareMap.dcMotor.get("fl");
            ArmDriver driver = new ArmDriver(pully, hardwareMap.dcMotor.get("fr"));
            driver.rotate(215);
        } catch (Exception e) {
            System.out.println("oog something got gooned");
        }
        // it's fine everthing is fine
        try {
            Thread.sleep(4000);
        } catch (Exception e) {
            System.out.println("oog something got gooned");

        }
    }
}