/**
 * 
 */
package com.zhouzhou.utillib.component;

import android.app.DownloadManager;
import android.app.DownloadManager.Request;
import android.app.IntentService;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;

/**
 * @author : zhoujunzhou
 * @date : 2013-9-11
 * @Description :
 */
public class DownloadService extends IntentService {
	private static final String SERVICE_NAME = "DownloadService";

	private static Context mContext;

	public static void requestDownload(Context context, Uri uri) {
		mContext = context;
		Intent intent = new Intent(context, DownloadService.class);
		intent.setData(uri);
		context.startService(intent);
	}

	public DownloadService() {
		super(SERVICE_NAME);
	}

	@Override
	protected void onHandleIntent(Intent intent) {
		download(intent.getData());
	}
//Uri uri = Uri.parse("http://developer.android.com/shareables/icon_templates-v4.0.zip");  
	private void download(Uri uri) {
		// Download data..
		String serviceString = Context.DOWNLOAD_SERVICE;
		DownloadManager downloadManager;
		downloadManager = (DownloadManager) getSystemService(serviceString);
		
		DownloadManager.Request request = new Request(uri);  
		/*DownloadManager.Request对象request添加HTTP头，也可以通过setMimeType方法重写从服务器返回的mime type*/
		//request.addRequestHeader(arg0, arg1);
		long reference = downloadManager.enqueue(request);
		/*用于指定一个较大的文件只能在WiFi下进行下载*/
		//request.setAllowedNetworkTypes(Request.NETWORK_WIFI);  
		//request.setDestinationUri(Uri.fromFile(f));  
	}

}
