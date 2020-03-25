package com.example.handllerapp;

import androidx.appcompat.app.AppCompatActivity;

import android.nfc.Tag;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    TextView txt ;
    Button btn ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        txt=findViewById(R.id.show);
        btn=findViewById(R.id.clickbtn);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new Thread() {
                    public void run() {
                        synchronized (this) {
                            try {

                                Log.i("Tag", "==========start");
                                wait(1000);
                                txt.setText("Text Changed Now");
                                Log.i("Tag", "==========changed");
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }

                        }
                    }


                }.start();
            }
            //
        });
    }
}
