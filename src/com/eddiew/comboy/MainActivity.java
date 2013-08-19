package com.eddiew.comboy;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.view.MenuItem;

public class MainActivity extends Activity {
	private Combo combo;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		//setContentView(R.layout.activity_main);
		//combo = (Combo)findViewById(R.id.comboView);
		//load settings
		//if a saved state exists (previous combo)
		if(savedInstanceState != null) return;
			//load it
		//else show a blank combo
		combo = new Combo(this, 6);
		setContentView(combo);
		combo.addSequence(0,50);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.action_bar, menu);
		
		return super.onCreateOptionsMenu(menu);
	}
	@Override
	public boolean onOptionsItemSelected(MenuItem item){
		switch(item.getItemId()){
		case R.id.action_load:
			return true;
		case R.id.action_new:
			combo.clear();
			combo.addSequence(0,25);
			return true;
		case R.id.action_save:
			return true;
		default:
			return super.onOptionsItemSelected(item);
		}
	}
	@Override
	public void onSaveInstanceState(Bundle outState){
		super.onSaveInstanceState(outState);
		
	}
}
