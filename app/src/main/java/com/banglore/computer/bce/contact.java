package com.banglore.computer.bce;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.net.Uri;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.webkit.WebView;

import com.google.firebase.auth.FirebaseAuth;

public class contact extends AppCompatActivity {

    Toolbar toolbar;
    WebView webview;
    FirebaseAuth firebaseauth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_SECURE,WindowManager.LayoutParams.FLAG_SECURE);
        toolbar=(Toolbar)findViewById(R.id.contact_toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Contact Details");
        toolbar.setTitleTextColor(Color.WHITE);
        firebaseauth=FirebaseAuth.getInstance();
        webview=(WebView)findViewById(R.id.contact_web);
        webview.loadUrl("file:///android_asset/contactus.html");


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
            Intent it=new Intent(contact.this,googlesignin.class);
            it.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(it);
        }


        return true;
    }

    public void docall(View view)
    {
        int permissionCheck= ContextCompat.checkSelfPermission(this,android.Manifest.permission.CALL_PHONE);
        if(permissionCheck!= PackageManager.PERMISSION_GRANTED)
            ActivityCompat.requestPermissions(this, new String[]{android.Manifest.permission.CALL_PHONE}, 123);
        else {

            Intent it = new Intent();
            it.setAction(Intent.ACTION_CALL);
            String mob = "9872246056";
            it.setData(Uri.parse("tel:" + mob));
            startActivity(it);
        }
    }
    @Override
    public void onBackPressed() {

        Intent i=new Intent(this,MainActivity.class);
        i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(i);

    }
}
