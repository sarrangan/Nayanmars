package com.devi.nayanmars;
import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;

public class StoryView extends FragmentActivity {

	PageAdapter pageAdapter;
	NayanmarControl nayanmars;
	ViewPager storyPager;
	private AsyncTask<Void, Void, Void> pageLoader;
	
	@Override
	public void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		setContentView(R.layout.story_view);
		nayanmars = new NayanmarControl(this);
		List<Fragment> stories = getStories();
		storyPager = (ViewPager)findViewById(R.id.story_pager);
		pageAdapter = new PageAdapter(getSupportFragmentManager(), stories);
		storyPager.setAdapter(pageAdapter);
		Intent intent = getIntent();
		storyPager.setCurrentItem(intent.getIntExtra(MainActivity.NAYANMAR_ID, 0));
		storyPager.setOffscreenPageLimit(1);
		pageLoader = new loadBigPages(this).execute();
	}
	
	@Override
	protected void onPause() {
		if(pageLoader != null){
			pageLoader.cancel(true);
		}
		super.onPause();
	}
	
	private List<Fragment> getStories(){
		List<Fragment> list = new ArrayList<Fragment>();
		list = nayanmars.getNayanmarFragments();
		return list;
	}
	
	private class loadBigPages extends AsyncTask<Void, Void, Void>{
		private Activity activity;
		private int loader;
		
		public loadBigPages(Activity activity){
			this.activity = activity;
			loader = 2;
		}
		
		@Override
		protected Void doInBackground(Void... params) {
			while(!isCancelled() && loader != 5){
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					break;
				}
				runOnUiThread(new Runnable() {
					@Override
					public void run() {
						storyPager.setOffscreenPageLimit(loader);
					}
				});
				System.out.println("Loader: " + loader);
				loader+=1;
			}
			return null;
		}
		
		
	}
}
