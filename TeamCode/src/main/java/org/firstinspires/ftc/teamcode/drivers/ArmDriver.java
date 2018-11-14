
package org.firstinspires.ftc.teamcode.drivers;

import com.qualcomm.robotcore.hardware.DcMotor;

public class ArmDriver {

        DcMotor pully;
        DcMotor ang;
        void extend(float distance){
             try {
                     pully.setPower(1);

                     Thread.sleep(200);

             } catch (Exception e){
                     
             }







        }
        void openClaw() {
        }

        void closeClaw(){

        }
        void  rotate(float degrees){

        }
}
