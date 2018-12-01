package org.firstinspires.ftc.teamcode.drivers;


import android.graphics.Bitmap;

public class imageProcessor {
    public Bitmap image;
    public imageProcessor(){
        CameraInput camera = new CameraInput();
        image = camera.takePic();
    }

    public int getLandingSpot(){
        return -1;
    }

    int getMineralLocation(){
        return -1;
    }
}