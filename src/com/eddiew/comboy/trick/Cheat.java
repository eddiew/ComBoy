package com.eddiew.comboy.trick;

public class Cheat extends Trick {

	public Cheat() {
		super();
		typeName = "Cheat";
	}
	
	@Override
	public void complete(int difficulty){
		super.complete(difficulty);
		int nSpins = difficulty/4;
		int degrees = (nSpins+2)*360 + (endName == "Left"?180:0);
		trickName = "Cheat " + Integer.toString(degrees);
	}

}
