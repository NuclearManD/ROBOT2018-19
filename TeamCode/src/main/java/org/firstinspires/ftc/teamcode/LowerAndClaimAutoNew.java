package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.drivers.Multitasker;


@TeleOp(name="Lower and claim")
public class LowerAndClaimAutoNew extends AutoHelper {
    public Multitasker multi;

    @Override
    public void runOpMode() {
        super.initHardware();
        waitForStart();

        // save start position
        long ref = lm.getCurrentPosition();

        // drop
        lift.setState(1);
        // this loop makes the linear actuator displacement independent of battery life using encoders.
        while(opModeIsActive()&&(lm.getCurrentPosition()-ref)>-2750){
            multi.yield();
        }
        lift.setState(0);
        if(isStopRequested()){
            return;
        }

        // unlatch
        multi.waitTime(500);
        driver.setY(-.5);
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
        // straef out and turn
        driver.setX(-1);
        multi.waitTime(800);
        driver.setX(0);
        multi.waitTime(1500);
        driver.setR(-1);
        multi.waitTime(290);
        driver.setR(0);
        multi.waitTime(400);
        driver.setY(1);
        multi.waitTime(350);
        driver.setY(0);
        multi.waitTime(500);
        arm.ColectBoiBack();
        multi.waitTime(5000);
        arm.ColectBoiOff();
        multi.waitTime(500);
        driver.setY(-1);
        multi.waitTime(300);
        driver.setY(0);
        multi.waitTime(500);
        driver.setR(1);
        multi.waitTime(690);
        driver.setR(0);
        multi.waitTime(1000);
        driver.setY(1);
        multi.waitTime(800);
        driver.setY(0);


        arm.rotate(-100);
        multi.waitTime(3000);

        arm.extend();
        multi.waitTime(1000);
        arm.pullyoff();

        multi.waitTime(1000);
        arm.rotate(-20);
    }
}
