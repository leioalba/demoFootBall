package com.example.administrator.demo22;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Environment;

import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;

import de.hdodenhof.circleimageview.CircleImageView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{
    private TextView tv_name;
    private DrawerLayout drawerLayout;
    private CircleImageView circle;
    private Button btn_local;
    private static final File USER_ICON = new File(Environment.getExternalStorageDirectory(), "user_icon.jpg");
    private static final int CODE_PHOTO_REQUEST = 1;
    private static final int CODE_CAMERA_REQUEST = 2;
    private static final int CODE_PHOTO_CLIP = 3;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.menu_main);
        initView();
        String userInfo = RegisterActivity.username;
        if (userInfo != null){
            tv_name.setText(userInfo);
        }
    }

    private void showDrawerLayout() {
        if (!drawerLayout.isDrawerOpen(Gravity.LEFT)) {
            drawerLayout.openDrawer(Gravity.LEFT);
        } else {
            drawerLayout.closeDrawer(Gravity.LEFT);
        }
    }
    private void initView() {
        drawerLayout=findViewById(R.id.dl_left);
        tv_name=findViewById(R.id.tv_name);
        btn_local=findViewById(R.id.btn_local);
        btn_local.setOnClickListener(this);

    }
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_CANCELED) {
            Toast.makeText(MainActivity.this, "取消", Toast.LENGTH_LONG).show();
            return;
        }
        switch (requestCode) {
            case CODE_CAMERA_REQUEST:
                if (USER_ICON.exists()) {
                    photoClip(Uri.fromFile(USER_ICON));
                }
                break;
            case CODE_PHOTO_REQUEST:
                if (data != null) {
                    photoClip(data.getData());
                }
                break;
            case CODE_PHOTO_CLIP:
                if (data != null) {
                    setImageToHeadView(data);
                }
                break;
        }
        super.onActivityResult(requestCode, resultCode, data);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_local:
                getPicFromLocal();
                break;
            default:
                break;
        }
    }
    private void getPicFromLocal() {
        Intent intent = new Intent();
        intent.setAction(Intent.ACTION_GET_CONTENT);
        intent.setType("image/*");
        startActivityForResult(intent, CODE_PHOTO_REQUEST);
    }

    private void photoClip(Uri uri) {
        Intent intent = new Intent();
        intent.setAction("com.android.camera.action.CROP");
        intent.setDataAndType(uri, "image/*");
        intent.putExtra("crop", "true");
        intent.putExtra("aspectX", 1);
        intent.putExtra("aspectY", 1);
        intent.putExtra("outputX", 100);
        intent.putExtra("outputY", 100);
        intent.putExtra("return-data", true);
        startActivityForResult(intent, CODE_PHOTO_CLIP);
    }

    private void setImageToHeadView(Intent intent) {
        Bundle extras = intent.getExtras();
        if (extras != null) {
            Bitmap photo = extras.getParcelable("data");
            circle.setImageBitmap(photo);
        }
    }
}
