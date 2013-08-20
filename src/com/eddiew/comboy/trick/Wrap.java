package com.eddiew.comboy.trick;

public class Wrap extends Trick {

	public Wrap() {
		super();
		typeName = "Wrap";
		//validEnds.add("Back");
		validEnds.add("Carry");
		validEnds.add("Doubleleg");
		validEnds.add("Left");
		validEnds.add("Right");
		validEnds.add("Step");
		validEnds.add("Swing");
	}
	@Override
	public void complete(int difficulty){
		super.complete(difficulty);
		trickName = "";
		int nSpins = difficulty/3;
		if(nSpins == 0){
			if(endName == "Back" || endName == "Left" || endName == "Swing") trickName = "Wrap Full Twist";
			else if(endName == "Carry") trickName = "Hyper Wrap Full";//
			else if(endName == "Right" || endName == "Step") trickName = "Grandmaster Swipe";//
			else if(endName == "Doubleleg") trickName = "Wrap Full Twist Doubleleg";//
		}
		else{
			if(endName == "Right" || endName == "Step" || endName == "Carry") trickName = "Hyper ";
			if(nSpins == 2) trickName += "Double ";
			else if(nSpins == 3) trickName += "Triple ";
			trickName += "Wrap Full Twist";
			if(endName == "Doubleleg") trickName += " Doubleleg";
		}
	}
}
