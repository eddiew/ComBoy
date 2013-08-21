package com.eddiew.comboy;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.ListIterator;
import java.util.Random;

import com.eddiew.comboy.trick.Trick;

import android.content.Context;
import android.view.GestureDetector;
import android.view.GestureDetector.OnGestureListener;
import android.view.MotionEvent;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ScrollView;

public class Combo extends ScrollView implements OnGestureListener{
	//variables for scrolling/view selection
	private GestureDetector gestureDetector;
	private float prevY;
	//everything else
	private Random random;
	private ArrayList<Trick> trickList;
	private int difficulty;
	private LinearLayout layout;
	private static final String trickClassPackage = "com.eddiew.comboy.trick.";
	private static final ArrayList<String> allTypes = new ArrayList<String>();
	private static final HashMap<String, ArrayList<String>> validTypes = new HashMap<String, ArrayList<String>>();
	private static final HashMap<String, String> actualTransitionNames = new HashMap<String, String>();
	static{
		//list of all tricks
		allTypes.add("Aerial");
		allTypes.add("Back");
		allTypes.add("Backside");
		allTypes.add("Butterfly");
		allTypes.add("Carry");
		allTypes.add("Cheat");
		allTypes.add("Front");
		allTypes.add("Gainer");
		allTypes.add("Hook");
		allTypes.add("Pop");
		allTypes.add("Raiz");
		allTypes.add("Swing");
		allTypes.add("Wrap");
		
		//valid types given a transition. Change lists of strings to lists of string, int (relative probability) pairs?
		ArrayList<String> left = new ArrayList<String>();//vanish
		left.add("Cheat");
		left.add("Cheat");
		left.add("Pop");
		left.add("Raiz");
		validTypes.put("Left", left);
		actualTransitionNames.put("Left", "Vanish");
		
		ArrayList<String> right = new ArrayList<String>();//vanish
		right.add("Aerial");
		right.add("Backside");
		right.add("Butterfly");
		validTypes.put("Right", right);
		actualTransitionNames.put("Right", "Vanish");
		
		ArrayList<String> swing = new ArrayList<String>();
		swing.add("Gainer");
		validTypes.put("Swing", swing);
		actualTransitionNames.put("Swing", "Swing Through");
		
		ArrayList<String> carry = new ArrayList<String>();
		carry.add("Wrap");
		carry.add("Raiz");
		validTypes.put("Carry", carry);
		actualTransitionNames.put("Carry", "Carry Through");
		
		ArrayList<String> back = new ArrayList<String>();
		back.add("Back");
		validTypes.put("Back", back);
		actualTransitionNames.put("Back", "Punch");
		
		ArrayList<String> front = new ArrayList<String>();
		front.add("Front");
		validTypes.put("Front", front);
		actualTransitionNames.put("Front", "Punch");
		
		//Step-transitions
		ArrayList<String> step = new ArrayList<String>();//right foot step
		step.add("Raiz");
		//step.add("Cheat");
		//step.add("Hook");//step-over
		validTypes.put("Step",step);
		actualTransitionNames.put("Step", "Step");
		
		ArrayList<String> lStep = new ArrayList<String>();
		lStep.add("Hook");
		validTypes.put("Hook", lStep);
		actualTransitionNames.put("Hook", "Step");
		
		//rarer, non-inverted transitions
		ArrayList<String> doubleleg = new ArrayList<String>();//non-inverted back-punch
		doubleleg.add("Backside");
		validTypes.put("Doubleleg", doubleleg);
		actualTransitionNames.put("Doubleleg", "Punch");
		
		ArrayList<String> wrap = new ArrayList<String>();//non-inverted carry
		wrap.add("Carry");
		validTypes.put("Wrap", wrap);
		actualTransitionNames.put("Wrap", "Wrap Through");
		
		ArrayList<String> vSwing = new ArrayList<String>();//non-inverted swing
		vSwing.add("Swing");
		validTypes.put("vSwing", vSwing);
		actualTransitionNames.put("vSwing", "Swing Through");
		
	}
	
	public Combo(Context context){
		super(context);
		trickList = new ArrayList<Trick>();
		random = new Random();
		layout = new LinearLayout(context);
		layout.setOrientation(LinearLayout.VERTICAL);
		addView(layout);
		gestureDetector = new GestureDetector(context, this);
		this.difficulty = 10;
	}
	
	public Combo (Context context, int difficulty){
		this(context);
		this.difficulty = difficulty;
	}
	
	//allows user to manually add a move
	public void addTrick(int index){
		//check if new move is valid (check prev move -> this move -> next move)
	}
	
