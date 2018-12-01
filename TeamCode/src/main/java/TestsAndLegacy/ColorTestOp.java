package TestsAndLegacy;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.ColorSensor;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.ModernRoboticsI2cColorSensor2;

/**
 * Created by SCRoboticsDev on 11/27/2018.
 */

@TeleOp(name="ColorTegzt", group="2018")
public class ColorTestOp extends LinearOpMode {
    public void runOpMode(){
        ColorSensor sensor = hardwareMap.colorSensor.get("color");
        while(true) {
            telemetry.addLine("Blu: " + sensor.blue());
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
