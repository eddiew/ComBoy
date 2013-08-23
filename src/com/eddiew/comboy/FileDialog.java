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

public class FileDialog extends DialogFragment {
    private String selectedFile = "";
    /* The activity that creates an instance of this dialog fragment must
     * implement this interface in order to receive event callbacks.
     * Each method passes the DialogFragment in case the host needs to query it. */
    public interface FileDialogListener {
        public void onSave(String fileName);
        public void onDelete(String fileName);
        public void onLoad(String fileName);
        //public void onDialogNegativeClick(DialogFragment dialog);
    }

    // Use this instance of the interface to deliver action events
    FileDialogListener mListener;

    // Override the Fragment.onAttach() method to instantiate the NoticeDialogListener
    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        // Verify that the host activity implements the callback interface
        try {
            // Instantiate the NoticeDialogListener so we can send events to the host
            mListener = (FileDialogListener) activity;
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
                builder.setSingleChoiceItems(files, -1, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int idx) {
                        selectedFile = files[idx];
                    }
                })
                .setPositiveButton("Load",  new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        mListener.onLoad(selectedFile);
                    }
                })
                .setNeutralButton("Delete",  new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                         mListener.onDelete(selectedFile);
                    }
                })
                .setNegativeButton("Cancel",  new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                    	// User cancelled the dialog
                        dismiss();
                    }
                });
                break;
        }
        return builder.create();
    }
}
