package org.firstinspires.ftc.teamcode.drivers;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import java.util.ArrayList;

public class Multitasker {
    private LinearOpMode master;

    private ArrayList<Long> taskTimes = new ArrayList<Long>();
    private ArrayList<Task> tasks = new ArrayList<Task>();

    int currentTask = -1;

    public void taskSleep(long time) {
        taskTimes.set(currentTask,System.currentTimeMillis()+time);
    }

    public void addTask(Task t){
        tasks.add(t);
        taskTimes.add(new Long(0));
    }

    public Multitasker(LinearOpMode you){
        master = you;
    }

    public void yield(){
        for(currentTask=0;currentTask<tasks.size();currentTask++){
            Task t = tasks.get(currentTask);
            if(taskTimes.get(currentTask)<System.currentTimeMillis()){
                t.update(this);
            }
        }
        currentTask = -1;
    }
    public void waitTime(long time){
        time+=System.currentTimeMillis();
        while(master.opModeIsActive()&&time>System.currentTimeMillis()){
            yield();
            if(master.isStopRequested()){
                return;
            }
            try{
                Thread.sleep(2);
            }catch(Exception e){}
        }
    }
}
