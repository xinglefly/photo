package com.king.photo.adapter;

import android.content.Context;
import android.graphics.BitmapFactory;
import android.os.Handler;
import android.os.Message;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import com.king.photo.R;
import com.king.photo.util.Bimp;
import com.king.photo.util.ViewHolder;


public class GridAdapter extends BaseAdapter {
    private LayoutInflater inflater;
    private int selectedPosition = -1;
    private boolean shape;
    private Context mContext;

    public boolean isShape() {
        return shape;
    }

    public void setShape(boolean shape) {
        this.shape = shape;
    }

    public GridAdapter(Context context) {
        this.mContext = context;
        inflater = LayoutInflater.from(context);
    }

    public void isRefresh() {
        this.notifyDataSetChanged();
    }

    public int getCount() {
        if (Bimp.tempSelectBitmap.size() == 6) {
            return 6;
        }
        return (Bimp.tempSelectBitmap.size() + 1);
    }

    public Object getItem(int arg0) {
        return null;
    }

    public long getItemId(int arg0) {
        return 0;
    }

    public void setSelectedPosition(int position) {
        selectedPosition = position;
    }

    public int getSelectedPosition() {
        return selectedPosition;
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = inflater.inflate(R.layout.item_published_grida, parent, false);
        }

        ImageView image = ViewHolder.get(convertView, R.id.item_grida_image);

        if (position == Bimp.tempSelectBitmap.size()) {
            image.setImageBitmap(BitmapFactory.decodeResource(mContext.getResources(), R.drawable.icon_addpic_unfocused));
            if (position == 6) image.setVisibility(View.GONE);
        } else {
            image.setImageBitmap(Bimp.tempSelectBitmap.get(position).getBitmap());
        }

        return convertView;
    }

}