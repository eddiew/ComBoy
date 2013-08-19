package com.eddiew.comboy.trick;

public class Wrap extends Trick {

	public Wrap() {
		super();
		typeName = "Wrap";
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
				trickName = "Grandmaster Swipe";//Put something better here?
			}
			else if(endName == "Hook"){
				trickName = "Grandmaster Swipe Knife";
			}
			else if(endName == "Doubleleg"){
				trickName = "Wrap Full Twist Doubleleg";
			}
			else if(endName == "Round"){
				trickName = "Wrap Full Twist Round";
			}
			else {
				trickName = "Wrap Full";
			}
		}
		else{
			if(endName == "Right") trickName = "Hyper ";
			if(nSpins == 2) trickName += "Double ";
			else if(nSpins == 3) trickName += "Triple ";
			trickName += "Wrap Full";
			if(endName == "Doubleleg") trickName += " Doubleleg";
			if(endName == "Hook") trickName += " Hyper Hook";
			if(endName == "Round") trickName += " Round";
		}
	}
}
