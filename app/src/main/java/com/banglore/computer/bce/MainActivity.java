package com.banglore.computer.bce;

import android.*;
import android.Manifest;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.support.design.widget.CoordinatorLayout;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ViewFlipper;

import com.google.firebase.auth.FirebaseAuth;
import com.squareup.picasso.Picasso;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
    ImageView img;
    TextView txt,one_text,twotxt,threetxt,fouttxt,fivetxt,sixtxt,seventxt,eighttxt,ninetxt,tentxt,eleventxt,twelvetxt;
    FirebaseAuth firebaseauth;
    ImageView one, two, three, five, four, six, seven, eight, nine, ten, eleven, twelve,laptop,company,teacher,photo,open,cart,meeting,raisehand,where,phonecall,abtus,internet;
    boolean flag;
    CoordinatorLayout coordinator;
    ViewFlipper flipper;
RelativeLayout main_relative;
    SwipeRefreshLayout swiperefresh;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_SECURE,WindowManager.LayoutParams.FLAG_SECURE);
        Toolbar toolbar = (Toolbar) findViewById(R.id.main_toolbar);
        setSupportActionBar(toolbar);
        swiperefresh=(SwipeRefreshLayout)findViewById(R.id.refresh);
        flipper=(ViewFlipper)findViewById(R.id.viewflipper);
        coordinator=(CoordinatorLayout)findViewById(R.id.coordinator);
        toolbar.setTitleTextColor(Color.WHITE);
        main_relative=(RelativeLayout)findViewById(R.id.main_relative);
        laptop=(ImageView)findViewById(R.id.laptop);
        one_text=(TextView)findViewById(R.id.one_text);
        twotxt=(TextView)findViewById(R.id.two_textView);
        threetxt=(TextView)findViewById(R.id.three_textView);
        fouttxt=(TextView)findViewById(R.id.four_textView);
        fivetxt=(TextView)findViewById(R.id.five_textView);
        sixtxt=(TextView)findViewById(R.id.six_textView);
        seventxt=(TextView)findViewById(R.id.seven_textView);
        eighttxt=(TextView)findViewById(R.id.eight_textView);
        ninetxt=(TextView)findViewById(R.id.nine_textView);
        tentxt=(TextView)findViewById(R.id.ten_textView);
        eleventxt=(TextView)findViewById(R.id.eleven_textView);
        twelvetxt=(TextView)findViewById(R.id.twelve_textView);
        one = (ImageView) findViewById(R.id.one_img);
        two = (ImageView) findViewById(R.id.two_img);
        three = (ImageView) findViewById(R.id.three_img);
        four = (ImageView) findViewById(R.id.four_img);
        six = (ImageView) findViewById(R.id.six_img);
        seven = (ImageView) findViewById(R.id.seven_img);
        eight = (ImageView) findViewById(R.id.eight_img);
        ten = (ImageView) findViewById(R.id.ten_img);
        nine = (ImageView) findViewById(R.id.nine_img);
        five = (ImageView) findViewById(R.id.five_img);
        eleven = (ImageView) findViewById(R.id.eleven_img);
        twelve = (ImageView) findViewById(R.id.twelve_img);
        company = (ImageView) findViewById(R.id.company);
        teacher = (ImageView) findViewById(R.id.teacher);
        company = (ImageView) findViewById(R.id.company);
        photo = (ImageView) findViewById(R.id.photo);
        open = (ImageView) findViewById(R.id.open);
        cart = (ImageView) findViewById(R.id.cart);
        company = (ImageView) findViewById(R.id.company);
        meeting = (ImageView) findViewById(R.id.meeting);
        raisehand = (ImageView) findViewById(R.id.raisehand);
        where = (ImageView) findViewById(R.id.where);
        phonecall = (ImageView) findViewById(R.id.phonecall);
        abtus = (ImageView) findViewById(R.id.abtus);
        internet = (ImageView) findViewById(R.id.internet);


        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);

        navigationView.setNavigationItemSelectedListener(this);

        img = (ImageView) navigationView.getHeaderView(0).findViewById(R.id.imageView);

        txt = (TextView) navigationView.getHeaderView(0).findViewById(R.id.email_id);
        boolean isconnected = checkconnectiom();

        swiperefresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                swiperefresh.setRefreshing(true);
                if(!checkconnectiom())
                {
                    swiperefresh.setRefreshing(false);
                    AlertDialog.Builder dialog = new AlertDialog.Builder(MainActivity.this);
                    dialog.setTitle("Message");
                    dialog.setCancelable(false);
                    dialog.setMessage("Connection time out.Please check your internet connection and try again");
                    dialog.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {

                        }
                    });
                    dialog.show();
                }
                else
                {
                    flipper.setDisplayedChild(0);
                    String email = firebaseauth.getCurrentUser().getDisplayName();
                    txt.setText(email);
                    getSupportActionBar().setTitle(email);
                    Picasso.with(MainActivity.this).load(firebaseauth.getCurrentUser().getPhotoUrl()).into(img);
                }
            }
        });

            firebaseauth = FirebaseAuth.getInstance();
            if (firebaseauth.getCurrentUser() == null) {
                Intent intent = new Intent(this, googlesignin.class);
                startActivity(intent);
            } else {

                String email = firebaseauth.getCurrentUser().getDisplayName();
                txt.setText(email);
                getSupportActionBar().setTitle(email);
                Picasso.with(this).load(firebaseauth.getCurrentUser().getPhotoUrl()).into(img);

//                if (!isconnected) {
//                    flipper.setDisplayedChild(1);
        }



        one.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                boolean isconnected = checkconnectiom();
                if (!isconnected) {
                    flipper.setDisplayedChild(1);
                } else {
                       one_text.setTextColor(Color.BLACK);
                    Intent it = new Intent(MainActivity.this, tabslider.class);
                    startActivity(it);
                }
            }
        });
        two.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean isconnected = checkconnectiom();
                if (!isconnected) {
                    flipper.setDisplayedChild(1);
                } else {
                        twotxt.setTextColor(Color.BLACK);
                    Intent it = new Intent(MainActivity.this, allplacements.class);
                    startActivity(it);
                }
            }
        });
        three.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean isconnected = checkconnectiom();
                if (!isconnected) {
                    flipper.setDisplayedChild(1);
                } else {
                    threetxt.setTextColor(Color.BLACK);
                    Intent it = new Intent(MainActivity.this, allbatches.class);
                    startActivity(it);
                }
            }
        });
        four.setOnClickListener(new View.OnClickListener() {
           @Override
            public void onClick(View v) {
                boolean isconnected = checkconnectiom();
                if (!isconnected) {
                    flipper.setDisplayedChild(1);
                } else {
                    fouttxt.setTextColor(Color.BLACK);
                    Intent it = new Intent(MainActivity.this, bce_gallery.class);
                    startActivity(it);
                }
            }
        });
        six.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean isconnected = checkconnectiom();
                if (!isconnected) {
                    flipper.setDisplayedChild(1);
                } else {
                    sixtxt.setTextColor(Color.BLACK);
                    Intent it = new Intent(MainActivity.this, buyjava.class);
                    startActivity(it);
                }
            }
        });
        seven.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean isconnected = checkconnectiom();
                if (!isconnected) {
                    flipper.setDisplayedChild(1);
                } else {
                    seventxt.setTextColor(Color.BLACK);
                    Intent it = new Intent(MainActivity.this, question.class);
                    startActivity(it);
                }
            }
        });
        eight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean isconnected = checkconnectiom();
                if (!isconnected) {
                    flipper.setDisplayedChild(1);
                } else {
                    eighttxt.setTextColor(Color.BLACK);
                    Intent it = new Intent(MainActivity.this, bce_query.class);
                    startActivity(it);
                }
            }
        });
        ten.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean isconnected = checkconnectiom();
                if (!isconnected) {
                    flipper.setDisplayedChild(1);
                } else {
                    tentxt.setTextColor(Color.BLACK);
                    Intent it = new Intent(MainActivity.this, contact.class);
                    startActivity(it);
                }
            }
        });
        nine.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean isconnected = checkconnectiom();
                if (!isconnected) {
                    flipper.setDisplayedChild(1);
                } else {
                    ninetxt.setTextColor(Color.BLACK);
                    Intent it = new Intent(MainActivity.this, location.class);
                    startActivity(it);
                }

            }
        });
        five.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean isconnected = checkconnectiom();
                if (!isconnected) {
                    flipper.setDisplayedChild(1);
                } else {
                    fivetxt.setTextColor(Color.BLACK);
                    Intent it = new Intent(MainActivity.this, realjava.class);
                    startActivity(it);
                }
            }
        });
        eleven.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean isconnected = checkconnectiom();
                if (!isconnected) {
                    flipper.setDisplayedChild(1);
                } else {
                    eleventxt.setTextColor(Color.BLACK);
                    Intent it = new Intent(MainActivity.this, about.class);
                    startActivity(it);
                }
            }
        });

        twelve.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean isconnected = checkconnectiom();
                if (!isconnected) {
                    flipper.setDisplayedChild(1);
                } else {
                    twelvetxt.setTextColor(Color.BLACK);
                    Intent it = new Intent(MainActivity.this, website.class);
                    startActivity(it);
                }
            }
        });
         flag=false;

    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {

      if(flag==true) {

          Intent intent = new Intent(Intent.ACTION_MAIN);
          intent.addCategory(Intent.CATEGORY_HOME);
          intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
          startActivity(intent);
      }
      else
      {
          flag=true;
          Toast.makeText(this, "Please click back button again to exit", Toast.LENGTH_SHORT).show();
          Runnable rn=new Runnable() {
              @Override
              public void run() {
                  flag=false;
              }
          };
          Handler handler=new Handler();
          handler.postDelayed(rn,2000);
      }
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();


        if(id==R.id.itm_call)
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

        return true;
    }

    public boolean checkconnectiom() {
        ConnectivityManager cm =
                (ConnectivityManager) this.getSystemService(Context.CONNECTIVITY_SERVICE);

        NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
        boolean isConnected = activeNetwork != null &&
                activeNetwork.isConnectedOrConnecting();
        return isConnected;

    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {

        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_tech) {
            boolean isconnected = checkconnectiom();
            if (!isconnected) {
                flipper.setDisplayedChild(1);
            } else {

                Intent it = new Intent(this, tabslider.class);
                startActivity(it);
            }

        } else if (id == R.id.nav_placements) {
            boolean isconnected = checkconnectiom();
            if (!isconnected) {
                flipper.setDisplayedChild(1);
            } else {
                Intent it = new Intent(this, allplacements.class);
                startActivity(it);
            }

        } else if (id == R.id.nav_batches) {
            boolean isconnected = checkconnectiom();
            if (!isconnected) {
                flipper.setDisplayedChild(1);
            } else {
                Intent it = new Intent(this, allbatches.class);
                startActivity(it);
            }

        } else if (id == R.id.nav_real) {
            boolean isconnected = checkconnectiom();
            if (!isconnected) {
                flipper.setDisplayedChild(1);
            } else {
                Intent it = new Intent(this, realjava.class);
                startActivity(it);
            }

        } else if (id == R.id.nav_buyjava) {
            boolean isconnected = checkconnectiom();
            if (!isconnected) {
                flipper.setDisplayedChild(1);
            } else {
                Intent it = new Intent(this, buyjava.class);
                startActivity(it);
            }

        } else if (id == R.id.nav_gallery) {
            boolean isconnected = checkconnectiom();
            if (!isconnected) {
                flipper.setDisplayedChild(1);
            } else {
                Intent it = new Intent(this, bce_gallery.class);
                startActivity(it);
            }

        } else if (id == R.id.nav_location) {
            boolean isconnected = checkconnectiom();
            if (!isconnected) {
                flipper.setDisplayedChild(1);
            } else {
                Intent it = new Intent(this, location.class);
                startActivity(it);
            }


        } else if (id == R.id.nav_question) {
            boolean isconnected = checkconnectiom();
            if (!isconnected) {
                flipper.setDisplayedChild(1);
            } else {
                Intent it = new Intent(this, question.class);
                startActivity(it);
            }


        } else if (id == R.id.nav_query) {
            boolean isconnected = checkconnectiom();
            if (!isconnected) {
                flipper.setDisplayedChild(1);
            } else {
                Intent it = new Intent(this, bce_query.class);
                startActivity(it);
            }


        } else if (id == R.id.nav_aboutus) {
            boolean isconnected = checkconnectiom();
            if (!isconnected) {
                flipper.setDisplayedChild(1);
            } else {
                Intent it = new Intent(this, about.class);
                startActivity(it);
            }


        } else if (id == R.id.nav_contact) {
            boolean isconnected = checkconnectiom();
            if (!isconnected) {
                flipper.setDisplayedChild(1);
            } else {
                Intent it = new Intent(this, contact.class);
                startActivity(it);
            }
        } else if (id == R.id.nav_logout) {
                firebaseauth.signOut();
                Intent it = new Intent(MainActivity.this, googlesignin.class);
                it.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(it);

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}

