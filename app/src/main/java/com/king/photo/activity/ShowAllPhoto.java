package com.king.photo.activity;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.Color;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;

import com.king.photo.R;
import com.king.photo.adapter.AlbumGridViewAdapter;
import com.king.photo.bean.ImageItem;
import com.king.photo.event.PhotoEvent;
import com.king.photo.util.Bimp;
import com.king.photo.util.PublicWay;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.OnItemClick;

public class ShowAllPhoto extends Activity {

    @BindView(R.id.tv_headtitle) TextView tvHeadTitle;
    @BindView(R.id.btn_preview) Button btnPreview;
    @BindView(R.id.btn_finish) Button btnFinish;
    @BindView(R.id.progressbar) ProgressBar progressBar;
    @BindView(R.id.gridview_photos) GridView gridview_photos;


    private AlbumGridViewAdapter gridImageAdapter;
    public static ArrayList<ImageItem> dataList = new ArrayList<ImageItem>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.plugin_camera_show_all_photo);
        ButterKnife.bind(this);
        EventBus.getDefault().register(this);
        PublicWay.activityList.add(this);

        initView();
        initListener();
        isShowOkBt();
    }


    private void initView() {
        String folderName = getIntent().getStringExtra("folderName");
        if (!TextUtils.isEmpty(folderName))
            folderName = folderName.length() > 8?folderName.substring(0, 9) + "...":folderName;

        tvHeadTitle.setText(folderName);
        progressBar.setVisibility(View.GONE);
        gridImageAdapter = new AlbumGridViewAdapter(this, dataList, Bimp.tempSelectBitmap);
        gridview_photos.setAdapter(gridImageAdapter);
    }


    @OnClick({R.id.btn_back, R.id.btn_cancle, R.id.btn_preview, R.id.btn_finish})
    void onButtonClick(View v) {
        switch (v.getId()) {
            case R.id.btn_back:
                startActivity(new Intent(this, ImageFile.class));
                break;
            case R.id.btn_cancle:
                Bimp.tempSelectBitmap.clear();
                startActivity(new Intent(this, MainActivity.class));
                break;
            case R.id.btn_preview:
                if (Bimp.tempSelectBitmap.size() > 0)
                    startActivity(new Intent(this, GalleryActivity.class).putExtra("position", "2"));
                break;
            case R.id.btn_finish:
                startActivity(new Intent(this, MainActivity.class));
                finish();
                break;
        }
    }


    @Subscribe
    public void isRefreshAlbum(PhotoEvent event){
        if (event.isRefresh()) gridImageAdapter.notifyDataSetChanged();
    }




    private void initListener() {

        gridImageAdapter
                .setOnItemClickListener(new AlbumGridViewAdapter.OnItemClickListener() {
                    public void onItemClick(final ToggleButton toggleButton, int position, boolean isChecked, Button button) {
                        if (Bimp.tempSelectBitmap.size() >= PublicWay.num && isChecked) {
                            button.setVisibility(View.GONE);
                            toggleButton.setChecked(false);
                            Toast.makeText(ShowAllPhoto.this, "超出可选图片张数", Toast.LENGTH_SHORT).show();
                            return;
                        }

                        if (isChecked) {
                            button.setVisibility(View.VISIBLE);
                            Bimp.tempSelectBitmap.add(dataList.get(position));
                            btnFinish.setText(Bimp.tempSelectBitmap.size() + "/" + PublicWay.num);
                        } else {
                            button.setVisibility(View.GONE);
                            Bimp.tempSelectBitmap.remove(dataList.get(position));
                            btnFinish.setText(Bimp.tempSelectBitmap.size() + "/" + PublicWay.num);
                        }
                        isShowOkBt();
                    }
                });
    }

    public void isShowOkBt() {
        if (Bimp.tempSelectBitmap.size() > 0) {
            btnFinish.setText(Bimp.tempSelectBitmap.size() + "/" + PublicWay.num);
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

    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK)
            startActivity(new Intent(this, ImageFile.class));
        return false;
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
