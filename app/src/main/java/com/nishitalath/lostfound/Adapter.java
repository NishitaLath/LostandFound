package com.nishitalath.lostfound;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class Adapter extends RecyclerView.Adapter<Adapter.ViewHolder> {
    Context context;
    ArrayList<Lost> lostlist;

    public Adapter(Context context, ArrayList<Lost> lostlist) {
        this.context = context;
        this.lostlist = lostlist;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v= LayoutInflater.from(context).inflate(R.layout.cardfeedlost, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Lost user=lostlist.get(position);
        holder.vName.setText(user.getLname());
        holder.vPhone.setText(user.getLphone());
        holder.vItemname.setText(user.getLitemname());
        holder.vLocation.setText(user.getLlocation());
        holder.vMsg.setText(user.getLmsg());
        holder.vPhotos.setText(user.getLphotos());
    }

    @Override
    public int getItemCount() {
        return lostlist.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder{
        TextView vName, vPhone, vItemname, vLocation, vMsg, vPhotos;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            vName=itemView.findViewById(R.id.lname);
            vPhone=itemView.findViewById(R.id.lphone);
            vItemname=itemView.findViewById(R.id.litemname);
            vLocation=itemView.findViewById(R.id.llocation);
            vMsg=itemView.findViewById(R.id.lmsg);
            vPhotos=itemView.findViewById(R.id.lphotos);
        }
    }
}