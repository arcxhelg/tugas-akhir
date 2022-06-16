package com.example.tugasakhir;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.TextView;

import com.google.android.material.navigation.NavigationView;


public class MainActivity extends AppCompatActivity {

//    TextView txt_username, txt_email;
//    public DrawerLayout drawer;
//    public ActionBarDrawerToggle toggle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//        txt_username = (TextView) findViewById(R.id.user_username);
//        txt_email = (TextView) findViewById(R.id.user_email);
//
//        Intent intent = getIntent();
//        String user_username = intent.getStringExtra("user_username");
//
//        if (intent != null) {
//            txt_username.setText(user_username);
//        }
//
//        Toolbar toolbar = findViewById(R.id.toolbar);
//        setSupportActionBar(toolbar);
//
//        NavigationView navigationView = findViewById(R.id.nav_view);
//        navigationView.setNavigationItemSelectedListener(this);

//        drawer = findViewById(R.id.layout_drawer);
//        toggle = new ActionBarDrawerToggle(this, drawer, R.string.navigation_open, R.string.navigation_close);
//        drawer.addDrawerListener(toggle);
//        toggle.syncState();
//        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

//        if (savedInstanceState == null) {
//            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new NewMessageFragment()).commit();
//            navigationView.setCheckedItem(R.id.nav_compose);
//        }

    }

//    @Override
//    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
//
//        switch (item.getItemId()) {
//            case R.id.nav_compose:
//                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new NewMessageFragment()).commit();
//                break;
//            case R.id.nav_inbox:
//                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new ReceivedMessages()).commit();
//                break;
//            case R.id.nav_compose:
//                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new SentMessages()).commit();
//                break;
//        }
//
//        drawer.closeDrawer(GravityCompat.START);
//        return true;
//    }

//    @Override
//    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
//        if (toggle.onOptionsItemSelected(item)) {
//            return true;
//        }
//        return super.onOptionsItemSelected(item);
//    }

//    public void onBackPressed() {
//        if (drawer.isDrawerOpen(GravityCompat.START)) {
//            drawer.closeDrawer(GravityCompat.START);
//        } else {
//            AlertDialog.Builder builder = new AlertDialog.Builder(this);
//            builder.setCancelable(false);
//            builder.setMessage("Are you sure to exit the app?");
//            builder.setPositiveButton("Exit", new DialogInterface.OnClickListener() {
//                @Override
//                public void onClick(DialogInterface dialog, int which) {
//                    finishAffinity();
//                }
//            });
//            builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
//                @Override
//                public void onClick(DialogInterface dialog, int which) {
//                    dialog.cancel();
//                }
//            });
//            AlertDialog alert = builder.create();
//            alert.show();
//        }
//    }
}