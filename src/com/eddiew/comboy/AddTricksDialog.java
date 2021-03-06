package com.eddiew.comboy;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
//import android.app.DialogFragment;
import android.support.v4.app.DialogFragment;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.TextView;

public class AddTricksDialog extends DialogFragment {
	public int length;
	public int index;
	public int difficulty;

//    public AddTricksDialog(int length, int difficulty){
//        super();
//        chosenLength = length;
//        chosenDifficulty = difficulty;
//    }
	/* The activity that creates an instance of this dialog fragment must
     * implement this interface in order to receive event callbacks.
     * Each method passes the DialogFragment in case the host needs to query it. */
    public interface DialogListener {
        public void onDialogPositiveClick(AddTricksDialog dialog);
        //public void onDialogNegativeClick(DialogFragment dialog);
    }
    
    // Use this instance of the interface to deliver action events
    DialogListener mListener;
    
    // Override the Fragment.onAttach() method to instantiate the NoticeDialogListener
    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        // Verify that the host activity implements the callback interface
        try {
            // Instantiate the NoticeDialogListener so we can send events to the host
            mListener = (DialogListener) activity;
        } catch (ClassCastException e) {
            // The activity doesn't implement the interface, throw exception
            throw new ClassCastException(activity.toString()
                    + " must implement NoticeDialogListener");
        }
    }
	
	@Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
		index = getArguments().getInt("index");
		length = getArguments().getInt("length");
		difficulty = getArguments().getInt("difficulty");
		// Get the layout inflater
		LayoutInflater inflater = getActivity().getLayoutInflater();
        // Use the Builder class for convenient dialog construction
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        // Inflate and set the layout for the dialog
        // Pass null as the parent view because it's going in the dialog layout
        final View dialogView = inflater.inflate(R.layout.addtricks_dialog, null);
        final EditText lengthField = (EditText)(dialogView.findViewById(R.id.length));
        final SeekBar difficultyField = (SeekBar) dialogView.findViewById(R.id.difficulty_seekbar);
        final TextView difficultyView = (TextView) (dialogView.findViewById(R.id.difficulty));
        lengthField.setText(Integer.toString(length));
        lengthField.setSelection(1);
        difficultyView.setText(Integer.toString(difficulty));
        difficultyField.setProgress(difficulty-1);
        difficultyField.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
            	difficulty = i + 1;
                difficultyView.setText(Integer.toString(difficulty));
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
        builder.setView(dialogView)
        	.setTitle("Add Tricks")
        	// Add action buttons
            .setPositiveButton("OK", new DialogInterface.OnClickListener() {
	            public void onClick(DialogInterface dialog, int id) {
	            	try{
	            		length = Integer.parseInt(lengthField.getText().toString());
	            	}
	            	catch(Exception e){
	            		length = 0;
	            	}
	                mListener.onDialogPositiveClick(AddTricksDialog.this);
	            }
            })
            .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            	public void onClick(DialogInterface dialog, int id) {
            		// User cancelled the dialog
                	dismiss();
                }
            });
        // Create the AlertDialog object and return it
        return builder.create();
    }
}
