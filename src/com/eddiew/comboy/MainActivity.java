package com.eddiew.comboy;

import com.eddiew.comboy.AddTricksDialog.DialogListener;
import com.eddiew.comboy.FileDialog.FileDialogListener;
import com.eddiew.comboy.TrickView.AddTricksListener;
import com.eddiew.comboy.trick.Trick;

import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
//import android.app.Activity;//stupid OS compatibility
//import android.app.DialogFragment;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.FragmentActivity;
import android.text.InputType;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;

public class MainActivity extends FragmentActivity implements DialogListener, AddTricksListener, FileDialogListener{
    //private File file;
    public int lastDifficulty = 5;
    public int lastLength = 6;
	public Combo combo;
    private LinearLayout layout;
    EditText comboNameView;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
        layout = new LinearLayout(this);
        layout.setOrientation(LinearLayout.VERTICAL);
        layout.setFocusableInTouchMode(true);
        combo = new Combo(this);
        comboNameView = new EditText(this);
        comboNameView.setInputType(InputType.TYPE_TEXT_FLAG_NO_SUGGESTIONS);
        comboNameView.setGravity(Gravity.CENTER);
        comboNameView.setText(combo.comboName);
        comboNameView.setSingleLine();
        comboNameView.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView textView, int i, KeyEvent keyEvent) {
                if(i == EditorInfo.IME_ACTION_DONE){
                    combo.comboName = textView.getText().toString();
                    InputMethodManager in = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                    in.hideSoftInputFromWindow(textView.getApplicationWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
                    return true;
                }
                return false;
            }
        });
        layout.addView(comboNameView, new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT));
        //load settings
        //file = new File(this.getExternalFilesDir(null));
		//if a saved state exists (previous combo)
		//if(savedInstanceState != null) return;
			//load it
		//else show a blank combo
        layout.addView(combo, new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT));
        layout.requestFocus();
	}
	@Override
	public void onResume(){
		super.onResume();
		setContentView(layout);
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
    public void onConfigurationChanged(Configuration config) {
        super.onConfigurationChanged(config);
    }
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item){
		switch(item.getItemId()){
		case R.id.action_load:
			showFileDialog('L');
			return true;
		case R.id.action_save:
            try {
                saveCombo(combo.comboName);
            } catch (IOException e) {
                e.printStackTrace();
            } catch (JSONException e) {
                e.printStackTrace();
            }
            String saveMsg = combo.comboName;
            saveMsg += " Saved";
            Toast.makeText(this, saveMsg, Toast.LENGTH_SHORT).show();
            return true;
        case R.id.action_new:
            combo.clear();//clear / reset the combo AFTER making the new one, so that cancel doesn't get rid of the combo
            showAddTricksDialog(0);
            return true;
        case R.id.action_menu:
            startActivity(new Intent(this, SettingsActivity.class));
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
		DialogFragment addTricksDialogFragment = new AddTricksDialog();
		Bundle args = new Bundle();
		args.putInt("index", index);
		args.putInt("difficulty", lastDifficulty);
		args.putInt("length", lastLength);
		addTricksDialogFragment.setArguments(args);
		addTricksDialogFragment.show(getSupportFragmentManager(), "addTricks");
	}
	
	public void showFileDialog(char mode){
		DialogFragment FileDialog = new FileDialog();
		Bundle args = new Bundle();
		args.putChar("mode", mode);
		FileDialog.setArguments(args);
		FileDialog.show(getSupportFragmentManager(), "file_operation");
	}

	@Override
	public void onDialogPositiveClick(AddTricksDialog dialog) {
		combo.addTricks(dialog.index, dialog.length, dialog.difficulty);
		lastDifficulty = dialog.difficulty;
		lastLength = dialog.length;
        comboNameView.setText(combo.comboName);
        setContentView(layout);
	}

	@Override
	public void insertTricks(int index) {
		showAddTricksDialog(index);
	}

    public void saveCombo(String fileName) throws IOException, JSONException {
        JSONArray tricks = new JSONArray();
        tricks.put(combo.comboName);
        for(Trick t : combo.trickList){
            JSONObject trick = new JSONObject();
            trick.put("typeName",t.typeName);
            trick.put("trickName",t.trickName);
            trick.put("endName",t.endName);
            trick.put("transitionName", t.transitionName);
            tricks.put(trick);
        }
        FileOutputStream fos = openFileOutput(fileName, Context.MODE_PRIVATE);
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
        layout.removeViewAt(1);
        layout.addView(combo, new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT));
    }
	@Override
	public void onSave(String fileName) {
		try {
			saveCombo(fileName);
		} catch (IOException e) {
			e.printStackTrace();
		} catch (JSONException e) {
			e.printStackTrace();
		}
	}

    @Override
    public void onDelete(String fileName) {
        deleteFile(fileName);
    }

    @Override
	public void onLoad(String fileName) {
		try {
			loadCombo(fileName);
		} catch (IOException e) {
			e.printStackTrace();
		} catch (JSONException e) {
			e.printStackTrace();
		}
        comboNameView.setText(combo.comboName);
		//setContentView(layout);
	}
}
