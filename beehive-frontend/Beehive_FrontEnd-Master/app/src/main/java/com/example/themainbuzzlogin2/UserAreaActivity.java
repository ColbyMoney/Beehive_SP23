package com.example.themainbuzzlogin2;

//import androidx.appcompat.app.AppCompatActivity;
//
//import android.os.Bundle;
//import android.widget.EditText;
//import android.widget.TextView;
//
//public class UserAreaActivity extends AppCompatActivity {
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_user_area);
//
//        final EditText etName = (EditText) findViewById(R.id.etName);
//        final EditText etAge = (EditText) findViewById(R.id.etAge); // ignore these for now - JG
//        final TextView welcomeMessage = (TextView) findViewById(R.id.textView2);
//    }
//}


import static android.media.MediaRecorder.VideoSource.CAMERA;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import android.Manifest;
import java.net.HttpURLConnection;
import java.net.URL;
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Random;

public class UserAreaActivity extends AppCompatActivity {
    public static final int CAMERA = 18;
    ImageView imageView;
    FloatingActionButton fab;
    Uri imageUri;
    Button BTN;


    String[] myArray = new String[]{"Ice", "Leaves", "Flowers", "Sun", "Bee", "Pumpkin", "King", "School", "Time"};

    int rnd = new Random().nextInt(myArray.length);

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        //Get rid of Action bar and title
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getSupportActionBar().hide();

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_area);

        imageView = findViewById(R.id.imageView1);
        fab = findViewById(R.id.fab);

        //setContentView(R.layout.activity_user_area);
        TextView tv1 = (TextView)findViewById(R.id.textView5);
        tv1.setText(myArray[rnd]);

        //opening the camera
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //this will open our default camera
                Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                Uri imagePath = createImage();
                intent.putExtra(MediaStore.EXTRA_OUTPUT, imagePath);
                startActivityForResult(intent, CAMERA);
            }
        });

        BTN = findViewById(R.id.button);

        BTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), PhotoFeedActivity.class);
                startActivity(intent);
            }
        });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode,Intent data){
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode ==CAMERA) {
            if (resultCode == Activity.RESULT_OK) {
                Toast.makeText(this, "Image captured successfully", Toast.LENGTH_SHORT).show();
                imageView.setImageURI(imageUri);
            }
        }
    }

    private Uri createImage(){
        Uri uri = null;
        ContentResolver resolver = getContentResolver();

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q){
            uri = MediaStore.Images.Media.getContentUri(MediaStore.VOLUME_EXTERNAL_PRIMARY);
            //DCIM
            //Pictures
        }else {
            uri = MediaStore.Images.Media.EXTERNAL_CONTENT_URI;
        }
        String imgName = String.valueOf(System.currentTimeMillis());
        ContentValues contentValues = new ContentValues();
        contentValues.put(MediaStore.Images.Media.DISPLAY_NAME, imgName+".jpg");
        //Start of new code
        try {
            URL url = new URL("http://153.91.238.178:8080/post/upload");
            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            con.setRequestMethod("POST");

            String postData = "username=user&password=pass";
            con.setDoOutput(true);
            DataOutputStream out = new DataOutputStream(con.getOutputStream());
            out.writeBytes(postData);
            out.flush();
            out.close();

            BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
            String inputLine;
            StringBuffer content = new StringBuffer();
            while ((inputLine = in.readLine()) != null) {
                content.append(inputLine);
            }
            in.close();

            System.out.println(content.toString());
        }catch (Exception e){
            e.printStackTrace();
        }

        contentValues.put(MediaStore.Images.Media.RELATIVE_PATH,"Pictures/"+"My Images"); //New Version
        //End of New Code
        //contentValues.put(MediaStore.Images.Media.RELATIVE_PATH,"Pictures/"+"My Images/"); //hey baby bois this is where the image is saved
        Uri finalUri = resolver.insert(uri, contentValues);
        imageUri = finalUri;
        return finalUri;

        /*

   |\---/|
   | ,_, |
    \_`_/-..----.
 ___/ `   ' ,""+ \  Cat Ghost
(__...'   __\    |`.___.';
  (_,...'(_,.`__)/'.....+

         */

    }
}