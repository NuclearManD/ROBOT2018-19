package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

@TeleOp(name="DepotSideSamplePark")
public class SampleClaimAndPark extends AutoHelper {
    @Override
    public void runOpMode() {
        initHardware();
        waitForStart();
        lowerAndSample();
        //multi.waitTime(100000);   // pranx
        turn(-90);
        goY(.71);
        turn(40);
        goY(.9);
        turn(85);
        goY(1.75);
        arm.ColectBoiBack();
        multi.waitTime(2500);
        arm.ColectBoiOff();
        multi.waitTime(100);
        turn(180);
        rideWall(3.8);
        arm.rotate(-30);
        multi.waitTime(2500);
        arm.extend();
        multi.waitTime(500);
        arm.pullyoff();
        arm.rotate(0);
        multi.waitTime(1000);
    }
}
