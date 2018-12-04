
package org.firstinspires.ftc.teamcode.drivers;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;

public class ArmDriver extends Task{

        DcMotor pully;
        DcMotor ang;

    /**
     *  @param a  Pully motor
     * @param b  Angle Motor
     * @param goboi
     */
    public ArmDriver(DcMotor a, DcMotor b, CRServo goboi){
        pully=a;
        ang=b;
        ang.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        ang.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);

        this.goboi=goboi;

        lsEncoderVal = ang.getCurrentPosition();
        targetAngle = lsEncoderVal;
        lastTime=System.currentTimeMillis();
    }

    @Deprecated
    public void extend(float distance){
        if (distance>0) {
            try {
                pully.setPower(1);
                Thread.sleep((long) (distance * DIS_COVERSION));
            } catch (Exception e) {
                System.out.println("oog something got gooned");
            }
        } else {
            try {
                pully.setPower(-1);
                Thread.sleep((long) (distance * -DIS_COVERSION));
            } catch (Exception e) {
                System.out.println("oog something got gooned");
            }
        }
        pully.setPower(0);

    }
    public static final float DIS_COVERSION = 10.3f;

    public void  extend() {
        pully.setPower(1);
    }
    public void  retract() {
        pully.setPower(-1);
    }
    public void  pullyoff() {
        pully.setPower(0);
    }
    Servo angServo;
    CRServo goboi;
    float pos = .5f;
    float neoPos = .1f;
    void ColectBoiSet() {
        angServo.setPosition(pos);
    }
    void ColectBoiMove() {
        angServo.setPosition(neoPos);
    }

    public void ColectBoiOn() {
        goboi.setPower(1);
    }
    public void ColectBoiOff() {
        goboi.setPower(0);
    }
    public void ColectBoiBack() {
        goboi.setPower(-1);
    }

    // Angle Control configuration
    static final float angleAgility = 0.05f;        // change in motor power per 10ms
    static final float angleAgilityNear = 0.03f;        // change in motor power per 10ms when n34r
    static final float ANG_CONVERSION = 9f;         // degrees to encoder units conversion factor
    static final float maxAnglePower = 0.6f;        // maximum motor power
    static final float maxAngleEncoderSpeed = 300; // motor target speed in encoder units per 1s
    static final float maxAngleEncoderSpeedNearTarget = 100;  // motor target speed when near target in encoder units per 1s
    static final float nearDistance = 200;          // distance regarded as near target
    /**
     * make the arm rotate to a position
     * @param angle the target angle, in degrees
     */
    public void rotate(float angle){
        targetAngle=(int)(angle*ANG_CONVERSION);
    }
    int targetAngle; // measured in encoder units


    float currentPower = 0; // current angle motor power
    int lsEncoderVal; // last encoder value in encoder units

    long lastTime = 0; // last time we updated.

    public void update(Multitasker man){

        // keep track of time; Multitasker cannot garantee that time will be kept.
        float dt = System.currentTimeMillis()-lastTime;
        lastTime=System.currentTimeMillis();

        int encoderVal = ang.getCurrentPosition();

        int dEncoder = encoderVal-lsEncoderVal;
        lsEncoderVal = encoderVal;

        if(Math.abs(targetAngle-encoderVal)<(10*ANG_CONVERSION)) {
            man.taskSleep(6);
            return;
        }

        float vel = dEncoder*1000f/dt;
        float targVel = maxAngleEncoderSpeed;

        if(Math.abs(targetAngle-encoderVal)<nearDistance){
            targVel=maxAngleEncoderSpeedNearTarget;
        }

        if(targetAngle<encoderVal){
            targVel=-targVel;
        }
         float lmao = angleAgility;
        if(Math.abs(targetAngle-encoderVal)<nearDistance){
            targVel=angleAgilityNear;
        }
        if(targVel>vel && currentPower<maxAnglePower)
            currentPower+=angleAgility;
        else if(currentPower>-maxAnglePower)
            currentPower-=angleAgility;
        ang.setPower(currentPower);

        man.taskSleep(3);
        man.master.telemetry.addLine("Val: "+encoderVal);
        man.master.telemetry.addLine("Trg: "+targetAngle);
        man.master.telemetry.addLine("Vel: "+vel);
        man.master.telemetry.addLine("Pwr: "+currentPower);
    }
}