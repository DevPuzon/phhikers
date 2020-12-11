package com.softwaresolution.phhikers.Adapter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
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
import com.softwaresolution.phhikers.Activities.SaveHike;
//import com.softwaresolution.phhikers.Activities.TrackLocation;
import com.softwaresolution.phhikers.Pojo.MountainList;
import com.softwaresolution.phhikers.Pojo.SaveHikePojo;
import com.softwaresolution.phhikers.R;
import com.softwaresolution.phhikers.Utils.Loading;

import java.util.ArrayList;

public class HistoryAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private String TAG ="HistoryAdapter";
    private ArrayList<SaveHikePojo> items;
    private Context context;
    private Loading loading;
    public HistoryAdapter(Context context, ArrayList<SaveHikePojo> items ) {
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
                R.layout.item_history, parent, false);
        return new HistoryAdapter.MainHolder(menuItemLayoutView);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, final int position) {
        final SaveHikePojo saveHikePojo =(SaveHikePojo) items.get(position);
        final MountainList mountain = saveHikePojo.getMountain();
        HistoryAdapter.MainHolder h = (HistoryAdapter.MainHolder) holder;
        h.txt_exp.setText(saveHikePojo.getExp());
        h.txt_timehike.setText(saveHikePojo.getHikeTime());
        h.txt_date.setText(saveHikePojo.getDate());

        h.txt_name.setText(mountain.getName());
        String desc = "Location : "+mountain.getLocation()+"\nElevation : "+mountain.getElevation()+
                "\nDifficulty : "+mountain.getDifficulty();
        h.txt_desc.setText(desc);
        Glide.with(context)
                .load("https://lh3.googleusercontent.com/proxy/L18nSAVupOl5MqHRJz4tshJhTGrG4Ye1qdQ8gVwN6wDX-s6GkT6oMs6t66R9VjZapIINmFEdkyWCv9HM6l_betLGDEGMTz0HxYtxchzqDpMh8FVp-gzOj1wgZVvrhJhLrcXHCEBh")
                .centerCrop()
                .into(h.img);
        Glide.with(context)
                .load(mountain.getImageSrc())
                .centerCrop()
                .into(h.img);

        h.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, SaveHike.class)
                        .putExtra("mountain",new Gson().toJson(mountain))
                        .putExtra("urlLoc",saveHikePojo.getUrlLoc())
                        .putExtra("hikeTime",saveHikePojo.getHikeTime())
                        .putExtra("urlLocStarted",saveHikePojo.getUrlLocStarted())
                        .putExtra("exp",saveHikePojo.getExp())
                        .putExtra("date",saveHikePojo.getDate())
                        .putExtra("history","History "+mountain.getName());
                context.startActivity(intent);
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
        TextView txt_name,txt_desc,txt_timehike,txt_exp,txt_date;

        public MainHolder(@NonNull View v) {
            super(v);
            img = v.findViewById(R.id.img);
            cardView = v.findViewById(R.id.card);
            txt_name = v.findViewById(R.id.txt_name);
            txt_desc = v.findViewById(R.id.txt_desc);
            txt_timehike = v.findViewById(R.id.txt_timehike);
            txt_exp = v.findViewById(R.id.txt_exp);
            txt_date = v.findViewById(R.id.txt_date);

        }
    }
}