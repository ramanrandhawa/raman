package com.banglore.computer.bce;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.TextView;

import java.util.HashMap;
import java.util.List;

/**
 * Created by a on 4/20/2017.
 */

public class expandquestion extends BaseExpandableListAdapter {

    Context context;
    List<String> parent;
    HashMap<String, List<String>> child;

   public expandquestion(Context context, List<String> parent, HashMap<String,List<String>> child)
    {
        this.context=context;
        this.parent=parent;
        this.child=child;
    }

    @Override
    public int getGroupCount() {
        return parent.size();
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        return child.get(parent.get(groupPosition)).size();
    }

    @Override
    public Object getGroup(int groupPosition) {
        return parent.get(groupPosition);
    }

    @Override
    public Object getChild(int groupPosition, int childPosition) {
        return child.get(parent.get(groupPosition)).get(childPosition);
    }

    @Override
    public long getGroupId(int groupPosition) {
        return groupPosition;
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return childPosition;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    @Override
    public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {

        String s1=(String)getGroup(groupPosition);
        if(convertView==null)
        {
            LayoutInflater lf=LayoutInflater.from(context);
            convertView=lf.inflate(R.layout.question_parent,parent,false);
        }
        TextView txt=(TextView)convertView.findViewById(R.id.question_txt);
        txt.setText(s1);



        return convertView;
    }

    @Override
    public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {

        String s1=(String)getChild(groupPosition,childPosition);
        if(convertView==null)
        {
            LayoutInflater lf=LayoutInflater.from(context);
          convertView=  lf.inflate(R.layout.answer_child,parent,false);
        }
        TextView txt=(TextView)convertView.findViewById(R.id.answer_txt);
        txt.setText(s1);

        return convertView;

    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return true;
    }
}
