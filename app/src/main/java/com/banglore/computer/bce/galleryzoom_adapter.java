package com.banglore.computer.bce;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import uk.co.senab.photoview.PhotoView;
import uk.co.senab.photoview.PhotoViewAttacher;

/**
 * Created by a on 4/18/2017.
 */

public class galleryzoom_adapter extends PagerAdapter {

    ArrayList<String> array;
    Context context;
    int pos;
    galleryzoom_adapter(ArrayList<String> arry, Context context,int pos)
    {
        array=arry;
        this.context=context;
        this.pos=pos;
    }

    galleryzoom_adapter(ArrayList<String> arry, Context context)
    {
        array=arry;
        this.context=context;

    }



    @Override
    public int getCount() {
        return array.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view==((android.widget.LinearLayout) object);
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {

        LayoutInflater lf=LayoutInflater.from(context);
        View view=lf.inflate(R.layout.inflate_zoomgallerypic,container,false);
        PhotoView img=(PhotoView)view.findViewById(R.id.inflate_zoomgalleryimg);
        final PhotoViewAttacher attacher=new PhotoViewAttacher(img);
        Picasso.with(context).load("http://www.banglorecomputereducation.com/retrofit/app_banglorecomp2017/gallery/"+array.get(position)).into(img, new Callback() {
            @Override
            public void onSuccess() {
                if(attacher!=null)
                {
                    attacher.update();
                }
            }

            @Override
            public void onError() {


            }
        });
       container.addView(view);





        return view;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object)
    {
        // Remove viewpager_item.xml from ViewPager
         container.removeView((LinearLayout)object);

    }
}
