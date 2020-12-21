package com.example.myapplication;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class ItemAdapter extends BaseAdapter{

    LayoutInflater mInflater;
    String[] items;
    String[] description;
    TypedArray icon;


    public ItemAdapter(Context c, String[] i, String[] d, TypedArray ic){
        items = i;
        description = d;
        icon = ic;
        mInflater = (LayoutInflater) c.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return items.length;
    }

    @Override
    public Object getItem(int position) {
        return items[position];
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View v = mInflater.inflate(R.layout.my_listview_detail, null);
        TextView nameTextView = (TextView) v.findViewById(R.id.nameTextView);
        TextView descriptionTextView = (TextView) v.findViewById(R.id.descriptionTextView2);
        ImageView imageView2 = (ImageView) v.findViewById(R.id.imageView2);

        String name = items[position];
        String desc = description[position];
        Drawable icons = icon.getDrawable(position);

        nameTextView.setText(name);
        descriptionTextView.setText(desc);
        imageView2.setImageDrawable(icons);
        return v;
    }
}
