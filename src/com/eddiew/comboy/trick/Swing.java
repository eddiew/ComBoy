package com.eddiew.comboy.trick;

public class Swing extends Trick {

	public Swing() {
		super();
		typeName = "Swing";
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
		int nSpins = difficulty/5;
		int degrees = (nSpins+2)*360 + ((endName == "Left"||endName=="Hook"||endName=="vSwing")?180:0);
		trickName = "Swing " + Integer.toString(degrees);
	}
}
