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
import android.widget.ImageView;
import android.widget.TextView;

import com.example.nayan.newmybdreceipetest.R;
import com.example.nayan.newmybdreceipetest.model.MReceipe;
import com.example.nayan.newmybdreceipetest.recycler.MyAcharAdapter;
import com.google.gson.Gson;
import com.jewel.dbmanager.DBManager;

import java.util.ArrayList;

/**
 * Created by NAYAN on 10/25/2016.
 */
public class FragDemo extends Fragment {
    private View view;
    private TextView tv;
    private MyAcharAdapter acharAdapter;
    private RecyclerView recyclerView;
    private Gson gson;
    private int id;
    private String image,title;
    private ImageView imgAchar;
    private MReceipe mReceipe;
    private ArrayList<MReceipe> receipes;
    TextView txtTitle;

    public static FragDemo getInstance() {
        FragDemo fragDemo = new FragDemo();
        return fragDemo;
    }


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.acar_activity, container, false);
        init();
        getDataFromDb();
        return view;
    }
    private void init() {
        acharAdapter = new MyAcharAdapter(getContext());
        mReceipe=new MReceipe();

        recyclerView = (RecyclerView)view. findViewById(R.id.listReceipe);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setAdapter(acharAdapter);

    }
    private void getDataFromDb(){
        receipes= DBManager.getInstance().getData(MReceipe.class,"select * from MReceipe where Fav='1'");
        Log.e("receipe", "ss" + receipes.size());
        acharAdapter.setData(receipes);

    }
}
