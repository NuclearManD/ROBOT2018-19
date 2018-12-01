package TestsAndLegacy;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.teamcode.drivers.Mecanum4WheelDriver;
import org.firstinspires.ftc.teamcode.drivers.MovementDriver;
import org.firstinspires.ftc.teamcode.drivers.Multitasker;


//@TeleOp(name="Back'n Forth", group="2018")
public class BackAndForthOp extends LinearOpMode {
    static final float FORWARD_SPEED = -0.6f;
    public void runOpMode(){
        Mecanum4WheelDriver driver = new Mecanum4WheelDriver();
        Multitasker multi = new Multitasker(this);
        try {
            DcMotor[] motors = {hardwareMap.dcMotor.get("fl"),hardwareMap.dcMotor.get("fr"),hardwareMap.dcMotor.get("bl"),hardwareMap.dcMotor.get("br")};
            driver.init(motors,-1,.1f);
            multi.addTask(driver);
        }catch (Exception e){
            System.out.println("\n------    HARDWARE ERROR IN INIT!   ------\n");
            e.printStackTrace();
        }
        try {
            waitForStart();
        }catch (Exception e) {
            e.printStackTrace();
        }
        for(int i=0;i<3;i++){
            //robot runs the commands 3 times
            //forwards, backwards
            driver.setY(1);
            multi.waitTime(1000);
            driver.setY(-1);
            multi.waitTime(1000);
        }
        driver.setY(0);
        driver.setX(0);
        driver.setR(0);
    }
}
