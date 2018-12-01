package TestsAndLegacy;

import com.qualcomm.hardware.modernrobotics.ModernRoboticsI2cColorSensor;
import com.qualcomm.robotcore.hardware.ColorSensor;
import com.qualcomm.robotcore.hardware.NormalizedRGBA;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.robotcore.internal.opmode.TelemetryImpl;

/**
 * Created by Kyle on 12/3/2017.
 */

public class ServoFlapDriver1712 {
    Servo rarm;
    Servo larm;
    ModernRoboticsI2cColorSensor rs;
    ModernRoboticsI2cColorSensor ls;
    int value=0;
    private final int BLUE=-1;
    private final int RED=1;

    public ServoFlapDriver1712(Servo left, Servo right){
        rarm=right;
        larm=left;
        flapLeftUp();
        flapRightUp();
    }
    public ServoFlapDriver1712(Servo left, Servo right, ModernRoboticsI2cColorSensor r, ModernRoboticsI2cColorSensor l){
        rarm=right;
        larm=left;
        rs=r;
        ls=l;
        ls.enableLed(true);
        rs.enableLed(true);
        flapLeftUp();
        flapRightUp();
    }
    public void ledEn(boolean dont_die){
        ls.enableLed(dont_die);
        rs.enableLed(dont_die);
    }
    public void flapLeftDown(){
        larm.setDirection(Servo.Direction.REVERSE);
        larm.setPosition(0);
    }
    public void flapRightDown(){
        rarm.setDirection(Servo.Direction.FORWARD);
        rarm.setPosition(.02);
    }
    public void flapLeftUp(){
        larm.setDirection(Servo.Direction.FORWARD);
        larm.setPosition(.2);
    }
    public void flapRightUp(){
        rarm.setDirection(Servo.Direction.REVERSE);
        rarm.setPosition(0.25);
    }
    public float[] readLeft(Telemetry telemetry){

        ls.enableLed(true);
        NormalizedRGBA result = ls.getNormalizedColors();
        float[] r = {result.red,  result.blue};
        return r;


    }
    public float[] readRight(Telemetry telemetry){
        NormalizedRGBA result = rs.getNormalizedColors();
        float[] r = {result.red, result.blue};
        return r;
    }

    public float[] readRightAlt(){
        float[] r = {rs.red(),rs.green()};
        return r;
    }

    public int getValue(){
        int[] dr={0,0,0,0};
        int[] dl={0,0,0,0};
        for(int i=0;i<100;i++){
            //int[] j=readRight();
            //dr[0]+=j[0];
            //dr[1]+=j[1];
            //dr[2]+=j[2];
            //dr[3]+=j[3];
            //j=readLeft();
            //dl[0]+=j[0];
            //dl[1]+=j[1];
            //dl[2]+=j[2];
            //dl[3]+=j[3];
        }
        if(dr[1]>dl[0]){
            dr=dl;
        }
        if(dr[0]>dr[2])
            return RED;
        return BLUE;
    }
    public final void sleep(long milliseconds) {
        try {
            Thread.sleep(milliseconds);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}
