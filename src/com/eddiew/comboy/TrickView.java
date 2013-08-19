package com.eddiew.comboy;

import com.eddiew.comboy.trick.Trick;

import android.content.Context;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class TrickView extends RelativeLayout {
	private Trick trickData;
	private TextView nameView, transitionView;
	private Button prefixButton, postfixButton;
	private boolean isDetailedView;
	public static enum TrickViewComponent {
		NAMEVIEW(1), TRANSITIONVIEW(2), PREFIXBUTTON(3), POSTFIXBUTTON(4);
		private final int id;
		TrickViewComponent(int id){
			this.id = id;
		}
		public int getId(){
			return id;
		}
	}
	private static final View.OnClickListener addTricksListener = new View.OnClickListener() {
		
		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			//tell main activity to show an addtricks dialog at the TrickView's index (how to get index?)
		}
	};
	private static final RelativeLayout.LayoutParams nameViewParams = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.MATCH_PARENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
	private static final RelativeLayout.LayoutParams transitionViewParams = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
	private static final RelativeLayout.LayoutParams prefixButtonParams = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
	private static final RelativeLayout.LayoutParams postfixButtonParams = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
	static{
		nameViewParams.addRule(RelativeLayout.ALIGN_PARENT_LEFT, RelativeLayout.TRUE);
		
		transitionViewParams.addRule(RelativeLayout.ALIGN_PARENT_LEFT, RelativeLayout.TRUE);
		transitionViewParams.addRule(RelativeLayout.ABOVE, TrickViewComponent.NAMEVIEW.getId());
		
		prefixButtonParams.addRule(RelativeLayout.ALIGN_PARENT_LEFT, RelativeLayout.TRUE);
		prefixButtonParams.addRule(RelativeLayout.ABOVE, TrickViewComponent.TRANSITIONVIEW.getId());
		
		postfixButtonParams.addRule(RelativeLayout.ALIGN_PARENT_LEFT, RelativeLayout.TRUE);
		postfixButtonParams.addRule(RelativeLayout.BELOW, TrickViewComponent.NAMEVIEW.getId());
	}

	
	public TrickView(Context context){
		//if this constructor gets called the program fucked up. Throw something?
		super(context);
		this.trickData = new Trick();
		setSummaryView();
	}
	
	public TrickView (Context context, Trick trickData){
		super(context);
		this.trickData = trickData;
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
			return false;
		}
	}
	public void setSummaryView(){
		removeAllViews();
		nameView = new TextView(getContext());//move this elsewhere
		nameView.setId(TrickViewComponent.NAMEVIEW.getId());
		nameView.setText(trickData.getTrickName());
		addView(nameView, nameViewParams);
		isDetailedView = false;
		//TextView transitionView = new TextView(getContext());
		//nameView.setText(trickData.trickName);
	}
	public void setDetailedView(){
		setSummaryView();
		//add transition name below trick name
		transitionView = new TextView(getContext());
		transitionView.setId(TrickViewComponent.TRANSITIONVIEW.getId());
		transitionView.setText(trickData.transitionName);
		addView(transitionView, transitionViewParams);
//		//add combo modification buttons
//		//prefix stuff
//		prefixButton = new Button(getContext());
//		prefixButton.setId(TrickViewComponent.PREFIXBUTTON.getId());
//		prefixButton.setText("Insert Trick(s)");
//		prefixButton.setOnClickListener(addTricksListener);
//		addView(prefixButton, prefixButtonParams);
//		//postfix stuff
//		postfixButton = new Button(getContext());
//		postfixButton.setId(TrickViewComponent.POSTFIXBUTTON.getId());
//		postfixButton.setText("Insert Trick(s)");
//		postfixButton.setOnClickListener(addTricksListener);
//		addView(postfixButton, postfixButtonParams);
		isDetailedView = true;
	}
	public void addTrick(){
		
	}
	public void addSequence(){
		
	}
	//go to the tricktionary page?
}
