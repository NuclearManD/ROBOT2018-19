package org.firstinspires.ftc.teamcode.oldops;

import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.AutoHelper;

/**
 * Created by SCRoboticsDev on 1/11/2019.
 */

//@TeleOp(name = "Auto Op Crater")
public class LowerToOurCrater extends AutoHelper {

    @Override
    public void runOpMode() {
        initHardware();
        waitForStart();

        lower();

        // get into position to claim
        waitShort();
        turn(-70);
        waitShort();

        // go forward
        driver.setY(1);
        multi.waitTime(450);
        driver.setY(0);

        if(isStopRequested()){
            stopMotors();
            return;
        }

        // extend arm into crater
        arm.rotate(-80);
        waitLong();
        arm.extend();
        waitShort();
        arm.pullyoff();
        arm.off();
        multi.waitTime(1000);
    }
}
