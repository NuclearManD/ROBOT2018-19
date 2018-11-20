
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
       int pos;
       int neoPos;
        void ColectBoiSet() {
            try {
                ColAng.setPosition(pos);
            } catch (Exception e) {
                System.out.println("oog something got gooned");
            }
        }
        void ColectBoiMove() {
            try {
                ColAng.setPosition(neoPos);
            }catch (Exception e) {
                System.out.println("oog something got gooned");
            }
        }


    void rotate(float angle){
        ang.setTargetPosition((int)(ANG_COVERSION*angle));
        ang.setPower(.5);
    }
    
    public static final float ANG_COVERSION = 46.666f;
}
