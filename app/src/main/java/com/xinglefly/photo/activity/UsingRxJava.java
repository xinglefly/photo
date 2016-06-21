package com.xinglefly.photo.activity;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.xinglefly.photo.R;
import com.xinglefly.photo.activity.actions.SuccessAction;

import butterknife.BindView;
import butterknife.ButterKnife;
import rx.Observable;
import rx.Observer;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

public class UsingRxJava extends Activity {

    private static final String TAG = UsingRxJava.class.getSimpleName();

    @BindView(R.id.btn_click) Button btnClick;
    @BindView(R.id.img_pic) ImageView imgPic;

    String[] str = {"ershazi","zhendong","xiaobao"};
    int drawables = R.drawable.btn_album;
    String imgPath = "/storage/emulated/0/Music/Cover/Tragédie-Hey Oh.jpg";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.rxjava);
        ButterKnife.bind(this);
        useObservableFrom();
//        useObservableCreate();
        useObservableJust();
        useObserverBitMap();
    }

    private void useObserverBitMap() {
        Observable.just(imgPath)
                .map(new Func1<String, Bitmap>() {
                    @Override
                    public Bitmap call(String s) {
                        return BitmapFactory.decodeFile(imgPath);
                    }
                }).subscribe(new Action1<Bitmap>() {
            @Override
            public void call(Bitmap bitmap) {
                imgPic.setImageBitmap(bitmap);
            }
        });
    }


    private void useObservableJust() {
        Observable.just(1,2,3,4)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new SuccessAction<Integer>() {
                    @Override
                    protected void success(Integer number) {
                        Log.d(TAG,number+"");
                        btnClick.setText(number+"");
                    }
                });
    }


    private void useObservableCreate() {
        Observable.create(new Observable.OnSubscribe<Drawable>() {

            @Override
            public void call(Subscriber<? super Drawable> subscriber) {
                Drawable drawable = getResources().getDrawable(drawables);
                subscriber.onNext(drawable);
                subscriber.onCompleted();
            }
        }).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<Drawable>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        Toast.makeText(UsingRxJava.this, "set picture is err.", Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onNext(Drawable drawable) {
                        imgPic.setImageDrawable(drawable);
                    }
        });
    }

    private void useObservableFrom() {
        Observable.from(str).subscribe(new Action1<String>() {
            @Override
            public void call(String name) {
                Log.d(TAG,name);
            }
        });
    }


    Observable<String> observable = Observable.create(new Observable.OnSubscribe<String>(){

        @Override
        public void call(Subscriber<? super String> subscriber) {
            Toast.makeText(UsingRxJava.this, "hahah", Toast.LENGTH_SHORT).show();
            subscriber.onNext("ershazi");
            subscriber.onNext("zhendong");
            subscriber.onNext("xiaobao");
            subscriber.onCompleted();
        }
    });

    Observable obser = Observable.just("1","2","3");
    Observable ob = Observable.from(str);

    Action1<String> onNextAction = new Action1<String>() {
        @Override
        public void call(String s) {
            Log.d(TAG,"onNext-->"+s);
        }
    };

}