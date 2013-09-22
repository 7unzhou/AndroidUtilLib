/**
 * 
 */
package com.zhouzhou.utillib.customloader;

import android.os.AsyncTask;
import android.support.v4.content.Loader;

/**
 * @author : zhoujunzhou
 * @date : 2013-8-30
 * @Description :
 */
public abstract class ContentChangingTask<T1, T2, T3> extends
		AsyncTask<T1, T2, T3> {

	private Loader<?> loader = null;

	ContentChangingTask(Loader<?> loader) {
		this.loader = loader;
	}

	@Override
	protected void onPostExecute(T3 param) {
		loader.onContentChanged();
	}
}
