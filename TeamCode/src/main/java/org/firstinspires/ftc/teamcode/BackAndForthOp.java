package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.teamcode.drivers.Mecanum4WheelDriver;
import org.firstinspires.ftc.teamcode.drivers.MovementDriver;


@TeleOp(name="Back'n Forth", group="2018")
public class BackAndForthOp extends LinearOpMode {
    static final float FORWARD_SPEED = -0.6f;
    public void runOpMode(){
        Mecanum4WheelDriver driver = new Mecanum4WheelDriver();
        try {
            DcMotor[] motors = {hardwareMap.dcMotor.get("fl"),hardwareMap.dcMotor.get("fr"),hardwareMap.dcMotor.get("bl"),hardwareMap.dcMotor.get("br")};
            driver.init(motors);
        }catch (Exception e){
            System.out.println("\n------    HARDWARE ERROR IN INIT!   ------\n");
            e.printStackTrace();
        }
        try {
            waitForStart();
        }catch (Exception e) {
            e.printStackTrace();

        }
        for(int i=0;i<3;i++) {//robot runs the commands 3 times
            try {//forwards, backwards
                driver.setY(1);
                Thread.sleep(1000);
                driver.setY(-1);
                Thread.sleep(1000);
            }catch(Exception e){
                telemetry.addLine("ERROR IN MAIN CODE!!!!");
                return;
            }
        }
        driver.setY(0);

    }
}
