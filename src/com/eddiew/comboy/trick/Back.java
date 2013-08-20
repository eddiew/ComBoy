package com.eddiew.comboy.trick;

import java.util.Random;

public class Back extends Trick {
	
	public Back(){
		super();
		typeName = "Back";
		//validEnds.add("Back");
		validEnds.add("Doubleleg");
		validEnds.add("Hook");
		validEnds.add("Left");
		validEnds.add("Left");
		validEnds.add("Right");
		validEnds.add("Round");
	}
	
	@Override
	public void complete(int difficulty){
		super.complete(difficulty);
		trickName = "";
		int nSpins = difficulty/3;
		if(nSpins == 0){
			if(endName == "Left"){
				trickName = "Left Flash Kick";
			}
			else if(endName == "Right"){
				trickName = "Flash Kick";
			}
			else if(endName == "Hook"){
				trickName = "Flash Knife";
			}
			else if(endName == "Doubleleg"){
				trickName = "Full Twist Doubleleg";//these are awkward. Force the move to change endings?
			}
			else if(endName == "Round"){
				trickName = "Full Twist Round";
			}
			else {
				boolean hands = new Random().nextBoolean();
				if (hands) trickName = "Back Handspring";
				else trickName = "Back Flip";
			}
		}
		else{
			if(endName == "Right") trickName = "Hyper ";
			if(nSpins == 2) trickName += "Double ";
			else if(nSpins == 3) trickName += "Triple ";
			trickName += "Full Twist";
			if(endName == "Doubleleg") trickName += " Doubleleg";
			else if(endName == "Hook") trickName += " Hyper Hook";
			else if(endName == "Round") trickName += " Round";
		}
	}
}
