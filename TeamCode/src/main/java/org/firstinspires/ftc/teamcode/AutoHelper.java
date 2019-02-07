package org.firstinspires.ftc.teamcode;

import com.disnodeteam.dogecv.CameraViewDisplay;
import com.disnodeteam.dogecv.detectors.roverrukus.SamplingOrderDetector;
import com.qualcomm.hardware.modernrobotics.ModernRoboticsI2cCompassSensor;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;

import org.firstinspires.ftc.teamcode.drivers.ArmDriver;
import org.firstinspires.ftc.teamcode.drivers.LinearActuator;
import org.firstinspires.ftc.teamcode.drivers.Mecanum4WheelDriver;
import org.firstinspires.ftc.teamcode.drivers.Multitasker;
import org.firstinspires.ftc.teamcode.drivers.TelemetryUpdater;

public abstract class AutoHelper extends LinearOpMode {

    DcMotor lm;
    Mecanum4WheelDriver driver;
    LinearActuator lift;
    ArmDriver arm;
    Multitasker multi;
    DcMotor[] motors;
    ModernRoboticsI2cCompassSensor compass;

    private SamplingOrderDetector detector;

    public void initHardware(){
        lm = hardwareMap.dcMotor.get("lift");
        //sensorColor = hardwareMap.get(ColorSensor.class, "sensor_color_distance");
        driver = new Mecanum4WheelDriver();
        lift = new LinearActuator(lm);
        arm = new ArmDriver(hardwareMap.dcMotor.get("pully"), hardwareMap.dcMotor.get("angle"), hardwareMap.crservo.get("goboi"));
        multi = new Multitasker(this);
        motors = new DcMotor[]{hardwareMap.dcMotor.get("fl"), hardwareMap.dcMotor.get("fr"), hardwareMap.dcMotor.get("bl"), hardwareMap.dcMotor.get("br")};
        compass = hardwareMap.get(ModernRoboticsI2cCompassSensor.class, "compass");
        //gyro = (IntegratingGyroscope) modernRoboticsI2cGyro;
        driver.init(motors, -1, .1f);
        multi.addTask(driver);
        multi.addTask(arm);
        multi.addTask(new TelemetryUpdater());

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
    String detectCube(){
        return detector.getLastOrder().toString();
    }
    void turn(float angle){
        float mag = (float)Math.copySign(.3,angle);
        driver.setR(mag);
        multi.waitTime(10);
        angle = Math.abs(angle);
        while(opModeIsActive() && Math.abs(driver.rotation)<angle){
            multi.yield();
            if(Math.abs(driver.rotation)-angle>-5f)driver.setR(mag/5);
        }
        driver.setR(0);
    }
    void goY(double d){
        float mag = (float)Math.copySign(.3,d);
        driver.setY(mag);
        multi.waitTime(10);
        d = Math.abs(d);
        while(opModeIsActive() && Math.abs(driver.distance)<d){
            multi.yield();
            if(Math.abs(driver.distance)-d>-.1f)driver.setY(mag/5);
        }
        driver.setY(0);
    }
    void lower(){

        // save start position
        long ref = lm.getCurrentPosition();

        // drop
        lift.setState(1);
        // this loop makes the linear actuator displacement independent of battery life using encoders.
        while (opModeIsActive() && (lm.getCurrentPosition() - ref) > -8035) {
            multi.yield();
        }
        lift.setState(0);
        if (isStopRequested()) {
            return;
        }

        // unlatch
        multi.waitTime(100);
        driver.setY(-.3);
        multi.waitTime(600);
        driver.setY(0);

        // retract
        lift.setState(-1);
        while (opModeIsActive() && (lm.getCurrentPosition() - ref) < -10) {
            multi.yield();
        }
        lift.setState(0);
        if (isStopRequested()) {
            return;
        }
        driver.setY(.3);
        multi.waitTime(400);
        driver.setY(0);
    }
    void stopMotors(){
        driver.motorSet(0,0,0,0);
        arm.off();
        arm.pullyoff();
        lift.setState(0);
    }
    void waitShort(){
        multi.waitTime(400);
    }
    void waitLong(){
        multi.waitTime(2500);
    }
    void shutdown() {
        stopMotors();
        detector.getLastOrder();
        detector.disable();
        detector.getLastOrder();
        detector.disable();
    }
    void lowerAndSample(){

        // save start position
        long ref = lm.getCurrentPosition();

        // drop
        lift.setState(1);
        // this loop makes the linear actuator displacement independent of battery life using encoders.
        while (opModeIsActive() && (lm.getCurrentPosition() - ref) > -8035) {
            multi.yield();
        }
        lift.setState(0);
        if (isStopRequested()) {
            return;
        }
        // detect before moving right or left
        String option = detectCube();
        detector.getLastOrder();
        detector.disable();

        // unlatch
        multi.waitTime(100);
        driver.setY(-.3);
        multi.waitTime(600);
        driver.setY(0);

        // retract
        lift.setState(-1);
        while (opModeIsActive() && (lm.getCurrentPosition() - ref) < -10) {
            multi.yield();
        }
        lift.setState(0);
        if (isStopRequested()) {
            return;
        }
        driver.setY(.3);
        multi.waitTime(400);
        driver.setY(0);

        if(option.equals("LEFT")){
            turn(-120);
            waitShort();
            goY(.875);
        }else if(option.equals("CENTER")){
            turn(-85);
            waitShort();
            goY(.875);
        }else if(option.equals("RIGHT")){
            turn(-60);
            waitShort();
            goY(.875);
        }
        telemetry.addData("opt=",option);
        telemetry.update();
    }
}
