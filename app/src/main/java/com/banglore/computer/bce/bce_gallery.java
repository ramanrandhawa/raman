package com.banglore.computer.bce;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
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

public class bce_gallery extends AppCompatActivity {

    RecyclerView recycler;

    Toolbar toolbar;
    FirebaseAuth firebaseauth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bce_gallery);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_SECURE,WindowManager.LayoutParams.FLAG_SECURE);
        recycler=(RecyclerView)findViewById(R.id.recycler_gallery);
        toolbar=(Toolbar)findViewById(R.id.gallery_toolbar);

        setSupportActionBar(toolbar);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Gallery");
        toolbar.setTitleTextColor(Color.WHITE);
        firebaseauth=FirebaseAuth.getInstance();
        serverconnection con = new serverconnection();
        retrofitapi retro = con.doconnect();
        final ProgressDialog dialog=new ProgressDialog(this,R.style.mydialog);

        dialog.setCancelable(false);
        dialog.show();

        retro.ferchpics(new Callback<responsepics>() {
            ArrayList<String> array=new ArrayList<String>();
            @Override
            public void success(responsepics responsepics, Response response) {
                dialog.dismiss();
               ArrayList<responsepics.innerclass> arry=responsepics.data;

                for(int i=0;i<arry.size();i++)
                {
                    array.add(arry.get(i).getFn());
                }
                adapter_gallery adapter=new adapter_gallery(array,getApplicationContext());
                RecyclerView.LayoutManager manager=new GridLayoutManager(bce_gallery.this,3);
                recycler.setLayoutManager(manager);
                recycler.setAdapter(adapter);

            }

            @Override
            public void failure(RetrofitError error) {
   dialog.dismiss();
                Toast.makeText(bce_gallery.this, "Try again..", Toast.LENGTH_SHORT).show();
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
            Intent it=new Intent(bce_gallery.this,googlesignin.class);
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
