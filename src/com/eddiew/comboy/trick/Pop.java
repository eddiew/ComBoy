package com.eddiew.comboy.trick;

public class Pop extends Trick {

	public Pop() {
		super();
		typeName = "Pop";
		validEnds.add("Round");
		validEnds.add("Hook");
	}
	
	@Override
	public void complete(int difficulty){
		super.complete(difficulty);
		int nSpins = difficulty/4;
		int degrees = (nSpins+1)*360 + (endName == "Round"?180:0);
		trickName = "Pop " + Integer.toString(degrees);
	}

}
