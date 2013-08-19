package com.eddiew.comboy.trick;

public class Swing extends Trick {

	public Swing() {
		super();
		typeName = "Swing";
		validEnds.add("Round");
		validEnds.add("Hook");
	}
	
	@Override
	public void complete(int difficulty){
		super.complete(difficulty);
		int nSpins = difficulty/5;
		int degrees = (nSpins+2)*360 + (endName == "Round"?180:0);
		trickName = "Swing " + Integer.toString(degrees);
	}
}
