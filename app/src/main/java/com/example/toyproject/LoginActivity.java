package com.example.toyproject;

import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class LoginActivity extends AppCompatActivity {
    private final String TAG = "LoginActivity";
    private final String ip = "http://localhost:8081/";

    private EditText etName;

    public Item info;
    private API api;

    private boolean exist;
    private String url;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        info = new Item();
    }

    public void onClick(View view) {
        etName = (EditText) findViewById(R.id.etName);
        info.setId(etName.getText().toString());

        Log.d(TAG, "name: " + info.getId());

        if(info.getId().equals("")) {
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
                String co = etCode.getText().toString();
                Log.d(TAG, "code: " + co);
                if(co.equals("")) {
                    Toast.makeText(LoginActivity.this, "참여 코드를 입력하세요", Toast.LENGTH_SHORT).show();
                    return;
                }
                info.setCo(co);
                findRoom(info.getCo());
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

    private boolean findRoom(String co) {
        Log.d(TAG, "co is " + co);
        url = ip + "info/find/room/";
        Log.d(TAG, "url is " + url);
        initMyAPI(url);
        Call<Integer> findRoom = api.findRoom(co);
        findRoom.enqueue(new Callback<Integer>() {
            @Override
            public void onResponse(Call<Integer> call, Response<Integer> response) {
                if(response.isSuccessful()) {
                    Integer result = response.body();
                    Log.d(TAG, "response is " + result);
                    if(result == 1)
                        exist = true;
                    else
                        exist = false;

                }
            }

            @Override
            public void onFailure(Call<Integer> call, Throwable t) {
                Log.d(TAG, "findRoom Failed!");
            }
        });

        Log.d(TAG, "findRoom is " + exist);

        return exist;
    }

    private void initMyAPI(String url) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(url)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        api = retrofit.create(API.class);
    }
}
