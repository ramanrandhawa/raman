package com.banglore.computer.bce;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;


public class adapter_gallery extends RecyclerView.Adapter<adapter_gallery.inneradapter> {

ArrayList<String> array;
    Context context;

    adapter_gallery(ArrayList<String> arry,Context context)
    {
        array=arry;
        this.context=context;
    }

    @Override
    public inneradapter onCreateViewHolder(ViewGroup parent, int viewType) {

        LayoutInflater lf=LayoutInflater.from(context);
        View view=lf.inflate(R.layout.inflate_gallery,parent,false);
        inneradapter holder=new inneradapter(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(inneradapter holder, int position) {
        Picasso.with(context).load("http://www.banglorecomputereducation.com/retrofit/app_banglorecomp2017/gallery/"+array.get(position)).into(holder.img);

final int pos=position;
   holder.img.setOnClickListener(new View.OnClickListener() {
       @Override
       public void onClick(View v) {

           Intent it=new Intent(context,zoom_galleryimage.class);
           it.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
           it.putExtra("picposition",pos);
           it.putExtra("arraylist",array);
           context.startActivity(it);

       }
   });


    }

    @Override
    public int getItemCount() {
        return array.size();
    }

    class inneradapter extends RecyclerView.ViewHolder{

        ImageView img;
      public  inneradapter(View view)
        {
            super(view);
          img=(ImageView) view.findViewById(R.id.inflate_img);

        }

    }



}
