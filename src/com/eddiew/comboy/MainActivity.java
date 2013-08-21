package com.eddiew.comboy;

import com.eddiew.comboy.AddTricksDialog.DialogListener;
import com.eddiew.comboy.TrickView.AddTricksListener;

import android.os.Bundle;
import android.app.Activity;
import android.app.DialogFragment;
import android.os.Environment;
import android.view.Menu;
import android.view.MenuItem;

import java.io.File;

public class MainActivity extends Activity implements DialogListener, AddTricksListener{
    //private File file;
    public int lastDifficulty = 5;
    public int lastLength = 6;
	public Combo combo;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		//load settings
        //file = new File(this.getExternalFilesDir(null));
		//if a saved state exists (previous combo)
		if(savedInstanceState != null) return;
			//load it
		//else show a blank combo
		combo = new Combo(this);
		setContentView(combo);
		showAddTricksDialog(0);
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
			showAddTricksDialog(0);
			return true;
		case R.id.action_save:
            //start the save activity
            //saveCombo();
			return true;
		default:
			return super.onOptionsItemSelected(item);
		}
	}
	@Override
	public void onSaveInstanceState(Bundle outState){
		super.onSaveInstanceState(outState);
	}
	
	public void showAddTricksDialog(int index){
		DialogFragment addTricksDialogFragment = new AddTricksDialog(lastLength, lastDifficulty);
		Bundle args = new Bundle();
		args.putInt("index", index);
		addTricksDialogFragment.setArguments(args);
		addTricksDialogFragment.show(getFragmentManager(), "addTricks");
	}

	@Override
	public void onDialogPositiveClick(AddTricksDialog dialog) {
		// TODO Auto-generated method stub
		combo.addTricks(dialog.givenIndex, dialog.chosenLength, dialog.chosenDifficulty);
		
	}

	@Override
	public void insertTricks(int index) {
		// TODO Auto-generated method stub
		showAddTricksDialog(index);
	}

    public void saveCombo(){
        /* Check if external storage is available to write */
        if (Environment.MEDIA_MOUNTED.equals(Environment.getExternalStorageState())) {
            //writable

        }
        //not writable
        else return;
    }
}
