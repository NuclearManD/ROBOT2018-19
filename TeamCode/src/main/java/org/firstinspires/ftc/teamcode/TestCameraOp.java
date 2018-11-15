package org.firstinspires.ftc.teamcode;

import android.graphics.Bitmap;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.drivers.CameraInput;

import java.io.FileOutputStream;
import java.io.IOException;

public class TestCameraOp extends LinearOpMode {

    public void runOpMode(){
        CameraInput inp = new CameraInput();
        Bitmap img = inp.takePic();
        try (FileOutputStream out = new FileOutputStream("iphonexfootage.png")) {
            img.compress(Bitmap.CompressFormat.PNG, 100, out); // bmp is your Bitmap instance
            // PNG is a lossless format, the compression factor (100) is ignored
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
