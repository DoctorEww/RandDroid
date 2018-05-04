package com.drewgriess.testapplication;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.security.Security;

public class MainActivity extends AppCompatActivity {

    public String photohash;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button btn = findViewById(R.id.getHash);
        Button photoBtn = findViewById(R.id.photoHash);
        final TextView text = findViewById(R.id.getText);
        Button EncyptAES = findViewById(R.id.EncryptAES);
        //final TextView textInput = findViewById(R.id.editText);

        if (!hasCamera())
        {
            btn.setEnabled(false);
        }
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                HashValue hash = new HashValue(textInput.getText().toString());
//                try {
//                    text.setText(hash.getHexHash());
//                } catch (NoSuchAlgorithmException e) {
//                    e.printStackTrace();
//                } catch (UnsupportedEncodingException e) {
//                    e.printStackTrace();
//                }
                text.setText(getPhotohash());
            }
        });

        photoBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                launchCamera(view);
            }
        });
        EncyptAES.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                launchCamera(view);
            }
        });

    }

    public boolean hasCamera()
    {
        return getPackageManager().hasSystemFeature(PackageManager.FEATURE_CAMERA_ANY);
    }
    public static final int REQUEST_CATPTURE = 1;
    private String b64;
    public void launchCamera(View v)
    {
        Intent i = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        startActivityForResult(i , REQUEST_CATPTURE);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        //super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == REQUEST_CATPTURE && resultCode == RESULT_OK)
        {
            Bundle extras = data.getExtras();
            Bitmap photo = (Bitmap) extras.get("data");
            b64 = ImageUtil.convert(photo);
           HashValue hash = new HashValue(b64);
            try {
                photohash = (hash.getHexHash());
            } catch (NoSuchAlgorithmException e) {
                e.printStackTrace();
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
        }

    }
    public String getPhotohash()
    {
        return photohash;
    }
    public String getB64()
    {
        return b64;
    }


}
