package com.zhouzhou.utillib;

import java.io.File;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.AnimationDrawable;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.Toast;

import com.zhouzhou.utillib.commons.Utils;
import com.zhouzhou.utillib.component.DebugInfo;
import com.zhouzhou.utillib.component.FileUtils;
import com.zhouzhou.utillib.component.MyDownloadManager;
import com.zhouzhou.utillib.fragment.CoursesFragment;
import com.zhouzhou.utillib.view.CircleDrawable;
import com.zhouzhou.utillib.view.EdgeEffectListView;

public class MainActivity extends FragmentActivity {
	private static final int FILE_SELECT_CODE = 889;
	View testBt;
	View animationView;
	View contentView;

	String uri = "http://static.yulebaby.com/mp3/32/78/32780527bd504547833620d5ef76e3e6.mp3";
	String uri2 = "http://static.yulebaby.com/mp3/cc/f7/ccf70d41b23141bcb00ecbadb3f30324_128.mp3";
	String uri3 = "http://static.yulebaby.com/mp3/c8/35/c8350daa4b2c4809b30e081a2034f32e.mp3";

	String filePath = Utils.getSDPath() + "/" + "BybyMusic" + "/";

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		DebugInfo.trace();
		testBt = findViewById(R.id.bt_test);

		testBt.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// showFileChooser();
				// downLoadTask(uri);
			}
		});

//		EdgeEffectListView listView = (EdgeEffectListView) findViewById(R.id.listview);
//		listView.setAdapter(new ArrayAdapter<String>(this,
//				android.R.layout.simple_list_item_1, getResources()
//						.getStringArray(R.array.stringarray)));

		initView();
		showFragment();

	}

	/**
	 * 
	 */
	private void downLoadTask(String url) {

		int index = url.lastIndexOf("/");
		String fileName = url.substring(index + 1, url.length());

		filePath = filePath + fileName;

		File tempMp3 = new File(filePath);
		if (tempMp3.isFile()) {
			System.out.println("is File in sdcardDir:" + filePath);
		} else {
			System.out.println("is not File in sdcardDir:" + filePath);

			MyDownloadManager dm = MyDownloadManager
					.getInstance(getApplicationContext());
			dm.download(getApplicationContext(), url);
		}
		// System.out.println("sdcardDir:"+sdcardDir.getPath());

		// dm.download(getApplicationContext(), uri);
		// dm.download(getApplicationContext(), uri2);
		// dm.download(getApplicationContext(), uri3);

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

	private void showFragment() {
		Fragment contentFragment = new CoursesFragment();
		FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
		// ft.replace(R.id.rl_content, contentFragment);
		// ft.addToBackStack(null);
		// ft.commitAllowingStateLoss();
	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		switch (requestCode) {
		case FILE_SELECT_CODE:
			if (resultCode == RESULT_OK) {
				// Get the Uri of the selected file
				Uri uri = data.getData();
				String path = FileUtils.getPath(this, uri);
				System.out.println("The file path:" + path);
			}
			break;
		}
		super.onActivityResult(requestCode, resultCode, data);
	}

	private void showFileChooser() {
		Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
		intent.setType("*/*");
		intent.addCategory(Intent.CATEGORY_OPENABLE);
		try {
			startActivityForResult(
					Intent.createChooser(intent, "Select a File to Upload"),
					FILE_SELECT_CODE);
		} catch (android.content.ActivityNotFoundException ex) {
			Toast.makeText(this, "Please install a File Manager.",
					Toast.LENGTH_SHORT).show();
		}
	}

}
