package com.example.petapp;

import android.os.Bundle;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class PetInfoActivity extends AppCompatActivity {

    private TextView petId;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.pet_info);

        petId = (TextView)findViewById(R.id.petId);

        Bundle arguments = getIntent().getExtras();
        String id = arguments.get("petid").toString();



        petId.setText(id);
    }
}
