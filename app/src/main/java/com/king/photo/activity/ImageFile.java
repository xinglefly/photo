package com.king.photo.activity;


import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.GridView;
import android.widget.TextView;

import com.king.photo.R;
import com.king.photo.adapter.FolderAdapter;
import com.king.photo.util.Bimp;
import com.king.photo.util.PublicWay;
import com.king.photo.util.Res;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;


public class ImageFile extends Activity {

    @BindView(R.id.headerTitle) TextView headerTitle;
    @BindView(R.id.gridview_file) GridView gridviewFile;
    private FolderAdapter folderAdapter;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.plugin_camera_image_file);
        ButterKnife.bind(this);
        initView();
    }

    private void initView() {
        headerTitle.setText("photo");
        PublicWay.activityList.add(this);
        folderAdapter = new FolderAdapter(this);
        gridviewFile.setAdapter(folderAdapter);
    }

    @OnClick(R.id.btn_cancle)
    void onBtnClick(){
        Bimp.tempSelectBitmap.clear();
        startActivity(new Intent(this,MainActivity.class));
    }



    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK)
            startActivity(new Intent(this, MainActivity.class));
        return true;
    }

}
