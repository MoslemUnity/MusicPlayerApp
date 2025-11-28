package com.example.musicplayer.views;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;

import androidx.activity.OnBackPressedCallback;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.musicplayer.R;
import com.example.musicplayer.adapters.Music_adapter;
import com.example.musicplayer.app.app;
import com.example.musicplayer.models.Music_model;
import com.example.musicplayer.retrofit.Retrofit_client;
import com.example.musicplayer.retrofit.Retrofit_interface;
import com.google.android.material.floatingactionbutton.ExtendedFloatingActionButton;
import com.google.android.material.navigation.NavigationView;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Home_activity extends AppCompatActivity implements View.OnClickListener {

    DrawerLayout drawerLayout;
    NavigationView navigationView;
    ImageView imageView_menu, imageView_search;
    ExtendedFloatingActionButton fab_add;
    RecyclerView recyclerview;


    Retrofit_interface retrofit_interface;

    List<Music_model> list;

    Music_adapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        getWindow().getDecorView().setSystemUiVisibility(
                View.SYSTEM_UI_FLAG_FULLSCREEN |
                        View.SYSTEM_UI_FLAG_HIDE_NAVIGATION |
                        View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY |
                        View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN |
                        View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
        );

        setContentView(R.layout.activity_home);

        init();
        getMusic();
        onclick();


        OnBackPressedCallback callback = new OnBackPressedCallback(true) {
            @Override
            public void handleOnBackPressed() {

                if (drawerLayout.isDrawerOpen(Gravity.LEFT)) {
                    drawerLayout.closeDrawers();
                } else {

                    setEnabled(false);
                    getOnBackPressedDispatcher().onBackPressed();
                }
            }
        };

        getOnBackPressedDispatcher().addCallback(this, callback);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.drawerLayout), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }

    private void init() {
        drawerLayout = findViewById(R.id.drawerLayout);
        navigationView = findViewById(R.id.navigationView);
        imageView_menu = findViewById(R.id.imageView_menu);
        imageView_search = findViewById(R.id.imageView_search);
        fab_add = findViewById(R.id.fab_add);
        recyclerview = findViewById(R.id.recyclerview);

        navigationView.bringToFront();

        retrofit_interface= Retrofit_client.getRetrofit().create(Retrofit_interface.class);

        list=new ArrayList<>();

        adapter=new Music_adapter(list,this);

        recyclerview.setAdapter(adapter);
        recyclerview.setLayoutManager(new LinearLayoutManager(this));
        recyclerview.setHasFixedSize(true);
    }

    private void onclick() {
        imageView_menu.setOnClickListener(this);
        imageView_search.setOnClickListener(this);
        fab_add.setOnClickListener(this);

        navigationView.setNavigationItemSelectedListener(item -> {

            int id = item.getItemId();
            drawerLayout.closeDrawers();

            if (id == R.id.menu_channel) {
                startActivity(new Intent(Intent.ACTION_VIEW,
                        Uri.parse("https://www.instagram.com/moslemdev22?igsh=aG5wdjU3ZGFrM3Vo")));
            }

            else if (id == R.id.menu_rate) {
                startActivity(new Intent(Intent.ACTION_VIEW,
                        Uri.parse("https://cafebazaar.ir")));

            }

            else if (id == R.id.menu_share) {

                Intent intentsend = new Intent();
                intentsend.setAction(Intent.ACTION_SEND);
                intentsend.putExtra(Intent.EXTRA_TEXT, "Download App Music Player https://cafebazaar.ir");
                intentsend.setType("text/plain");

                Intent share = Intent.createChooser(intentsend, null);
                startActivity(share);
            }


            else if (id == R.id.menu_aboutUs) {
                startActivity(new Intent(Intent.ACTION_VIEW,
                        Uri.parse("https://moslemdev.ir")));
            }

            else if (id == R.id.menu_exit) {
                finish();
            }

            return true;
        });

    }

    private void getMusic() {
        retrofit_interface.getMusics().enqueue(new Callback<List<Music_model>>() {
            @Override
            public void onResponse(Call<List<Music_model>> call, Response<List<Music_model>> response) {

                list.addAll(response.body());

                adapter.notifyDataSetChanged();

                //app.t(list.size()+"-----");
            }

            @Override
            public void onFailure(Call<List<Music_model>> call, Throwable throwable) {

                app.t("You are not connected to the Internet !");

            }
        });
    }

    @Override
    public void onClick(View v) {

        if (v == imageView_menu) {
            drawerLayout.openDrawer(Gravity.LEFT);
        }
        if (v == imageView_search) {

            Intent intent=new Intent(this, Search_activity.class);

            startActivity(intent);

        }
        if (v == fab_add) {

            Intent intent=new Intent(this,AddMusic_activity.class);
            startActivity(intent);

        }
    }
}