	private int gaussianDifficulty(int difficulty){
		int rawDifficulty = (int)random.nextInt(difficulty);
		if(rawDifficulty < 0) rawDifficulty = 0;
		if(rawDifficulty > 10) rawDifficulty = 10;
		return rawDifficulty;
	}
	
	private boolean canFlow(String end, String type){
		return validTypes.get(end).contains(type);
	}
	
	private boolean canFlowTypes(String typeA, String typeB){
		//create a Trick from type
		Trick testTrick;
		try {
			testTrick = (Trick)(Class.forName(trickClassPackage + typeA).getConstructor().newInstance());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
		
		//test if this particular type can flow into the nextType
		for(String end : testTrick.validEnds){
			return canFlow(end, typeB);
		}
		return false;
	}
	
	//adds a computer-generated combo of the specified length at the specified index. Offload this to its own thread?
	public void addSequence(int index, int length){//move difficulty here?
		boolean hasNext = false, hasPrev = false;
		String prevEnd = "", nextType = "";
		if(index != 0){
			prevEnd = trickList.get(index-1).endName;
			hasPrev = true;
		}
		if(index < trickList.size()-1){
			nextType = trickList.get(index).typeName;
			hasNext = true;
		}
		boolean tryAgain = true;
		ArrayList<Trick> newTricks = new ArrayList<Trick>(length);
		while(tryAgain){
			newTricks = new ArrayList<Trick>(length);
		for(int i = 0; i < length; i++){
			//Generate a new trick
			ArrayList<String> possibleTypes = new ArrayList<String>();
			//get the previous transition's list of valid tricks if it exists
			if(hasPrev){
				for(String type : validTypes.get(prevEnd)){
					if(i != length-1 || !hasNext || canFlowTypes(type, nextType)){
						possibleTypes.add(type);
					}
				}
			}
			//otherwise just choose from all types
			else{
				for(String type : allTypes){
					if(i != length-1 || !hasNext || canFlowTypes(type, nextType)){
						possibleTypes.add(type);
					}
				}
			}
			if(possibleTypes.isEmpty()){
				tryAgain = true;
				break;
			}
			else tryAgain = false;
			//make the new trick of a randomly picked, valid type
			int classIdx = random.nextInt(possibleTypes.size());
			String trickClass = trickClassPackage + possibleTypes.get(classIdx);
			Trick newTrick;
			try {
				newTrick = (Trick)(Class.forName(trickClass).getConstructor().newInstance());
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				return;
			}
			
			//if the trick needs to flow
			if(i == length-1 && hasNext){
				//get rid of its ends that don't
				for(ListIterator<String> it = newTrick.validEnds.listIterator(newTrick.validEnds.size()-1); it.hasPrevious();){
					if(!validTypes.get(it.previous()).contains(nextType)) it.remove();
				}
			}
			//Set the transition to this trick
			newTrick.setTransitionName(actualTransitionNames.get(prevEnd));
			//finish trick generation
			newTrick.complete(gaussianDifficulty(difficulty));
			
			//add new trick to new trick list
			newTricks.add(newTrick);
			//create TrickView for new Trick
			TrickView newView = new TrickView(getContext(), layout, newTrick);
			//add TrickView to scrollView
			layout.addView(newView, index+i, new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
			//bookkeeping stuff
			hasPrev = true;
			prevEnd = newTrick.endName;
		}
		}
		//insert new tricks to trick list
		trickList.addAll(index, newTricks);
	}
	public void clear(){
		trickList.clear();
		layout.removeAllViews();
	}
	//Touch event handling
	@Override
	public boolean onTouchEvent(MotionEvent event){
		return gestureDetector.onTouchEvent(event);
	}
	@Override
	public boolean onInterceptTouchEvent(MotionEvent event){
		return gestureDetector.onTouchEvent(event);
	}

	@Override
	public boolean onDown(MotionEvent event) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean onFling(MotionEvent arg0, MotionEvent arg1, float vX, float vY) {
		fling((int)-vY);
		prevY = -1;
		return true;
	}

	@Override
	public void onLongPress(MotionEvent arg0) {
		// TODO Auto-generated method stub
	}

	@Override
	public boolean onScroll(MotionEvent motionStart, MotionEvent event, float arg2, float arg3) {
		if(prevY == -1) prevY = motionStart.getY();
		smoothScrollBy(0,(int)(prevY - event.getY()));
		prevY = event.getY();
		return true;
	}

	@Override
	public void onShowPress(MotionEvent event) {
		// TODO Auto-generated method stub
	}

	@Override
	public boolean onSingleTapUp(MotionEvent arg0) {
		// TODO Auto-generated method stub
		return false;
	}
}
