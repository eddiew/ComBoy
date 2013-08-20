package com.eddiew.comboy.trick;

public class Carry extends Trick {

	public Carry() {
		super();
		typeName = "Carry";
		validEnds.add("Left");
		validEnds.add("Hook");
		validEnds.add("Right");
		validEnds.add("Step");
		validEnds.add("vSwing");
		validEnds.add("Wrap");
	}
	
	@Override
	public void complete(int difficulty){
		super.complete(difficulty);
		int nSpins = difficulty/4;
		int degrees = (nSpins+2)*360 + ((endName == "Left"||endName=="Hook"||endName=="vSwing")?180:0);
		trickName = "Wrap " + Integer.toString(degrees);
	}
}
