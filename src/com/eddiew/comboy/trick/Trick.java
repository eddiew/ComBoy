package com.eddiew.comboy.trick;

import java.util.ArrayList;
import java.util.Random;

public class Trick{
	public String typeName, trickName, endName, transitionName;//the transition into this move
	public ArrayList<String> validEnds;
	private Random random = new Random();
	public Trick(){
		typeName = "";
		trickName = "";
		endName = "";
		transitionName = "";
		validEnds = new ArrayList<String>();
	}
	public String getTrickName(){
		return trickName;
	}
	public String getTypeName(){
		return typeName;
	}
	public ArrayList<String> getEnds(){
		return validEnds;
	}
	
	public void setTransitionName(String transition){
		transitionName = transition;
	}
	
	public void pickEnd(){
		int endIdx = random.nextInt(validEnds.size());
		endName = validEnds.get(endIdx);
	}
	
	//override to allow for custom naming / variations
	public void complete(int difficulty){
		trickName = typeName + ' ' + endName;
	}
}
