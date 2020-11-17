package com.classy.class_2021a_ands_2;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.media.Image;
import android.os.Bundle;
import android.util.Base64;
import android.util.Log;
import android.widget.ImageView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.io.ByteArrayOutputStream;

public class MainActivity extends AppCompatActivity {

    private ImageView main_IMG_a;
    private ImageView main_IMG_b;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        main_IMG_a = findViewById(R.id.main_IMG_a);
        main_IMG_b = findViewById(R.id.main_IMG_b);

        //v// get bitmap from image view.
        //v// convert to base64 String
        //v// Encrypt Data
        //v// Upload

        start();

        displayImage();
    }

    private void start() {
        try {
            Bitmap bitmap1 = ImageUtil.getBitmapFromImageView(main_IMG_a);
            String base64 = ImageUtil.bitmapToBase64(bitmap1);
            String encryptedBase64 = CryptoUtil.encrypt(base64);
            MyFirebaseRLDB.uploadString(encryptedBase64);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void displayImage() {
        MyFirebaseRLDB.downloadData(new MyFirebaseRLDB.CallBack_DataIsReady() {
            @Override
            public void CallBack_DataIsReady(String encryptedBase64) {
                try {
                    String decryptedBase64 = CryptoUtil.decrypt(encryptedBase64);
                    Bitmap bitmap2 = ImageUtil.base64ToBitmap(decryptedBase64);
                    main_IMG_b.setImageBitmap(bitmap2);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

}