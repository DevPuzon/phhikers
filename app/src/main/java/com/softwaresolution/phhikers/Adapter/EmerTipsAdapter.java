package com.softwaresolution.phhikers.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.softwaresolution.phhikers.Activities.CustBrowser;
import com.softwaresolution.phhikers.Pojo.EmergencyGuide;
import com.softwaresolution.phhikers.Pojo.EmergencyGuide;
import com.softwaresolution.phhikers.R;
import com.softwaresolution.phhikers.Utils.Loading;

import java.util.ArrayList;

public class EmerTipsAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private String TAG ="EmerTipsAdapter";
    private ArrayList<EmergencyGuide> items;
    private Context context;
    private Loading loading;
    public EmerTipsAdapter(Context context, ArrayList<EmergencyGuide> items ) {
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
                R.layout.item_guidetips, parent, false);
        return new EmerTipsAdapter.MainHolder(menuItemLayoutView);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, final int position) {
        final EmergencyGuide data =(EmergencyGuide) items.get(position);
        EmerTipsAdapter.MainHolder h = (EmerTipsAdapter.MainHolder) holder;
        h.txt_name.setText(data.getName());

        h.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                context.startActivity(new Intent(context, CustBrowser.class)
                        .putExtra("title",data.getName()).putExtra("url",data.getUrl())
                );
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
        TextView txt_name;

        public MainHolder(@NonNull View v) {
            super(v);
            cardView = v.findViewById(R.id.card);
            txt_name = v.findViewById(R.id.txt_name);
        }
    }
}