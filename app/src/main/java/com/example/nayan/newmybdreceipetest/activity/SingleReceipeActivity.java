package com.example.nayan.newmybdreceipetest.activity;

import android.graphics.Typeface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.nayan.newmybdreceipetest.R;
import com.example.nayan.newmybdreceipetest.model.MFavourite;
import com.example.nayan.newmybdreceipetest.model.MReceipe;
import com.example.nayan.newmybdreceipetest.support.DBManager;
import com.squareup.picasso.Picasso;

/**
 * Created by NAYAN on 11/3/2016.
 */
public class SingleReceipeActivity extends AppCompatActivity {
    public static MReceipe receipe;
    private MFavourite mFavourite;
    private TextView txtProcess, txtMaterials, txtTitle, txtFav;
    private ImageView imgSingle, imgFav;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.single_receipe_acivity);
        mFavourite=new MFavourite();
        Typeface tf = Typeface.createFromAsset(getAssets(),
                "fonts/solaiman.ttf");
        txtProcess = (TextView) findViewById(R.id.process);
        txtMaterials = (TextView) findViewById(R.id.materials);
        txtTitle = (TextView) findViewById(R.id.txtTitle);
        txtFav = (TextView) findViewById(R.id.txtFav);
        imgSingle = (ImageView) findViewById(R.id.imgSingle);
        imgFav = (ImageView) findViewById(R.id.imgFav);

        Picasso.with(this)
                .load(MainActivity.IMAGE_URL + receipe.getThumb())
                .into(imgSingle);

        txtProcess.setTypeface(tf);
        txtTitle.setTypeface(tf);
        txtMaterials.setTypeface(tf);
        txtProcess.setText(receipe.getProcess());
        txtMaterials.setText(receipe.getIngredients());
        txtTitle.setText(receipe.getTitle());
        clickFav();

        txtFav.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (receipe.getFav() == 1) {
                    mFavourite.setId(receipe.getId());
                    mFavourite.setFav(0);

                    Toast.makeText(SingleReceipeActivity.this, "UnFavourite", Toast.LENGTH_SHORT).show();
                } else {
                    mFavourite.setFav(1);
                    mFavourite.setId(receipe.getId());
                    Toast.makeText(SingleReceipeActivity.this, "Favourite", Toast.LENGTH_SHORT).show();
                }
                receipe.setFav(mFavourite.getFav());
                DBManager.getInstance().addData(mFavourite, "id");
                clickFav();
            }

        });
    }

    public void clickFav() {
        if (receipe.getFav() == 1) {

            imgFav.setImageResource(R.drawable.fav);

        } else {
            imgFav.setImageResource(R.drawable.unfav);
        }


    }

    @Override
    protected void onResume() {
        super.onResume();
        clickFav();
        Log.e("res","res");
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();

//        MainActivity.tabLayout.getTabAt(0).select();
    }
}
