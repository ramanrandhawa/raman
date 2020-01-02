package com.banglore.computer.bce;

import android.content.Intent;
import android.graphics.Color;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.ActionMenuView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.WindowManager;

import com.google.firebase.auth.FirebaseAuth;

public class tabslider extends AppCompatActivity {

    TabLayout tablayout;
    AppBarLayout appbar;
    Toolbar toolbar;
    ActionMenuView actionmenu;
    ViewPager vp;
    FirebaseAuth firebaseauth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tabslider);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_SECURE,WindowManager.LayoutParams.FLAG_SECURE);
        tablayout=(TabLayout)findViewById(R.id.tablayout);
        appbar=(AppBarLayout)findViewById(R.id.appbar);
        toolbar=(Toolbar) findViewById(R.id.toolbar);
        vp=(ViewPager)findViewById(R.id.viewpage);
        setSupportActionBar(toolbar);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Training Technologies");
        toolbar.setTitleTextColor(Color.WHITE);
        viewpageradapter viewpager=new viewpageradapter(getSupportFragmentManager());
        tablayout.setupWithViewPager(vp);
        vp.setAdapter(viewpager);
        firebaseauth=FirebaseAuth.getInstance();

    }




    class viewpageradapter extends FragmentPagerAdapter{

    public viewpageradapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
      switch(position) {
          case 0:
              return new tblefrag();
          case 1: return new corejava();
          case 2:return new phpfrag();
          case 3:return new cfrag();
          case  4:return new fragadvsyllabus();
          case 5: return new python();
          default: return new reactjs();
      }
    }


    @Override
    public int getCount() {
        return 7;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        switch (position)
        {
            case 0:return "Android";
            case 1: return "Core Java";
            case 2:return "WDP-PHP";
            case 3: return "C++";
            case 4:return "Advance Java";
            case 5:return "Python";
            default: return "React/AngularJS";

        }
    }
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
            Intent it=new Intent(tabslider.this,googlesignin.class);
            it.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(it);
        }

        return true;
    }

//    @Override
//    public boolean onOptionsItemSelected(MenuItem item) {
//
//        if(item.getItemId()==android.R.id.home)
//        {
//            Intent i=new Intent(this,MainActivity.class);
//            i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
//            startActivity(i);
//        }
//
//
//        return true;
//    }


    @Override
    public void onBackPressed() {

        Intent i=new Intent(this,MainActivity.class);
        i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(i);

    }
}
