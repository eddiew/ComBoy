package com.eddiew.comboy;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

public class AddTricksDialog extends DialogFragment {
	public int chosenLength = 0;
	public int givenIndex = 0;
	
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
		givenIndex = getArguments().getInt("index");
		// Get the layout inflater
		LayoutInflater inflater = getActivity().getLayoutInflater();
        // Use the Builder class for convenient dialog construction
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        // Inflate and set the layout for the dialog
        // Pass null as the parent view because it's going in the dialog layout
        final View dialogView = inflater.inflate(R.layout.addtricks_dialog, null);
        builder.setView(dialogView)
        // Add action buttons
               .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                   public void onClick(DialogInterface dialog, int id) {
                       // FIRE ZE MISSILES!
                	  //((MainActivity) getActivity()).combo.addSequence(getArguments().getInt("index"), Integer.parseInt(((EditText)(dialogView.findViewById(R.id.length))).getText().toString()));
                	   chosenLength = Integer.parseInt(((EditText)(dialogView.findViewById(R.id.length))).getText().toString());
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
//	@Override
//	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
//		View dialogView = inflater.inflate(R.layout.addtricks_dialog, container, false);
//		
//	}
}