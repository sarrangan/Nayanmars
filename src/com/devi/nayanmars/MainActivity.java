package com.devi.nayanmars;

import java.util.ArrayList;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.EditText;
import android.widget.ListView;


public class MainActivity extends ActionBarActivity {
	private CustomAdapter mAdapter;
	public static final String NAYANMAR_ID = "nayani";
	private String prevSearchTerm = "";
	private static String [] nayanmarNames;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setUpList();
    	nayanmarNames = this.getResources().getStringArray(R.array.nayanmar_names);
        EditText inputSearch = (EditText) findViewById(R.id.input_search);
        inputSearch.addTextChangedListener(new TextWatcher() {
            @Override
            public void onTextChanged(CharSequence cs, int arg1, int arg2, int arg3) {
        		CustomAdapter adapter = new CustomAdapter(MainActivity.this, false);
        		String currentSearchTerm = cs.toString();
           		ListView main_menu = (ListView) findViewById(R.id.main_menu_list);
           		
           		//Search current list instead of full list
           		//User added a character to the search, so results must be in current list
            	if(prevSearchTerm.length() < currentSearchTerm.length()){
            		CustomAdapter oldAdapter = (CustomAdapter) main_menu.getAdapter();
            		for(int i = 0; i < oldAdapter.getCount(); i++){
            			if(oldAdapter.getItem(i).toLowerCase().contains(currentSearchTerm.toLowerCase())){
            				adapter.addItem(oldAdapter.getItem(i));
            			}
            		}
            	}
            	
            	//Search full list
            	else{
                   for(int i = 0; i < nayanmarNames.length; i++){
                	   if(nayanmarNames[i].toLowerCase().contains(currentSearchTerm.toLowerCase())){
                		   adapter.addItem(nayanmarNames[i]);
                	   }
                   }
            	}
           		main_menu.setAdapter(adapter);
           		prevSearchTerm = currentSearchTerm;
            }
             
            @Override
            public void beforeTextChanged(CharSequence arg0, int arg1, int arg2,
                    int arg3) {
                // TODO Auto-generated method stub
                 
            }
             
            @Override
            public void afterTextChanged(Editable arg0) {
                // TODO Auto-generated method stub                          
            }
        });
    }
    
	@Override
	public void onResume() {
		super.onResume();
        EditText inputSearch = (EditText) findViewById(R.id.input_search);
        inputSearch.setText("");
	}

    private void setUpList() {
		ListView main_menu = (ListView) findViewById(R.id.main_menu_list);
		mAdapter = new CustomAdapter(this, false);
		setUpNames(mAdapter);
		main_menu.setAdapter(mAdapter);
		main_menu.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				Intent in = new Intent(getApplicationContext(), StoryView.class);
				String name = (String) parent.getAdapter().getItem(position);
				int index = 0;
				for(int i = 0; i < nayanmarNames.length; i++){
					if(name == nayanmarNames[i]){
						index = i;
						break;
					}
				}
				in.putExtra(NAYANMAR_ID, index);
				startActivity(in);
    }
		});
	}
    
    private void setUpNames(CustomAdapter mAdapt){
    	mAdapt.addItem("Tiru Neelakanta Nayanar");
    	mAdapt.addItem("Iyarpahai Nayanar");
    	mAdapt.addItem("Ilayankudi Mara Nayanar");
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
    
    
}
