package com.banglore.computer.bce;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;


public class recycler_placement extends RecyclerView.Adapter<recycler_placement.placementviewholder > {

ArrayList<responseplacement.placement> array;
    Context context;
    ViewGroup parent;

  public  recycler_placement(ArrayList<responseplacement.placement> placement, Context context)
    {
        array=placement;
        this.context=context;
    }

    @Override
    public placementviewholder onCreateViewHolder(ViewGroup parent, int viewType) {
this.parent=parent;
        LayoutInflater lf=LayoutInflater.from(context);
        View view=  lf.inflate(R.layout.inflate_allplacement,parent,false);
        placementviewholder holder=new placementviewholder(view);
        return  holder;

    }

    @Override
    public void onBindViewHolder(final placementviewholder holder, int position) {

       final responseplacement.placement obj=array.get(position);
        holder.sname.setText(obj.getSname());
        holder.company.setText(obj.getCompany());
        holder.packag.setText(obj.getPackag());
        Picasso.with(context).load("http://www.banglorecomputereducation.com/retrofit/app_banglorecomp2017/uploads/"+obj.getPic()).into(holder.pic);

    }

    @Override
    public int getItemCount() {
        return array.size();
    }

   class placementviewholder extends RecyclerView.ViewHolder{

       TextView sname,packag,company;
       ImageView pic;
       placementviewholder(View view)
       {
           super(view);
           sname=(TextView)view.findViewById(R.id.placement_txtsname);
           packag=(TextView)view.findViewById(R.id.placement_packag);
           company=(TextView)view.findViewById(R.id.placement_company);
           pic=(ImageView)view.findViewById(R.id.placement_img);


       }


    }

}
