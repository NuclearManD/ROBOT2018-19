package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

@TeleOp(name="DepotSideSampleClaim")
public class SampleClaimNoPark extends AutoHelper {
    @Override
    public void runOpMode() {
        initHardware();
        waitForStart();
        lowerAndSample();
        //multi.waitTime(100000);   // pranx
        turn(-90);
        goY(.71);
        turn(40);
        goY(.8);
        turn(95);
        goY(1.75);
        arm.ColectBoiBack();
        multi.waitTime(1500);
        arm.ColectBoiOff();
    }
}
