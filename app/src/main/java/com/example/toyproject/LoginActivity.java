package com.example.toyproject;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

public class LoginActivity extends AppCompatActivity {
    private final String TAG = "LoginActivity";

    private EditText etName;

    Item info;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        info = new Item();
    }

    public void onClick(View view) {
        etName = (EditText) findViewById(R.id.etName);
        info.setName(etName.getText().toString());

        Log.d(TAG, "name: " + info.getName());

        if(info.getName().equals("")) {
            Toast.makeText(this, "닉네임을 입력하세요", Toast.LENGTH_SHORT).show();
            return;
        }

        final EditText etCode = new EditText(LoginActivity.this);
        AlertDialog.Builder builder = new AlertDialog.Builder(LoginActivity.this);
        builder.setTitle("참여 코드를 입력하세요");
        builder.setView(etCode);
        builder.setPositiveButton("확인", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                String code = etCode.getText().toString();
                Log.d(TAG, "code: " + code);
                if(code.equals("")) {
                    Toast.makeText(LoginActivity.this, "참여 코드를 입력하세요", Toast.LENGTH_SHORT).show();
                    return;
                }
                info.setCode(code);
            }
        });
        builder.setNegativeButton("취소", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                return;
            }
        });
        builder.show();

        switch(view.getId()) {
            case R.id.btnJoin:

        }
    }
}
