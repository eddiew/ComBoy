package com.eddiew.comboy;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;

public class FileDialog extends DialogFragment {
    /* The activity that creates an instance of this dialog fragment must
     * implement this interface in order to receive event callbacks.
     * Each method passes the DialogFragment in case the host needs to query it. */
    public interface fileDialogListener {
        public void onSave(String fileName);
        public void onLoad(String fileName);
        //public void onDialogNegativeClick(DialogFragment dialog);
    }

    // Use this instance of the interface to deliver action events
    fileDialogListener mListener;

    // Override the Fragment.onAttach() method to instantiate the NoticeDialogListener
    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        // Verify that the host activity implements the callback interface
        try {
            // Instantiate the NoticeDialogListener so we can send events to the host
            mListener = (fileDialogListener) activity;
        } catch (ClassCastException e) {
            // The activity doesn't implement the interface, throw exception
            throw new ClassCastException(activity.toString()
                    + " must implement fileDialogListener");
        }
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        // Use the Builder class for convenient dialog construction
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        switch(getArguments().getChar("mode")) {
            case 'S':
                builder.setTitle("Save");
                // Get the layout inflater
                LayoutInflater inflater = getActivity().getLayoutInflater();
                final View dialogView = inflater.inflate(R.layout.save_dialog, null);
                final EditText fileNameField = (EditText) dialogView.findViewById(R.id.file_name);
                builder.setView(dialogView)
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
    	            public void onClick(DialogInterface dialog, int id) {
    	                mListener.onSave(fileNameField.getText().toString());
    	            }
                })
                .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                	public void onClick(DialogInterface dialog, int id) {
                		// User cancelled the dialog
                    	dismiss();
                    }
                });
                break;
            case 'L':
                builder.setTitle("Load");
                final String[] files = getActivity().fileList();
                builder.setItems(files, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        mListener.onLoad(files[which]);
                    }
                })
                .setNegativeButton("Cancel",  new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                    	// User cancelled the dialog
                        dismiss();
                    }
                });

        }
        return builder.create();
    }
}
