package com.zhouzhou.utillib;

import java.io.IOException;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.Toast;

import com.zhouzhou.utillib.application.App;
import com.zhouzhou.utillib.component.DebugInfo;
import com.zhouzhou.utillib.view.CircleDrawable;

public class MainActivity extends Activity {
	View testBt;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		if (App.getInstance().isDebugMode()) {
			DebugInfo.trace();
		}
		testBt = findViewById(R.id.bt_test);
		testBt.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {

				// playRTSP();
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

		final int shadowSize = getResources().getDimensionPixelSize(
				R.dimen.shadow_size);
		final int shadowColor = getResources().getColor(R.color.shadow_color);
		// Bitmap mBitmap = getResources().get

		Drawable d = getResources().getDrawable(R.drawable.avater);

		BitmapDrawable bd = (BitmapDrawable) d;

		Bitmap mBitmap = bd.getBitmap();

		imageView.setImageDrawable(new CircleDrawable(mBitmap, shadowSize,
				shadowColor));

		if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB) {
			imageView.setLayerType(View.LAYER_TYPE_SOFTWARE, null);
		}

	}

	public void playRTSP() {
		MediaPlayer mediaPlayer = new MediaPlayer();
		mediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
		try {
			mediaPlayer.setDataSource("rtsp://116.90.85.230/2.mp3");
			mediaPlayer.prepare();
		} catch (IllegalArgumentException e) {
			// TODO Auto-generated catch block
			// e.printStackTrace();
			Toast.makeText(MainActivity.this, "Eror Play", Toast.LENGTH_SHORT)
					.show();
		} catch (IllegalStateException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		mediaPlayer.start();

	}
}
