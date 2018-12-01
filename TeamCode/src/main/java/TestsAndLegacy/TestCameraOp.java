package TestsAndLegacy;

import android.graphics.Bitmap;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.drivers.CameraInput;
import org.firstinspires.ftc.teamcode.drivers.imageProcessor;

import java.io.FileOutputStream;
import java.io.IOException;

@TeleOp(name="Camera Test", group="2018")
public class TestCameraOp extends LinearOpMode {

    public void runOpMode(){
        imageProcessor processor = new imageProcessor();
        int spot = processor.getLandingSpot();
        if(spot == 1){

        }
        else if(spot == -1)
        System.out.println("BE GONE DIE ERREURS!!!!!!!  !@#%$^$%^*&^&%$^#$#$*&%(^&*%*^$%#$^@#%$#&%^%$*&%^^&&*^$%#$^@%$#&^%*$^%(&*^&%%^$%&#$^#@&#%*$^%&^%&^$%&$^");
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
