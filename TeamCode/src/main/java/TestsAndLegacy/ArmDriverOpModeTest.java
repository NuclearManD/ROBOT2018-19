package TestsAndLegacy;


import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;

import org.firstinspires.ftc.teamcode.drivers.ArmDriver;
import org.firstinspires.ftc.teamcode.drivers.Multitasker;
import org.firstinspires.ftc.teamcode.drivers.TelemetryUpdater;

//@TeleOp(name="Arm Test Op", group="2018")
public class ArmDriverOpModeTest extends LinearOpMode {

    DcMotor pully;


    public void runOpMode() {
        Multitasker man = new Multitasker(this);
        pully = hardwareMap.dcMotor.get("pully");
        ArmDriver driver = new ArmDriver(pully, hardwareMap.dcMotor.get("angle"),hardwareMap.crservo.get("goboi"));
        man.addTask(driver);
        man.addTask(new TelemetryUpdater());
        driver.rotate(90);

        // it's fine everthing is fine
        waitForStart();
        while(opModeIsActive()){
            man.yield();
        }
    }
}
