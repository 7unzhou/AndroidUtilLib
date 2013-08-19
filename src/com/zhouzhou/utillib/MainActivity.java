package com.zhouzhou.utillib;

import java.io.IOException;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.drawable.AnimationDrawable;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.Toast;

import com.zhouzhou.utillib.application.App;
import com.zhouzhou.utillib.component.DebugInfo;
import com.zhouzhou.utillib.view.CircleDrawable;

public class MainActivity extends FragmentActivity {
	View testBt;
	View animationView;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		DebugInfo.trace();
		testBt = findViewById(R.id.bt_test);
		testBt.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {

			}
		});

		initView();

	}

	/**
	 * 
	 */
	private void initView() {
		final ImageView imageView = (ImageView) this
				.findViewById(R.id.iv_avater);

		animationView = this.findViewById(R.id.rl_playstatus);

		drawCircleView(imageView);
	}

	/*
	 * 在onCreate()方法中，View并未完成显示(同理，在此方法中测量某个View的宽高，常得到0值。
	 */
	@Override
	public void onWindowFocusChanged(boolean hasFocus) {
		super.onWindowFocusChanged(hasFocus);
		AnimationDrawable ad = (AnimationDrawable) animationView
				.getBackground();
		ad.start();
	}

	private void drawCircleView(ImageView imageView) {
		final int shadowSize = getResources().getDimensionPixelSize(
				R.dimen.shadow_size);
		final int shadowColor = getResources().getColor(R.color.shadow_color);
		Drawable d = getResources().getDrawable(R.drawable.avater);
		BitmapDrawable bd = (BitmapDrawable) d;
		Bitmap mBitmap = bd.getBitmap();

		imageView.setImageDrawable(new CircleDrawable(mBitmap, shadowSize,
				shadowColor));

		if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB) {
			imageView.setLayerType(View.LAYER_TYPE_SOFTWARE, null);
		}
	}

}
