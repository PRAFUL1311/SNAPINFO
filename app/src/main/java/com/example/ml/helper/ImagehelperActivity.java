package com.example.ml.helper;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.FileProvider;
import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.ImageDecoder;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import com.example.ml.R;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
public class ImagehelperActivity extends AppCompatActivity {
     private int REQUEST_PICK_IMAGE=1000;
    private int REQUEST_CAPTURE_IMAGE=1001;
    private ImageView imageViewInput;
    private TextView textViewOutput;



    private  File photoFile;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_imagehelper);
        imageViewInput=findViewById(R.id.imageViewInput);
        textViewOutput=findViewById(R.id.textViewOutput);


    if(Build.VERSION.SDK_INT>=Build.VERSION_CODES.M) {
        if (checkSelfPermission(Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED)
        {
            requestPermissions(new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, 0);
        }
    }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

         Log.d(ImagehelperActivity.class.getSimpleName(),"Grant result for "+permissions[0] + "is" + grantResults[0]);
    }
    public  void onPickImage(View V){
        Intent intent=new Intent(Intent.ACTION_GET_CONTENT);
        intent.setType("image/*");

       startActivityForResult(intent,REQUEST_PICK_IMAGE);
    }


   public  void onStartCamera(View V){
       photoFile= createPhotoFile();

        Uri fileUri= FileProvider.getUriForFile(this,"com.iago.fileprovider",photoFile);
        Intent intent=new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        intent.putExtra(MediaStore.EXTRA_OUTPUT,fileUri);
        startActivityForResult(intent,REQUEST_CAPTURE_IMAGE);
    }

   private File createPhotoFile(){
       File photofilelDir=new File(getExternalFilesDir(Environment.DIRECTORY_PICTURES),"ML_IMAGE_HELPER");
    if(!photofilelDir.exists()){
                photofilelDir.mkdirs();
           }
 String name=new SimpleDateFormat("yyyymMMdd_HHmmss").format(new Date());
            File file =new File(photofilelDir.getPath()  + File.separator +name);
            return file;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(resultCode == RESULT_OK){
            if(requestCode ==REQUEST_PICK_IMAGE){
                Uri uri=data.getData();
                Bitmap bitmap=loadfromUri(uri);
                imageViewInput.setImageBitmap(bitmap);
                runclassification(bitmap);
            }
            else if(requestCode==REQUEST_CAPTURE_IMAGE){
                Log.d("ML","RECIVERD CALLBACK FROM CAMERA");
                Bitmap bitmap = BitmapFactory.decodeFile(photoFile.getAbsolutePath());
                imageViewInput.setImageBitmap(bitmap);
                runclassification(bitmap);
            }
        }
    }

    private  Bitmap loadfromUri(Uri uri){
        Bitmap bitmap=null;
          try {
            if(Build.VERSION.SDK_INT>Build.VERSION_CODES.O_MR1){
                ImageDecoder.Source source=ImageDecoder.createSource(getContentResolver(),uri);
                bitmap=ImageDecoder.decodeBitmap(source);
            }
            else{
                bitmap= MediaStore.Images.Media.getBitmap(getContentResolver(),uri);
            }
          }
     catch (IOException e){
              e.printStackTrace();
     }

        return bitmap;

    }

    protected  void runclassification(Bitmap bitmap) {
    }

    protected TextView getTextViewOutput(){
        return textViewOutput;
    }

    protected ImageView getImageViewInput(){
        return  imageViewInput;
    }
}