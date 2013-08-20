package com.eddiew.comboy;

import com.eddiew.comboy.trick.Trick;

import android.content.Context;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class TrickView extends RelativeLayout {
	private ViewGroup container;
	private Trick trickData;
	private TextView nameView = new TextView(getContext()), transitionView = new TextView(getContext());
	private Button prefixButton = new Button(getContext()), postfixButton = new Button(getContext());
	private View separator = new View(getContext());
	private boolean isDetailedView;
	private static final int NAMEVIEWID = 0xBEEF, TRANSITIONVIEWID = 0xBEF0, PREFIXBUTTONID = 0xBEF1, POSTFIXBUTTONID = 0xBEF2;
	public interface AddTricksListener{
		public void insertTricks(int index);
	}
	private AddTricksListener listener;
	private View.OnClickListener addTricksListener = new View.OnClickListener() {
		@Override
		public void onClick(View v) {
			//tell main activity to show an addtricks dialog at the TrickView's index or the proceeding index
			if(v.getId() == PREFIXBUTTONID){
				listener.insertTricks(container.indexOfChild(TrickView.this));
			}
			else if(v.getId() == POSTFIXBUTTONID) listener.insertTricks(container.indexOfChild(TrickView.this)+1);
		}
	};
	private static final RelativeLayout.LayoutParams summaryParams = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.MATCH_PARENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
	private static final RelativeLayout.LayoutParams nameViewParams = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.MATCH_PARENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
	private static final RelativeLayout.LayoutParams transitionViewParams = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
	private static final RelativeLayout.LayoutParams prefixButtonParams = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
	private static final RelativeLayout.LayoutParams postfixButtonParams = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
	private static final RelativeLayout.LayoutParams separatorParams = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.MATCH_PARENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
	
	static {
		summaryParams.addRule(RelativeLayout.ALIGN_PARENT_LEFT);
		
		nameViewParams.addRule(RelativeLayout.ALIGN_PARENT_LEFT);
		nameViewParams.addRule(RelativeLayout.BELOW, TRANSITIONVIEWID);
		
		transitionViewParams.addRule(RelativeLayout.ALIGN_PARENT_LEFT);
		transitionViewParams.addRule(RelativeLayout.ALIGN_PARENT_TOP);
		
		prefixButtonParams.addRule(RelativeLayout.ALIGN_PARENT_RIGHT);
		prefixButtonParams.addRule(RelativeLayout.ALIGN_PARENT_TOP);
		
		postfixButtonParams.addRule(RelativeLayout.ALIGN_PARENT_RIGHT);
		postfixButtonParams.addRule(RelativeLayout.BELOW, PREFIXBUTTONID);
		
		separatorParams.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM);
	}

	
	public TrickView(Context context){
		//if this constructor gets called the program fucked up. Throw something?
		super(context);
		this.trickData = new Trick();
		try{
			listener = (AddTricksListener)context;
		}
		catch (ClassCastException e) {
            // The activity doesn't implement the interface, throw exception
            throw new ClassCastException(context.toString()
                    + " must implement NoticeDialogListener");
        }
		//nameView
		nameView.setId(NAMEVIEWID);
		nameView.setTextSize(24);
		nameView.setPadding(16,24,16,24);
		//transitionView
		transitionView.setId(TRANSITIONVIEWID);
		//prefix button
		prefixButton.setId(PREFIXBUTTONID);
		prefixButton.setText("Insert Tricks Before");
		prefixButton.setOnClickListener(addTricksListener);
		//postfix button
		postfixButton.setId(POSTFIXBUTTONID);
		postfixButton.setText("Insert Tricks After");
		postfixButton.setOnClickListener(addTricksListener);
		//separator
		separator.setBackgroundColor(0xff000000);
		separator.setMinimumHeight(1);
	}
	
	public TrickView (Context context, ViewGroup container, Trick trickData){
		this(context);
		this.trickData = trickData;
		this.container = container;
		refresh();
		setSummaryView();
	}
	@Override
	public boolean onTouchEvent(MotionEvent event){
		switch(event.getAction()){
		case MotionEvent.ACTION_DOWN:
			//necessary so that the view keeps control of the event
			return true;
		//toggle summary / detailed view
		case MotionEvent.ACTION_UP:
			if(isDetailedView) setSummaryView();
			else setDetailedView();
			return true;
		default:
			return true;
		}
	}
	public void refresh(){
		nameView.setText(trickData.trickName);
		transitionView.setText(trickData.transitionName);
	}
	public void setSummaryView(){
		removeAllViews();
		setBackgroundColor(0xffffffff);
		addView(nameView, summaryParams);
		addView(separator, separatorParams);
		isDetailedView = false;
	}
	public void setDetailedView(){
		removeAllViews();
		setBackgroundColor(0xffddeeff);
		addView(prefixButton, prefixButtonParams);
		addView(transitionView,transitionViewParams);
		addView(nameView, nameViewParams);
		addView(postfixButton, postfixButtonParams);
		addView(separator, separatorParams);
		isDetailedView = true;
	}
	//go to tricktionary page? (Hash by trickName)
}
