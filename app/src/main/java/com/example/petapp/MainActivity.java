package com.example.petapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    private EditText petid;
    private ProgressBar progBar;
    private Boolean isSuccess;
    private Integer id;
    private TextView errorLog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        petid = (EditText) findViewById(R.id.searchId);
        errorLog = (TextView)findViewById(R.id.errorLog);
        progBar = (ProgressBar) findViewById(R.id.progressBar);
        isSuccess = false;
        progBar.setVisibility(View.INVISIBLE);
    }

    public void onClick() {
        progBar.setVisibility(View.VISIBLE);
        PetAPI petAPI = PetAPI.retrofit.create(PetAPI.class);
        Call<PetInfo> call = petAPI.getPet();

        call.enqueue(new Callback<PetInfo>() {
            @Override
            public void onResponse(Call<PetInfo> call, Response<PetInfo> response) {
                if (response.isSuccessful()) {
                    PetInfo pet = response.body();
                    isSuccess = true;
                    progBar.setVisibility(View.INVISIBLE);
                    id = pet.getId();
                } else {
                    ResponseBody errorBody = response.errorBody();
                    try {
                        Toast.makeText(MainActivity.this, errorBody.string(),
                                Toast.LENGTH_SHORT).show();
                        progBar.setVisibility(View.INVISIBLE);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }

            @Override
            public void onFailure(Call<PetInfo> call, Throwable t) {
                errorLog.setText("Что-то пошло не так!");
                progBar.setVisibility(View.INVISIBLE);
            }
        });

        if (isSuccess) {
            Intent intent = new Intent(this, PetInfoActivity.class);
            intent.putExtra("id", id.intValue());
            startActivity(intent);
        }
    }
}