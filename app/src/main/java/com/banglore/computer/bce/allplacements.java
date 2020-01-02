package com.banglore.computer.bce;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.ActionMenuView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.WindowManager;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;

import java.util.ArrayList;

import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

public class allplacements extends AppCompatActivity {

    ActionMenuView action;
    Toolbar toolbar;
    RecyclerView list;
    FirebaseAuth firebaseauth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_allplacements);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_SECURE,WindowManager.LayoutParams.FLAG_SECURE);
        toolbar = (Toolbar)findViewById(R.id.placement_toolbar);
        list = (RecyclerView) findViewById(R.id.placement_recycle);
        setSupportActionBar(toolbar);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Our Placed Trainees");
        toolbar.setTitleTextColor(Color.WHITE);
        serverconnection con = new serverconnection();
        retrofitapi retro = con.doconnect();
        final ProgressDialog dialog=new ProgressDialog(this,R.style.mydialog);
        firebaseauth=FirebaseAuth.getInstance();
        dialog.setCancelable(false);
        dialog.show();
        retro.fetchplacement(new Callback<responseplacement>() {
            @Override
            public void success(responseplacement responseplacement, Response response) {
              dialog.dismiss();
                ArrayList<responseplacement.placement> arry=new ArrayList<>();

                arry=responseplacement.data;


               recycler_placement adapter=new recycler_placement(arry,allplacements.this);

                RecyclerView.LayoutManager manager=new LinearLayoutManager(allplacements.this);
                list.setLayoutManager(manager);
                list.setAdapter(adapter);

            }

            @Override
            public void failure(RetrofitError error) {
                dialog.dismiss();
                Toast.makeText(allplacements.this,"Try again..", Toast.LENGTH_SHORT).show();
            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.menu_toolbar,menu);
        return super.onCreateOptionsMenu(menu);
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId()==android.R.id.home)
        {
            Intent i=new Intent(this,MainActivity.class);
            i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(i);
        }
        if(item.getItemId()==R.id.itm_logout)
        {
          firebaseauth.signOut();
            Intent it=new Intent(allplacements.this,googlesignin.class);
            it.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(it);
        }

        return true;
    }
    @Override
    public void onBackPressed() {

        Intent i=new Intent(this,MainActivity.class);
        i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(i);

    }

}
