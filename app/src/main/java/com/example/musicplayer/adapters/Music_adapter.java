package com.example.musicplayer.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.musicplayer.R;
import com.example.musicplayer.models.Music_model;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class Music_adapter extends RecyclerView.Adapter<Music_adapter.MyViewHolder> {

    List<Music_model> list;
    Context context;

    public Music_adapter(List<Music_model> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view= LayoutInflater.from(context).inflate(R.layout.items_music,parent,false);
        return new MyViewHolder(view);




    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

        holder.textview_songName.setText(list.get(position).getSongName());
        holder.textview_singerName.setText(list.get(position).getSingerName());
        holder.textview_duration.setText(list.get(position).getDuration());


        Glide.with(context).load(list.get(position).getImg_link()).into(holder.imageView_cover);

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {

        LinearLayout parent;

        CircleImageView imageView_cover;

        TextView textview_songName,textview_singerName,textview_duration;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            parent=itemView.findViewById(R.id.parent);

            imageView_cover=itemView.findViewById(R.id.imageView_cover);
            textview_songName=itemView.findViewById(R.id.textview_songName);
            textview_singerName=itemView.findViewById(R.id.textview_singerName);
            textview_duration=itemView.findViewById(R.id.textview_duration);

            parent.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                }
            });
        }
    }
}
