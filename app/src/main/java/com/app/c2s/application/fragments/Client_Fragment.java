package com.app.c2s.application.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.app.c2s.application.R;


/**
 * Created by Nawel on 7/25/2015.
 */
public class Client_Fragment extends Fragment{

    View myView;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        myView = inflater.inflate(R.layout.client_layout, container, false);
        Log.d("Client_Fragment", "Client_Fragment oncreate");
        return myView;
    }
}
