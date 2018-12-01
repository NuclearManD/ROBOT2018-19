
package org.firstinspires.ftc.teamcode.drivers;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;

public class ArmDriver extends Task{

        DcMotor pully;
        DcMotor ang;

    /**
     *
     * @param a  Pully motor
     * @param b  Angle Motor
     */
    public ArmDriver(DcMotor a, DcMotor b){
        pully=a;
        ang=b;
        ang.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        ang.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        lsEncoderVal = ang.getCurrentPosition();
        targetAngle = lsEncoderVal;
        lastTime=System.currentTimeMillis();
    }

        void extend(float distance){
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

        void  power() {

            pully.setPower(1);
        }
       Servo angServo;
       Servo goboi;
       float pos = .5f;
       float neoPos = .1f;
        void ColectBoiSet() {
            try {
                angServo.setPosition(pos);

            } catch (Exception e) {
                System.out.println("oog something got gooned");
            }
        }
        void ColectBoiMove() {
            try {
                angServo.setPosition(neoPos);
            }catch (Exception e) {
                System.out.println("oog something got gooned");
            }
        }

        void ColectBoiRot() {
            try {
                goboi.setPosition(1);
            }catch (Exception e) {
                System.out.println("oog something got gooned");
            }
        }

    // Angle Control configuration
    static final float angleAgility = 0.01f;        // change in motor power per 10ms
    static final float ANG_CONVERSION = 9f;         // degrees to encoder units conversion factor
    static final float maxAnglePower = 0.6f;        // maximum motor power
    static final float maxAngleEncoderSpeed = 1200; // motor target speed in encoder units per 1s
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

        man.master.telemetry.addLine("UPDATE;");
        int encoderVal = ang.getCurrentPosition();

        int dEncoder = encoderVal-lsEncoderVal;
        lsEncoderVal = encoderVal;

        if(Math.abs(targetAngle-encoderVal)<5) {
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

        if(targVel>vel && currentPower<maxAnglePower)
            currentPower+=angleAgility;
        else if(currentPower>-maxAnglePower)
            currentPower-=angleAgility;
        ang.setPower(currentPower);

        man.taskSleep(6);
        man.master.telemetry.addLine("Val: "+encoderVal);
        man.master.telemetry.addLine("Trg: "+targetAngle);
        man.master.telemetry.addLine("Vel: "+vel);
        man.master.telemetry.addLine("Pwr: "+currentPower);
    }
}
