package com.eddiew.comboy.trick;

public class Front extends Trick {

	public Front() {
		super();
		typeName = "Front";
		//validEnds.add("Back");
		//validEnds.add("Front");
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
			else {
				trickName = "Full Twist";
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
