package com.eddiew.comboy.trick;

import java.util.Random;

public class Back extends Trick {
	
	public Back(){
		super();
		typeName = "Back";
		//validEnds.add("Back");
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
		}
	}
}
