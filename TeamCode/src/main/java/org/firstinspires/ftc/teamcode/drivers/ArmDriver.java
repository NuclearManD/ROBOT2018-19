
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


       Servo rot;
       Servo colect;
        void openScoop() {

                //rot = 0;;
        }


        void  rotate(float degrees){
            try {
                ang.setPower(1);
                Thread.sleep(500);
            }catch (Exception e){
                System.out.println("oog something got gooned");
            }
            ang.setPower(0);
        }
    public static final float ANG_COVERSION = 1;
}
