package com.example.abim.lks_jadwal_pake_api;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class Adapter extends RecyclerView.Adapter<Adapter.ViewHolder> {
    List<Students> list;
    Context ctx;

    public Adapter(List<Students> list, Context ctx) {
        this.list = list;
        this.ctx = ctx;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(ctx).inflate(R.layout.card_row, parent, false);

        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.tv_id1.setText(String.valueOf(list.get(position).getId()));
        holder.tv_user1.setText(list.get(position).getUsername());
        holder.tv_classname1.setText(list.get(position).getClassname());
        holder.tv_hp1.setText(list.get(position).getHp());
        holder.tv_name1.setText(list.get(position).getName());
        holder.tv_address1.setText(list.get(position).getAddress());
        holder.tv_date1.setText(list.get(position).getDate());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements  View.OnClickListener{
        private TextView tv_id1, tv_name1, tv_address1, tv_date1, tv_hp1, tv_classname1, tv_user1;
        private LinearLayout layout;

        public ViewHolder(@NonNull View v) {
            super(v);
            tv_id1 = v.findViewById(R.id.tv_id1);
            tv_name1 = v.findViewById(R.id.tv_name1);
            tv_address1 = v.findViewById(R.id.tv_address1);
            tv_date1 = v.findViewById(R.id.tv_date1);
            tv_hp1 = v.findViewById(R.id.tv_hp1);
            tv_classname1 = v.findViewById(R.id.tv_class1);
            tv_user1 = v.findViewById(R.id.tv_username1);
            layout = v.findViewById(R.id.row);

            layout.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            Students s = list.get(getAdapterPosition());
            Intent intent = new Intent(ctx, DetailActivity.class);
            intent.putExtra("id", s.getId());
            intent.putExtra("name", s.getName());
            intent.putExtra("address", s.getAddress());
            intent.putExtra("date", s.getDate());
            intent.putExtra("hp", s.getHp());
            intent.putExtra("classname", s.getClassname());
            intent.putExtra("username", s.getUsername());
            ctx.startActivity(intent);
        }
    }
}
