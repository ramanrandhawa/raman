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
public class tblefrag extends Fragment {

    WebView web3;

    public tblefrag() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_tblefrag, container, false);
        web3=(WebView)view.findViewById(R.id.web3);
        web3.loadUrl("http://www.banglorecomputereducation.com/retrofit/app_banglorecomp2017/syllabus/tble.html");
        return view;
    }

}
