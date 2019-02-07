package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

@TeleOp(name="ClaimSideSamplePark")
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
        goY(1);
        turn(90);
        goY(1.5);
        arm.ColectBoiBack();
        multi.waitTime(2000);
        goY(-3.21);
    }
}
