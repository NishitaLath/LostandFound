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

public class Adapter2 extends RecyclerView.Adapter<Adapter2.ViewHolder2> {
    Context context;
    ArrayList<Found> foundlist;

    public Adapter2(Context context, ArrayList<Found> foundlist) {
        this.context = context;
        this.foundlist = foundlist;
    }

    @NonNull
    @Override
    public ViewHolder2 onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v= LayoutInflater.from(context).inflate(R.layout.cardfeedfound, parent, false);
        return new ViewHolder2(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder2 holder, int position) {
        Found user=foundlist.get(position);
        holder.vName.setText(user.getLname());
        holder.vPhone.setText(user.getLphone());
        holder.vItemname.setText(user.getLitemname());
        holder.vLocation.setText(user.getLlocation());
        holder.vMsg.setText(user.getLmsg());
        holder.vPhotos.setText(user.getLphotos());
    }

    @Override
    public int getItemCount() { return foundlist.size(); }

    public static class ViewHolder2 extends RecyclerView.ViewHolder{
        TextView vName, vPhone, vItemname, vLocation, vMsg, vPhotos;
        public ViewHolder2(@NonNull View itemView) {
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