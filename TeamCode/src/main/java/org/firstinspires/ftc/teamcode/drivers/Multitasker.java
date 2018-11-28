package org.firstinspires.ftc.teamcode.drivers;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

public class Multitasker {
    LinearOpMode master;
    public Multitasker(LinearOpMode you){
        master = you;
    }
    public void yield(){

    }
    public void waitTime(long time){
        time+=System.currentTimeMillis();
        while(master.opModeIsActive()&&time<System.currentTimeMillis()){
            yield();
            if(master.isStopRequested()){
                return;
            }
            try{
                Thread.sleep(5);
            }catch(Exception e){

            }
        }
    }
}
