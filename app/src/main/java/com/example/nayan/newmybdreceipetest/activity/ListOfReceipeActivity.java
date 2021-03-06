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
import com.example.nayan.newmybdreceipetest.model.MCategory;
import com.example.nayan.newmybdreceipetest.model.MReceipe;
import com.example.nayan.newmybdreceipetest.model.MReceipeObject;
import com.example.nayan.newmybdreceipetest.recycler.MyAcharAdapter;
import com.example.nayan.newmybdreceipetest.support.DBManager;
import com.example.nayan.newmybdreceipetest.support.EndlessScrollListener;
import com.google.gson.Gson;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.RequestParams;
import com.squareup.picasso.Picasso;

import org.json.JSONObject;

import java.util.ArrayList;

import cz.msebera.android.httpclient.Header;

/**
 * Created by NAYAN on 11/3/2016.
 */
public class ListOfReceipeActivity extends AppCompatActivity {
    public static MCategory category;
    private MyAcharAdapter acharAdapter;
    private RecyclerView recyclerView;
    private Gson gson;


    private ImageView imgAchar;
    private MReceipe mReceipe;
    private ArrayList<MReceipe> receipes;
    TextView txtTitle;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.acar_activity);

        init();

        getDataFromDb();
        getOnlineData();


    }

    private void init() {
        acharAdapter = new MyAcharAdapter(this);
        gson = new Gson();
        mReceipe = new MReceipe();
        imgAchar = (ImageView) findViewById(R.id.imgAcar);
        Log.e("image", "uu" + category.getCategoryPhoto());
        Picasso.with(ListOfReceipeActivity.this)
                .load(MainActivity.IMAGE_URL + category.getCategoryPhoto())

                .into(imgAchar);
        recyclerView = (RecyclerView) findViewById(R.id.listReceipe);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(acharAdapter);
        recyclerView.setOnScrollListener(new EndlessScrollListener((LinearLayoutManager) recyclerView.getLayoutManager()) {
            @Override
            public void onLoadMore(int current_page) {
                getOnlineData();
            }
        });
        txtTitle = (TextView) findViewById(R.id.txtReceipeTitle);
        Typeface tf = Typeface.createFromAsset(getAssets(),
                "fonts/solaiman.ttf");
        txtTitle.setTypeface(tf);
        txtTitle.setText(category.getCategoryTitle());
    }

    private void getOnlineData() {
        AsyncHttpClient client = new AsyncHttpClient();
        RequestParams requestParams = new RequestParams();

        requestParams.put("cat", category.getCategoryId());
        requestParams.put("startat", receipes.size());
        client.post("http://www.radhooni.com/content/api/v2/recipes_a.php", requestParams, new JsonHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                super.onSuccess(statusCode, headers, response);
                MReceipeObject mReceipeObject = gson.fromJson(response.toString(), MReceipeObject.class);
                Log.e("data", "ss" + mReceipeObject);
                DBManager.getInstance().addData(mReceipeObject.getRecipes(), "Id");
                getDataFromDb();


            }

            @Override
            public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {
                super.onFailure(statusCode, headers, responseString, throwable);
            }
        });
    }

    private void getDataFromDb() {
        receipes = DBManager.getInstance().getData(MReceipe.class, "select a.Id,a.CategoryId,a.Title,a.Ingredients,a.Process,a.CategoryTitle,a.SearchTag,a.Photo,a.Thumb,a.TypeOne,a.TypeTwo,a.TypeFive, b.Fav  from MReceipe a left join MFavourite b on a.Id=b.id where  a.CategoryId='" + category.getCategoryId() + "' OR a.TypeOne='" + category.getCategoryId() + "' OR a.TypeTwo='" + category.getCategoryId() + "' OR a.TypeFive='" + category.getCategoryId() + "'");
        Log.e("receipe", "ss" + receipes.size());
        acharAdapter.setData(receipes);

    }

    @Override
    protected void onResume() {
        super.onResume();
        getDataFromDb();
    }
}
