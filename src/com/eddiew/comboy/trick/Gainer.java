package com.eddiew.comboy.trick;

public class Gainer extends Trick {

	public Gainer() {
		super();
		typeName = "Gainer";
		validEnds.add("Carry");
		validEnds.add("Doubleleg");
		validEnds.add("Hook");
		validEnds.add("Left");
		validEnds.add("Right");
		validEnds.add("Step");
		validEnds.add("Swing");
		validEnds.add("Swing");
		validEnds.add("Swing");
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
			if(endName == "Left" || endName == "Swing") trickName = "Gainer Switch";
			else if(endName == "Carry") trickName = "Gainer";
			else if(endName == "Right" || endName == "Step"){
				if(difficulty > 0) trickName = "Moon Kick";
				else trickName = "Gainer";
			}
			else if(endName == "Doubleleg") trickName = "Corkscrew Doubleleg";//
			else if(endName == "Hook") trickName = "Corkscrew";
		}
		else{
			if(endName == "Carry" || ((endName == "Right" || endName == "Step") && difficulty == 0)) trickName = "Hyper ";
			if(nSpins == 2) trickName += "Double ";
			else if(nSpins == 3) trickName += "Triple ";
			if((endName == "Right" || endName == "Step") && difficulty > 0) trickName += "Boxcutter";
			else{
				trickName += "Corkscrew";
				if(endName == "Doubleleg") trickName += " Doubleleg";
				else if(difficulty > 0 && endName != "Swing") trickName += " Round";
			}
		}
	}
}
