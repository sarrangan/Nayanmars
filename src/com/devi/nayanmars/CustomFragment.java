package com.devi.nayanmars;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class CustomFragment extends Fragment {
	
	public static final String TITLE = "TITLE";
	public static final String CONTENT = "CONTENT";
	
	public static final CustomFragment newStory(String title, String content){
		CustomFragment f = new CustomFragment();
		Bundle bundle = new Bundle(1);
		bundle.putString(TITLE, title);
		bundle.putString(CONTENT, content);
		f.setArguments(bundle);
		return f;
	}
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, 
			Bundle savedInstanceState){
		String title = getArguments().getString(TITLE);
		String content = getArguments().getString(CONTENT);
		View v = inflater.inflate(R.layout.story_page, container, false);
		TextView titleText = (TextView)v.findViewById(R.id.story_title);
		titleText.setText(title);
		TextView storyText = (TextView) v.findViewById(R.id.story_text);
		storyText.setText(content);
		return v;
	}
}
