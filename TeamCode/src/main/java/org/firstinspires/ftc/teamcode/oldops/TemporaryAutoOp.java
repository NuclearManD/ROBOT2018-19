package org.firstinspires.ftc.teamcode.oldops;

import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.AutoHelper;

//@TeleOp(name="Auto Temporary (DO NOT RUN)")
public class TemporaryAutoOp extends AutoHelper {
    @Override
    public void runOpMode() throws InterruptedException {
        initHardware();

        waitForStart();

        // save start position
        long ref = lm.getCurrentPosition();

        // drop
        lift.setState(1);
        // this loop makes the linear actuator displacement independent of battery life using encoders.
        while(opModeIsActive()&&(lm.getCurrentPosition()-ref)>-2900){
            multi.yield();
        }
        lift.setState(0);
        if(isStopRequested()){
            return;
        }

        // unlatch
        multi.waitTime(500);
        driver.setY(-.3);
        multi.waitTime(600);
        driver.setY(0);
        multi.waitTime(1500);

        // retract
        lift.setState(-1);
        while(opModeIsActive()&&(lm.getCurrentPosition()-ref)<-10){
            multi.yield();
        }
        lift.setState(0);
        if(isStopRequested()){
            return;
        }


        multi.waitTime(1500);
        turn(-90);
        multi.waitTime(500);
    }
}
