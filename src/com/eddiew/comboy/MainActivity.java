package com.eddiew.comboy;

import com.eddiew.comboy.AddTricksDialog.DialogListener;
import com.eddiew.comboy.TrickView.AddTricksListener;
import com.eddiew.comboy.trick.Trick;

import android.content.Context;
import android.os.Bundle;
import android.app.Activity;
import android.app.DialogFragment;
import android.os.Environment;
import android.view.Menu;
import android.view.MenuItem;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;

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
		//if(savedInstanceState != null) return;
			//load it
		//else show a blank combo
		combo = new Combo(this);
		showAddTricksDialog(0);
	}
	@Override
	public void onResume(){
		super.onResume();
		setContentView(combo);
	}
	@Override
	public void onPause(){
		super.onPause();
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
            //show some sort of load menu. use fileList() to get saved files. allow for deleting combos with deleteFile()

			return true;
		case R.id.action_new:
			combo.clear();
			showAddTricksDialog(0);
			return true;
		case R.id.action_save:
            try {
                saveCombo();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (JSONException e) {
                e.printStackTrace();
            }
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

    public void saveCombo() throws IOException, JSONException {
        JSONArray tricks = new JSONArray();
        for(Trick t : combo.trickList){
            JSONObject trick = new JSONObject();
            trick.put("typeName",t.typeName);
            trick.put("trickName",t.trickName);
            trick.put("endName",t.endName);
            trick.put("transitionName", t.transitionName);
            tricks.put(trick);
        }
        FileOutputStream fos = openFileOutput(combo.comboName, Context.MODE_PRIVATE);
        fos.write(tricks.toString().getBytes());
        fos.close();
    }

    public void loadCombo(String fileName)  throws IOException, JSONException {
        StringBuffer fileContent = new StringBuffer("");
        FileInputStream fis = openFileInput(fileName);
        InputStreamReader isr = new InputStreamReader (fis);
        BufferedReader buffReader = new BufferedReader (isr) ;
        String currentLine = buffReader.readLine();
        while(currentLine != null){
            fileContent.append(currentLine);
            currentLine = buffReader.readLine();
        }
        isr.close();
        combo = new Combo(this, fileContent.toString());
    }
}
