package com.xinglefly.photo.util;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Environment;

public class FileUtils {

	public static String SDPATH = Environment.getExternalStorageDirectory() + "/Photo_sd/";

	public static void saveBitmap(Bitmap bm, String picName) {
		try {
			File dirFile = new File(SDPATH);
			if (!dirFile.exists())
				dirFile.mkdir();
			File f = new File(SDPATH, picName + ".JPEG");
			BufferedOutputStream out = new BufferedOutputStream(new FileOutputStream(f));
			bm.compress(Bitmap.CompressFormat.JPEG, 100, out);
			out.flush();
			out.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
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


}
