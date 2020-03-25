package com.example.handllerapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.nfc.Tag;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
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
        final Handler handler=new Handler(){
            @Override
            public void handleMessage(@NonNull Message msg) {
                //this what i want to change in main thread from different thread
                txt.setText("Text Changed Now");
            }
        };
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new Thread() {
                    public void run() {
                        synchronized (this) {
                            try {

                                Log.i("Tag", "==========start");
                                wait(10000);
                                //what i want to update in different thread
                                //here just want to tell that i finshed no more
                                handler.sendEmptyMessage(0);
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
