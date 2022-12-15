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

public class Adapter3 extends RecyclerView.Adapter<Adapter3.ViewHolder3> {
    Context context;
    ArrayList<Post> postlist;

    public Adapter3(Context context, ArrayList<Post> postlist) {
        this.context = context;
        this.postlist = postlist;
    }

    @NonNull
    @Override
    public ViewHolder3 onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v= LayoutInflater.from(context).inflate(R.layout.cardmyposts, parent, false);
        return new ViewHolder3(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder3 holder, int position) {
        Post user=postlist.get(position);
        holder.vName.setText(user.getLname());
        holder.vPhone.setText(user.getLphone());
        holder.vItemname.setText(user.getLitemname());
        holder.vLocation.setText(user.getLlocation());
        holder.vMsg.setText(user.getLmsg());
        holder.vPhotos.setText(user.getLphotos());
    }

    @Override
    public int getItemCount() {
        return postlist.size();
    }

    public static class ViewHolder3 extends RecyclerView.ViewHolder{
        TextView vName, vPhone, vItemname, vLocation, vMsg, vPhotos;
        public ViewHolder3(@NonNull View itemView) {
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