package com.eddiew.comboy.trick;

import java.util.ArrayList;
import java.util.Random;

public class Trick{
	public String typeName, trickName, endName, transitionName;//the transition into this move
	public ArrayList<String> validEnds;
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
	
	//override to allow for custom naming / variations
	public void complete(int difficulty){
		int endIdx = new Random().nextInt(validEnds.size());
		endName = validEnds.get(endIdx);
		trickName = typeName + ' ' + endName;
	}
}
