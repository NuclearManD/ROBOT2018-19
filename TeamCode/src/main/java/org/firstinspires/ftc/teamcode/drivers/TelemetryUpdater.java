package org.firstinspires.ftc.teamcode.drivers;

/**
 * Created by SCRoboticsDev on 12/1/2018.
 */

public class TelemetryUpdater extends Task {
    public void update(Multitasker man){
        man.master.telemetry.update();
        man.taskSleep(30); // about 32 FPS
    }
}
