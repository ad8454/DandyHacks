package edu.rit.ad8454.dandyhack;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.util.SparseArray;
import android.widget.TextView;

import com.google.android.gms.vision.Frame;
import com.google.android.gms.vision.text.Text;
import com.google.android.gms.vision.text.TextBlock;
import com.google.android.gms.vision.text.TextRecognizer;

public class ImageFrameActivity extends AppCompatActivity {

    private TextRecognizer detector;
    private StringBuilder detectResults;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image_frame);

        // get absolute file path to saved image
        Intent intent = getIntent();
        String mCurrentPhotoPath = intent.getStringExtra(MainActivity.INTENT_MSG);
        detectResults = new StringBuilder();
        detector = new TextRecognizer.Builder(getApplicationContext()).build();
        Bitmap myBitmap = null;

        try{
            myBitmap = BitmapFactory.decodeFile(mCurrentPhotoPath);
        }
        catch (Exception e){
            Log.e("ERR cannot open image", e.getMessage());
        }

        TextView result = (TextView) findViewById(R.id.result);
        Frame frame = new Frame.Builder().setBitmap(myBitmap).build();

        if(detector.isOperational()){
            SparseArray<TextBlock> results = detector.detect(frame);

            for(int i=0; i<results.size(); i++){
                // Text blocks are like paragraphs
                TextBlock resBlock = results.valueAt(i);

                // Get Lines in Text Block
                for(Text line: resBlock.getComponents()){
                    detectResults.append(line.getValue()).append("\n");
                }
            }
        }

        // Set text view
        result.setText(detectResults.toString());
    }
}
