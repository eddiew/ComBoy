package com.eddiew.comboy.trick;

public class Carry extends Trick {

	public Carry() {
		super();
		typeName = "Carry";
	}
	
	@Override
	public void complete(int difficulty){
		super.complete(difficulty);
		int nSpins = difficulty/4;
		int degrees = (nSpins+2)*360 + (endName == "Left"?180:0);
		trickName = "Wrap " + Integer.toString(degrees);
	}
}
