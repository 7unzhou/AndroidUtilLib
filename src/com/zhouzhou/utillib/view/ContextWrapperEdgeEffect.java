/**
 * 
 */
package com.zhouzhou.utillib.view;

import com.zhouzhou.utillib.R;

import android.content.Context;
import android.content.ContextWrapper;
import android.content.res.AssetManager;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.util.DisplayMetrics;
import android.util.Log;

/**
 * @author : zhoujunzhou
 * @date : 2013-9-18
 * @Description :
 */
public class ContextWrapperEdgeEffect extends ContextWrapper {

	private ResourcesEdgeEffect mResourcesEdgeEffect;
	private int mColor;
	private Drawable mEdgeDrawable;
	private Drawable mGlowDrawable;

	public ContextWrapperEdgeEffect(Context context) {
		this(context, 0);
	}

	public ContextWrapperEdgeEffect(Context context, int color) {
		super(context);
		mColor = color;
		Resources resources = context.getResources();
		mResourcesEdgeEffect = new ResourcesEdgeEffect(resources.getAssets(),
				resources.getDisplayMetrics(), resources.getConfiguration());
	}

	public void setEdgeEffectColor(int color) {
		mColor = color;
		if (mEdgeDrawable != null)
			mEdgeDrawable.setColorFilter(color, PorterDuff.Mode.MULTIPLY);
		if (mGlowDrawable != null)
			mGlowDrawable.setColorFilter(color, PorterDuff.Mode.MULTIPLY);
	}

	@Override
	public Resources getResources() {
		return mResourcesEdgeEffect;
	}

	private class ResourcesEdgeEffect extends Resources {
		private int overscroll_edge = getPlatformDrawableId("overscroll_edge");
		private int overscroll_glow = getPlatformDrawableId("overscroll_glow");

		public ResourcesEdgeEffect(AssetManager assets, DisplayMetrics metrics,
				Configuration config) {
			// super(metrics, localConfiguration);
			super(assets, metrics, config);
		}

		// 用反射取得framework-res中的overscroll_edge图片id
		private int getPlatformDrawableId(String name) {
			try {
				int i = ((Integer) Class
						.forName("com.android.internal.R$drawable")
						.getField(name).get(null)).intValue();
				return i;
			} catch (ClassNotFoundException e) {
				Log.e("[ContextWrapperEdgeEffect].getPlatformDrawableId()",
						"Cannot find internal resource class");
				return 0;
			} catch (NoSuchFieldException e1) {
				Log.e("[ContextWrapperEdgeEffect].getPlatformDrawableId()",
						"Internal resource id does not exist: " + name);
				return 0;
			} catch (IllegalArgumentException e2) {
				Log.e("[ContextWrapperEdgeEffect].getPlatformDrawableId()",
						"Cannot access internal resource id: " + name);
				return 0;
			} catch (IllegalAccessException e3) {
				Log.e("[ContextWrapperEdgeEffect].getPlatformDrawableId()",
						"Cannot access internal resource id: " + name);
			}
			return 0;
		}

		@Override
		public Drawable getDrawable(int resId)
				throws Resources.NotFoundException {
			Drawable ret = null;
			if (resId == this.overscroll_edge) {
				mEdgeDrawable = ContextWrapperEdgeEffect.this.getBaseContext()
						.getResources().getDrawable(R.drawable.overscroll_edge);
				ret = mEdgeDrawable;
			} else if (resId == this.overscroll_glow) {
				mGlowDrawable = ContextWrapperEdgeEffect.this.getBaseContext()
						.getResources().getDrawable(R.drawable.overscroll_glow);
				ret = mGlowDrawable;
			} else
				return super.getDrawable(resId);

			if (ret != null) {
				ret.setColorFilter(mColor, PorterDuff.Mode.MULTIPLY);
			}

			return ret;
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see android.content.res.Resources#getString(int)
		 */
		@Override
		public String getString(int id) throws NotFoundException {
			// TODO Auto-generated method stub
			return super.getString(id);
		}

	}

}
