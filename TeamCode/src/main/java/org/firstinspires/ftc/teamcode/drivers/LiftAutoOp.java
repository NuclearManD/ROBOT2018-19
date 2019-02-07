
package org.firstinspires.ftc.teamcode.drivers;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

/**
 * Created by SCRoboticsDev on 1/5/2019.
 */

//@TeleOp(name="LiftAutoOp", group="2018")
public class LiftAutoOp extends LinearOpMode{

    @Override
    public void runOpMode(){
        try {
            LinearActuator driver = new LinearActuator(hardwareMap.dcMotor.get("lift"));
            driver.extend();
        } catch (Exception e) {
            System.out.println("oog something got gooned");
        }
    }
}
