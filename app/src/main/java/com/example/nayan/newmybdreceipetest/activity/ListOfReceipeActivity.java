package com.example.nayan.newmybdreceipetest.activity;

import android.graphics.Typeface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.nayan.newmybdreceipetest.R;
import com.example.nayan.newmybdreceipetest.model.MReceipe;
import com.example.nayan.newmybdreceipetest.model.MReceipeObject;
import com.example.nayan.newmybdreceipetest.recycler.MyAcharAdapter;
import com.google.gson.Gson;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.RequestParams;
import com.squareup.picasso.Picasso;

import org.json.JSONObject;

import cz.msebera.android.httpclient.Header;

/**
 * Created by NAYAN on 11/3/2016.
 */
public class ListOfReceipeActivity extends AppCompatActivity {
    private MyAcharAdapter acharAdapter;
    private RecyclerView recyclerView;
    private Gson gson;
    private int id;
    private String image,title;
    private ImageView imgAchar;
    private MReceipe mReceipe;
    TextView txtTitle;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.acar_activity);
        image=getIntent().getStringExtra("image");
        title=getIntent().getStringExtra("title");
        init();
        id = getIntent().getIntExtra("id", 0);

        getOnlineData();


    }

    private void init() {
        acharAdapter = new MyAcharAdapter(this);
        gson = new Gson();
        mReceipe=new MReceipe();
        imgAchar=(ImageView)findViewById(R.id.imgAcar);
        Log.e("image","uu"+image);
        Picasso.with(ListOfReceipeActivity.this)
                .load(MainActivity.IMAGE_URL+image)

                .into(imgAchar);
        recyclerView = (RecyclerView) findViewById(R.id.listReceipe);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(acharAdapter);
        txtTitle=(TextView)findViewById(R.id.txtReceipeTitle);
        Typeface tf = Typeface.createFromAsset(getAssets(),
                "fonts/solaiman.ttf");
        txtTitle.setTypeface(tf);
        txtTitle.setText(title);
    }

    private void getOnlineData() {
        AsyncHttpClient client = new AsyncHttpClient();
        RequestParams requestParams = new RequestParams();

        requestParams.put("cat", id);
        requestParams.put("startat", "0");
        client.post("http://www.radhooni.com/content/api/v2/recipes_a.php", requestParams, new JsonHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                super.onSuccess(statusCode, headers, response);
                MReceipeObject mReceipeObject = gson.fromJson(response.toString(), MReceipeObject.class);
                Log.e("data", "ss" + mReceipeObject);

                acharAdapter.setData(mReceipeObject.getRecipes());



            }

            @Override
            public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {
                super.onFailure(statusCode, headers, responseString, throwable);
            }
        });
    }
}
