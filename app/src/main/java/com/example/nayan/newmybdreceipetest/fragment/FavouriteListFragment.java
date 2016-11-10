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
import com.example.nayan.newmybdreceipetest.support.DBManager;
import com.google.gson.Gson;

import java.util.ArrayList;

/**
 * Created by NAYAN on 10/25/2016.
 */
public class FavouriteListFragment extends Fragment {
    private View view;
    private TextView tv;
    private MyAcharAdapter acharAdapter;
    private RecyclerView recyclerView;
    private Gson gson;
    private int id;
    private String image, title;
    private ImageView imgAchar;
    private MReceipe mReceipe;
    private ArrayList<MReceipe> receipes;
    TextView txtTitle;

    public static FavouriteListFragment getInstance() {
        FavouriteListFragment favouriteListFragment = new FavouriteListFragment();
        return favouriteListFragment;
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
        imgAchar = (ImageView) view.findViewById(R.id.imgAcar);
        imgAchar.setVisibility(View.GONE);
//        Picasso.with(getContext())
//                .load(MainActivity.IMAGE_URL+ ListOfReceipeActivity.category.getCategoryPhoto())
//                .into(imgAchar);
        acharAdapter = new MyAcharAdapter(getContext());
        mReceipe = new MReceipe();

        recyclerView = (RecyclerView) view.findViewById(R.id.listReceipe);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setAdapter(acharAdapter);

    }

    private void getDataFromDb() {
        receipes = DBManager.getInstance().getData(MReceipe.class, "select a.Id,a.CategoryId,a.Title,a.Ingredients,a.Process,a.CategoryTitle,a.SearchTag,a.Photo,a.Thumb, b.Fav  from MReceipe a inner join MFavourite b on a.Id=b.id where b.Fav='1'");
        Log.e("receipe", "ss" + receipes.size());
        acharAdapter.setData(receipes);

    }

    @Override
    public void onResume() {
        super.onResume();
        getDataFromDb();
    }
}
