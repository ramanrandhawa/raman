package com.banglore.computer.bce;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by raman on 8/4/17.
 */

public class recycler_allbatches extends RecyclerView.Adapter<recycler_allbatches.recyclerviewholder> {

   Context context;
    ArrayList<responsemodel.innerdata> arry;


   public recycler_allbatches(ArrayList<responsemodel.innerdata> arry,Context context)
    {
        this.arry=arry;
        this.context=context;
    }

    @Override
    public recyclerviewholder onCreateViewHolder(ViewGroup parent, int viewType) {

        LayoutInflater lf=LayoutInflater.from(context);
        View view=lf.inflate(R.layout.inflate_allbatches,parent,false);
        recyclerviewholder holder=new recyclerviewholder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(recyclerviewholder holder, int position) {

        responsemodel.innerdata inner=arry.get(position);
        holder.tech.setText(inner.getTecnology());
        holder.bdate.setText(inner.getBdate());
        holder.btime.setText(inner.getBtime());

    }


    @Override
    public int getItemCount() {
        return arry.size();
    }

    class recyclerviewholder extends RecyclerView.ViewHolder
    {
        TextView tech,bdate,btime;
    recyclerviewholder(View view)
    {
        super(view);

        tech=(TextView) view.findViewById(R.id.inflate_tech);
        bdate=(TextView) view.findViewById(R.id.inflate_bdate);
        btime=(TextView) view.findViewById(R.id.inflate_btime);

    }

    }

}
