package TestsAndLegacy;

import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.teamcode.AutoHelper;
import org.firstinspires.ftc.teamcode.drivers.Multitasker;


//@TeleOp(name="Gyrotester")
public class GyroTester extends AutoHelper {
    public Multitasker multi;

    @Override
    public void runOpMode() {
        
        waitForStart();
        super.initHardware();
        telemetry.log().add("Gyro Calibrating. Do Not Move!");
        //modernRoboticsI2cGyro.calibrate();
        ElapsedTime timer = new ElapsedTime();
        // Wait until the gyro calibration is complete
        timer.reset();
        /*while (!isStopRequested() && modernRoboticsI2cGyro.isCalibrating())  {
            telemetry.addData("calibrating", "%s", Math.round(timer.seconds())%2==0 ? "|.." : "..|");
            telemetry.update();
            sleep(50);
        }*/

        telemetry.log().clear(); telemetry.log().add("Gyro Calibrated. Press Start.");
        telemetry.clear(); telemetry.update();
        //int heading = modernRoboticsI2cGyro.getHeading();

        //driver.agility = .05f;
        //int turn = heading + 90;
        //driver.setY(1);
        int safety = 0;
        /*while (modernRoboticsI2cGyro.getHeading() != turn){
            telemetry.addData("Alpha", modernRoboticsI2cGyro.getHeading());
            safety++;
            if(safety == 20){
                break;
            }
        }
        driver.setY(0);
*/
    }
}
