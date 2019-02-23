package TestsAndLegacy;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.drivers.MRDistanceSensor;

@TeleOp(name="Test Distaancce Senseerz")
public class TestMRDistanceSensor extends LinearOpMode {
    @Override
    public void runOpMode() {
        MRDistanceSensor sensor = new MRDistanceSensor();
        sensor.setup(hardwareMap.i2cDevice.get("distance"));
        while(true){
            telemetry.addLine("->"+sensor.getDistance());
            telemetry.update();
        }
    }
}
