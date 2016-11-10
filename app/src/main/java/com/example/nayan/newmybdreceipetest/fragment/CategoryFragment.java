package com.example.nayan.newmybdreceipetest.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.nayan.newmybdreceipetest.R;
import com.example.nayan.newmybdreceipetest.model.MCategory;
import com.example.nayan.newmybdreceipetest.model.MCategoryObject;
import com.example.nayan.newmybdreceipetest.recycler.RecyclerViewCatg;
import com.example.nayan.newmybdreceipetest.support.DBManager;
import com.google.gson.Gson;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.JsonHttpResponseHandler;

import org.json.JSONObject;

import java.util.ArrayList;

import cz.msebera.android.httpclient.Header;

/**
 * Created by NAYAN on 10/31/2016.
 */
public class CategoryFragment extends Fragment {
    RecyclerView recyclerView;
    RecyclerViewCatg recyclerViewCatg;
    private static ArrayList<MCategory> categories;
    View view;
    private Gson gson;
    private MCategory category;

    public static CategoryFragment getInstance() {
        CategoryFragment categoryFragment = new CategoryFragment();
        return categoryFragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.frag_category, container, false);
        init();
        getOnlineData();
        getDataFromDb();
        return view;
    }

    private void init() {
        category = new MCategory();

        recyclerViewCatg = new RecyclerViewCatg(getContext());
        gson = new Gson();
        recyclerView = (RecyclerView) view.findViewById(R.id.listCatgory);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setAdapter(recyclerViewCatg);

    }

    private void getOnlineData() {
        final AsyncHttpClient client = new AsyncHttpClient();
        client.post("http://www.radhooni.com/content/api/category.php", new JsonHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                super.onSuccess(statusCode, headers, response);
                MCategoryObject mCategories = gson.fromJson(response.toString(), MCategoryObject.class);
                Log.e("Online ", "data is" + mCategories);
                DBManager.getInstance().addData(mCategories.getCategories(), "categoryId");

                categories = DBManager.getInstance().getData(MCategory.class);
                Log.e("list", "catagories" + categories);
                recyclerViewCatg.setData(categories);
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {
                super.onFailure(statusCode, headers, responseString, throwable);
            }
        });

    }

    public void getDataFromDb() {
        categories = DBManager.getInstance().getData(MCategory.class);
        Log.e("list", "catagories" + categories);
        recyclerViewCatg.setData(categories);
    }


}
