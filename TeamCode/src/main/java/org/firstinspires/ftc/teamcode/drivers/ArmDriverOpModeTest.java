package org.firstinspires.ftc.teamcode.drivers;


import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;

public class ArmDriverOpModeTest extends LinearOpMode {

    DcMotor pully;
    DcMotor ang;

    public void runOpMode () {
        try {
            pully.setPower(1);
            Thread.sleep(500);
        } catch (Exception e) {
            System.out.println("oog something got gooned");
        }

        pully.setPower(0);
    }
}
