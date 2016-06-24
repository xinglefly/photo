package com.xinglefly.photo.util;

import java.io.BufferedOutputStream;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Environment;

public class FileUtils {

	public static String SDPATH = Environment.getExternalStorageDirectory() + "/Photo_sd/";

	public static String saveBitmap(Bitmap bm, String picName) {
		try {
			File dirFile = new File(SDPATH);
			if (!dirFile.exists()) {
				dirFile.mkdir();
			}
			File f = new File(SDPATH, picName + ".jpg");
			BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(f));
			bm.compress(Bitmap.CompressFormat.JPEG, 100, bos);
			bos.flush();
			bos.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return SDPATH + picName + ".jpg";
	}


	public static boolean hasSdcard() {
		String state = Environment.getExternalStorageState();
		return state.equals(Environment.MEDIA_MOUNTED);
	}


	public static Uri getCaptureFilePath() {
		String saveDir;
		if (hasSdcard()) {
			saveDir = SDPATH;
			createSaveDir(saveDir);
		} else {
			return null;
		}
		String fileName = "picture" + System.currentTimeMillis() + ".JPEG";
		return Uri.fromFile(new File(saveDir, fileName));
	}

	public static File getCaptureFile() {
		String saveDir;
		if (hasSdcard()) {
			saveDir = SDPATH;
			createSaveDir(saveDir);
		} else {
			return null;
		}
		String fileName = "picture" + System.currentTimeMillis() + ".JPEG";
		return new File(saveDir, fileName);
	}

	public static void createSaveDir(String savePath) {
		File savedir = new File(savePath);
		if (!savedir.exists()) {
			savedir.mkdirs();
		}
	}


	public static  Bitmap compressImage(Bitmap image) {

		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		image.compress(Bitmap.CompressFormat.JPEG, 100, baos);//质量压缩方法，这里100表示不压缩，把压缩后的数据存放到baos中
		int options = 100;
		while ( baos.toByteArray().length / 1024>100) {    //循环判断如果压缩后图片是否大于100kb,大于继续压缩
			baos.reset();//重置baos即清空baos
			if(options>10){
				options -= 10;//每次都减少10
			}
			image.compress(Bitmap.CompressFormat.JPEG, options, baos);//这里压缩options%，把压缩后的数据存放到baos中
		}
		ByteArrayInputStream isBm = new ByteArrayInputStream(baos.toByteArray());//把压缩后的数据baos存放到ByteArrayInputStream中
		Bitmap bitmap = BitmapFactory.decodeStream(isBm, null, null);//把ByteArrayInputStream数据生成图片
		return bitmap;
	}

}
