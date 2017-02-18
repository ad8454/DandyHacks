package edu.rit.ad8454.dandyhack;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.google.android.gms.vision.Frame;

public class ImageFrameActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image_frame);

        // get absolute file path to saved image
        Intent intent = getIntent();
        String mCurrentPhotoPath = intent.getStringExtra(MainActivity.INTENT_MSG);

        Bitmap myBitmap = null;
        try{
            myBitmap = BitmapFactory.decodeFile(mCurrentPhotoPath);
        }
        catch (Exception e){
            Log.e("ERR cannot open image", e.getMessage());
        }
        Frame frame = new Frame.Builder().setBitmap(myBitmap).build();

    }
}
