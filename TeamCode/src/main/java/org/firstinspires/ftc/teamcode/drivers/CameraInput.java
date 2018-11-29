package org.firstinspires.ftc.teamcode.drivers;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.hardware.Camera;
import android.hardware.camera2.CameraDevice;

/**
 * Created by SCRoboticsDev on 11/8/2018.
 */
// https://developer.android.com/reference/android/hardware/camera2/CameraCaptureSession
public class CameraInput {
    Camera cam;
    public CameraInput() {
        cam=Camera.open();
        cam.startPreview();

    }
    byte[] data=null;

    public Bitmap takePic(){
        data=null;
        Camera.PictureCallback raw = new Camera.PictureCallback() {
            @Override
            public void onPictureTaken(byte[] bytes, Camera camera) {
                data=bytes;
            }
        };
        cam.takePicture(null, raw, null);
        while(data==null);
        return BitmapFactory.decodeByteArray(data,0,data.length);
    }
}
