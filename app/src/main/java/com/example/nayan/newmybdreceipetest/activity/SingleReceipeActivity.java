package com.example.nayan.newmybdreceipetest.activity;

import android.graphics.Typeface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.nayan.newmybdreceipetest.R;
import com.squareup.picasso.Picasso;

/**
 * Created by NAYAN on 11/3/2016.
 */
public class SingleReceipeActivity extends AppCompatActivity {
    private String ingredins, process,title,image;
    private TextView txtProcess,txtMaterials,txtTitle;
    private ImageView imgSingle;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.single_receipe_acivity);
        Typeface tf = Typeface.createFromAsset(getAssets(),
                "fonts/solaiman.ttf");
        process=getIntent().getStringExtra("process");
        image=getIntent().getStringExtra("image");
        ingredins=getIntent().getStringExtra("imgredins");
        title=getIntent().getStringExtra("title");
        txtProcess=(TextView)findViewById(R.id.process);
        txtMaterials=(TextView)findViewById(R.id.materials);
        txtTitle=(TextView)findViewById(R.id.txtTitle);
        imgSingle=(ImageView)findViewById(R.id.imgSingle);

        Picasso.with(this)
                .load(MainActivity.IMAGE_URL+image)
                .into(imgSingle);

        txtProcess.setTypeface(tf);
        txtTitle.setTypeface(tf);
        txtMaterials.setTypeface(tf);
        txtProcess.setText(process);
        txtMaterials.setText(ingredins);
        txtTitle.setText(title);
    }
}
