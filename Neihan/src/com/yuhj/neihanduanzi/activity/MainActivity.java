package com.yuhj.neihanduanzi.activity;

import java.util.ArrayList;

import com.yuhj.neihanduanzi.R;
import com.yuhj.neihanduanzi.R.layout;
import com.yuhj.neihanduanzi.R.menu;
import com.yuhj.neihanduanzi.fragment.HuodongFragment;
import com.yuhj.neihanduanzi.fragment.ImageListFragment;
import com.yuhj.neihanduanzi.fragment.MyFragment;
import com.yuhj.neihanduanzi.fragment.ReviewFragment;
import com.yuhj.neihanduanzi.fragment.TestFragment;

import android.os.Bundle;
import android.R.integer;
import android.app.Activity;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.Menu;
import android.view.Window;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RadioGroup.OnCheckedChangeListener;

public class MainActivity extends FragmentActivity implements OnCheckedChangeListener {
	private ArrayList<Fragment> fragments;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_main);
		RadioGroup group = (RadioGroup) findViewById(R.id.main_tab_bar);
		group.setOnCheckedChangeListener(this);
		fragments=new ArrayList<Fragment>();
		fragments.add(new TestFragment());
		fragments.add(new ImageListFragment());
		fragments.add(new MyFragment());
		fragments.add(new ReviewFragment());
		fragments.add(new HuodongFragment());
		Fragment fragment =fragments.get(0);
		FragmentManager manager =getSupportFragmentManager();
		FragmentTransaction transaction =manager.beginTransaction();
		transaction.replace(R.id.main_framelayout,fragment);
		transaction.commit();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public void onCheckedChanged(RadioGroup group, int checkedId) {
		int childCount = group.getChildCount();
		int checkedIndex = 0;
		RadioButton btnButton = null;
		for (int i = 0; i < childCount; i++) {
			btnButton = (RadioButton) group.getChildAt(i);
			if (btnButton.isChecked()) {
				checkedIndex = i;
				break;
			}
		}
		Fragment fragment =fragments.get(checkedIndex);
		FragmentManager manager =getSupportFragmentManager();
		FragmentTransaction transaction =manager.beginTransaction();
		transaction.replace(R.id.main_framelayout,fragment);
		transaction.commit();
	}

}
