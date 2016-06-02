package com.king.photo.adapter;

import android.content.Context;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;

import com.king.photo.R;
import com.king.photo.util.Bimp;
import com.king.photo.util.ViewHolder;


public class PhotoGridAdapter extends BaseAdapter {

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

    public PhotoGridAdapter(Context context) {
        this.mContext = context;
        inflater = LayoutInflater.from(context);
    }

    public void isRefresh() {
        this.notifyDataSetChanged();
    }

    public int getCount() {
        if (Bimp.tempSelectBitmap.size() == 6)
            return 6;
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

    public View getView(final int position, View convertView, ViewGroup parent) {
        if (convertView == null)
            convertView = inflater.inflate(R.layout.photo_item, parent, false);

        ImageView image = ViewHolder.get(convertView, R.id.item_grida_image);
        Button btnChoose = ViewHolder.get(convertView, R.id.btn_choose);

        if (position == Bimp.tempSelectBitmap.size()) {
            btnChoose.setVisibility(View.GONE);
            image.setImageBitmap(BitmapFactory.decodeResource(mContext.getResources(), R.drawable.addpic));
            if (position == 6) image.setVisibility(View.GONE);
        } else {
            image.setImageBitmap(Bimp.tempSelectBitmap.get(position).getBitmap());
            btnChoose.setVisibility(View.VISIBLE);
        }

        btnChoose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bimp.tempSelectBitmap.remove(position);
                Bimp.max--;
                notifyDataSetChanged();
            }
        });

        return convertView;
    }

    @Override
    public boolean areAllItemsEnabled() {
        return true;
    }
}