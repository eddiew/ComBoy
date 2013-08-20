package com.eddiew.comboy;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.ListIterator;
import java.util.Random;

import com.eddiew.comboy.trick.Trick;

import android.content.Context;
import android.view.MotionEvent;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ScrollView;

public class Combo extends ScrollView{
	//variables for scrolling/view selection
	private float downX, downY;
	private int xPos = 0, yPos = 0;
	private static final float maxPressTravel = 32;//distance in px
	//everything else
	private Random random;
	private ArrayList<Trick> trickList;
	private int difficulty;
	private LinearLayout layout;
	private static final String trickClassPackage = "com.eddiew.comboy.trick.";
	private static final ArrayList<String> allTypes = new ArrayList<String>();
	private static final HashMap<String, ArrayList<String>> validTypes = new HashMap<String, ArrayList<String>>();
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
		
		//valid types given an end. Modify this to include transitions? Change lists of strings to lists of string, int (relative probability) pairs?
		ArrayList<String> left = new ArrayList<String>();
		left.add("Cheat");
		left.add("Gainer");
		left.add("Gainer");
		left.add("Gainer");
		left.add("Gainer");
		left.add("Hook");
		left.add("Hook");
		left.add("Pop");
		validTypes.put("Left", left);
		ArrayList<String> right = new ArrayList<String>();
		right.add("Aerial");
		right.add("Backside");
		right.add("Butterfly");
		//right.add("Hook");//step-over
		right.add("Raiz");
		right.add("Raiz");
		right.add("Wrap");
		validTypes.put("Right", right);
		ArrayList<String> back = new ArrayList<String>();
		back.add("Back");
		back.add("Gainer");
		back.add("Gainer");
		validTypes.put("Back", back);
		ArrayList<String> front = new ArrayList<String>();
		front.add("Front");
		front.add("Wrap");
		validTypes.put("Front", front);
		//other semi-duplicate endings
		ArrayList<String> doubleleg = new ArrayList<String>();
		doubleleg.add("Backside");
		validTypes.put("Doubleleg", doubleleg);
		ArrayList<String> round = new ArrayList<String>();
		round.add("Cheat");
		round.add("Cheat");
		round.add("Cheat");
		round.add("Hook");
		round.add("Hook");
		//round.add("Raiz");
		left.add("Pop");
		round.add("Swing");
		validTypes.put("Round", round);
		ArrayList<String> hook = new ArrayList<String>();
		hook.add("Aerial");
		//hook.add("Backside");
		hook.add("Butterfly");
		hook.add("Carry");
		//right.add("Hook");//step-over
		hook.add("Raiz");
		hook.add("Raiz");
		hook.add("Raiz");
		validTypes.put("Hook", hook);
	}
	
	public Combo(Context context){
		super(context);
		trickList = new ArrayList<Trick>();
		random = new Random();
		layout = new LinearLayout(context);
		layout.setOrientation(LinearLayout.VERTICAL);
		addView(layout);
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
		ArrayList<Trick> newTricks = new ArrayList<Trick>(length);
		for(int i = 0; i < length; i++){
			//Generate a new trick
			//Copy the list of all possible tricks
			ArrayList<String> possibleTypes = new ArrayList<String>();
			for(String type : allTypes){
				//Consider only the types that flow from previous End
				if(!hasPrev || validTypes.get(prevEnd).contains(type)){
					//if we're not worrying about flowing into types, then just add the type to the list
					if(i != length-1 || !hasNext){
						possibleTypes.add(type);
					}
					//if we are, then we need to check that this type flows into the next type
					else{
						//create a Trick from type
						Trick testTrick;
						try {
							testTrick = (Trick)(Class.forName(trickClassPackage + type).getConstructor().newInstance());
						} catch (Exception e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
							return;
						}
						
						//test if this particular type can flow into the nextType
						for(String end : testTrick.validEnds){
							if(validTypes.get(end).contains(nextType)){
								possibleTypes.add(type);
								break;
							}
						}
					}
				}
			}
			
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
			newTrick.setTransitionName(prevEnd);
			//finish trick generation
			newTrick.complete(random.nextInt(difficulty));
			
			//add new trick to trick list
			newTricks.add(newTrick);
			//create TrickView for new Trick
			TrickView newView = new TrickView(getContext(), layout, newTrick);
			//add TrickView to scrollView
			layout.addView(newView, index+i, new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
			//bookkeeping stuff
			hasPrev = true;
			prevEnd = newTrick.endName;
		}
		trickList.addAll(index, newTricks);
	}
	public void addSequence(ListIterator<Trick> pos, int length){
		addSequence(pos.nextIndex(), length);
	}
	public void clear(){
		trickList.clear();
		layout.removeAllViews();
	}
	//Touch event handling (Scroll vs TrickView Toggle vs Button Press)
	//Is there scroll code I can copypasta?
	@Override
	public boolean onTouchEvent(MotionEvent event){
		switch(event.getAction()){
		case MotionEvent.ACTION_UP:
			yPos += (int)(downY - event.getY());
			//smooth scroll here?
			return true;
		default:
			smoothScrollTo(0,yPos + (int)(downY - event.getY()));//remember, Y values are flipped (0 is top)
			return true;
		}
	}
	@Override
	public boolean onInterceptTouchEvent(MotionEvent event){
		switch(event.getAction()){
		case MotionEvent.ACTION_DOWN:
			downX = event.getX();
			downY = event.getY();
			return false;
		default:
			//if the finger has moved considerably in the Y axis
			if(Math.abs(event.getY()-downY) > maxPressTravel){
				//Handle this gesture as a scroll
				return true;
			}
			//if not, don't hijack this event yet
			return false;
		}
	}
}
