package com.softwaresolution.phhikers.Adapter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.google.gson.Gson;
import com.shashank.sony.fancytoastlib.FancyToast;
//import com.softwaresolution.phhikers.Activities.TrackLocation;
import com.softwaresolution.phhikers.Activities.TrackLocation;
import com.softwaresolution.phhikers.Pojo.MountainList;
import com.softwaresolution.phhikers.R;
import com.softwaresolution.phhikers.Utils.Loading;

import java.util.ArrayList;

public class MountainListAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private String TAG ="MountainListAdapter";
    private ArrayList<MountainList> items;
    private Context context; 
    private Loading loading;
    public MountainListAdapter(Context context, ArrayList<MountainList> items ) {
        this.context = context;
        this.items =items;
        loading = new Loading(context);
 
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View menuItemLayoutView = LayoutInflater.from(parent.getContext()).inflate(
                R.layout.item_mountain, parent, false);
        return new MountainListAdapter.MainHolder(menuItemLayoutView);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, final int position) {
        final MountainList data =(MountainList) items.get(position);
        MountainListAdapter.MainHolder h = (MountainListAdapter.MainHolder) holder;
        h.txt_name.setText(data.getName());
        String desc = "Location : "+data.getLocation()+"\nElevation : "+data.getElevation()+
                "\nDifficulty : "+data.getDifficulty();
        h.txt_desc.setText(desc);
        Glide.with(context)
                .load("https://lh3.googleusercontent.com/proxy/L18nSAVupOl5MqHRJz4tshJhTGrG4Ye1qdQ8gVwN6wDX-s6GkT6oMs6t66R9VjZapIINmFEdkyWCv9HM6l_betLGDEGMTz0HxYtxchzqDpMh8FVp-gzOj1wgZVvrhJhLrcXHCEBh")
                .centerCrop()
                .into(h.img);
        Glide.with(context)
                .load(data.getImageSrc())
                .centerCrop()
                .into(h.img);

        h.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                context.startActivity(new Intent(context, TrackLocation.class)
                        .putExtra("title",data.getName())
                        .putExtra("mountain",new Gson().toJson(data))
                        .putExtra("url_tracker",data.getPoint())
                );
                Activity activity = (Activity) context;
                activity.overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
            }
        });
    }

    public void removeAt(int position) {
        items.remove(position);
        notifyItemRemoved(position);
        notifyItemRangeChanged(position, items.size());
    }

    public static class MainHolder extends RecyclerView.ViewHolder {
        CardView cardView;
        ImageView img;
        TextView txt_name,txt_desc;

        public MainHolder(@NonNull View v) {
            super(v);
            img = v.findViewById(R.id.img);
            cardView = v.findViewById(R.id.card);
            txt_name = v.findViewById(R.id.txt_name);
            txt_desc = v.findViewById(R.id.txt_desc);
        }
    }
}