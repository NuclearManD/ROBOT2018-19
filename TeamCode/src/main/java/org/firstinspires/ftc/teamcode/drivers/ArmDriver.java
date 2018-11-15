
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
        }
        public static final float DIS_COVERSION = 71.423f;
        // this constant is tmp plz change and test

       Servo rot;
       Servo colect;
        void openScoop() {

                //rot = 0;;
        }


        void  rotate(float degrees){

        }
}
