package com.banglore.computer.bce;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

public class bce_query extends AppCompatActivity {

    EditText edt_name,edt_mobile,edt_query;
    Button btn;
    Toolbar toolbar;
    FirebaseAuth firebaseauth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bce_query);
        // -------------------to avoid screenshots-----------------------------------
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_SECURE,WindowManager.LayoutParams.FLAG_SECURE);

        edt_name=(EditText)findViewById(R.id.edt_name);
        toolbar=(Toolbar)findViewById(R.id.query_toolbar);
        edt_mobile=(EditText)findViewById(R.id.edt_mobile);
        edt_query=(EditText)findViewById(R.id.edt_query);
        btn=(Button)findViewById(R.id.btn_submit);
        setSupportActionBar(toolbar);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Query");
        toolbar.setTitleTextColor(Color.WHITE);
firebaseauth=FirebaseAuth.getInstance();

        edt_mobile.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if(edt_mobile.getText().length()==10)
                {
                    InputMethodManager manager=(InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
                    manager.hideSoftInputFromWindow(v.getWindowToken(), InputMethodManager.HIDE_IMPLICIT_ONLY);
                }
            }
        });
    }

    public void dosubmit(View view) {
        Pattern mpattern = Pattern.compile("^(?:(?:\\+|0{0,2})91(\\s*[\\-]\\s*)?|[0]?)?[789]\\d{9}$");
        Matcher match = mpattern.matcher(edt_mobile.getText().toString());


        if ((edt_name.getText().length() == 0) || (!match.find()) || (edt_mobile.getText().length() == 0) || (edt_query.getText().length() == 0)) {

            if (edt_name.getText().length() == 0) {
                edt_name.setError("Fill your name");

            }

            if (!match.find()|| edt_mobile.getText().length() == 0) {
                if (edt_mobile.getText().length() == 0) {
                    edt_mobile.setError("Enter mobile number");
                } else {

                    if (!match.find()) {
                        edt_mobile.setError("Enter valid mobile number");
                    }
                }

            }
            if (edt_query.getText().length() == 0) {
                edt_query.setError("Fill your query");
            }


        } else {

            Toast.makeText(this, "hello", Toast.LENGTH_SHORT).show();

            final ProgressDialog dialog = new ProgressDialog(bce_query.this);
            dialog.setCancelable(false);
            dialog.setMessage("Sending");
            dialog.show();
            serverconnection con = new serverconnection();
            retrofitapi retro = con.doconnect();
            retro.sendQuery(edt_name.getText().toString(), edt_mobile.getText().toString(), edt_query.getText().toString(), new Callback<responseusersubmit>() {
                @Override
                public void success(responseusersubmit responseusersubmit, Response response) {
                    dialog.dismiss();
                    Toast.makeText(bce_query.this,"We will contact you soon", Toast.LENGTH_SHORT).show();
                }

                @Override
                public void failure(RetrofitError error) {
                    dialog.dismiss();
                    Toast.makeText(bce_query.this, error + "", Toast.LENGTH_SHORT).show();
                }
            });

        }

    }
    @Override
    public boolean onCreateOptionsMenu (Menu menu){
        getMenuInflater().inflate(R.menu.menu_toolbar,menu);
        return true;
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
            Intent it=new Intent(bce_query.this,googlesignin.class);
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
