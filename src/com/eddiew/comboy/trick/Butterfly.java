package com.eddiew.comboy.trick;

public class Butterfly extends Trick {

	public Butterfly() {
		super();
		typeName = "Butterfly";
		//validEnds.add("Carry");
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
		difficulty %= 3;
		if(nSpins == 0){
			if(endName == "Doubleleg") trickName = "Butterfly Twist Doubleleg";//
			else if(endName == "Left" || endName == "Swing") trickName = "Butterfly Twist";//
			else if(endName == "Right" || endName == "Step") trickName = "Butterfly Kick";
		}
		else{
			if(((endName == "Right" || endName == "Step") && difficulty == 0) || endName == "Carry") trickName = "Hyper ";
			if(nSpins == 2) trickName += "Double ";
			else if(nSpins == 3) trickName += "Triple ";
			trickName += "Butterfly Twist";
			if(endName == "Doubleleg") trickName += " Doubleleg";
			else if(difficulty > 0 && (endName == "Right" || endName == "Step")) trickName += " Hyper Hook";
			else if(difficulty > 0 && endName != "Swing") trickName += " Round";
		}
	}
}
