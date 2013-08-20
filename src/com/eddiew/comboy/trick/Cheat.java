package com.eddiew.comboy.trick;

public class Cheat extends Trick {

	public Cheat() {
		super();
		typeName = "Cheat";
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
		if((endName == "Left"||endName=="Hook"||endName=="vSwing")){
			if(difficulty < 2) trickName = "Tornado Kick";
			else if(difficulty < 9) trickName = "Cheat 900";
			else trickName = "Cheat 1260";
		}
		else{
			if(difficulty < 4) trickName = "Cheat 720";
			else if(difficulty < 10) trickName = "Cheat 1080";
			else trickName = "Cheat 1440";
		}
	}

}
