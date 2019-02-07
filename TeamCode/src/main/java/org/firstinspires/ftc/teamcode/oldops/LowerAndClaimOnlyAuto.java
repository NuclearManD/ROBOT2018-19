package org.firstinspires.ftc.teamcode.oldops;

import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.AutoHelper;

/**
 * Created by SCRoboticsDev on 1/11/2019.
 */

//@TeleOp(name = "Auto Op Claim Only")
public class LowerAndClaimOnlyAuto extends AutoHelper {

    @Override
    public void runOpMode() {
        initHardware();
        waitForStart();

        lower();


        // get into position to claim
        waitShort();
        turn(-75);
        waitShort();
        driver.setY(1);
        multi.waitTime(450);
        driver.setY(0);

        if(isStopRequested()){
            stopMotors();
            return;
        }

        // claim
        arm.ColectBoiBack();
        waitLong();

        // leave
        driver.setY(-.75);
        multi.waitTime(300);
        driver.setY(0);
        multi.waitTime(1000);
    }
}
