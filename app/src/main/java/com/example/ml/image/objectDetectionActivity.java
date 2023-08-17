package com.example.ml.image;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.util.Log;

import androidx.annotation.NonNull;

import com.example.ml.helper.ImagehelperActivity;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.mlkit.vision.common.InputImage;
import com.google.mlkit.vision.label.ImageLabeling;
import com.google.mlkit.vision.objects.DetectedObject;
import com.google.mlkit.vision.objects.ObjectDetection;
import com.google.mlkit.vision.objects.ObjectDetector;
import com.google.mlkit.vision.objects.defaults.ObjectDetectorOptions;

import java.util.List;

public class objectDetectionActivity extends ImagehelperActivity {

    private ObjectDetector objectdetector;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        ObjectDetectorOptions options=new ObjectDetectorOptions.Builder()
                .setDetectorMode(ObjectDetectorOptions.SINGLE_IMAGE_MODE)
                .enableMultipleObjects()
                .enableClassification()  // Optional
                .build();
        objectdetector = ObjectDetection.getClient(options);
    }

    @Override
    protected void runclassification(Bitmap bitmap) {
        InputImage inputImage= InputImage.fromBitmap(bitmap,0);
        objectdetector.process(inputImage).addOnSuccessListener(new OnSuccessListener<List<DetectedObject>>() {
            @Override
            public void onSuccess(List<DetectedObject> detectedObjects) {
               if(!detectedObjects.isEmpty()){
                   StringBuilder stringBuilder=new StringBuilder();
                   for(DetectedObject object:detectedObjects){
                        if(!object.getLabels().isEmpty()){
                            String labels=object.getLabels().get(0).getText();
                            stringBuilder.append(labels).append("\n");
                            Log.d("Object detection","object detetected" + labels);
                        }
                   }
                   getTextViewOutput().setText(stringBuilder.toString());
               }
               else{
                   getTextViewOutput().setText("could not detect");
               }
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                 e.printStackTrace();
            }
        });
    }
}
