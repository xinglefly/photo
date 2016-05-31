package com.king.photo.activity;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.GridView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;

import com.king.photo.R;
import com.king.photo.adapter.AlbumGridViewAdapter;
import com.king.photo.event.PhotoEvent;
import com.king.photo.util.AlbumHelper;
import com.king.photo.util.Bimp;
import com.king.photo.bean.ImageBucket;
import com.king.photo.bean.ImageItem;
import com.king.photo.util.PublicWay;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;


public class AlbumActivity extends Activity {

    @BindView(R.id.btn_preview) Button btnPreview;
    @BindView(R.id.btn_finish) Button btnFinish;
    @BindView(R.id.gridview_album) GridView gridViewAlbum;
    @BindView(R.id.tv_none) TextView tvNone;

    private AlbumGridViewAdapter gridImageAdapter;
    private ArrayList<ImageItem> dataList;
    private AlbumHelper helper;
    public static List<ImageBucket> contentList;
    public static Bitmap bitmap;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.plugin_camera_album);
        ButterKnife.bind(this);
        EventBus.getDefault().register(this);
        PublicWay.activityList.add(this);

        init();
        initListener();
        isShowOkBt();
    }


    private void init() {
        bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.plugin_camera_no_pictures);
        helper = AlbumHelper.getHelper();
        helper.init(getApplicationContext());

        contentList = helper.getImagesBucketList(false);
        dataList = new ArrayList<ImageItem>();

        for (int i = 0; i < contentList.size(); i++)
            dataList.addAll(contentList.get(i).imageList);

        gridImageAdapter = new AlbumGridViewAdapter(this, dataList, Bimp.tempSelectBitmap);
        gridViewAlbum.setAdapter(gridImageAdapter);
        gridViewAlbum.setEmptyView(tvNone);
        btnFinish.setText(Bimp.tempSelectBitmap.size() + "/" + PublicWay.num);
    }




    @OnClick({R.id.btn_album,R.id.btn_cancle,R.id.btn_preview,R.id.btn_finish})
    void onButtonClick(View v){
        switch (v.getId()){
            case R.id.btn_album:
                startActivity(new Intent(AlbumActivity.this, ImageFile.class));
                break;
            case R.id.btn_cancle:
                Bimp.tempSelectBitmap.clear();
                startActivity(new Intent(this, MainActivity.class));
                break;
            case R.id.btn_preview:
                if (Bimp.tempSelectBitmap.size() > 0)
                    startActivity(new Intent(this,GalleryActivity.class).putExtra("position", "1"));
                break;
            case R.id.btn_finish:
                overridePendingTransition(R.anim.activity_translate_in, R.anim.activity_translate_out);
                finish();
                break;

        }
    }


    @Subscribe
    public void isRefreshAlbum(PhotoEvent event){
        if (event.isRefresh()) gridImageAdapter.notifyDataSetChanged();
    }

    private void initListener() {
        gridImageAdapter.setOnItemClickListener(new AlbumGridViewAdapter.OnItemClickListener() {

                    @Override
                    public void onItemClick(final ToggleButton toggleButton, int position, boolean isChecked, Button chooseBt) {
                        if (Bimp.tempSelectBitmap.size() >= PublicWay.num) {
                            toggleButton.setChecked(false);
                            chooseBt.setVisibility(View.GONE);
                            if (!removeOneData(dataList.get(position)))
                                Toast.makeText(AlbumActivity.this, "超出可选图片张数", Toast.LENGTH_LONG).show();
                            return;
                        }
                        if (isChecked) {
                            chooseBt.setVisibility(View.VISIBLE);
                            Bimp.tempSelectBitmap.add(dataList.get(position));
                            btnFinish.setText(Bimp.tempSelectBitmap.size() + "/" + PublicWay.num);
                        } else {
                            Bimp.tempSelectBitmap.remove(dataList.get(position));
                            chooseBt.setVisibility(View.GONE);
                            btnFinish.setText(Bimp.tempSelectBitmap.size() + "/" + PublicWay.num);
                        }
                        isShowOkBt();
                    }
                });
    }

    private boolean removeOneData(ImageItem imageItem) {
        if (Bimp.tempSelectBitmap.contains(imageItem)) {
            Bimp.tempSelectBitmap.remove(imageItem);
            btnFinish.setText(Bimp.tempSelectBitmap.size() + "/" + PublicWay.num);
            return true;
        }
        return false;
    }

    public void isShowOkBt() {
        if (Bimp.tempSelectBitmap.size() > 0) {
            btnFinish.setText(Bimp.tempSelectBitmap.size() + "/" + PublicWay.num );
            btnPreview.setPressed(true);
            btnFinish.setPressed(true);
            btnPreview.setClickable(true);
            btnFinish.setClickable(true);
            btnFinish.setTextColor(Color.WHITE);
            btnPreview.setTextColor(Color.WHITE);
        } else {
            btnFinish.setText(Bimp.tempSelectBitmap.size() + "/" + PublicWay.num);
            btnPreview.setPressed(false);
            btnPreview.setClickable(false);
            btnFinish.setPressed(false);
            btnFinish.setClickable(false);
            btnFinish.setTextColor(Color.parseColor("#E1E0DE"));
            btnPreview.setTextColor(Color.parseColor("#E1E0DE"));
        }
    }


    @Override
    protected void onRestart() {
        super.onRestart();
        isShowOkBt();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }
}
