package com.example.nayan.newmybdreceipetest.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.example.nayan.newmybdreceipetest.R;

/**
 * Created by NAYAN on 11/3/2016.
 */
public class SingleReceipeActivity extends AppCompatActivity {
    private String ingredins, process;
    private TextView txtProcess,txtMaterials;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.single_receipe_acivity);
        process=getIntent().getStringExtra("process");
        ingredins=getIntent().getStringExtra("imgredins");
        txtProcess=(TextView)findViewById(R.id.process);
        txtMaterials=(TextView)findViewById(R.id.materials);
        txtProcess.setText(process);
        txtMaterials.setText(ingredins);
    }
}
