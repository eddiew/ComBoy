package com.eddiew.comboy.trick;

public class Front extends Trick {

	public Front() {
		super();
		typeName = "Front";
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
			if(endName == "Back" || endName == "Left" || endName == "Swing") trickName = "Full Twist";
			else if(endName == "Carry" || endName == "Right" || endName == "Step") trickName = "Hyper Full Twist";//
			else if(endName == "Doubleleg") trickName = "Full Twist Doubleleg";//
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
