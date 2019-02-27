package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

@TeleOp(name="CraterSideSampleClaim")
public class SampleParkThenClaim extends AutoHelper {
    @Override
    public void runOpMode() {
        initHardware();
        waitForStart();
        lowerAndSample();
        //multi.waitTime(100000);   // pranx
        turn(-90);
        goY(.71);
        turn(40);
        goY(1);
        turn(-87);
        rideWall(2.1);
        arm.ColectBoiBack();
        multi.waitTime(1700);
        arm.ColectBoiOff();
        rideWall(-2.87);
        activateParker();
        multi.waitTime(500);
    }
}
