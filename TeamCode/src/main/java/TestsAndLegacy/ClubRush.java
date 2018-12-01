package TestsAndLegacy;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.robotcore.internal.opmode.TelemetryImpl;
import org.firstinspires.ftc.teamcode.drivers.MovementDriver;
/*
 MainOp 2017 Edition

 Made with:
 * MovementDriver 1.x.x
 * FiringMechanism 1.0.0
*/

public class ClubRush extends LinearOpMode {
    // constructor calls LinearOpMode
    private MovementDriver drive;
       // declares variable drive type: MovementDriver

    Servo arm;
    // declares variable arm
    public Telemetry telemetry = new TelemetryImpl(this);
    // creates new Telemetry
    public void runOpMode(){
        try {
            DcMotor[] tmp={hardwareMap.dcMotor.get("fl"),hardwareMap.dcMotor.get("fr"),hardwareMap.dcMotor.get("bl"),hardwareMap.dcMotor.get("br")}; //this is our rear wheel motors.
            // sets DcMotor array to a tmp fl, fr, bl, br
            drive=new MovementDriver();
            // sets var drive to new MovementDriver
            arm=hardwareMap.servo.get("arm");
            // sets var arm to the hardwareMap arm
            arm.setPosition(0);
            // sets arm position to 0
        }catch (Exception e){
            System.out.println("\n------    HARDWARE ERROR IN INIT!   ------\n");
            // if something goes wrong it will print an error message
            e.printStackTrace();
            telemetry.addData("Main op", "%s error", e.toString());
            // if something goes wrong "Main op", and "%s error" will be added to the telemetry array
            telemetry.update();
            // if something goes wrong telemetry will update
        }
        try {
            waitForStart();
            // it waits for it to dasboot
        }catch (Exception e) {
            e.printStackTrace();
            // use try block just incase it dose not dasboot
        }
        boolean state=false;
        boolean ls=false;
        while (opModeIsActive()) {
            drive.setRotspeed(gamepad2.left_stick_x*.7f);
            // sets up gamepad2's left joystick
            drive.setSpeed(gamepad2.right_stick_y*.7f);
            // sets up gamepad2's right joystick
            if(gamepad2.a!=ls){
                ls=gamepad2.a;
                if(ls==true){
                    // state was =to false but if gamepad2 is =to ls then state=true
                     if(state){
                        arm.setPosition(180);
                        // if state=true then it moves the arm 180 degrees
                    }else
                        arm.setPosition(0);
                     // if not then it is set to 0
                    state=!state;
                }
            }
        }
    }
}

