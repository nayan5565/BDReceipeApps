package com.example.nayan.newmybdreceipetest.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.nayan.newmybdreceipetest.R;

/**
 * Created by NAYAN on 11/1/2016.
 */
public class FragmentMail extends Fragment {
    private View view;

    public static FragmentMail getinstance(){
        FragmentMail fragmentMail=new FragmentMail();
        return fragmentMail;
    }
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view=inflater.inflate(R.layout.fragment_mail,container,false);

        return view;
    }
}
