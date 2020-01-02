package com.banglore.computer.bce;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;


/**
 * A simple {@link Fragment} subclass.
 */
public class cfrag extends Fragment {

WebView web;
    public cfrag() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_cfrag, container, false);
        web=(WebView)view.findViewById(R.id.web4);

web.loadUrl("http://www.banglorecomputereducation.com/retrofit/app_banglorecomp2017/syllabus/csyllabus.html");

        return view;


    }

}
