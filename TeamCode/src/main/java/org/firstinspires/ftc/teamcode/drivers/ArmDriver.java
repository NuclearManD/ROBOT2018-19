
package org.firstinspires.ftc.teamcode.drivers;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;

public class ArmDriver {

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
        }

        void extend(float distance){
             try {
                     pully.setPower(1);
                     Thread.sleep((long)(distance*DIS_COVERSION));
             } catch (Exception e){
                     System.out.println("oog something got gooned");
             }

             pully.setPower(0);
            try {
                pully.setPower(-1);
                Thread.sleep((long)(distance*DIS_COVERSION));
            } catch (Exception e){
                System.out.println("oog something got gooned");
            }

            pully.setPower(0);

        }
        public static final float DIS_COVERSION = 71.423f;


       Servo ColAng;
       Servo Rotcol;
        void ColectBoi() {
            try {
                ColAng.setPosition(0);
            }catch (Exception e) {
            }
        }


    void rotate(float angle){
        ang.setTargetPosition((int)(ANG_COVERSION*angle));
    }
    
    public static final float ANG_COVERSION = 6.22f;
}
