package com.eddiew.comboy.trick;

public class Backside extends Trick {
	
	public Backside(){
		super();
		typeName = "Backside";
	}
	
	@Override
	public void complete(int difficulty){
		super.complete(difficulty);
		int nSpins = difficulty/4;
		int degrees = (nSpins+2)*360 + (endName == "Left"?180:0);
		trickName = "Backside " + Integer.toString(degrees);
	}
}
