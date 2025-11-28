package com.example.musicplayer.views;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.musicplayer.R;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

public class AddMusic_activity extends AppCompatActivity implements View.OnClickListener {


    ImageView imageView_back;
    TextInputLayout textInputLayout_songName,textInputLayout_singerName,textInputLayout_imgLink,textInputLayout_playLink,textInputLayout_duration;
    TextInputEditText editText_songName,editText_singerName,editText_imgLink,editText_playLink,editText_duration;
    MaterialButton button_submit;



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

        setContentView(R.layout.activity_add_music);



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

        textInputLayout_songName=findViewById(R.id.textInputLayout_songName);

        textInputLayout_singerName=findViewById(R.id.textInputLayout_singerName);

        textInputLayout_imgLink=findViewById(R.id.textInputLayout_imgLink);

        textInputLayout_playLink=findViewById(R.id.textInputLayout_playLink);

        textInputLayout_duration=findViewById(R.id.textInputLayout_duration);

        editText_songName=findViewById(R.id.editText_songName);

        editText_singerName=findViewById(R.id.editText_singerName);

        editText_imgLink=findViewById(R.id.editText_imgLink);

        editText_playLink=findViewById(R.id.editText_playLink);

        editText_duration=findViewById(R.id.editText_duration);

        button_submit=findViewById(R.id.button_submit);

    }

    private void onclick(){

        imageView_back.setOnClickListener(this);
        button_submit.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {

        if (v==imageView_back){
            finish();
        }

        if (v==button_submit){

        }

    }
}