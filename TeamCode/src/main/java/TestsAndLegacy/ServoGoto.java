package TestsAndLegacy;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.AutoHelper;

//@TeleOp(name="GotoServo")
public class ServoGoto extends AutoHelper {

    @Override
    public void runOpMode() throws InterruptedException {
        initHardware();
        waitForStart();

        deactivateParker();
        while(opModeIsActive())
            Thread.sleep(100);
    }
}
