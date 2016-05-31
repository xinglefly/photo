package com.king.photo.adapter;

import java.util.ArrayList;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.king.photo.R;
import com.king.photo.activity.AlbumActivity;
import com.king.photo.activity.ShowAllPhoto;
import com.king.photo.util.BitmapCache;
import com.king.photo.util.BitmapCache.ImageCallback;
import com.king.photo.bean.ImageItem;
import com.king.photo.util.ViewHolder;


public class FolderAdapter extends BaseAdapter {

	private Context mContext;
	private Intent mIntent;
	private DisplayMetrics dm;
	BitmapCache cache;
	final String TAG = getClass().getSimpleName();

	public FolderAdapter(Context c) {
		cache = new BitmapCache();
		init(c);
	}


	public void init(Context c) {
		mContext = c;
		mIntent = ((Activity) mContext).getIntent();
		dm = new DisplayMetrics();
		((Activity) mContext).getWindowManager().getDefaultDisplay().getMetrics(dm);
	}

	

	@Override
	public int getCount() {
		return AlbumActivity.contentList.size();
	}

	@Override
	public Object getItem(int position) {
		return position;
	}

	@Override
	public long getItemId(int position) {
		return position;
	}
	
	ImageCallback callback = new ImageCallback() {
		@Override
		public void imageLoad(ImageView imageView, Bitmap bitmap, Object... params) {
			if (imageView != null && bitmap != null) {
				String url = (String) params[0];
				if (url != null && url.equals((String) imageView.getTag())) {
					((ImageView) imageView).setImageBitmap(bitmap);
				} else {
					Log.e(TAG, "callback, bmp not match");
				}
			} else {
				Log.e(TAG, "callback, bmp null");
			}
		}
	};


	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		if (convertView == null)
			convertView =LayoutInflater.from(mContext).inflate(R.layout.plugin_camera_select_folder,parent, false);

		ImageView imgFile = ViewHolder.get(convertView, R.id.img_file);
		ImageView imgChoose = ViewHolder.get(convertView, R.id.img_choose);
		TextView tvFileNum = ViewHolder.get(convertView, R.id.tv_filenum);
		TextView tvFolderName = ViewHolder.get(convertView, R.id.tv_folder_name);

		String path;
		if (AlbumActivity.contentList.get(position).imageList != null) {
			path = AlbumActivity.contentList.get(position).imageList.get(0).imagePath;
			tvFolderName.setText(AlbumActivity.contentList.get(position).bucketName);
			tvFileNum.setText("" + AlbumActivity.contentList.get(position).count);
		} else path = "android_hybrid_camera_default";

		if (path.contains("android_hybrid_camera_default"))
			imgFile.setImageResource(R.drawable.plugin_camera_no_pictures);
		else {
			final ImageItem item = AlbumActivity.contentList.get(position).imageList.get(0);
			imgFile.setTag(item.imagePath);
			cache.displayBmp(imgFile, item.thumbnailPath, item.imagePath, callback);
		}

		imgFile.setOnClickListener(new ImageViewClickListener(position, mIntent,imgChoose));
		
		return convertView;
	}

	private class ImageViewClickListener implements OnClickListener {
		private int position;
		private Intent intent;
		private ImageView imgChoose;
		public ImageViewClickListener(int position, Intent intent,ImageView imgChoose) {
			this.position = position;
			this.intent = intent;
			this.imgChoose = imgChoose;
		}
		
		public void onClick(View v) {
			ShowAllPhoto.dataList = (ArrayList<ImageItem>) AlbumActivity.contentList.get(position).imageList;
			String folderName = AlbumActivity.contentList.get(position).bucketName;
			mContext.startActivity(new Intent(mContext, ShowAllPhoto.class).putExtra("folderName", folderName));
			imgChoose.setVisibility(v.VISIBLE);
		}
	}

	public int dipToPx(int dip) {
		return (int) (dip * dm.density + 0.5f);
	}

}
