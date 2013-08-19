package com.eddiew.comboy.trick;

public class Wrap extends Trick {

	public Wrap() {
		super();
		typeName = "Wrap";
		//validEnds.add("Back");
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
			else {
				trickName = "Wrap Full";
			}
		}
		else{
			if(endName == "Right") trickName = "Hyper ";
			if(nSpins == 2) trickName += "Double ";
			else if(nSpins == 3) trickName += "Triple ";
			trickName += "Wrap Full";
		}
	}
}
