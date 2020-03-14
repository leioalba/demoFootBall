package com.example.administrator.demo22.Utils;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.administrator.demo22.R;

import java.util.List;

public class TitleAdapter extends ArrayAdapter<Title> {
    private int resourceId;

    public TitleAdapter(Context context, int resource, List<Title> objects) {
        super(context, resource,objects);
        this.resourceId = resource;
    }
    public View getView(int position, View convertView, ViewGroup parent){
        Title title=getItem(position);
        View view;
        ViewHolder viewHolder;
        if (convertView == null){
            view = LayoutInflater.from(getContext()).inflate(resourceId,parent,false);
            viewHolder=new ViewHolder();
            viewHolder.TitleText = (TextView) view.findViewById(R.id.title_text);
            viewHolder.titlePic = (ImageView) view.findViewById(R.id.title_pic);
            viewHolder.titleDes = (TextView) view.findViewById(R.id.descr_text);
            view.setTag(viewHolder);
        }else {
            view=convertView;
            viewHolder = (ViewHolder) view.getTag();
        }
        Glide.with(getContext()).load(title.getPicUrl()).into(viewHolder.titlePic);
        viewHolder.TitleText.setText(title.getTitle());
        viewHolder.titleDes.setText(title.getSource());

        return view;
    }
    public class ViewHolder{
        TextView TitleText;
        TextView titleDes;
        ImageView titlePic;

    }
}
