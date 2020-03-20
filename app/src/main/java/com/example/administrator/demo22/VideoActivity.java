package com.example.administrator.demo22;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageButton;
import android.widget.VideoView;

import java.io.IOException;

public class VideoActivity extends AppCompatActivity {
    private ImageButton ibv1,ibv2,ibv3,ibv4,ibv5;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video);
        ibv1=findViewById(R.id.ib_v1);
        ibv1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(VideoActivity.this,Video1Activity.class);
                startActivity(intent);
            }
        });
        ibv2=findViewById(R.id.ib_v2);
        ibv2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(VideoActivity.this,Video2Activity.class);
                startActivity(intent);
            }
        });
        ibv3=findViewById(R.id.ib_v3);
        ibv3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(VideoActivity.this,Video3Activity.class);
                startActivity(intent);
            }
        });
        ibv4=findViewById(R.id.ib_v4);
        ibv4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(VideoActivity.this,Video4Activity.class);
                startActivity(intent);
            }
        });
        ibv5=findViewById(R.id.ib_v5);
        ibv5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(VideoActivity.this,Video5Activity.class);
                startActivity(intent);
            }
        });
    }


}
