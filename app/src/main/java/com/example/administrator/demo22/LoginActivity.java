package com.example.administrator.demo22;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity {
    private String userName, psw, spPsw;
    private EditText et_username;
    private EditText et_psw;
    private Button btn_Login,btn_Register;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        initView();
    }

    private void initView() {
        btn_Register = findViewById(R.id.btn_login_register);
        btn_Register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this, RegisterActivity.class);
                startActivity(intent);
            }
        });
        et_username = findViewById(R.id.ed_name);
        et_psw = findViewById(R.id.ed_psw);
        btn_Login = findViewById(R.id.btn_login);
        btn_Login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                userName = et_username.getText().toString().trim();
                psw = et_psw.getText().toString().trim();
                String md5Psw = (psw);
                spPsw = readPsw(userName);
                if (TextUtils.isEmpty(userName)) {
                    Toast.makeText(LoginActivity.this, "请输入用户名", Toast.LENGTH_SHORT).show();
                } else if (TextUtils.isEmpty(psw)) {
                    Toast.makeText(LoginActivity.this, "请输入密码", Toast.LENGTH_SHORT).show();
                } else if (md5Psw.equals(spPsw)) {
                    Toast.makeText(LoginActivity.this, "登录成功", Toast.LENGTH_SHORT).show();
                    saveLoginStatus(true, userName);
                    Intent data = new Intent();
                    data.putExtra("isLogin", true);
                    setResult(RESULT_OK, data);
                    LoginActivity.this.finish();
                    startActivity(new Intent(LoginActivity.this, MainActivity.class));


                } else if ((spPsw != null && !TextUtils.isEmpty(spPsw) && !md5Psw.equals(spPsw))) {
                    Toast.makeText(LoginActivity.this, "输入的用户名和密码不一致", Toast.LENGTH_SHORT).show();

                } else {
                    Toast.makeText(LoginActivity.this, "此用户名不存在", Toast.LENGTH_SHORT).show();

                }
            }
        });
    }

    private String readPsw(String userName) {
        SharedPreferences sp = getSharedPreferences("loginInfo", MODE_PRIVATE);
        return sp.getString(userName, "");
    }

    private void saveLoginStatus(boolean status, String userName) {
        SharedPreferences sp = getSharedPreferences("loginInfo", MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();
        editor.putBoolean("isLogin", status);
        editor.putString("loginUserName", userName);
        editor.apply();
    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (data != null) {
            String userName = data.getStringExtra("userName");
            if (!TextUtils.isEmpty(userName)) {
                et_username.setText(userName);
                et_username.setSelection(userName.length());
            }
        }
    }
}
