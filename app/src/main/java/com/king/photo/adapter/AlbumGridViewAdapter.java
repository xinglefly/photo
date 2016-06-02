package com.king.photo.adapter;

import java.util.ArrayList;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.ToggleButton;

import com.king.photo.R;
import com.king.photo.util.BitmapCache;
import com.king.photo.util.BitmapCache.ImageCallback;
import com.king.photo.bean.ImageItem;
import com.king.photo.util.ViewHolder;


public class AlbumGridViewAdapter extends BaseAdapter{

	final String TAG = getClass().getSimpleName();
	private Context mContext;
	private ArrayList<ImageItem> dataList;
	private ArrayList<ImageItem> selectedDataList;
	private DisplayMetrics dm;
	BitmapCache cache;

	public AlbumGridViewAdapter(Context c, ArrayList<ImageItem> dataList, ArrayList<ImageItem> selectedDataList) {
		mContext = c;
		cache = new BitmapCache();
		this.dataList = dataList;
		this.selectedDataList = selectedDataList;
		dm = new DisplayMetrics();
		((Activity) mContext).getWindowManager().getDefaultDisplay().getMetrics(dm);
	}

	public int getCount() {
		return dataList.size();
	}

	public Object getItem(int position) {
		return dataList.get(position);
	}

	public long getItemId(int position) {
		return position;
	}


	public View getView(int position, View convertView, ViewGroup parent) {
		if (convertView == null)
			convertView =LayoutInflater.from(mContext) .inflate(R.layout.album_item,parent, false);

		ImageView imgView = ViewHolder.get(convertView, R.id.img_view);
		ToggleButton btnToggle = ViewHolder.get(convertView, R.id.btn_toggle);
		Button btnChoose = ViewHolder.get(convertView, R.id.btn_choose);
		RelativeLayout.LayoutParams lp = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.MATCH_PARENT,dipToPx(65));
		lp.setMargins(50, 0, 50,0);
		imgView.setLayoutParams(lp);

		String path;
		if (dataList != null && dataList.size() > position) path = dataList.get(position).imagePath;
		else path = "camera_default";

		if (path.contains("camera_default")) {
			imgView.setImageResource(R.drawable.plugin_camera_no_pictures);
		} else {
			final ImageItem item = dataList.get(position);
			imgView.setTag(item.imagePath);
			cache.displayBmp(imgView, item.thumbnailPath, item.imagePath, callback);
		}
		btnToggle.setTag(position);
		btnChoose.setTag(position);
		btnToggle.setOnClickListener(new ToggleClickListener(btnChoose));
		if (selectedDataList.contains(dataList.get(position))) {
			btnToggle.setChecked(true);
			btnChoose.setVisibility(View.VISIBLE);
		} else {
			btnToggle.setChecked(false);
			btnChoose.setVisibility(View.GONE);
		}

		return convertView;
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

	public int dipToPx(int dip) {
		return (int) (dip * dm.density + 0.5f);
	}

	private class ToggleClickListener implements OnClickListener{
		Button chooseBt;
		public ToggleClickListener(Button choosebt){
			this.chooseBt = choosebt;
		}
		
		@Override
		public void onClick(View view) {
			if (view instanceof ToggleButton) {
				ToggleButton toggleButton = (ToggleButton) view;
				int position = (Integer) toggleButton.getTag();
				if (dataList != null && mOnItemClickListener != null
						&& position < dataList.size()) {
					mOnItemClickListener.onItemClick(toggleButton, position, toggleButton.isChecked(),chooseBt);
				}
			}
		}
	}
	

	private OnItemClickListener mOnItemClickListener;

	public void setOnItemClickListener(OnItemClickListener l) {
		mOnItemClickListener = l;
	}

	public interface OnItemClickListener {
		public void onItemClick(ToggleButton view, int position, boolean isChecked,Button chooseBt);
	}

}
