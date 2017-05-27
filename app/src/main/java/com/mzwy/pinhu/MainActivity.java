package com.mzwy.pinhu;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;


public class MainActivity extends Activity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        new Handler().postDelayed(new Runnable() {
            public void run() {
                //execute the task
                Intent _intent = new Intent(MainActivity.this, HomeWebviewActivity.class);
//                Intent _intent = new Intent(MainActivity.this,WebviewActivity.class);
                startActivity(_intent);
                finish();
            }
        }, 1500);

    }


}
