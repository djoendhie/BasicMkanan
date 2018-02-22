package com.example.macbook.makanbasc.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.macbook.makanbasc.R;
import com.example.macbook.makanbasc.activity.DetailMakananActivity;
import com.example.macbook.makanbasc.helper.MyConstant;
import com.example.macbook.makanbasc.helper.MyFunction;
import com.example.macbook.makanbasc.model.MakananItem;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by macbook on 2/22/18.
 */

public class ListMakananAdapter extends RecyclerView.Adapter<ListMakananAdapter.MyViewHolder>{

    Context c;

    List<MakananItem> makananl;

    public ListMakananAdapter(Context c, List<MakananItem> makananlist) {
        this.c = c;
        makananl = makananlist;

    }

    @Override
    public ListMakananAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(c).inflate(R.layout.tampilanlistmakanan,parent,false);
        MyViewHolder h = new MyViewHolder(v);
        return h;
    }

    @Override
    public void onBindViewHolder(ListMakananAdapter.MyViewHolder holder, final int position) {

        holder.txtnama.setText(makananl.get(position).getNama1());
        holder.txtharga.setText(makananl.get(position).getHarga1());
        holder.txtstatus.setText(makananl.get(position).getStatus1());
        Picasso.with(c).load(MyConstant.IMAGE_URL+makananl.get(position).getGambar1())
                .error(R.drawable.noimage).placeholder(R.drawable.noimage).into(holder.immakanan);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent kirimdata = new Intent(c, DetailMakananActivity.class);
                kirimdata.putExtra("nm",makananl.get(position).getNama1());
                kirimdata.putExtra("dm",makananl.get(position).getDetail1());
                kirimdata.putExtra("gm",makananl.get(position).getGambar1());
                kirimdata.putExtra("hm",makananl.get(position).getHarga1());
                kirimdata.putExtra("sm",makananl.get(position).getStatus1());
                c.startActivity(kirimdata);

            }
        });

    }

    @Override
    public int getItemCount() {
        return makananl.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{
        ImageView immakanan;
        TextView txtnama, txtharga,txtstatus;
        public MyViewHolder(View itemView) {
            super(itemView);

            immakanan = (ImageView)itemView.findViewById(R.id.imgmakanan);
            txtnama = (TextView)itemView.findViewById(R.id.txtnama);
            txtharga = (TextView)itemView.findViewById(R.id.txtharga);
            txtstatus = (TextView)itemView.findViewById(R.id.txtstatus);



        }
    }
}
