package com.banglore.computer.bce;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.BaseExpandableListAdapter;
import android.widget.ExpandableListView;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

public class question extends AppCompatActivity {

    Toolbar toolbar;
    Spinner spinner;
    ExpandableListView expand;
    FirebaseAuth firebaseauth;
    HashMap<String, List<String>> map = new HashMap<>();
    //TableLayout table;
    // ListView listview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_question);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_SECURE,WindowManager.LayoutParams.FLAG_SECURE);
        toolbar = (Toolbar) findViewById(R.id.query_toolbar);
        spinner = (Spinner) findViewById(R.id.query_spinner);
        expand = (ExpandableListView) findViewById(R.id.expand);
        setSupportActionBar(toolbar);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Interview Questions");
        toolbar.setTitleTextColor(Color.WHITE);
        firebaseauth=FirebaseAuth.getInstance();
        serverconnection con = new serverconnection();
        final retrofitapi retro = con.doconnect();
        final ProgressDialog dialog=new ProgressDialog(question.this,R.style.mydialog);
        dialog.setCancelable(false);
        dialog.show();
        retro.fetchquestion(new Callback<responsemodelquestion>() {
            ArrayList<String> spin = new ArrayList<>();
            @Override
            public void success(responsemodelquestion responsemodelquestion, Response response) {
                dialog.dismiss();
                spin.add("Select Language");
                ArrayList<responsemodelquestion.innerclass> arraydata=responsemodelquestion.data;
                for(int i=0;i<arraydata.size();i++)
                {
                    spin.add(arraydata.get(i).getLang());
                }

                ArrayAdapter adapter=new ArrayAdapter(question.this,android.R.layout.simple_list_item_1,spin);
                adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                spinner.setAdapter(adapter);
            }

            @Override
            public void failure(RetrofitError error) {
                Toast.makeText(question.this,error+"", Toast.LENGTH_LONG).show();
            }
        });




spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
    @Override
    public void onItemSelected(final AdapterView<?> parent, View view, final int position, long id) {


        if(parent.getItemAtPosition(position).toString().equals("Select Language"))
        {
            expand.setAdapter((BaseExpandableListAdapter)null);

        }
        else {
            expand.setAdapter((BaseExpandableListAdapter)null);
            dialog.show();

            retro.fetchallquestion(parent.getItemAtPosition(position).toString(), new Callback<responsemodelquestion>() {
                @Override
                public void success(responsemodelquestion responsemodelquestion, Response response) {
                    dialog.dismiss();
                    ArrayList<responsemodelquestion.innerclass> innerdata = responsemodelquestion.data;
                    List<String> parent = new ArrayList<>();

                    for (int i = 0; i < innerdata.size(); i++) {
                        List<String> child = new ArrayList<>();
                        parent.add(innerdata.get(i).getQuestion());
                        child.add(innerdata.get(i).getAnswer());

                        map.put(parent.get(i), child);
                    }
                    expandquestion adapter = new expandquestion(getApplicationContext(), parent, map);
                    expand.setAdapter(adapter);
                }

                @Override
                public void failure(RetrofitError error) {
                    dialog.dismiss();
                    Toast.makeText(question.this, "Try again..", Toast.LENGTH_LONG).show();
                }
            });
        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {
    }
});
    }

        @Override
        public boolean onCreateOptionsMenu (Menu menu){

            getMenuInflater().inflate(R.menu.menu_toolbar, menu);
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
            Intent it=new Intent(question.this,googlesignin.class);
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
