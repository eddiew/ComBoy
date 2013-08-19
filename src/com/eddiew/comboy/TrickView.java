package com.eddiew.comboy;

import com.eddiew.comboy.trick.Trick;

import android.content.Context;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class TrickView extends RelativeLayout {
	private Combo container;
	private Trick trickData;
	private TextView nameView = new TextView(getContext()), transitionView = new TextView(getContext());
	private Button prefixButton = new Button(getContext()), postfixButton = new Button(getContext());
	private boolean isDetailedView;
	private static final int NAMEVIEWID = 0xBEEF, TRANSITIONVIEWID = 0xBEF0, PREFIXBUTTONID = 0xBEF1, POSTFIXBUTTONID = 0xBEF2;
	private static View.OnClickListener addTricksListener = new View.OnClickListener() {
		
		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			//tell main activity to show an addtricks dialog at the TrickView's index (how to get index?)
		}
	};
	private static final RelativeLayout.LayoutParams summaryParams = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.MATCH_PARENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
	private static final RelativeLayout.LayoutParams nameViewParams = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.MATCH_PARENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
	private static final RelativeLayout.LayoutParams transitionViewParams = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
	private static final RelativeLayout.LayoutParams prefixButtonParams = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
	private static final RelativeLayout.LayoutParams postfixButtonParams = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
	static {
		summaryParams.addRule(RelativeLayout.ALIGN_PARENT_LEFT);
		
		nameViewParams.addRule(RelativeLayout.ALIGN_PARENT_LEFT);
		nameViewParams.addRule(RelativeLayout.BELOW, TRANSITIONVIEWID);
		
		transitionViewParams.addRule(RelativeLayout.ALIGN_PARENT_LEFT);
		transitionViewParams.addRule(RelativeLayout.BELOW, PREFIXBUTTONID);
		
		prefixButtonParams.addRule(RelativeLayout.ALIGN_PARENT_LEFT);
		
		postfixButtonParams.addRule(RelativeLayout.ALIGN_PARENT_LEFT);
		postfixButtonParams.addRule(RelativeLayout.BELOW, NAMEVIEWID);
	}

	
	public TrickView(Context context){
		//if this constructor gets called the program fucked up. Throw something?
		super(context);
		this.trickData = new Trick();
		//nameView
		nameView.setId(NAMEVIEWID);
		//transitionView
		transitionView.setId(TRANSITIONVIEWID);
		//prefix button
		prefixButton.setId(PREFIXBUTTONID);
		prefixButton.setText("Insert Tricks");
		prefixButton.setOnClickListener(addTricksListener);
		//postfix button
		postfixButton.setId(POSTFIXBUTTONID);
		postfixButton.setText("Insert Tricks");
		postfixButton.setOnClickListener(addTricksListener);
	}
	
	public TrickView (Context context, Combo container, Trick trickData){
		this(context);
		this.container = container;
		this.trickData = trickData;
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
		addView(nameView, summaryParams);
		isDetailedView = false;
	}
	public void setDetailedView(){
		removeAllViews();
		addView(prefixButton, prefixButtonParams);
		addView(transitionView,transitionViewParams);
		addView(nameView, nameViewParams);
		addView(postfixButton, postfixButtonParams);
		isDetailedView = true;
	}
	public void addTrick(){
		
	}
	public void addSequence(){
		
	}
	//go to the tricktionary page?
}
