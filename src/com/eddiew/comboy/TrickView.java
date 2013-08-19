package com.eddiew.comboy;

import com.eddiew.comboy.trick.Trick;

import android.content.Context;
import android.view.MotionEvent;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class TrickView extends RelativeLayout {
	private Trick trickData;
	private TextView nameView, transitionView;
	private boolean isDetailedView;
	public static enum TrickViewComponent {
		NAMEVIEW(1), TRANSITIONVIEW(2);
		private final int id;
		TrickViewComponent(int id){
			this.id = id;
		}
		public int getId(){
			return id;
		}
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
		nameView = new TextView(getContext());
		nameView.setId(TrickViewComponent.NAMEVIEW.getId());
		nameView.setText(trickData.getTrickName());
		RelativeLayout.LayoutParams lp = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
		lp.addRule(RelativeLayout.ALIGN_PARENT_LEFT, RelativeLayout.TRUE);
		addView(nameView, lp);
		isDetailedView = false;
		//TextView transitionView = new TextView(getContext());
		//nameView.setText(trickData.trickName);
	}
	public void setDetailedView(){
		setSummaryView();
		//add transition name below trick name
		transitionView = new TextView(getContext());
		transitionView.setId(TrickViewComponent.TRANSITIONVIEW.getId());
		transitionView.setText("Transition Placeholder");//have the combo figure out the transition
		RelativeLayout.LayoutParams lp = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
		lp.addRule(RelativeLayout.ALIGN_PARENT_LEFT,RelativeLayout.TRUE);
		lp.addRule(RelativeLayout.BELOW,nameView.getId());
		addView(transitionView, lp);
		//add combo modification buttons
		isDetailedView = true;
	}
	public void addTrick(){
		
	}
	public void addSequence(){
		
	}
	//go to the tricktionary page?
}
