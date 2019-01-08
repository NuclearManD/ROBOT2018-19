
package org.firstinspires.ftc.teamcode.drivers;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;

/**
 * Created by SCRoboticsDev on 1/5/2019.
 */

//@TeleOp(name="LiftAutoOp", group="2018")
public class LiftAutoOp extends LinearOpMode{

    @Override
    public void runOpMode(){
        try {
            LinerActuator driver = new LinerActuator(hardwareMap.dcMotor.get("lift"));
            driver.extend();
        } catch (Exception e) {
            System.out.println("oog something got gooned");
        }
    }
}
