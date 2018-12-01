
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
        ang.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        lsEncoderVal = ang.getCurrentPosition();
        targetAngle = lsEncoderVal;
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
    static final float angleAgility = 0.05f;        // change in motor power per 10ms
    static final float ANG_CONVERSION = 46.666f;    // degrees to encoder units conversion factor
    static final float maxAnglePower = 0.4f;        // maximum motor power
    static final float maxAngleEncoderSpeed = 0;    // motor target speed

    /**
     * make the arm rotate to a position
     * @param angle the target angle, in degrees
     */
    void rotate(float angle){
        targetAngle=(int)(angle*ANG_CONVERSION);
    }
    int targetAngle; // measured in encoder units


    float currentPower = 0; // current angle motor power
    int lsEncoderVal; // last encoder value in encoder units

    public void update(Multitasker man){
        int encoderVal = ang.getCurrentPosition();
        if(Math.abs(targetAngle-encoderVal)<100) {
            man.taskSleep(10);
            return;
        }
        int dEncoder = encoderVal-lsEncoderVal;
        lsEncoderVal = encoderVal;

        float vel = dEncoder/10.0f;
        float targVel = maxAngleEncoderSpeed;
        if(targetAngle<encoderVal){
            targVel=-targVel;
        }

        if(targVel>vel && currentPower<maxAnglePower)
            currentPower+=angleAgility;
        else if(currentPower>-maxAnglePower)
            currentPower-=angleAgility;
        ang.setPower(currentPower);
        man.taskSleep(10);
    }
}
