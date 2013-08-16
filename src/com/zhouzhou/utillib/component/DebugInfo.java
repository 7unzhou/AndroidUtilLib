/**
 * 
 */
package com.zhouzhou.utillib.component;

import android.util.Log;

/**
 * @author : zhoujunzhou
 * @date : 2013-8-12
 * @Description :
 */
public class DebugInfo {
	private static boolean mIsDebugMode = true;// 获取堆栈信息会影响性能，发布应用时记得关闭DebugMode

	private static final String CLASS_METHOD_LINE_FORMAT = "%s.%s()  Line:%d  (%s)";

	public static void trace() {
		if (mIsDebugMode) {
			StackTraceElement traceElement = Thread.currentThread()
					.getStackTrace()[3];// 从堆栈信息中获取当前被调用的方法信息

			String logText = String.format(CLASS_METHOD_LINE_FORMAT,
					traceElement.getClassName(), traceElement.getMethodName(),
					traceElement.getLineNumber(), traceElement.getFileName());
			Log.d("DebugInfo", logText);// 打印Log

			// Log.i("DebugInfo", traceElement.toString());
		}
	}
}
