package com.banglore.computer.bce;

import android.content.Intent;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.WindowManager;

import com.google.firebase.auth.FirebaseAuth;

import java.util.ArrayList;

import uk.co.senab.photoview.PhotoViewAttacher;

public class zoom_galleryimage extends AppCompatActivity {

    PhotoViewAttacher attacher;
    ViewPager pager;
    Toolbar toolbar;
    FirebaseAuth firebaseauth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_zoom_galleryimage);
        pager=(ViewPager)findViewById(R.id.galleryzoom_viewpager);
        toolbar=(Toolbar)findViewById(R.id.galleryzoom_toolbar);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_SECURE,WindowManager.LayoutParams.FLAG_SECURE);

        setSupportActionBar(toolbar);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("");
        firebaseauth=FirebaseAuth.getInstance();
        Intent it=getIntent();
        int pos=it.getIntExtra("picposition",0);
        ArrayList<String> array=it.getStringArrayListExtra("arraylist");
        galleryzoom_adapter adapter=new galleryzoom_adapter(array,getApplicationContext(),pos);
        pager.setAdapter(adapter);
        pager.setCurrentItem(pos);



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
            Intent it=new Intent(zoom_galleryimage.this,googlesignin.class);
            it.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(it);
        }


        return true;
    }


}
