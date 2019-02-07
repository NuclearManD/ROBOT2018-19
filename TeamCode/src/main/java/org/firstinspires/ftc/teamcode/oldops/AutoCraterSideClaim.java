package org.firstinspires.ftc.teamcode.oldops;

import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.AutoHelper;

//@TeleOp(name="Auto Op Crater Side Claim")
public class AutoCraterSideClaim extends AutoHelper {
    @Override
    public void runOpMode() throws InterruptedException {
        initHardware();
        waitForStart();

        lower();

        // get into position to claim
        waitShort();
        turn(-70);
        waitShort();

        // go forward
        driver.setY(1);
        multi.waitTime(270);
        driver.setY(0);
        waitShort();

        // go into position
        turn(-90);
        waitShort();
        driver.setY(1);
        driver.setR(.1);
        multi.waitTime(540);
        driver.setY(0);
        driver.setR(0);
        waitShort();
        turn(-60);
        waitShort();

        // go to depot
        driver.setY(.6);
        multi.waitTime(800);
        driver.setY(0);

        if(isStopRequested()){
            stopMotors();
            return;
        }

        // claim
        arm.ColectBoiBack();
        waitLong();
        arm.ColectBoiOff();

        // leave
        driver.setY(-.6);
        multi.waitTime(600);
        driver.setY(0);
        waitShort();

        // rotate 180 for crater
        turn(-180);

        if(isStopRequested()){
            stopMotors();
            return;
        }

        // go into crater
        driver.setY(.6);
        driver.setR(-.15);
        multi.waitTime(800);
        driver.setY(0);
        driver.setR(0);

        // extend arm into crater
        arm.rotate(-80);
        waitLong();
        arm.extend();
        multi.waitTime(1700);
        arm.pullyoff();
        arm.off();
        multi.waitTime(1000);
    }
}
