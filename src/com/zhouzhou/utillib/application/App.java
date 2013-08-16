/**
 * 
 */
package com.zhouzhou.utillib.application;

import android.app.Application;
import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.os.Build;

import com.zhouzhou.utillib.component.CrashHandler;

/**
 * @author : zhoujunzhou
 * @date : 2013-8-12
 * @Description :
 */
public class App extends Application {
	private static App instance;
	private boolean isDebugMode;
	public static CrashHandler crashHandler;
	/**SDK版本号*/
	public static int SDK_INT = Build.VERSION.SDK_INT;
	public static App getInstance() {
		return instance;
	}

	@Override
	public void onCreate() {
		// TODO Auto-generated method stub
		super.onCreate();
		instance = this;
		isDebugMode = isDebugMode(this);
		
		crashHandler = CrashHandler.getInstance();
		// 注册crashHandler
		crashHandler.init(getApplicationContext());
		// 发送报告(可选) 可添加在网络判断的新开线程来完成
		crashHandler.sendCrashReportsToServer(getApplicationContext());

	}

	public boolean isDebugMode() {
		return isDebugMode;
	}

	private boolean isDebugMode(Context context) {
		PackageManager pm = context.getPackageManager();
		try {
			ApplicationInfo info = pm.getApplicationInfo(
					context.getPackageName(), 0);
			return (info.flags & ApplicationInfo.FLAG_DEBUGGABLE) != 0;
		} catch (NameNotFoundException e) {
		}
		return true;
	}

}
