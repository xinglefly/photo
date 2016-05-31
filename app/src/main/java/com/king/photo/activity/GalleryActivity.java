package com.king.photo.activity;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.widget.Button;

import com.king.photo.R;
import com.king.photo.adapter.GalleryPageAdapter;
import com.king.photo.util.Bimp;
import com.king.photo.util.PublicWay;
import com.king.photo.zoom.PhotoView;
import com.king.photo.zoom.ViewPagerFixed;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.OnPageChange;


public class GalleryActivity extends Activity {

    @BindView(R.id.btn_finish) Button btn_finish;
    @BindView(R.id.page_gallery) ViewPagerFixed pager;

    private Intent intent;
    private int position;
    private int location = 0;

    private ArrayList<View> listViews = null;
    private GalleryPageAdapter adapter;

    public List<Bitmap> bmp = new ArrayList<Bitmap>();
    public List<String> drr = new ArrayList<String>();
    public List<String> del = new ArrayList<String>();


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.plugin_camera_gallery);
        ButterKnife.bind(this);
        PublicWay.activityList.add(this);
        initView();
    }

    private void initView() {
        intent = getIntent();
        Bundle bundle = intent.getExtras();
        position = Integer.parseInt(intent.getStringExtra("position"));
        isShowOkBt();
        for (int i = 0; i < Bimp.tempSelectBitmap.size(); i++) {
            initListViews(Bimp.tempSelectBitmap.get(i).getBitmap());
        }

        adapter = new GalleryPageAdapter(listViews);
        pager.setAdapter(adapter);
        pager.setPageMargin((int) getResources().getDimensionPixelOffset(R.dimen.ui_10_dip));
        int id = intent.getIntExtra("ID", 0);
        pager.setCurrentItem(id);
    }


    @OnPageChange(R.id.page_gallery)
    void onPageSelected(int position) {
        location = position;
    }


    @OnClick({R.id.btn_gallery, R.id.btn_finish, R.id.btn_del})
    void onGalleryClicks(View v) {
        switch (v.getId()) {
            case R.id.btn_gallery:
                startActivity(intent.setClass(this, ImageFile.class));
                break;
            case R.id.btn_finish:
                finish();
                startActivity(intent.setClass(this, MainActivity.class));
                break;
            case R.id.btn_del:
                if (listViews.size() == 1) {
                    Bimp.tempSelectBitmap.clear();
                    Bimp.max = 0;
                    btn_finish.setText(Bimp.tempSelectBitmap.size() + "/" + PublicWay.num);
                    Intent intent = new Intent("data.broadcast.action");
                    sendBroadcast(intent);
                    finish();
                } else {
                    Bimp.tempSelectBitmap.remove(location);
                    Bimp.max--;
                    pager.removeAllViews();
                    listViews.remove(location);
                    adapter.setListViews(listViews);
                    btn_finish.setText(Bimp.tempSelectBitmap.size() + "/" + PublicWay.num);
                    adapter.notifyDataSetChanged();
                }
                break;
        }
    }


    private void initListViews(Bitmap bm) {
        if (listViews == null)
            listViews = new ArrayList<View>();
        PhotoView img = new PhotoView(this);
        img.setBackgroundColor(0xff000000);
        img.setImageBitmap(bm);
        img.setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT));
        listViews.add(img);
    }


    public void isShowOkBt() {
        if (Bimp.tempSelectBitmap.size() > 0) {
            btn_finish.setText(Bimp.tempSelectBitmap.size() + "/" + PublicWay.num + "");
            btn_finish.setPressed(true);
            btn_finish.setClickable(true);
            btn_finish.setTextColor(Color.WHITE);
        } else {
            btn_finish.setPressed(false);
            btn_finish.setClickable(false);
            btn_finish.setTextColor(Color.parseColor("#E1E0DE"));
        }
    }


    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {

        if (keyCode == KeyEvent.KEYCODE_BACK) {
            if (position == 1) startActivity(intent.setClass(this, AlbumActivity.class));
            else if (position == 2) startActivity(intent.setClass(this, ShowAllPhoto.class));
            this.finish();
        }
        return true;
    }


}
