/**
 * 
 */
package com.zhouzhou.utillib.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.widget.ListView;

import com.zhouzhou.utillib.R;

/**
 * @author : zhoujunzhou
 * @date : 2013-9-18
 * @Description :
 */
public class EdgeEffectListView extends ListView {
	public EdgeEffectListView(Context context) {
		this(context, null);
	}

	public EdgeEffectListView(Context context, AttributeSet attrs) {
		this(context, attrs, android.R.attr.listViewStyle);
	}

	public EdgeEffectListView(Context context, AttributeSet attrs, int defStyle) {
		super(new ContextWrapperEdgeEffect(context), attrs, defStyle);
		init(context, attrs, defStyle);
	}

	private void init(Context context, AttributeSet attrs, int defStyle) {
		int color = context.getResources().getColor(
				R.color.default_edgeeffect_color);

		if (attrs != null) {
			TypedArray a = context.obtainStyledAttributes(attrs,
					R.styleable.EdgeEffectView, defStyle, 0);
			color = a.getColor(R.styleable.EdgeEffectView_edgeeffect_color,
					color);
			a.recycle();
		}
		setEdgeEffectColor(color);
	}

	public void setEdgeEffectColor(int edgeEffectColor) {
		((ContextWrapperEdgeEffect) getContext())
				.setEdgeEffectColor(edgeEffectColor);
	}
}
