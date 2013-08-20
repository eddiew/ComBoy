package com.eddiew.comboy.trick;

import java.util.Random;

public class Back extends Trick {
	
	public Back(){
		super();
		typeName = "Back";
		//validEnds.add("Back");
		validEnds.add("Carry");
		validEnds.add("Doubleleg");
		validEnds.add("Left");
		validEnds.add("Right");
		validEnds.add("Step");
		validEnds.add("Swing");
		validEnds.add("Swing");
	}
	
	@Override
	public void complete(int difficulty){
		super.complete(difficulty);
		trickName = "";
		int nSpins = difficulty/3;
		if(nSpins == 0){
			if(endName == "Back"){
				int nHands = new Random().nextInt(3);
				if (nHands == 0) trickName = "Back Whip";
				else if(nHands == 1) trickName = "Valdez";
				else trickName = "Back Handspring";
			}
			else if(endName == "Carry") trickName = "Hyper Full Twist";//
			else if(endName == "Doubleleg") trickName = "Full Twist Doubleleg";//
			else if(endName == "Left") trickName = "Full Twist";//
			else if(endName == "Right" || endName == "Step") trickName = "Flash Kick";
			else if(endName == "Swing"){
				int hands = new Random().nextInt(3);
				if (hands > 0) trickName = "Valdez";
				else trickName = "Left Flash Kick";
			}
		}
		else{
			if(endName == "Right" || endName == "Step" || endName == "Carry") trickName = "Hyper ";
			if(nSpins == 2) trickName += "Double ";
			else if(nSpins == 3) trickName += "Triple ";
			trickName += "Full Twist";
			if(endName == "Doubleleg") trickName += " Doubleleg";
		}
	}
}
