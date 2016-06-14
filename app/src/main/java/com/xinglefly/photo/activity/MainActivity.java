package com.xinglefly.photo.activity;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup.LayoutParams;
import android.view.animation.AnimationUtils;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.GridView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.xinglefly.photo.R;
import com.xinglefly.photo.adapter.PhotoGridAdapter;
import com.xinglefly.photo.util.Bimp;
import com.xinglefly.photo.util.FileUtils;
import com.xinglefly.photo.bean.ImageItem;
import com.xinglefly.photo.util.PublicWay;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.OnItemClick;


public class MainActivity extends Activity {

    @BindView(R.id.noScrollgridview) GridView noScrollgridview;

    private PhotoGridAdapter adapter;
    private View parentView;
    private PopupWindow pop = null;
    private LinearLayout ll_popup;
    public static Bitmap bimap;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        parentView = getLayoutInflater().inflate(R.layout.main_activity, null);
        setContentView(parentView);
        ButterKnife.bind(this);
        initView();
        initPup();
    }

    private void initView() {
        bimap = BitmapFactory.decodeResource(getResources(), R.drawable.addpic);
        PublicWay.activityList.add(this);
        noScrollgridview.setSelector(new ColorDrawable(Color.TRANSPARENT));
        adapter = new PhotoGridAdapter(this);
        adapter.isRefresh();
        noScrollgridview.setAdapter(adapter);
    }

    @OnClick(R.id.tv_upload)
    void onUploadClick(){
        Intent intent = new Intent();
        ComponentName name = new ComponentName("com.zcsy.yidian","com.zcsy.yidian.activity.WelcomeActivity");
        intent.setComponent(name);
        intent.setAction(Intent.ACTION_VIEW);
        startActivity(intent);
    }

    @OnItemClick(R.id.noScrollgridview)
    void onGridviewItemClick(AdapterView<?> parent,int position){
        if (position == Bimp.tempSelectBitmap.size()) {
            ll_popup.startAnimation(AnimationUtils.loadAnimation(MainActivity.this, R.anim.activity_translate_in));
            pop.showAtLocation(parentView, Gravity.BOTTOM, 0, 0);
        } else {
            Intent intent = new Intent(MainActivity.this, GalleryActivity.class);
            intent.putExtra("position", "1");
            intent.putExtra("ID", position);
            startActivity(intent);
        }
    }

    public void initPup() {
        pop = new PopupWindow(MainActivity.this);
        View view = getLayoutInflater().inflate(R.layout.popupwindows, null);
        ll_popup = ButterKnife.findById(view, R.id.ll_popup);

        pop.setWidth(LayoutParams.MATCH_PARENT);
        pop.setHeight(LayoutParams.WRAP_CONTENT);
        pop.setBackgroundDrawable(new BitmapDrawable());
        pop.setFocusable(true);
        pop.setOutsideTouchable(true);
        pop.setContentView(view);

        RelativeLayout parent = ButterKnife.findById(view, R.id.parent);
        Button bt1 = ButterKnife.findById(view, R.id.item_popupwindows_camera);
        Button bt2 = ButterKnife.findById(view, R.id.item_popupwindows_Photo);
        Button bt3 = ButterKnife.findById(view, R.id.item_popupwindows_cancel);
        parent.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                pop.dismiss();
                ll_popup.clearAnimation();
            }
        });
        bt1.setOnClickListener(new OnClickListener() {
            public void onClick(View v) {
                photo();
                pop.dismiss();
                ll_popup.clearAnimation();
            }
        });
        bt2.setOnClickListener(new OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, AlbumActivity.class);
                startActivity(intent);
                overridePendingTransition(R.anim.activity_translate_in, R.anim.activity_translate_out);
                pop.dismiss();
                ll_popup.clearAnimation();
            }
        });
        bt3.setOnClickListener(new OnClickListener() {
            public void onClick(View v) {
                pop.dismiss();
                ll_popup.clearAnimation();
            }
        });
    }


    protected void onRestart() {
        adapter.isRefresh();
        super.onRestart();
    }

    private static final int TAKE_PICTURE = 0x000001;

    public void photo() {
        if (!FileUtils.hasSdcard()) {
            Toast.makeText(this,"SD卡不存在，不能拍照",Toast.LENGTH_SHORT).show();
            return;
        }
        Intent openCameraIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        startActivityForResult(openCameraIntent, TAKE_PICTURE);
    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        switch (requestCode) {
            case TAKE_PICTURE:
                if (Bimp.tempSelectBitmap.size() < 6 && resultCode == RESULT_OK){
                    String fileName = String.valueOf(System.currentTimeMillis());
                    Bitmap bm = (Bitmap) data.getExtras().get("data");
                    FileUtils.saveBitmap(bm, fileName);
                    ImageItem takePhoto = new ImageItem();
                    takePhoto.setBitmap(bm);
                    Bimp.tempSelectBitmap.add(takePhoto);
                }
                break;
        }
    }

    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            for (int i = 0; i < PublicWay.activityList.size(); i++) {
                if (null != PublicWay.activityList.get(i))
                    PublicWay.activityList.get(i).finish();
            }
            System.exit(0);
        }
        return true;
    }

}

