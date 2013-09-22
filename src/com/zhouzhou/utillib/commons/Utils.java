package com.zhouzhou.utillib.commons;

import java.io.File;

import android.os.Environment;

public class Utils {

	/**
	 * 转化时长的格式到时：分：秒的格式
	 * 
	 * @param time
	 *            - 时间，以微妙的格式
	 * @return
	 */
	public static String durationFormat(int time) {
		time /= 1000;
		int minute = time / 60;
		int hour = minute / 60;
		int second = time % 60;
		minute %= 60;
		if (hour == 0) {
			return String.format("%02d:%02d", minute, second);
		} else {
			return String.format("%d:%02d:%02d", hour, minute, second);
		}
	}

	/** 获取根目录路径 */
	public static String getSDPath() {
		File sdDir = null;
		boolean sdCardExist = Environment.getExternalStorageState().equals(
				Environment.MEDIA_MOUNTED); // 判断sd卡是否存在
		if (sdCardExist) {
			sdDir = Environment.getExternalStorageDirectory();
		}
		return sdDir.toString();

	}
}
