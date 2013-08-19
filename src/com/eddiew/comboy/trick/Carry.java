package com.eddiew.comboy.trick;

public class Carry extends Trick {

	public Carry() {
		super();
		typeName = "Carry";
		validEnds.add("Round");
		validEnds.add("Hook");
	}
	
	@Override
	public void complete(int difficulty){
		super.complete(difficulty);
		int nSpins = difficulty/4;
		int degrees = (nSpins+2)*360 + (endName == "Round"?180:0);
		trickName = "Wrap " + Integer.toString(degrees);
	}
}
