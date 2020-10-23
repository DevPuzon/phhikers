package com.softwaresolution.phhikers.Adapter;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.google.gson.Gson;
import com.softwaresolution.phhikers.Activities.CustBrowser;
import com.softwaresolution.phhikers.Pojo.CusCheckList;
import com.softwaresolution.phhikers.R;
import com.softwaresolution.phhikers.Utils.Loading;

import java.util.ArrayList;

import static android.content.Context.MODE_PRIVATE;

public class CheckListAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private String TAG ="CheckListAdapter";
    private ArrayList<CusCheckList> items;
    private Context context;
    private Loading loading;
    private String check_tag;
    public CheckListAdapter(Context context, ArrayList<CusCheckList> items,String check_tag) {
        this.context = context;
        this.items =items;
        loading = new Loading(context);
        this.check_tag = check_tag;
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View menuItemLayoutView = LayoutInflater.from(parent.getContext()).inflate(
                R.layout.item_ch_primary, parent, false);
        return new CheckListAdapter.MainHolder(menuItemLayoutView);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, final int position) {
        final CusCheckList data =(CusCheckList) items.get(position);
        CheckListAdapter.MainHolder h = (CheckListAdapter.MainHolder) holder;
        h.txt_name.setText(data.getName());
        h.checkbox.setChecked(data.isChecked());

        h.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //click check
                items.get(position).setChecked(!items.get(position).isChecked());
                notifyDataSetChanged();
                saveChecks();
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
        CheckBox checkbox;

        public MainHolder(@NonNull View v) {
            super(v);
            cardView = v.findViewById(R.id.card);
            txt_name = v.findViewById(R.id.txt_name);
            checkbox = v.findViewById(R.id.checkbox);
        }
    }

    private SharedPreferences saveData;
    private SharedPreferences.Editor editor;
    private void saveChecks(){
        saveData = context.getSharedPreferences("saveData", MODE_PRIVATE);
        editor = saveData.edit();
        editor.putString(check_tag, new Gson().toJson(this.items));
        editor.apply();
        editor.commit();
    }
}