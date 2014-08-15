/**
 * 
 */
package com.zhouzhou.utillib.component;

import java.io.File;

import android.app.DownloadManager;
import android.app.DownloadManager.Request;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.Uri;
import android.os.Environment;
import android.util.Log;
import android.widget.Toast;

/**
 * @author : zhoujunzhou
 * @date : 2013-9-11
 * @Description :
 */
public class MyDownloadManager {

	protected static final String TAG = "MyDownloadManager";

	public static MyDownloadManager getInstance(Context context) {
		if (mDownLoadManager == null) {
			mDownLoadManager = new MyDownloadManager();
			String serviceString = Context.DOWNLOAD_SERVICE;
			downloadManager = (DownloadManager) context
					.getSystemService(serviceString);
			// context.registerReceiver(receiver, filter);

		}
		return mDownLoadManager;
	}

	private static MyDownloadManager mDownLoadManager;
	private static DownloadManager downloadManager;

	public MyDownloadManager() {

	}

	public long download(Context context, String url) {
		// Download data..
		Uri uri = Uri.parse(url);

		DownloadManager.Request request = new Request(uri);
		/*
		 * DownloadManager.Request对象request添加HTTP头，也可以通过setMimeType方法重写从服务器返回的mime
		 * type
		 */
		// request.addRequestHeader(arg0, arg1);
		// 禁止发出通知，既后台下载
		/*<uses-permission android:name="android.permission.DOWNLOAD_WITHOUT_NOTIFICATION"/>*/
		// request.setShowRunningNotification(false);
		//request.setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED);
		
		//表示下载进行中和下载完成的通知栏是否显示。
		//默认只显示下载中通知。VISIBILITY_VISIBLE_NOTIFY_COMPLETED表示下载完成后显示通知栏提示。
		//VISIBILITY_HIDDEN表示不显示任何通知栏提示，这个需要在AndroidMainfest中添加权限
		//request.setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED);
		// 不显示下载界面
		request.setVisibleInDownloadsUi(false);
		// 设置下载后文件存放的位置

		int index = url.lastIndexOf("/");
		String fileName = url.substring(index + 1, url.length());
		// System.out.println("File name :"+fileName);
		// request.setDestinationInExternalFilesDir(context, null,fileName);
		request.setDestinationInExternalPublicDir("BybyMusic", "testfile.mp3");
		request.allowScanningByMediaScanner();
		/* 用于指定一个较大的文件只能在WiFi下进行下载 */
		// request.setAllowedNetworkTypes(Request.NETWORK_WIFI);
		long downloadID = downloadManager.enqueue(request);
		context.registerReceiver(receiver, filter);
		return downloadID;
	}

	IntentFilter filter = new IntentFilter(
			DownloadManager.ACTION_DOWNLOAD_COMPLETE);

	BroadcastReceiver receiver = new BroadcastReceiver() {
		@Override
		public void onReceive(Context context, Intent intent) {
			if (intent.getAction().equals(
					DownloadManager.ACTION_DOWNLOAD_COMPLETE)) {
				long downId = intent.getLongExtra(
						DownloadManager.EXTRA_DOWNLOAD_ID, -1);
				Log.v(TAG, " download complete! id : " + downId);
//				File mp3File = context.getExternalFilesDir("testfile.mp3");
//				
//				if (null != mp3File) {
//					String filePath = mp3File.getPath();
//					Log.v(TAG, " download file path : " + filePath);
//				}

				Toast.makeText(context, intent.getAction() + "id : " + downId,
						Toast.LENGTH_SHORT).show();
			}
		}
	};

}
