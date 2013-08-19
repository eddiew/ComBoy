package com.eddiew.comboy.trick;

public class Backside extends Trick {
	
	public Backside(){
		super();
		typeName = "Backside";
		validEnds.add("Round");
		validEnds.add("Hook");
	}
	
	@Override
	public void complete(int difficulty){
		super.complete(difficulty);
		int nSpins = difficulty/4;
		int degrees = (nSpins+2)*360 + (endName == "Round"?180:0);
		trickName = "Backside " + Integer.toString(degrees);
	}
}
