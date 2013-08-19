package com.eddiew.comboy.trick;


public class Hook extends Trick {//include scoots here too?

	public Hook() {
		super();
		typeName = "Hook";
		validEnds.remove("Left");
	}
	@Override
	public void complete(int difficulty){
		endName = "Right";
		trickName = "Hook Kick";
	}

}
