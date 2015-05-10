package com.devi.nayanmars;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.support.v4.app.Fragment;

public class NayanmarControl {
	private Activity activity;
	
	
	public NayanmarControl(Activity activity){
		this.activity = activity;
	}
	
	public List<Fragment> getNayanmarFragments(){
		String [] stories = activity.getResources().getStringArray(R.array.nayanar_stories);
		List<Fragment> list = new ArrayList<Fragment>();

		for(int i = 0; i < stories.length; i+=2){
		CustomFragment x = CustomFragment.newStory(stories[i], stories[i+1]);
		list.add(x);
		}
		return list;
	}
}
