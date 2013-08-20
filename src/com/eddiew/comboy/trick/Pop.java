package com.eddiew.comboy.trick;

public class Pop extends Trick {

	public Pop() {
		super();
		typeName = "Pop";
		validEnds.add("Hook");
		validEnds.add("Left");
		validEnds.add("Right");
		validEnds.add("Step");
		validEnds.add("vSwing");
		validEnds.add("Wrap");
	}
	
	@Override
	public void complete(int difficulty){
		super.complete(difficulty);
		int nSpins = 2;
		if(difficulty < 5) nSpins = 0;
		else if(difficulty < 10) nSpins = 1;
		int degrees = (nSpins+2)*360 + ((endName == "Left"||endName=="Hook"||endName=="vSwing")?180:0);
		trickName = "Pop " + Integer.toString(degrees);
	}

}
