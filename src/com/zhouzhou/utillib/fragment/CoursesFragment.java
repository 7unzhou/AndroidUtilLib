/**
 * 
 */
package com.zhouzhou.utillib.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.zhouzhou.utillib.R;

/**
 * @author : zhoujunzhou
 * @date : 2013-8-19
 * @Description :
 */
public class CoursesFragment extends Fragment {

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		View view = inflater.inflate(R.layout.fragment_courses, null);
		return view;
	}
}
