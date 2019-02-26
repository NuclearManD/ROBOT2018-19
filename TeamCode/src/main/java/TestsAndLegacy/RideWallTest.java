package TestsAndLegacy;

import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.AutoHelper;

@TeleOp(name="TestWallRider")
public class RideWallTest extends AutoHelper {
    @Override
    public void runOpMode() throws InterruptedException {
        initHardware();
        waitForStart();
        while(opModeIsActive()){
            rideWall(3);
            multi.waitTime(1000);
            rideWall(3);
            multi.waitTime(1000);
        }
    }
}
