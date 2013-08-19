package com.eddiew.comboy.trick;

public class Front extends Trick {

	public Front() {
		super();
		typeName = "Front";
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
			if(endName == "Right"){
				trickName = "Hyper Full Twist";
			}
			else if(endName == "Front"){
				trickName = "Front Flip";
			}
			else if(endName == "Hook"){
				trickName = "Full Twist Hyper Hook";//these are awkward. Force the move to change endings?
			}
			else if(endName == "Doubleleg"){
				trickName = "Full Twist Doubleleg";
			}
			else if(endName == "Round"){
				trickName = "Full Twist Round";
			}
			else {
				trickName = "Full Twist";
			}
		}
		else{
			if(endName == "Right") trickName = "Hyper ";
			if(nSpins == 2) trickName += "Double ";
			else if(nSpins == 3) trickName += "Triple ";
			trickName += "Full Twist";
			if(endName == "Doubleleg") trickName += " Doubleleg";
			if(endName == "Hook") trickName += " Hyper Hook";
			if(endName == "Round") trickName += " Round";
		}
	}
}
