package org.firstinspires.ftc.teamcode.oldops;

import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.AutoHelper;

//@TeleOp(name="Auto Op Claim, our crater")
public class LowerAndClaimAuto extends AutoHelper {

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
        multi.waitTime(550);
        driver.setY(0);

        // claim
        arm.ColectBoiBack();
        waitLong();
        arm.ColectBoiOff();

        // leave
        driver.setY(-.6);
        multi.waitTime(400);
        driver.setY(0);
        waitShort();

        if(isStopRequested()) {
            stopMotors();
            return;
        }

        // turn
        turn(95);

        // go into crater
        driver.setY(.8);
        driver.setR(.15);
        multi.waitTime(1300);
        driver.setY(0);
        driver.setR(0);

        // extend arm into crater
        arm.rotate(-80);
        waitLong();
        arm.extend();
        multi.waitTime(1500);
        arm.pullyoff();
        arm.off();
        multi.waitTime(1000);
    }
}
