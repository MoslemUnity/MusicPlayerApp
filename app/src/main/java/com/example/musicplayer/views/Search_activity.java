package com.example.musicplayer.views;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.musicplayer.R;
import com.example.musicplayer.adapters.Music_adapter;
import com.example.musicplayer.app.app;
import com.example.musicplayer.models.Music_model;
import com.example.musicplayer.retrofit.Retrofit_client;
import com.example.musicplayer.retrofit.Retrofit_interface;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Search_activity extends AppCompatActivity implements View.OnClickListener {


    ImageView imageView_back;
    TextInputLayout textInputLayout;
    TextInputEditText edittext_search;
    RecyclerView recyclerview;


    Retrofit_interface retrofit_interface;

    List<Music_model>list;

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

        setContentView(R.layout.activity_search);

        init();

        onclick();

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }

    private void init(){

        imageView_back=findViewById(R.id.imageView_back);
        textInputLayout=findViewById(R.id.textInputLayout);
        edittext_search=findViewById(R.id.edittext_search);
        recyclerview=findViewById(R.id.recyclerview);

        retrofit_interface= Retrofit_client.getRetrofit().create(Retrofit_interface.class);

        list=new ArrayList<>();

        adapter=new Music_adapter(list,this);

        recyclerview.setAdapter(adapter);
        recyclerview.setLayoutManager(new LinearLayoutManager(this));
        recyclerview.setHasFixedSize(true);




    }

    private void onclick(){
        imageView_back.setOnClickListener(this);

        edittext_search.addTextChangedListener(new TextWatcher() {
            @Override
            public void afterTextChanged(Editable s) {

            }

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                if (!s.toString().equals("")){
                    search(s.toString());
                }
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }
        });



    }

    @Override
    public void onClick(View v) {

        if (v==imageView_back){
            finish();
        }

    }

    private void search(String key){

        list.clear();



        retrofit_interface.search(key).enqueue(new Callback<List<Music_model>>() {
            @Override
            public void onResponse(Call<List<Music_model>> call, Response<List<Music_model>> response) {
                list.addAll(response.body());

                adapter.notifyDataSetChanged();
            }

            @Override
            public void onFailure(Call<List<Music_model>> call, Throwable throwable) {
                app.t("You are not connected to the Internet !");
            }
        });
    }
}