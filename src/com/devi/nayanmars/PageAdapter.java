package com.devi.nayanmars;

import java.util.List;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.view.ViewGroup;

public class PageAdapter extends FragmentStatePagerAdapter {

	private List<Fragment> fragments;

	public PageAdapter(FragmentManager fm, List<Fragment> fragments){
		super(fm);
		this.fragments = fragments;
	}
		
	@Override
	public Fragment getItem(int position) {
		return this.fragments.get(position);
	}

	@Override
	public int getCount() {
		return this.fragments.size();
	}
}
