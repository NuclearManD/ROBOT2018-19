package TestsAndLegacy;
import com.disnodeteam.dogecv.CameraViewDisplay;
import com.disnodeteam.dogecv.DogeCV;
import com.disnodeteam.dogecv.detectors.roverrukus.SamplingOrderDetector;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import org.firstinspires.ftc.teamcode.drivers.Mecanum4WheelDriver;
//begin stolen code
//@TeleOp(name="LowerAndSample")
public class LowerSample extends OpMode {
    private SamplingOrderDetector detector;

    @Override
    public void init() { //code that runs once when the robot is initilized
        telemetry.addData("Status", "Please Oh Lord Just work for once");
        Mecanum4WheelDriver driver = new Mecanum4WheelDriver();
        //DcMotor[] motors = {hardwareMap.dcMotor.get("fl"), hardwareMap.dcMotor.get("fr"), hardwareMap.dcMotor.get("bl"), hardwareMap.dcMotor.get("br")}; //uncomment these later
        //driver.init(motors,-1,.1f); //uncomment these later
        //start fancy DogeCV Stuff
        // Setup detector
        detector = new SamplingOrderDetector(); // Create the detector
        detector.init(hardwareMap.appContext, CameraViewDisplay.getInstance()); // Initialize detector with app context and camera
        detector.useDefaults(); // Set detector to use default settings

        detector.downscale = 0.8; // How much to downscale the input frames

        // Optional tuning
        //detector.areaScoringMethod = DogeCV.AreaScoringMethod.MAX_AREA; // Can also be PERFECT_AREA
        detector.perfectAreaScorer.perfectArea = 10000; // if using PERFECT_AREA scoring
        detector.maxAreaScorer.weight = 0.001;

        detector.ratioScorer.weight = 15;
        detector.ratioScorer.perfectRatio = 1.0;

        detector.enable(); // Start detector
    }
    @Override
    public void init_loop() {
        telemetry.addData("Last Order" , detector.getLastOrder().toString()); // The last known result

    }
    @Override
    public void start() {
        detector.disable();
        String pos = detector.getLastOrder().toString();
        //L,C,R are based off being hooked on the lander, facing away from the lander
        if(pos.equals("LEFT")) {
        }
        if(pos.equals("CENTER")) {
        }
        if(pos.equals("RIGHT")) {
        }
    }
    @Override
    public void loop() {
    }
    @Override
    public void stop() {
        detector.disable();
    }

}
